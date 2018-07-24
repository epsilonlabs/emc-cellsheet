package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;

/**
 * 
 * @author Jonathan Co
 *
 */
public class ExcelFormulaTree implements IFormulaTree {

  protected ExcelBook book;
  protected ExcelCell cell;
  protected ExcelFormulaCellValue cellValue;
  protected ExcelFormulaTree parent;
  protected ExcelFormulaToken token;
  protected List<ExcelFormulaTree> children;

  // Pattern for matching the first occurrence of a function. Used when performing abstract
  // interpretation
  private static final Pattern p = Pattern.compile("\\w+\\Q(\\E");

  public ExcelFormulaTree(ExcelFormulaCellValue cellValue, ExcelFormulaTree parent, Ptg ptg) {
    super();
    this.book = cellValue.getCell().getBook();
    this.cell = cellValue.getCell();
    this.cellValue = cellValue;
    this.token = new ExcelFormulaToken(this, ptg);
    this.parent = parent;
    this.children = new LinkedList<>();
  }

  public ExcelFormulaTree(ExcelFormulaCellValue cellValue, Ptg ptg) {
    this(cellValue, null, ptg);
  }

  @Override
  public ExcelFormulaCellValue getCellValue() {
    return this.cellValue;
  }

  @Override
  public ExcelFormulaToken getToken() {
    return this.token;
  }

  @Override
  public ExcelFormulaTree getParent() {
    return this.parent;
  }

  @Override
  public void setParent(IFormulaTree parent) {
    if (!(parent instanceof ExcelFormulaTree))
      throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
    this.parent = (ExcelFormulaTree) parent;
  }

  @Override
  public List<IFormulaTree> getChildren() {
    return Collections.unmodifiableList(this.children);
  }

  @Override
  public ExcelFormulaTree getChildAt(int index) {
    return this.children.get(index);
  }

  @Override
  public void addChild(IFormulaTree child) {
    if (!(child instanceof ExcelFormulaTree))
      throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
    child.setParent(this);
    this.children.add((ExcelFormulaTree) child);
  }

  @Override
  public String evaluate() {
    return doEvaluation(getFormula());
  }

  @Override
  public String interpret() {
    String formula = getFormula();
    Matcher m = p.matcher(formula);
    if (m.find()) {
      int start = m.start();
      int end = m.end() - 1;
      String replacement =
          book.getAiFunctions().getInterpretedFunction(formula.substring(start, end));
      if (replacement != null) {
        StringBuilder sb = new StringBuilder();
        sb.append(formula, 0, start);
        sb.append(replacement);
        sb.append(formula, end, formula.length());
        formula = sb.toString();
      }
    }
    return doEvaluation(formula);
  }

  String doEvaluation(String formula) {
    final WorkbookEvaluator evaluator = book.evaluator;
    CellReference ref = new CellReference(this.cell.getDelegate());
    ValueEval result = evaluator.evaluate(formula, ref);
    return OperandResolver.coerceValueToString(result);

  }

  @Override
  public String getFormula() {
    final StringBuilder sb = new StringBuilder();
    // Open a bracket to preserve precedence
    sb.append("(");

    if (token.getDelegate() instanceof ValueOperatorPtg) {
      final ValueOperatorPtg cast = (ValueOperatorPtg) token.getDelegate();

      // Special case where operator occurs after operand
      if (cast instanceof PercentPtg) {
        sb.append(getChildAt(0).getFormula());
        sb.append(token.toString());
      }

      // Special case for only one operand and operator occurs before
      else if (cast.getNumberOfOperands() < 2) {
        sb.append(token.toString());
        sb.append(getChildAt(0).getFormula());
      }

      // For most arithmetic operations
      else {
        sb.append(getChildAt(0).getFormula());
        sb.append(token.toString());
        sb.append(getChildAt(1).getFormula());
      }
    }

    // Special case for SUM with 1 operand
    if (FormulaUtil.isSumPtg(token.getDelegate())) {
      sb.append(token.toString());
      sb.append("(");
      sb.append(getChildAt(0).getFormula());
      sb.append(")");
    }

    // General Functions
    if (token.getDelegate() instanceof AbstractFunctionPtg) {
      final AbstractFunctionPtg cast = (AbstractFunctionPtg) token.getDelegate();

      sb.append(token.toString());
      sb.append("(");

      for (int i = 0; i < cast.getNumberOfOperands(); i++) {
        sb.append(getChildAt(i).getFormula());
        if (!(cast.getNumberOfOperands() == i + 1))
          sb.append(",");
      }
      sb.append(")");
    }

    // Close bracket to complete the part
    sb.append(")");
    return sb.toString().equals("()") ? token.toString() : sb.toString();
  }

  @Override
  public String toString() {
    return this.token + " -> " + this.getFormula();
  }

}

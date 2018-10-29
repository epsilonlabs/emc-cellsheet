package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.emc.cellsheet.ICell;
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
	public ICell evaluateCell() {
		if (!(token.delegate instanceof AbstractFunctionPtg)) {
			return cell;			
		}
		
		AbstractFunctionPtg function = (AbstractFunctionPtg) token.delegate;
		String newFormula = null;
		
		// TODO: Translate this to a transformation
		switch (function.getName()) {
		case "VLOOKUP":			
			// Determine new lookup table
			AreaPtgBase table_array = (AreaPtgBase) getChildAt(1).token.delegate;
			String lookupCol = CellReference.convertNumToColString(table_array.getFirstColumn());
			int firstRow = table_array.getFirstRow();
			int lastRow = table_array.getLastRow();
			
			String sheetName = cell.getSheet().getName();	// Determine the sheet to use for refs
			if (table_array instanceof Area3DPxg) {
				sheetName = ((Area3DPxg) table_array).getSheetName();
			}
		
			String newTableArray = String.format("%s!%s%d:%s%d", sheetName, lookupCol, firstRow+1, lookupCol, lastRow);
			
			// Determine whether to do range lookup
			String isRangeLookup = "1";
			if (function.getNumberOfOperands() > 3) {
				isRangeLookup = getChildAt(3).getFormula();
			}

			// ADDRESS( MATCH ($lookup_value, $lookup_array, $is_exact_match), $not_needed, $not_needed, $sheet_to_look_in)
			newFormula = String.format(
					"ADDRESS(MATCH(%s,%s,%s),%s,,,\"%s\")",
					getChildAt(0).getFormula(),
					newTableArray,
					isRangeLookup,
					getChildAt(2).getFormula(),
					sheetName);
			break;
		default:
			throw new UnsupportedOperationException();
		}
		
		System.out.println(newFormula);
		String doEvaluation = doEvaluation(newFormula);
		System.out.println(doEvaluation);
		
		return null;
	}

	String doEvaluation(String formula) {
		CellReference ref = new CellReference(cell.getSheet().getName(), cell.getRowIndex(), cell.getColIndex(), false, false);
		
		ValueEval result = book.evaluator.evaluate(formula, ref);
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

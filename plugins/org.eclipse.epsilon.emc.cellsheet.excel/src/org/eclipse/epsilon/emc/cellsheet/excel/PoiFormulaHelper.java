package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelFormulaTree.ExcelToken;

public class PoiFormulaHelper {

	private static final Map<ExcelBook, FormulaParsingWorkbook> fpwMap = new HashMap<>();

	private PoiFormulaHelper() {
		throw new AssertionError();
	}

	public static Ptg[] parseFormula(ExcelBook book, ExcelFormulaValue cellValue) {
		return parseFormula(book, cellValue.getFormula(), cellValue.getCell().getSheet().getIndex());
	}
	
	public static Ptg[] parseFormula(ExcelBook book, String formula, int sheetIndex) {
		FormulaParsingWorkbook fpw = fpwMap.get(book);
		
		if (fpw == null) {
			final Workbook delegate = book.getDelegate();
			if (delegate instanceof HSSFWorkbook)
				fpw = HSSFEvaluationWorkbook.create((HSSFWorkbook) delegate);
			if (delegate instanceof XSSFWorkbook)
				fpw = XSSFEvaluationWorkbook.create((XSSFWorkbook) delegate);
			if (delegate instanceof SXSSFWorkbook)
				fpw = SXSSFEvaluationWorkbook.create((SXSSFWorkbook) delegate);
			if (fpw == null)
				throw new AssertionError();
			fpwMap.put(book, fpw);
		}
		
		return FormulaParser.parse(formula, 
				fpw, 
				FormulaType.CELL, 
				sheetIndex);
	}
	
	public static ExcelFormulaTree buildFormulaTree(IFormulaCellValue value) {
		if (value instanceof ExcelFormulaValue) return buildFormulaTree((ExcelFormulaValue) value);
		throw new IllegalArgumentException("Cannot build tree for a non ExcelFormulaValue");
	}
	
	public static ExcelFormulaTree buildFormulaTree(ExcelFormulaValue value) {		
		final Ptg[] ptgs = PoiFormulaHelper.parseFormula(value.getCell().getBook(), value);
		final Stack<ExcelFormulaTree> trees = new Stack<>();
		final Stack<ExcelFormulaTree> operands = new Stack<>();
		
		for (Ptg ptg : ptgs) {
			if (ptg instanceof ControlPtg && !(ptg instanceof AttrPtg)) continue;
			
			final ExcelFormulaTree current = new ExcelFormulaTree(value, ptg);
			
			// Special Case for SUM only
			if (isSumPtg(ptg)) {
				current.addChild(trees.pop());
				if (!operands.isEmpty()) throw new IllegalStateException("Not all operands have been consumed for " + ptg);
			}
			
			if (ptg instanceof OperationPtg) {
				OperationPtg operationPtg = (OperationPtg) ptg;
				for (int i = 0; i < operationPtg.getNumberOfOperands(); i++) {
					operands.push(trees.pop());
				}
				
				for (int i = 0; i < operationPtg.getNumberOfOperands(); i++) {
					current.addChild(operands.pop());
				}
				
				if (!operands.isEmpty()) {					
					throw new IllegalStateException();
				}
			}
			
			trees.push(current);
		}
		
		if (trees.size() != 1) throw new AssertionError("Not all tokens consumed");
		
		return trees.pop();
	}
	
	public static String buildFormulaString(IFormulaTree tree) {
		if (tree instanceof ExcelFormulaTree) return buildFormulaString((ExcelFormulaTree) tree);
		throw new IllegalArgumentException("Cannot build Formula String for a non ExcelFormulaTree");
	}
	
	public static String buildFormulaString(IFormulaCellValue value) {
		if (value instanceof ExcelFormulaValue) return buildFormulaString(value.getFormulaTree());
		throw new IllegalArgumentException("Cannot build Formula String for a non ExcelFormulaValue");
	}
	
	/**
	 * Helper function to rebuild a formula string from a
	 * {@link ExcelFormulaTree}.
	 * 
	 * @param tree
	 *            The tree to build the formula string from. This does not have
	 *            to be the root node, it can build a partial formula from a
	 *            subtree.
	 * @return The formula string represented by the tree.
	 */
	public static String buildFormulaString(ExcelFormulaTree tree) {
		final StringBuilder sb = new StringBuilder();
		// Open a bracket to preserve precedence
		sb.append("(");

		final ExcelToken token = (ExcelToken) tree.getToken();

		if (token.getDelegate() instanceof ValueOperatorPtg) {
			final ValueOperatorPtg cast = (ValueOperatorPtg) token.getDelegate();

			// Special case where operator occurs after operand
			if (cast instanceof PercentPtg) {
				sb.append(buildFormulaString(tree.getChildAt(0)));
				sb.append(token.toString());
			}

			// Special case for only one operand and operator occurs before
			else if (cast.getNumberOfOperands() < 2) {
				sb.append(token.toString());
				sb.append(buildFormulaString(tree.getChildAt(0)));
			}

			// For most arithmetic operations
			else {
				sb.append(buildFormulaString(tree.getChildAt(0)));
				sb.append(token.toString());
				sb.append(buildFormulaString(tree.getChildAt(1)));
			}
		}

		// Special case for SUM with 1 operand
		if (isSumPtg(token.getDelegate())) {
			sb.append(token.toString());
			sb.append("(");
			sb.append(buildFormulaString(tree.getChildAt(0)));
			sb.append(")");
		}

		// General Functions
		if (token.getDelegate() instanceof AbstractFunctionPtg) {
			final AbstractFunctionPtg cast = (AbstractFunctionPtg) token.getDelegate();

			sb.append(token.toString());
			sb.append("(");

			for (int i = 0; i < cast.getNumberOfOperands(); i++) {
				sb.append(buildFormulaString(tree.getChildAt(i)));
				if (!(cast.getNumberOfOperands() == i + 1)) sb.append(",");
			}
			sb.append(")");
		}

		// Close bracket to complete the part
		sb.append(")");
		return sb.toString().equals("()") ? token.toString() : sb.toString();
	}
	
	public static String evaluate(IFormulaTree tree) {
		if (tree instanceof ExcelFormulaTree) return evaluate((ExcelFormulaTree) tree);
		throw new IllegalArgumentException("Cannot build Formula String for a non ExcelFormulaTree");
	}
	
	public static String evaluate(IFormulaCellValue value) {
		if (value instanceof ExcelFormulaValue) return evaluate(value.getFormulaTree());
		throw new IllegalArgumentException("Cannot build Formula String for a non ExcelFormulaValue");
	}
	
	public static String evaluate(ExcelFormulaTree tree) {
		String formula = buildFormulaString(tree);
		CellReference ref = new CellReference(((ExcelCell)tree.getCellValue().getCell()).getDelegate());

		Workbook wb = ((ExcelBook)tree.getCellValue().getCell().getBook()).getDelegate();
		FormulaEvaluator fe = wb.getCreationHelper().createFormulaEvaluator();
		WorkbookEvaluator we = ((WorkbookEvaluatorProvider) fe)._getWorkbookEvaluator();
		
		ValueEval result = we.evaluate(formula, ref);
		return OperandResolver.coerceValueToString(result);
	}
	
	private static boolean isSumPtg(Ptg ptg) {
		return ptg instanceof AttrPtg && ((AttrPtg) ptg).isSum();
	}
}

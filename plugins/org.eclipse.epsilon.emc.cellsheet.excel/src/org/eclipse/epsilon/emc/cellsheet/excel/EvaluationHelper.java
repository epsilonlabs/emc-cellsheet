package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.emc.cellsheet.IBook;

/**
 * Helper class for evaluating AST/Formula
 * 
 * @author Jonathan Co
 *
 */
public enum EvaluationHelper {
	INSTANCE;

	static Map<ExcelBook, FormulaParsingWorkbook> fpwMap = new HashMap<>();
	static Map<ExcelBook, WorkbookEvaluator> evalMap = new HashMap<>();

	public static WorkbookEvaluator getEvaluator(IBook book) {
		return getEvaluator((ExcelBook) book);
	}

	public static FormulaParsingWorkbook getFpw(IBook book) {
		return getFpw((ExcelBook) book);
	}

	public static FormulaParsingWorkbook getFpw(ExcelBook book) {
		return fpwMap.computeIfAbsent(book, b ->
			{
				if (b.delegate instanceof HSSFWorkbook)
					return HSSFEvaluationWorkbook.create((HSSFWorkbook) b.delegate);
				if (b.delegate instanceof XSSFWorkbook)
					return XSSFEvaluationWorkbook.create((XSSFWorkbook) b.delegate);
				if (b.delegate instanceof SXSSFWorkbook)
					return SXSSFEvaluationWorkbook.create((SXSSFWorkbook) b.delegate);
				throw new AssertionError("Workbook evaluator not found for workbook format");
			});
	}

	public static WorkbookEvaluator getEvaluator(ExcelBook book) {
		return evalMap.computeIfAbsent(book,
				b -> ((BaseFormulaEvaluator) b.delegate.getCreationHelper().createFormulaEvaluator())
						._getWorkbookEvaluator());
	}

	public static String evaluate(String formula, ExcelFormulaTree tree) {
		final ValueEval result = getEvaluator(tree.getBook()).evaluate(formula, getCellRef(tree));

		try {
			if (result instanceof NumberEval)
				return Double.toString(OperandResolver.coerceValueToDouble(result));
			if (result instanceof StringEval)
				return String.format("\"%s\"", OperandResolver.coerceValueToString(result));
		} catch (Exception e) {
			throw new AssertionError("Should never get here, already checked for cast");
		}
		return OperandResolver.coerceValueToString(result);
	}

	public static CellReference getCellRef(ExcelFormulaTree tree) {
		return new CellReference(tree.getSheet().getName(), // Sheet name
				tree.getCell().getRowIndex(), // Cell row
				tree.getCell().getColIndex(), // Cell col
				true, // Is abs row
				true // Is abs col
		);
	}

	public static Ptg[] getPtgs(String formula, ExcelCell cell) {
		return FormulaParser.parse(formula, // Formula String
				getFpw(cell.getBook()), // FormulaParsingWorkbook
				FormulaType.CELL, // Formula Type
				cell.getSheet().getIndex(), // Absolute Sheet index
				cell.getRowIndex()); // Absolute Row index
	}

}
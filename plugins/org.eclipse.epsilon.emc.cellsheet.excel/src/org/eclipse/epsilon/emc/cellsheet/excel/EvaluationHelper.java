package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.NumberEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.emc.cellsheet.IAst;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.ICellValue;
import org.eclipse.epsilon.emc.cellsheet.poi.TokenMappingFormulaParser;
import org.eclipse.epsilon.emc.cellsheet.poi.TokenMappingFormulaParser.TokenMappings;

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

	/**
	 * 
	 * @param book
	 * @return
	 */
	public static WorkbookEvaluator getEvaluator(IBook book) {
		return getEvaluator((ExcelBook) book);
	}

	/**
	 * 
	 * @param book
	 * @return
	 */
	public static FormulaParsingWorkbook getFpw(IBook book) {
		return getFpw((ExcelBook) book);
	}

	/**
	 * 
	 * @param book
	 * @return
	 */
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

	/**
	 * 
	 * @param book
	 * @return
	 */
	public static WorkbookEvaluator getEvaluator(ExcelBook book) {
		return evalMap.computeIfAbsent(book,
				b -> ((BaseFormulaEvaluator) b.delegate.getCreationHelper().createFormulaEvaluator())
						._getWorkbookEvaluator());
	}

	/**
	 * 
	 * @param formula
	 * @param cell
	 * @return
	 */
	public static String evaluate(String formula, ExcelCell cell) {
		final ValueEval result = getEvaluator(cell.getBook()).evaluate(formula, getCellRef(cell));
		try {
			if (result instanceof NumberEval)
				return Double.toString(OperandResolver.coerceValueToDouble(result));

			if (result instanceof ErrorEval)
				return ((ErrorEval) result).getErrorString();

			if (result instanceof RefEval) {
				RefEval cast = (RefEval) result;
				if (cast.getNumberOfSheets() > 1) {
					throw new UnsupportedOperationException();
				}
				return cell.getBook().getSheet(cast.getFirstSheetIndex()).getRow(cast.getRow())
						.getCell(cast.getColumn()).getCellValue().toString();
			}

		} catch (Exception e) {
			throw new AssertionError("Should never get here, already checked for cast");
		}
		return OperandResolver.coerceValueToString(result);
	}

	/**
	 * 
	 * @param formula
	 * @param cell
	 * @return
	 */
	public static String evaluate(String formula, ICell cell) {
		return evaluate(formula, (ExcelCell) cell);
	}

	/**
	 * 
	 * @param cellValue
	 * @return
	 */
	public static String evaluate(ExcelCellValue cellValue) {
		return evaluate(cellValue.getFormula(), cellValue.getCell());
	}

	/**
	 * 
	 * @param cellValue
	 * @return
	 */
	public static String evaluate(ICellValue cellValue) {
		return evaluate((ExcelCellValue) cellValue);
	}

	/**
	 * 
	 * @param tree
	 * @return
	 */
	public static String evaluate(ExcelAst tree) {
		return evaluate(tree.getFormula(), (ExcelCell) tree.getCell());
	}

	/**
	 * 
	 * @param tree
	 * @return
	 */
	public static String evaluate(IAst tree) {
		return evaluate((ExcelAst) tree);
	}

	/**
	 * 
	 * @param cell
	 * @return
	 */
	public static CellReference getCellRef(ICell cell) {
		return new CellReference(cell.getSheet().getName(), // Sheet name
				cell.getRowIndex(), // Row
				cell.getCol(), // Col
				true, // Is abs row
				true // Is abs col
		);
	}

	/**
	 * 
	 * @param cellValue
	 * @return
	 */
	public static CellReference getCellRef(ICellValue cellValue) {
		return getCellRef(cellValue.getCell());
	}

	/**
	 * 
	 * @param tree
	 * @return
	 */
	public static CellReference getCellRef(IAst tree) {
		return getCellRef(tree.getCell());
	}

	/**
	 * 
	 * @param formula
	 * @param cell
	 * @return
	 */
	public static TokenMappings getPtgs(String formula, ExcelCell cell) {
		final TokenMappingFormulaParser parser = new TokenMappingFormulaParser( // mapping parser
				formula, // formula string
				getFpw(cell.getBook()), // delegate workbook
				cell.getSheet().getIndex(), // absolute sheet index
				cell.getRowIndex()); // absolute row index
		return parser.getTokenMappings();
	}

	/**
	 * 
	 * @param tree
	 * @return
	 */
	public static TokenMappings getPtgs(ExcelAst tree) {
		return getPtgs(tree.getFormula(), (ExcelCell) tree.getCell());
	}

	/**
	 * 
	 * @param tree
	 * @return
	 */
	public static TokenMappings getPtgs(IAst tree) {
		return getPtgs((ExcelAst) tree);
	}
}
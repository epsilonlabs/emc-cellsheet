package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.Token;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenSubtype;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExcelFormulaTreeTest {

	static final String SHEET_NAME = "TestSheet";
	static final int FORMULA_ROW = 100;
	static final int FORMULA_COLUMN = 100;
	static XSSFWorkbook poiBook;
	static XSSFCell poiFormulaCell;
	
	ExcelBook book;

	@BeforeClass
	public static void setupClass() {
		poiBook = new XSSFWorkbook();

		XSSFSheet sheet = poiBook.createSheet(SHEET_NAME);
		XSSFRow row;
		for (int r = 0; r < 5; r++) {
			row = sheet.createRow(r);
			for (int c = 0; c < 5; c++) {
				XSSFCell cell = row.createCell(c);
				cell.setCellValue(1);
			}
		}
		poiFormulaCell = sheet.createRow(FORMULA_ROW).createCell(FORMULA_COLUMN, CellType.FORMULA);
	}
	
	@Before
	public void setup() {
		// Reset the formula
		poiFormulaCell.setCellFormula("TRUE");
		book = new ExcelBook(poiBook);
	}

	public static ExcelFormulaTree fromToken(String value, TokenType type, TokenSubtype subtype) {
		return new ExcelFormulaTree(new Token(value, type, subtype));
	}

	public static ExcelFormulaTree fromToken(String value, TokenType type) {
		return new ExcelFormulaTree(new Token(value, type));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_single_arg_function() throws Exception {
		final String formula = "SUM(A1:A5)";
		final IFormulaTree expected = fromToken("SUM", TokenType.FUNCTION, TokenSubtype.START);
		expected.addChild(fromToken("A1:A5", TokenType.OPERAND, TokenSubtype.RANGE));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_function() throws Exception {
		final String formula = "IF(TRUE, \"Yes\", \"No\")";
		final IFormulaTree expected = fromToken("IF", TokenType.FUNCTION, TokenSubtype.START);
		expected.addChild(fromToken("TRUE", TokenType.OPERAND, TokenSubtype.LOGICAL));
		expected.addChild(fromToken("Yes", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.addChild(fromToken("No", TokenType.OPERAND, TokenSubtype.TEXT));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_function_within_function() throws Exception {
		final String formula = "IF(IF(TRUE, \"Yes\", \"No\"), \"Yes\", \"No\")";
		final IFormulaTree expected = fromToken("IF", TokenType.FUNCTION, TokenSubtype.START);
		expected.addChild(fromToken("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.getChildAt(0).addChild(fromToken("TRUE", TokenType.OPERAND, TokenSubtype.LOGICAL));
		expected.getChildAt(0).addChild(fromToken("Yes", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.getChildAt(0).addChild(fromToken("No", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.addChild(fromToken("Yes", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.addChild(fromToken("No", TokenType.OPERAND, TokenSubtype.TEXT));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_pre_and_postfix_unary_operators() throws Exception {
		final String formula = "-1%";
		final IFormulaTree expected = fromToken("%", TokenType.OPERATOR_POSTFIX, TokenSubtype.PERCENT);
		expected.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).addChild(fromToken("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_pre_and_postfix_unary_operators_with_parens_for_prefix()
			throws Exception {
		final String formula = "-(1%)";
		final IFormulaTree expected = fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION);
		expected.addChild(fromToken("%", TokenType.OPERATOR_POSTFIX, TokenSubtype.PERCENT));
		expected.getChildAt(0).addChild(fromToken("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_pre_and_postfix_unary_operators_with_parens_for_postfix()
			throws Exception {
		final String formula = "(-1)%";
		final IFormulaTree expected = fromToken("%", TokenType.OPERATOR_POSTFIX, TokenSubtype.PERCENT);
		expected.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).addChild(fromToken("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_multiple_prefix_ops_with_parens() throws Exception {
		final String formula = "-(-(-(-(-(1)))))";
		final IFormulaTree expected = fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION);
		expected.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).getChildAt(0).getChildAt(0)
				.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).getChildAt(0).getChildAt(0).getChildAt(0)
				.addChild(fromToken("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_multiple_prefix_ops() throws Exception {
		final String formula = "-----1";
		final IFormulaTree expected = fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION);
		expected.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).getChildAt(0).getChildAt(0)
				.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).getChildAt(0).getChildAt(0).getChildAt(0)
				.addChild(fromToken("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_prefixed_unary_whole_expression() {
		final String formula = "-(8/2+2/1)";
		final IFormulaTree expected = fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION);
		expected.addChild(fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION));

		// LHS
		expected.getChildAt(0).addChild(fromToken("/", TokenType.OPERATOR_INFIX, TokenSubtype.DIVISION));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("8", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("2", TokenType.OPERAND, TokenSubtype.NUMBER));

		// RHS
		expected.getChildAt(0).addChild(fromToken("/", TokenType.OPERATOR_INFIX, TokenSubtype.DIVISION));
		expected.getChildAt(0).getChildAt(1).addChild(fromToken("2", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.getChildAt(0).getChildAt(1).addChild(fromToken("1", TokenType.OPERAND, TokenSubtype.NUMBER));

		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void fromString_should_return_correct_tree_when_given_prefixed_unary_subexpression() {
		final String formula = "-(8/2)+(2/1)";
		final IFormulaTree expected = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);

		// LHS
		expected.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).addChild(fromToken("/", TokenType.OPERATOR_INFIX, TokenSubtype.DIVISION));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("8", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("2", TokenType.OPERAND, TokenSubtype.NUMBER));

		// RHS
		expected.addChild(fromToken("/", TokenType.OPERATOR_INFIX, TokenSubtype.DIVISION));
		expected.getChildAt(1).addChild(fromToken("2", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.getChildAt(1).addChild(fromToken("1", TokenType.OPERAND, TokenSubtype.NUMBER));

		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}

	@Test
	public void equals_should_return_true_when_the_same_instance() throws Exception {
		final IFormulaTree expected = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		expected.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).addChild(fromToken("/", TokenType.OPERATOR_INFIX, TokenSubtype.DIVISION));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("8", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("2", TokenType.OPERAND, TokenSubtype.NUMBER));
		assertTrue(expected.equals(expected));
	}

	@Test
	public void equals_should_return_true_when_the_same_structure() throws Exception {
		final IFormulaTree expected = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		expected.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.getChildAt(0).addChild(fromToken("/", TokenType.OPERATOR_INFIX, TokenSubtype.DIVISION));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("8", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.getChildAt(0).getChildAt(0).addChild(fromToken("2", TokenType.OPERAND, TokenSubtype.NUMBER));

		final IFormulaTree actual = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		actual.addChild(fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		actual.getChildAt(0).addChild(fromToken("/", TokenType.OPERATOR_INFIX, TokenSubtype.DIVISION));
		actual.getChildAt(0).getChildAt(0).addChild(fromToken("8", TokenType.OPERAND, TokenSubtype.NUMBER));
		actual.getChildAt(0).getChildAt(0).addChild(fromToken("2", TokenType.OPERAND, TokenSubtype.NUMBER));
		assertTrue(expected.equals(actual));
		assertTrue(actual.equals(expected));
	}

	@Test
	public void equals_should_return_true_when_cellvalue_both_same() throws Exception {
		IFormulaTree expected = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		IFormulaTree actual = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);

		IFormulaCellValue cellValue = mock(IFormulaCellValue.class);
		expected.setCellValue(cellValue);
		actual.setCellValue(cellValue);

		assertEquals(expected.getCellValue(), actual.getCellValue());
		assertTrue(expected.equals(actual));
		assertTrue(actual.equals(expected));
	}

	@Test
	public void equals_should_return_true_when_cellvalue_both_null() throws Exception {
		IFormulaTree expected = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		IFormulaTree actual = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);

		expected.setCellValue(null);
		actual.setCellValue(null);

		assertNull(expected.getCellValue());
		assertNull(actual.getCellValue());
		assertTrue(expected.equals(actual));
		assertTrue(actual.equals(expected));
	}

	@Test
	public void equals_should_return_false_when_cellvalue_is_different() throws Exception {
		IFormulaTree expected = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		IFormulaTree actual = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);

		IFormulaCellValue cellValue = mock(IFormulaCellValue.class);
		expected.setCellValue(cellValue);

		assertEquals(cellValue, expected.getCellValue());
		assertNull(actual.getCellValue());
		assertFalse(expected.equals(actual));
		assertFalse(actual.equals(expected));
	}

	@Test
	public void equals_should_return_true_when_token_both_same() throws Exception {
		IFormulaTree expected = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		IFormulaTree actual = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		assertTrue(expected.equals(actual));
		assertTrue(actual.equals(expected));
	}

	@Test
	public void equals_should_return_false_when_token_not_same() throws Exception {
		IFormulaTree expected = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		IFormulaTree actual = fromToken("-", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		assertFalse(expected.equals(actual));
		assertFalse(actual.equals(expected));
	}

	@Test
	public void equals_should_return_false_when_other_is_null() throws Exception {
		IFormulaTree tree = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		assertFalse(tree.equals(null));
	}

	@Test
	public void equals_should_return_false_when_other_is_not_same_class() throws Exception {
		IFormulaTree tree = fromToken("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION);
		assertFalse(tree.equals(this));
	}

	@Test
	public void evaluate_should_return_25_when_given_sum_A1D5() throws Exception {
		final String formula = "SUM(A1:D5)";
		poiFormulaCell.setCellFormula(formula);
		final ICell cell = book.getSheet(SHEET_NAME).getRow(FORMULA_ROW).getCell(FORMULA_COLUMN);
		
		final String expected = poiBook.getCreationHelper().createFormulaEvaluator().evaluate(poiFormulaCell).formatAsString();		
		final String actual = cell.getFormulaCellValue().getFormulaTree().evaluate();
		assertEquals(expected, actual);
	}
	
	@Test
	public void evaluate_should_return_string_yes_when_given_complex_if() throws Exception {
		final String formula = "IF(IF(TRUE, TRUE, FALSE), \"YES\", \"NO\")";
		poiFormulaCell.setCellFormula(formula);
		final ICell cell = book.getSheet(SHEET_NAME).getRow(FORMULA_ROW).getCell(FORMULA_COLUMN);

		final String expected = poiBook.getCreationHelper().createFormulaEvaluator().evaluate(poiFormulaCell).formatAsString();		
		final String actual = cell.getFormulaCellValue().getFormulaTree().evaluate();
		assertEquals(expected, actual);
	}
	

	@Test
	public void evaluate_should_return_8_when_given_arithmetic() throws Exception {
		final String formula = "-(8/4)+(5+5)";
		poiFormulaCell.setCellFormula(formula);
		final ICell cell = book.getSheet(SHEET_NAME).getRow(FORMULA_ROW).getCell(FORMULA_COLUMN);

		final String expected = poiBook.getCreationHelper().createFormulaEvaluator().evaluate(poiFormulaCell).formatAsString();		
		final String actual = cell.getFormulaCellValue().getFormulaTree().evaluate();
		assertEquals(expected, actual);
	}
	
	@Test
	public void evaluate_should_return_neg_50_when_given_unary_pre_post_ops() throws Exception {
		final String formula = "(20-10)%*500";

		poiFormulaCell.setCellFormula(formula);
		final ICell cell = book.getSheet(SHEET_NAME).getRow(FORMULA_ROW).getCell(FORMULA_COLUMN);

		final String expected = poiBook.getCreationHelper().createFormulaEvaluator().evaluate(poiFormulaCell).formatAsString();		
		final String actual = cell.getFormulaCellValue().getFormulaTree().evaluate();
		assertEquals(expected, actual);
	}
	
}

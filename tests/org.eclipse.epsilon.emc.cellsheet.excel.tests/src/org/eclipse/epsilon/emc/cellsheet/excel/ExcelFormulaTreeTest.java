package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.*;

import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.Token;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenSubtype;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenType;
import org.junit.Test;

public class ExcelFormulaTreeTest {

	public static Ptg[] toPtgs(String formula) {
		XSSFWorkbook book = new XSSFWorkbook();
		book.createSheet().createRow(0).createCell(0);
		return FormulaParser.parse(formula, // Formula String
				XSSFEvaluationWorkbook.create(book), // FormulaParsingWorkbook
				FormulaType.CELL, // Formula Type
				0, // Absolute Sheet index
				0); // Absolute Row index
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
		final String formula = "IF(true, \"Yes\", \"No\")";
		final IFormulaTree expected = fromToken("IF", TokenType.FUNCTION, TokenSubtype.START);
		expected.addChild(fromToken("true", TokenType.OPERAND, TokenSubtype.LOGICAL));
		expected.addChild(fromToken("\"Yes\"", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.addChild(fromToken("\"No\"", TokenType.OPERAND, TokenSubtype.TEXT));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}
	
	@Test
	public void fromString_should_return_correct_tree_when_given_function_within_function() throws Exception {
		final String formula = "IF(IF(true, \"Yes\", \"No\"), \"Yes\", \"No\")";
		final IFormulaTree expected = fromToken("IF", TokenType.FUNCTION, TokenSubtype.START);
		expected.addChild(fromToken("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.getChildAt(0).addChild(fromToken("true", TokenType.OPERAND, TokenSubtype.LOGICAL));
		expected.getChildAt(0).addChild(fromToken("\"Yes\"", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.getChildAt(0).addChild(fromToken("\"No\"", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.addChild(fromToken("\"Yes\"", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.addChild(fromToken("\"No\"", TokenType.OPERAND, TokenSubtype.TEXT));
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
	public void fromString_should_return_correct_tree_when_given_pre_and_postfix_unary_operators_with_parens_for_prefix() throws Exception {
		final String formula = "-(1%)";
		final IFormulaTree expected = fromToken("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION);
		expected.addChild(fromToken("%", TokenType.OPERATOR_POSTFIX, TokenSubtype.PERCENT));
		expected.getChildAt(0).addChild(fromToken("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		assertEquals(expected, ExcelFormulaTree.fromString(formula));
	}
	
	@Test
	public void fromString_should_return_correct_tree_when_given_pre_and_postfix_unary_operators_with_parens_for_postfix() throws Exception {
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
	public void equals() throws Exception {

	}

//	ExcelBook book;
//
//	@Before
//	public void setup() throws Exception {
//		book = ExcelTestUtil.getBook(ExcelFormulaTreeTest.class);
//	}
//
//	@Test
//	public void aiEvaluate_should_return_cell_reference_not_result() throws Exception {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(9).getCell(0).getFormulaCellValue().getFormulaTree();
//
//		final String result = tree.evaluate();
//		final ICell aiResult = tree.evaluateCell();
//		assertEquals("C1 Result", result);
//		assertEquals(book.getSheet("Lookup").getRow(0).getCell(2), aiResult);
//	}
//
//	@Test
//	public void getFormula_should_return_string_when_given_arithmetic_formula_with_no_brackets() {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(2).getCell(0).getFormulaCellValue().getFormulaTree();
//		assertEquals("65+20", tree.getFormula());
//		assertEquals(85, Double.parseDouble(tree.evaluate()), 0);
//	}
//
//	@Test
//	public void getFormula_should_return_string_when_given_arithmetic_formula_with_1_set_of_brackets() {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(3).getCell(0).getFormulaCellValue().getFormulaTree();
//		assertEquals("(6*5)+500", tree.getFormula());
//		assertEquals(530, Double.parseDouble(tree.evaluate()), 0);
//	}
//
//	@Test
//	public void getFormula_should_return_string_when_given_arithmetic_formula_with_1_set_of_brackets_in_different_place() {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(4).getCell(0).getFormulaCellValue().getFormulaTree();
//		assertEquals("6*(5+500)", tree.getFormula());
//		assertEquals(3030, Double.parseDouble(tree.evaluate()), 0);
//	}
//
//	@Test
//	public void getFormula_should_return_string_when_given_arithmetic_formula_with_2_set_of_brackets() {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(5).getCell(0).getFormulaCellValue().getFormulaTree();
//		assertEquals("(34*45)+(800/40)", tree.getFormula());
//		assertEquals(1550, Double.parseDouble(tree.evaluate()), 0);
//	}
//
//	@Test
//	public void buildFormulaString_should_return_string_when_given_sum_function_with_multiple_args() throws Exception {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(1).getCell(0).getFormulaCellValue().getFormulaTree();
//		assertEquals("SUM(Data!B1,Data!D5,Data!B5,Data!D2,Data!C2)", tree.getFormula());
//		assertEquals(5, Double.parseDouble(tree.evaluate()), 0);
//	}
//
//	@Test
//	public void buildFormulaString_should_return_string_when_given_sum_function_with_range() throws Exception {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(0).getCell(0).getFormulaCellValue().getFormulaTree();
//		assertEquals("SUM(Data!A1:D5)", tree.getFormula());
//		assertEquals(20, Double.parseDouble(tree.evaluate()), 0);
//	}
//
//	@Test
//	public void buildFormulaString_should_return_string_when_given_unary() throws Exception {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(6).getCell(0).getFormulaCellValue().getFormulaTree();
//		assertEquals("-(8-4)", tree.getFormula());
//		assertEquals(-4, Double.parseDouble(tree.evaluate()), 0);
//	}
//
//	@Test
//	public void buildFormulaString_should_return_string_when_given_percent() throws Exception {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(7).getCell(0).getFormulaCellValue().getFormulaTree();
//		assertEquals("9%", tree.getFormula());
//		assertEquals(0.09, Double.parseDouble(tree.evaluate()), 0);
//	}
//
//	@Test
//	public void evaluate_should_return_partial_result_when_subtree_is_given() throws Exception {
//		final ICell cell = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(8).getCell(0);
//		final IFormulaCellValue value = (IFormulaCellValue) cell.getCellValue();
//		final IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();
//
//		assertEquals(35, Double.parseDouble(value.getValue()), 0);
//		assertEquals(35, Double.parseDouble(tree.evaluate()), 0);
//
//		final IFormulaTree root = value.getFormulaTree();
//		assertEquals(2, root.getChildren().size());
//
//		final IFormulaTree left = root.getChildAt(0);
//		assertEquals("SUM(Data!A1:D5)", left.getFormula());
//		assertEquals(20, Double.parseDouble(left.evaluate()), 0);
//
//		final IFormulaTree right = root.getChildAt(1);
//		assertEquals("SUM(Data!B1:D5)", right.getFormula());
//		assertEquals(15, Double.parseDouble(right.evaluate()), 0);
//	}
//
//	@Test
//	public void getAllChildren_should_return_all_tokens() throws Exception {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getRow(8).getCell(0).getFormulaCellValue().getFormulaTree();
//		assertEquals(5, tree.getAllTrees().size());
//	}
//
//	/**
//	 * Regression test to check that all tokens are consumed when constructing the
//	 * FormulaTree for a formula with a {@code IF()} function.
//	 * 
//	 * {@code IF()} functions introduce special unseen tokens during the
//	 * tokenisation process that optimise how the formula should be evaluated.
//	 * Specifically these are {@link AttrPtg} with the {@code AttrPtg#isSkip()} set
//	 * to {@code true}.
//	 * 
//	 * @throws Exception
//	 */
//	@Test
//	public void test_if_funcvar_should_consume_all_tokens_when_building_formula_tree() throws Exception {
//		IFormulaTree tree = book.getSheet(ExcelFormulaTreeTest.class.getSimpleName()).getA1Row(12).getA1Cell("A").getFormulaCellValue().getFormulaTree();
//		assertEquals(8, tree.getAllTrees().size());
//	}
}

package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.assertEquals;

import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.junit.Before;
import org.junit.Test;

public class ExcelFormulaTreeTest {

	ExcelBook book;

	@Before
	public void setup() throws Exception {
		book = ExcelTestUtil.getBook(ExcelFormulaTreeTest.class);
	}

	@Test
	public void aiEvaluate_should_return_cell_reference_not_result() throws Exception {
		final ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 9, 0);
		final IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();

		final String result = tree.evaluate();
		final ICell aiResult = tree.evaluateCell();
		assertEquals("C1 Result", result);
		assertEquals(book.getCell("Lookup", 0, 2), aiResult);
	}

	@Test
	public void getFormula_should_return_string_when_given_arithmetic_formula_with_no_brackets() {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 2, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();

		assertEquals("65+20", tree.getFormula());
		assertEquals(85, Double.parseDouble(tree.evaluate()), 0);
	}

	@Test
	public void getFormula_should_return_string_when_given_arithmetic_formula_with_1_set_of_brackets() {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 3, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();
		assertEquals("(6*5)+500", tree.getFormula());
		assertEquals(530, Double.parseDouble(tree.evaluate()), 0);
	}

	@Test
	public void getFormula_should_return_string_when_given_arithmetic_formula_with_1_set_of_brackets_in_different_place() {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 4, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();
		assertEquals("6*(5+500)", tree.getFormula());
		assertEquals(3030, Double.parseDouble(tree.evaluate()), 0);
	}

	@Test
	public void getFormula_should_return_string_when_given_arithmetic_formula_with_2_set_of_brackets() {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 5, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();
		assertEquals("(34*45)+(800/40)", tree.getFormula());
		assertEquals(1550, Double.parseDouble(tree.evaluate()), 0);
	}

	@Test
	public void buildFormulaString_should_return_string_when_given_sum_function_with_multiple_args() throws Exception {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 1, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();
		assertEquals("SUM(Data!B1,Data!D5,Data!B5,Data!D2,Data!C2)", tree.getFormula());
		assertEquals(5, Double.parseDouble(tree.evaluate()), 0);
	}

	@Test
	public void buildFormulaString_should_return_string_when_given_sum_function_with_range() throws Exception {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 0, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();
		assertEquals("SUM(Data!A1:D5)", tree.getFormula());
		assertEquals(20, Double.parseDouble(tree.evaluate()), 0);
	}

	@Test
	public void buildFormulaString_should_return_string_when_given_unary() throws Exception {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 6, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();
		assertEquals("-(8-4)", tree.getFormula());
		assertEquals(-4, Double.parseDouble(tree.evaluate()), 0);
	}

	@Test
	public void buildFormulaString_should_return_string_when_given_percent() throws Exception {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 7, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();
		assertEquals("9%", tree.getFormula());
		assertEquals(0.09, Double.parseDouble(tree.evaluate()), 0);
	}

	@Test
	public void evaluate_should_return_partial_result_when_subtree_is_given() throws Exception {
		final ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 8, 0);
		final IFormulaCellValue value = (IFormulaCellValue) cell.getCellValue();
		final IFormulaTree tree = ((IFormulaCellValue) cell.getCellValue()).getFormulaTree();

		assertEquals(35, Double.parseDouble(value.getValue()), 0);
		assertEquals(35, Double.parseDouble(tree.evaluate()), 0);

		final IFormulaTree root = value.getFormulaTree();
		assertEquals(2, root.getChildren().size());

		final IFormulaTree left = root.getChildAt(0);
		assertEquals("SUM(Data!A1:D5)", left.getFormula());
		assertEquals(20, Double.parseDouble(left.evaluate()), 0);

		final IFormulaTree right = root.getChildAt(1);
		assertEquals("SUM(Data!B1:D5)", right.getFormula());
		assertEquals(15, Double.parseDouble(right.evaluate()), 0);
	}

	@Test
	public void getAllChildren_should_return_all_tokens() throws Exception {
		final ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 8, 0);
		final IFormulaCellValue cellValue = cell.getFormulaCellValue();
		final IFormulaTree tree = cellValue.getFormulaTree();
		assertEquals(5, tree.getAllTrees().size());
	}

	/**
	 * Regression test to check that all tokens are consumed when constructing the
	 * FormulaTree for a formula with a {@code IF()} function.
	 * 
	 * {@code IF()} functions introduce special unseen tokens during the
	 * tokenisation process that optimise how the formula should be evaluated.
	 * Specifically these are {@link AttrPtg} with the {@code AttrPtg#isSkip()} set
	 * to {@code true}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void test_if_funcvar_should_consume_all_tokens_when_building_formula_tree() throws Exception {
		ICell ifCell = book.getA1Cell(ExcelFormulaTreeTest.class.getSimpleName(), "A", 11);
		IFormulaTree ifTree = ifCell.getFormulaCellValue().getFormulaTree();
		assertEquals(8, ifTree.getAllTrees().size());
	}
}

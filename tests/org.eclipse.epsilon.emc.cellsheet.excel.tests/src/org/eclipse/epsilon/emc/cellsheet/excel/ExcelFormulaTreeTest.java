package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.assertEquals;

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
		final IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		
		String result = tree.evaluate();
		String aiResult = tree.evaluate(true);
		
		assertEquals("C1 Result", result);
		assertEquals("'1'!C1", aiResult);
	}
	
	@Test
	public void toFormula_should_return_string_when_given_arithmetic_formula_with_no_brackets() {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 2, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		
		assertEquals("(65+20)", tree.toFormula());
		assertEquals(85, Double.parseDouble(tree.evaluate()), 0);
	}
	
	@Test
	public void toFormula_should_return_string_when_given_arithmetic_formula_with_1_set_of_brackets() {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 3, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		assertEquals("((6*5)+500)", tree.toFormula());
		assertEquals(530, Double.parseDouble(tree.evaluate()), 0);
	}
	
	@Test
	public void toFormula_should_return_string_when_given_arithmetic_formula_with_1_set_of_brackets_in_different_place() {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 4, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		assertEquals("(6*(5+500))", tree.toFormula());
		assertEquals(3030, Double.parseDouble(tree.evaluate()), 0);
	}

	@Test
	public void toFormula_should_return_string_when_given_arithmetic_formula_with_2_set_of_brackets() {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 5, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		assertEquals("((34*45)+(800/40))", tree.toFormula());
		assertEquals(1550, Double.parseDouble(tree.evaluate()), 0);
	}
	
	@Test
	public void buildFormulaString_should_return_string_when_given_sum_function_with_multiple_args() throws Exception {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 1, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		assertEquals("(SUM(Data!B1,Data!D5,Data!B5,Data!D2,Data!C2))", tree.toFormula());
		assertEquals(5, Double.parseDouble(tree.evaluate()), 0);
	}
	
	@Test
	public void buildFormulaString_should_return_string_when_given_sum_function_with_range() throws Exception {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 0, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		assertEquals("(SUM(Data!A1:D5))", tree.toFormula());
		assertEquals(20, Double.parseDouble(tree.evaluate()), 0);
	}
	
	@Test
	public void buildFormulaString_should_return_string_when_given_unary() throws Exception {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 6, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		assertEquals("(-(8-4))", tree.toFormula());
		assertEquals(-4, Double.parseDouble(tree.evaluate()), 0);
	}
	
	@Test
	public void buildFormulaString_should_return_string_when_given_percent() throws Exception {
		ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 7, 0);
		IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		assertEquals("(9%)", tree.toFormula());
		assertEquals(0.09, Double.parseDouble(tree.evaluate()), 0);
	}
	
	@Test
	public void evaluate_should_return_partial_result_when_subtree_is_given() throws Exception {
		final ICell cell = book.getCell(ExcelFormulaTreeTest.class.getSimpleName(), 8, 0);
		final IFormulaCellValue value = (IFormulaCellValue) cell.getValue();
		final IFormulaTree tree = ((IFormulaCellValue) cell.getValue()).getFormulaTree();
		
		assertEquals(35, Double.parseDouble(value.getValue()), 0);
		assertEquals(35, Double.parseDouble(tree.evaluate()), 0);
		
		final IFormulaTree root = value.getFormulaTree();
		assertEquals(2, root.getChildren().size());
		
		final IFormulaTree left = root.getChildAt(0);
		assertEquals("(SUM(Data!A1:D5))", left.toFormula());
		assertEquals(20, Double.parseDouble(left.evaluate()), 0);
		
		final IFormulaTree right = root.getChildAt(1);
		assertEquals("(SUM(Data!B1:D5))", right.toFormula());
		assertEquals(15, Double.parseDouble(right.evaluate()), 0);
	}
}
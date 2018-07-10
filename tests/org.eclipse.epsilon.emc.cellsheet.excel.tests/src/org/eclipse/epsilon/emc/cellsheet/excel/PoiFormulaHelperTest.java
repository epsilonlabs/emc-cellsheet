package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.junit.Before;
import org.junit.Test;

public class PoiFormulaHelperTest {

	ExcelBook book;

	@Before
	public void setup() throws Exception {
		book = ExcelTestUtil.getBook(PoiFormulaHelperTest.class.getSimpleName() + ".xlsx");
	}

	@Test
	public void buildFormulaTree_should_return_tree_with_single_operand_and_sum_attr() throws Exception {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 0, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		
		IFormulaTree sumTree = PoiFormulaHelper.buildFormulaTree(cellValue);
		assertEquals("SUM",sumTree.getToken().toString());
		assertEquals(1, sumTree.getChildren().size());
		
		IFormulaTree areaTree = sumTree.getChildren().get(0);
		assertEquals("Data!A1:D5", areaTree.getToken().toString());
		assertEquals(sumTree, areaTree.getParent());
	}
	
	@Test
	public void buildFormulaTree_should_return_tree_with_multiple_operands_and_sum_function() throws Exception {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 1, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		
		IFormulaTree sumTree = PoiFormulaHelper.buildFormulaTree(cellValue);
		assertEquals("SUM",sumTree.getToken().toString());
		assertEquals(5, sumTree.getChildren().size());
		
		final Set<String> expected = new HashSet<>(Arrays.asList("Data!B1","Data!D5","Data!B5","Data!D2","Data!C2"));
		for (IFormulaTree child : sumTree.getChildren()) {
			assertThat(expected, hasItem(child.getToken().toString()));
			assertEquals(sumTree, child.getParent());
			expected.remove(child.getToken().toString());
		}
		assertEquals(0, expected.size());
	}
	
	@Test
	public void buildFromulaString_should_return_string_when_given_arithmetic_formula_with_no_brackets() {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 2, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		assertEquals("(65+20)", PoiFormulaHelper.buildFormulaString(cellValue));
		assertEquals("85", ((IFormulaCellValue) cellValue).getFormulaTree().evaluate());
	}
	
	@Test
	public void buildFromulaString_should_return_string_when_given_arithmetic_formula_with_1_set_of_brackets() {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 3, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		assertEquals("((6*5)+500)", PoiFormulaHelper.buildFormulaString(cellValue));
		assertEquals("530", ((IFormulaCellValue) cellValue).getFormulaTree().evaluate());
	}
	
	@Test
	public void buildFromulaString_should_return_string_when_given_arithmetic_formula_with_1_set_of_brackets_in_different_place() {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 4, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		assertEquals("(6*(5+500))", PoiFormulaHelper.buildFormulaString(cellValue));
		assertEquals("3030", ((IFormulaCellValue) cellValue).getFormulaTree().evaluate());
	}

	@Test
	public void buildFromulaString_should_return_string_when_given_arithmetic_formula_with_2_set_of_brackets() {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 5, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		assertEquals("((34*45)+(800/40))", PoiFormulaHelper.buildFormulaString(cellValue));
		assertEquals("1550", ((IFormulaCellValue) cellValue).getFormulaTree().evaluate());
	}
	
	@Test
	public void buildFormulaString_should_return_string_when_given_sum_function_with_multiple_args() throws Exception {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 1, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		assertEquals("(SUM(Data!B1,Data!D5,Data!B5,Data!D2,Data!C2))", PoiFormulaHelper.buildFormulaString(cellValue));
		assertEquals("5", ((IFormulaCellValue) cellValue).getFormulaTree().evaluate());
	}
	
	@Test
	public void buildFormulaString_should_return_string_when_given_sum_function_with_range() throws Exception {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 0, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		assertEquals("(SUM(Data!A1:D5))", PoiFormulaHelper.buildFormulaString(cellValue));
		assertEquals("20", ((IFormulaCellValue) cellValue).getFormulaTree().evaluate());
	}
	
	@Test
	public void buildFormulaString_should_return_string_when_given_unary() throws Exception {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 6, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		assertEquals("(-(8-4))", PoiFormulaHelper.buildFormulaString(cellValue));
		assertEquals("-4", ((IFormulaCellValue) cellValue).getFormulaTree().evaluate());
	}
	
	@Test
	public void buildFormulaString_should_return_string_when_given_percent() throws Exception {
		ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 7, 0);
		IFormulaCellValue cellValue = (IFormulaCellValue) cell.getValue();
		assertEquals("(9%)", PoiFormulaHelper.buildFormulaString(cellValue));
		System.out.println(PoiFormulaHelper.evaluate((ExcelFormulaTree) cellValue.getFormulaTree()));
		assertEquals("0.09", ((IFormulaCellValue) cellValue).getFormulaTree().evaluate());
	}
	
	
	public void print(IFormulaTree tree) {
		System.out.println(PoiFormulaHelper.buildFormulaString(tree));
		for (IFormulaTree tree2 : tree.getChildren()) {
			print(tree2);
		}
	}
}

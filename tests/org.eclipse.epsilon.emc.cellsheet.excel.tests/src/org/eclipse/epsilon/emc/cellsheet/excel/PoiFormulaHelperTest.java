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
			expected.remove(child.getToken().toString());
		}
		assertEquals(0, expected.size());
	}
	
}

package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

public class FormulaUtilTest {

	static ExcelBook book;
	
	@BeforeClass
	public static void setUpClass() throws Exception {
		book = ExcelTestUtil.getBook("Spreadsheet Equiv.xlsx");
	}
	
	@Test
	public void buildFormulaTree_should_return_tree_of_size_6_when_given_cellvalue() throws Exception {
		ExcelCell cell = book.getCell("Florida", 4, 2);
		ExcelFormulaCellValue cellValue = (ExcelFormulaCellValue) cell.getFormulaCellValue();
		ExcelFormulaTree formulaTree = FormulaUtil.buildFormulaTree(cellValue);
		assertEquals(6, formulaTree.getAllTrees().size());
	}

}

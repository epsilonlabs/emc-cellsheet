package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.assertEquals;

import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
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
		ICell cell = book.getSheet("Florida").getRow(4).getCell(2);
		IFormulaCellValue cellValue = cell.getFormulaCellValue();
		IFormulaTree formulaTree = FormulaUtil.buildFormulaTree((ExcelFormulaCellValue) cellValue);
		assertEquals(6, formulaTree.getAllTrees().size());
	}

}

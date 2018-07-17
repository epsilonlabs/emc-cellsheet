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
		book = ExcelTestUtil.getBook(PoiFormulaHelperTest.class.getSimpleName() + ".xlsx");
	}

	@Test
	public void aiEvaluate_should_return_cell_reference_not_result() throws Exception {
		final ICell cell = book.getCell(PoiFormulaHelperTest.class.getSimpleName(), 9, 0);
		final IFormulaCellValue value = (IFormulaCellValue) cell.getValue();
		final IFormulaTree tree = value.getFormulaTree();
		
		String result = tree.evaluate();
		String aiResult = tree.evaluate(true);
		
		assertEquals("C1 Result", result);
		assertEquals("'1'!C1", aiResult);
	}
}

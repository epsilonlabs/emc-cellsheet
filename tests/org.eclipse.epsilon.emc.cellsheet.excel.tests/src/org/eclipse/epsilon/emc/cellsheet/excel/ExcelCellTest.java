package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.*;

import org.eclipse.epsilon.emc.cellsheet.ICellValue;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link ExcelCell}
 * 
 * @author Jonathan Co
 *
 */
public class ExcelCellTest {

	ExcelBook book;
	ExcelCell cell;

	@Before
	public void setup() throws Exception {
		this.book = ExcelTestUtil.getBook("ExcelCellTest.xlsx");
		this.cell = this.book.getCell("ExcelCellTest", 13, 26);
	}

	@Test
	public void getColIndex_should_return_integer() throws Exception {
		assertEquals(26, cell.getColIndex());
	}

	@Test
	public void getCol_should_return_String() throws Exception {
		assertEquals("AA", cell.getCol());
	}

	@Test
	public void getRow_should_return_Row() throws Exception {
		ExcelRow row = cell.getRow();
		assertEquals(13, row.getIndex());
	}

	@Test
	public void getRowIndex_should_return_integer() throws Exception {
		assertEquals(13, cell.getRowIndex());
	}

	@Test
	public void getValue_should_return_ExcelBooleanValue() throws Exception {
		cell = book.getCell("ExcelCellTest", 1, 0);
		ICellValue<?> value = cell.getValue();
		assertTrue(value instanceof ExcelBooleanValue);
	}

	@Test
	public void getValue_should_return_ExcelNumericValue() throws Exception {
		cell = book.getCell("ExcelCellTest", 2, 0);
		ICellValue<?> value = cell.getValue();
		assertTrue(value instanceof ExcelNumericValue);
	}

	@Test
	public void getValue_should_return_ExcelStringValue() throws Exception {
		cell = book.getCell("ExcelCellTest", 0, 0);
		ICellValue<?> value = cell.getValue();
		assertTrue(value instanceof ExcelStringValue);
	}

	@Test
	public void getValue_should_return_ExcelFormulaValue() throws Exception {
		cell = book.getCell("ExcelCellTest", 3, 0);
		ICellValue<?> value = cell.getValue();
		assertTrue(value instanceof ExcelFormulaValue);
	}
	
	@Test
	public void getSheet_should_return_ExcelSheet() throws Exception {
		ExcelSheet sheet = cell.getSheet();
		assertEquals("ExcelCellTest", sheet.getName());
		assertEquals(0, sheet.getIndex());
	}
	
	@Test
	public void getBook_should_return_ExcelBook() throws Exception {
		assertSame(book, cell.getBook());
	}

}

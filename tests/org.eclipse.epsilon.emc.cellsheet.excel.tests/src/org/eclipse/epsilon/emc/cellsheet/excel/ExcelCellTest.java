package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.epsilon.emc.cellsheet.ICell;
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
	ICell cell;

	@Before
	public void setup() throws Exception {
		this.book = ExcelTestUtil.getBook("ExcelCellTest.xlsx");
		this.cell = book.getSheet("ExcelCellTest").getRow(13).getCell(26);
	}

	@Test
	public void getColIndex_should_return_integer() throws Exception {
		assertEquals(26, cell.getCol());
	}

	@Test
	public void getCol_should_return_String() throws Exception {
		assertEquals("AA", cell.getA1Col());
	}

	@Test
	public void getRow_should_return_Row() throws Exception {
		assertEquals(13, cell.getRow().getIndex());
	}

	@Test
	public void getRowIndex_should_return_integer() throws Exception {
		assertEquals(13, cell.getRowIndex());
	}

	@Test
	public void getValue_should_return_ExcelBooleanValue() throws Exception {
		cell = book.getSheet("ExcelCellTest").getRow(1).getCell(0);
		ICellValue<?> value = cell.getCellValue();
		assertTrue(value instanceof ExcelBooleanCellValue);
	}

	@Test
	public void getValue_should_return_ExcelNumericValue() throws Exception {
		cell = book.getSheet("ExcelCellTest").getRow(2).getCell(0);
		ICellValue<?> value = cell.getCellValue();
		assertTrue(value instanceof ExcelNumericCellValue);
	}

	@Test
	public void getValue_should_return_ExcelStringValue() throws Exception {
		cell = book.getSheet("ExcelCellTest").getRow(0).getCell(0);
		ICellValue<?> value = cell.getCellValue();
		assertTrue(value instanceof ExcelStringCellValue);
	}

	@Test
	public void getValue_should_return_ExcelFormulaValue() throws Exception {
		cell = book.getSheet("ExcelCellTest").getRow(3).getCell(0);
		ICellValue<?> value = cell.getCellValue();
		assertTrue(value instanceof ExcelFormulaCellValue);
	}

	@Test
	public void getValue_should_return_ExcelBlankCellValue() throws Exception {
		cell = book.getSheet("ExcelCellTest").getRow(5769).getCell(543);
		assertTrue(cell.getCellValue() instanceof ExcelBlankCellValue);
	}

	@Test
	public void getSheet_should_return_ExcelSheet() throws Exception {
		ExcelSheet sheet = (ExcelSheet) cell.getSheet();
		assertEquals("ExcelCellTest", sheet.getName());
		assertEquals(0, sheet.getIndex());
	}

	@Test
	public void getBook_should_return_ExcelBook() throws Exception {
		assertSame(book, cell.getBook());
	}

}

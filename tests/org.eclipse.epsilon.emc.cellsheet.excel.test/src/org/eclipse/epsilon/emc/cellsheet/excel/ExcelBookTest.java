package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;


public class ExcelBookTest {
	
	ExcelBook book;
	ExcelBook otherBook;

	@Before
	public void setup() throws Exception {
		book = new ExcelBook();
		book.setExcelFile(ExcelTestUtil.RES_PATH + ExcelTestUtil.BOOK_1);
		book.setName("Excel");
		book.load();
		
		otherBook = new ExcelBook();
		otherBook.setExcelFile(ExcelTestUtil.RES_PATH + ExcelTestUtil.BOOK_2);
		otherBook.setName("Other");
		otherBook.load();
	}
	
	@Test
	public void testSetExcelFile() throws URISyntaxException {
		final String filepath = "./resources/ExcelFile.xlsx";		
		final ExcelBook mBook = new ExcelBook();
		assertNull(mBook.excelFile);
		mBook.setExcelFile(filepath);
		assertNotNull(mBook.excelFile);
		assertEquals(mBook.excelFile, new File(filepath));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetExcelFileBad() {
		final String filepath = "badFilePath";
		final ExcelBook mBook = new ExcelBook();
		assertNull(mBook.excelFile);
		mBook.setExcelFile(filepath);
	}
	
	@Test
	public void testOwnsBook() throws Exception {
		assertTrue(book.owns(book));
		assertFalse(book.owns(otherBook));
	}
	
	@Test
	public void testOwnsSheet() throws Exception {
		assertTrue(book.owns(book.getSheet(0)));
		assertFalse(book.owns(otherBook.getSheet(0)));
	}
	
	@Test
	public void testOwnsRow() throws Exception {
		assertTrue(book.owns(book.getRow(0, 0)));
		assertFalse(book.owns(otherBook.getRow(0, 0)));
	}
	
	@Test
	public void testOwnsCell() throws Exception {
		assertTrue(book.owns(book.getCell(0, 0, 0)));
		assertFalse(book.owns(otherBook.getCell(0, 0, 0)));
	}
	
}

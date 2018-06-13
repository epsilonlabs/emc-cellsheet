package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Collection;

import org.eclipse.epsilon.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.junit.Before;
import org.junit.Test;


public class ExcelBookTest {
	
	ExcelBook book;
	ExcelBook other;

	@Before
	public void setup() throws Exception {
		book = ExcelTestUtil.getBook("TestFile.xlsx");
		other = ExcelTestUtil.getBook("Formula.xlsx");
	}
	
	@Test
	public void testSetExcelFile() throws URISyntaxException {
		final String filepath = "./resources/TestFile.xlsx";		
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
		assertFalse(book.owns(other));
	}
	
	@Test
	public void testOwnsSheet() throws Exception {
		assertTrue(book.owns(book.getSheet(0)));
		assertFalse(book.owns(other.getSheet(0)));
	}
	
	@Test
	public void testOwnsRow() throws Exception {
		assertTrue(book.owns(book.getRow(0, 0)));
		assertFalse(book.owns(other.getRow(0, 0)));
	}
	
	@Test
	public void testOwnsCell() throws Exception {
		assertTrue(book.owns(book.getCell(0, 0, 0)));
		assertFalse(book.owns(other.getCell(0, 0, 0)));
	}
	
	@Test
	public void testGetTypeOfBook() throws Exception {
		assertEquals(CellsheetType.BOOK, book.getTypeOf(book));
	}
	
	@Test
	public void testGetTypeOfSheet() throws Exception {
		assertEquals(CellsheetType.SHEET, book.getTypeOf(book.getSheet(0)));
	}
	
	@Test
	public void testGetTypeOfRow() throws Exception {
		assertEquals(CellsheetType.ROW, book.getTypeOf(book.getRow(0, 0)));
	}
	
	@Test
	public void testGetTypeOfCell() throws Exception {
		assertEquals(CellsheetType.CELL, book.getTypeOf(book.getCell(0, 0, 0)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetTypeOfBadType() throws Exception {
		book.getTypeOf(this);
	}
	
	@Test
	public void testTypeNameOfBook() throws Exception {
		assertEquals("Book", book.getTypeNameOf(book));
	}
	
	@Test
	public void testIsOfType() throws Exception {
		assertTrue(book.isOfType(book, CellsheetType.BOOK.getTypeName()));
	}
	
	@Test
	public void testIsOfTypeObjectDifferent() throws Exception {
		assertFalse(book.isOfType(this, CellsheetType.BOOK.getTypeName()));
	}
	
	@Test(expected = EolModelElementTypeNotFoundException.class)
	public void testIsOfTypeBadType() throws Exception {
		book.isOfType(book, "badtype");
	}
	
	@Test
	public void isOfKind() throws Exception {
		assertTrue(book.isOfType(book, CellsheetType.BOOK.getTypeName()));
	}
	
	@Test
	public void testGetAllOfTypeBook() throws Exception {
		Collection<?> all = book.getAllOfType(CellsheetType.BOOK.getTypeName());
		assertEquals(1, all.size());
		assertEquals(book, all.iterator().next());
	}
	
	@Test
	public void testGetAllOfTypeSheet() throws Exception {
		Collection<?> all = book.getAllOfType(CellsheetType.SHEET.getTypeName());
		assertEquals(2, all.size());
	}
	
	@Test
	public void testGetAllOfTypeRow() throws Exception {
		Collection<?> all = book.getAllOfType(CellsheetType.ROW.getTypeName());
		assertEquals(9, all.size());
	}
	
	@Test
	public void testGetAllOfTypeCell() throws Exception {
		Collection<?> all = book.getAllOfType(CellsheetType.CELL.getTypeName());
		assertEquals(25, all.size());
	}
	
	@Test(expected = EolModelElementTypeNotFoundException.class)
	public void testGetAllOfTypeBadType() throws Exception {
		book.getAllOfType("badtype");
	}
	
	@Test
	public void testGetAllOfKind() throws Exception {
		assertEquals(2, book.getAllOfKind(CellsheetType.SHEET.getTypeName()).size());
	}
}

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
	ExcelBook typesBook;

	@Before
	public void setup() throws Exception {
		book = new ExcelBook();
		book.setExcelFile(ExcelTestUtil.RES_PATH + ExcelTestUtil.BOOK_GENERIC);
		book.setName("Excel");
		book.load();
		
		typesBook = new ExcelBook();
		typesBook.setExcelFile(ExcelTestUtil.RES_PATH + ExcelTestUtil.BOOK_TYPES);
		typesBook.setName("Other");
		typesBook.load();
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
		assertFalse(book.owns(typesBook));
	}
	
	@Test
	public void testOwnsSheet() throws Exception {
		assertTrue(book.owns(book.getSheet(0)));
		assertFalse(book.owns(typesBook.getSheet(0)));
	}
	
	@Test
	public void testOwnsRow() throws Exception {
		assertTrue(book.owns(book.getRow(0, 0)));
		assertFalse(book.owns(typesBook.getRow(0, 0)));
	}
	
	@Test
	public void testOwnsCell() throws Exception {
		assertTrue(book.owns(book.getCell(0, 0, 0)));
		assertFalse(book.owns(typesBook.getCell(0, 0, 0)));
	}
	
	@Test
	public void testGetTypeOfBook() throws Exception {
		assertEquals(CellsheetType.BOOK, typesBook.getTypeOf(typesBook));
	}
	
	@Test
	public void testGetTypeOfSheet() throws Exception {
		assertEquals(CellsheetType.SHEET, typesBook.getTypeOf(typesBook.getSheet(0)));
	}
	
	@Test
	public void testGetTypeOfRow() throws Exception {
		assertEquals(CellsheetType.ROW, typesBook.getTypeOf(typesBook.getRow(0, 0)));
	}
	
	@Test
	public void testGetTypeOfCell() throws Exception {
		assertEquals(CellsheetType.CELL, typesBook.getTypeOf(typesBook.getCell(0, 0, 0)));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetTypeOfBadType() throws Exception {
		typesBook.getTypeOf(this);
	}
	
	@Test
	public void testTypeNameOfBook() throws Exception {
		assertEquals("Book", typesBook.getTypeNameOf(typesBook));
	}
	
	@Test
	public void testIsOfType() throws Exception {
		assertTrue(typesBook.isOfType(typesBook, CellsheetType.BOOK.getTypeName()));
	}
	
	@Test
	public void testIsOfTypeObjectDifferent() throws Exception {
		assertFalse(typesBook.isOfType(this, CellsheetType.BOOK.getTypeName()));
	}
	
	@Test(expected = EolModelElementTypeNotFoundException.class)
	public void testIsOfTypeBadType() throws Exception {
		typesBook.isOfType(typesBook, "badtype");
	}
	
	@Test
	public void isOfKind() throws Exception {
		assertTrue(typesBook.isOfType(typesBook, CellsheetType.BOOK.getTypeName()));
	}
	
	@Test
	public void testGetAllOfTypeBook() throws Exception {
		Collection<?> all = typesBook.getAllOfType(CellsheetType.BOOK.getTypeName());
		assertEquals(1, all.size());
		assertEquals(typesBook, all.iterator().next());
	}
	
	@Test
	public void testGetAllOfTypeSheet() throws Exception {
		Collection<?> all = typesBook.getAllOfType(CellsheetType.SHEET.getTypeName());
		assertEquals(5, all.size());
	}
	
	@Test
	public void testGetAllOfTypeRow() throws Exception {
		Collection<?> all = typesBook.getAllOfType(CellsheetType.ROW.getTypeName());
		assertEquals(10, all.size());
	}
	
	@Test
	public void testGetAllOfTypeCell() throws Exception {
		Collection<?> all = typesBook.getAllOfType(CellsheetType.CELL.getTypeName());
		assertEquals(20, all.size());
	}
	
	@Test(expected = EolModelElementTypeNotFoundException.class)
	public void testGetAllOfTypeBadType() throws Exception {
		typesBook.getAllOfType("badtype");
	}
	
	@Test
	public void testGetAllOfKind() throws Exception {
		assertEquals(5, typesBook.getAllOfKind(CellsheetType.SHEET.getTypeName()).size());
	}
}

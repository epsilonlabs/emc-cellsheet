package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;

import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.Type;
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
	public void testLoadProperties() throws Exception {
		final String name = "Some model name";
		final String filepath = "./resources/TestFile.xlsx";
		
		final StringProperties props = new StringProperties();
		props.setProperty(ExcelBook.EXCEL_PROPERTY_NAME, name);
		props.setProperty(ExcelBook.EXCEL_PROPERTY_FILE, filepath);
		
		final ExcelBook bookload = new ExcelBook();
		bookload.load(props);
		assertEquals(name, bookload.getName());
		assertTrue(!bookload.getAllOfKind("Cell").isEmpty());
	}
	
	@Test
	public void testSetExcelFile() {
		final String filepath = "./resources/TestFile.xlsx";		
		final ExcelBook mBook = new ExcelBook();
		assertNull(mBook.excelFile);
		mBook.setExcelFile(filepath);
		assertNotNull(mBook.excelFile);
		assertEquals(mBook.excelFile.getAbsolutePath(), new File(filepath).getAbsolutePath());
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
		assertEquals(Type.BOOK, book.getTypeOf(book));
	}
	
	@Test
	public void testGetTypeOfSheet() throws Exception {
		assertEquals(Type.SHEET, book.getTypeOf(book.getSheet(0)));
	}
	
	@Test
	public void testGetTypeOfRow() throws Exception {
		assertEquals(Type.ROW, book.getTypeOf(book.getRow(0, 0)));
	}
	
	@Test
	public void testGetTypeOfCell() throws Exception {
		assertEquals(Type.CELL, book.getTypeOf(book.getCell(0, 0, 0)));
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
		assertTrue(book.isOfType(book, Type.BOOK.getTypeName()));
	}
	
	@Test
	public void testIsOfTypeObjectDifferent() throws Exception {
		assertFalse(book.isOfType(this, Type.BOOK.getTypeName()));
	}
	
	@Test(expected = EolModelElementTypeNotFoundException.class)
	public void testIsOfTypeBadType() throws Exception {
		book.isOfType(book, "badtype");
	}
	
	@Test
	public void isOfKind() throws Exception {
		assertTrue(book.isOfType(book, Type.BOOK.getTypeName()));
	}
	
	@Test
	public void testGetAllOfTypeBook() throws Exception {
		Collection<?> all = book.getAllOfType(Type.BOOK.getTypeName());
		assertEquals(1, all.size());
		assertEquals(book, all.iterator().next());
	}
	
	@Test
	public void testGetAllOfTypeSheet() throws Exception {
		Collection<?> all = book.getAllOfType(Type.SHEET.getTypeName());
		assertEquals(2, all.size());
	}
	
	@Test
	public void testGetAllOfTypeRow() throws Exception {
		Collection<?> all = book.getAllOfType(Type.ROW.getTypeName());
		assertEquals(9, all.size());
	}
	
	@Test
	public void testGetAllOfTypeCell() throws Exception {
		Collection<?> all = book.getAllOfType(Type.CELL.getTypeName());
		assertEquals(25, all.size());
	}
	
	@Test(expected = EolModelElementTypeNotFoundException.class)
	public void testGetAllOfTypeBadType() throws Exception {
		book.getAllOfType("badtype");
	}
	
	@Test
	public void testGetAllOfKind() throws Exception {
		assertEquals(2, book.getAllOfKind(Type.SHEET.getTypeName()).size());
	}
}

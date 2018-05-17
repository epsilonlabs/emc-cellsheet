package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class ExcelBookTest {
	
	ExcelBook book;

	@Before
	public void setup() throws Exception {
		book = new ExcelBook();
		book.setExcelFile("./resources/ExcelFile.xlsx");
		book.load();
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
	
	
}

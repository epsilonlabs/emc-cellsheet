package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import static org.junit.Assert.*;

import org.eclipse.epsilon.common.util.StringProperties;
import org.junit.Test;

public class ExcelBookTest {

	
	@Test
	public void test_load_when_using_properties() throws Exception {
		StringProperties props = new StringProperties();
		props.put(ExcelBook.PROPERTY_NAME, "Cellsheet");
		props.put(ExcelBook.PROPERTY_FILE, "resources/Spreadsheet Equiv.xlsx");
		
		ExcelBook book = new ExcelBook();
		book.load(props);
		
		assertEquals(book.getName(), "Cellsheet");
		assertEquals(book.getBookname(), "Spreadsheet Equiv.xlsx");
		assertEquals(book.getSheet(0).getName(), "Assumptions");
	}
}

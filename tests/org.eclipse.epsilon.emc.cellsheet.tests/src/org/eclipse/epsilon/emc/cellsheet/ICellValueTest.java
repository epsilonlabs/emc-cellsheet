package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * <p>
 * Unit tests for default method implementations in {@link ICellValue}
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public class ICellValueTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule().silent();

	@Spy
	ICellValue<?> cellValue;

	@Spy
	ICell cell;

	@Spy
	IRow row;

	@Spy
	ISheet sheet;

	@Spy
	IBook book;

	@Before
	public void setup() {
		when(cellValue.getCell()).thenReturn(cell);

		when(cell.getRow()).thenReturn(row);
		when(cell.compareTo(cell)).thenReturn(0);

		when(row.getSheet()).thenReturn(sheet);
		when(row.compareTo(row)).thenReturn(0);

		when(sheet.getName()).thenReturn("Sheet");
		when(sheet.getBook()).thenReturn(book);
		when(sheet.compareTo(sheet)).thenReturn(0);

		when(book.getName()).thenReturn("Book.xlsx");
	}

	@Test
	public void getId_should_return_id() throws Exception {
		assertEquals("Book.xlsx/Sheet/0/0/value/", cellValue.getId());
	}

	@Test
	public void compareTo_should_return_call_parent() throws Exception {
		cellValue.compareTo(cellValue);
		verify(cell).compareTo(cell);
	}
	
	@Test
	public void getRow_should_return_parent() throws Exception {
		assertEquals(row, cellValue.getRow());
	}
	
	@Test
	public void getSheet_should_return_parent() throws Exception {
		assertEquals(sheet, cellValue.getSheet());
	}
	
	@Test
	public void getBook_should_return_parent() throws Exception {
		assertEquals(book, cellValue.getBook());
	}
}

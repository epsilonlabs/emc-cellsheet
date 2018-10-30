package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit tests for {@link ElementId}.
 * 
 * @author Jonathan Co
 *
 */
public class ElementIdTest {

	@Test(expected = IllegalArgumentException.class)
	public void toString_should_throw_exception_when_given_null_sheet_and_book() throws Exception {
		ElementId id = new ElementId(null, null, 23, 23);
		id.toString();
	}

	@Test
	public void fromString_should_return_ElementId_when_given_book_id_no_delimiter() throws Exception {
		String strId = "Test Book.xlsx";
		ElementId elementId = ElementId.fromString(strId);
		assertEquals("Test Book.xlsx", elementId.getBook());
	}

	@Test
	public void fromString_should_return_ElementId_when_given_book_id_with_delimiter() throws Exception {
		String strId = "Test Book.xlsx/";
		ElementId elementId = ElementId.fromString(strId);
		assertEquals("Test Book.xlsx", elementId.getBook());
	}

	@Test
	public void fromString_should_return_ElementId_when_given_book_id_with_delimiter_at_start() throws Exception {
		String strId = "Test Book.xlsx/";
		ElementId elementId = ElementId.fromString(strId);
		assertEquals("Test Book.xlsx", elementId.getBook());
	}

	@Test
	public void fromString_should_return_ElementId_when_given_sheet_id() throws Exception {
		String strId = "Test Book.xlsx/Test Sheet/";
		ElementId elementId = ElementId.fromString(strId);
		assertEquals("Test Book.xlsx", elementId.getBook());
		assertEquals("Test Sheet", elementId.getSheet());
	}

	@Test
	public void fromString_should_return_ElementId_when_given_sheet_id_with_no_book() throws Exception {
		String strId = "/Test Sheet/";
		ElementId elementId = ElementId.fromString(strId);
		assertEquals(null, elementId.getBook());
		assertEquals("Test Sheet", elementId.getSheet());
	}

	@Test
	public void fromString_should_return_ElementId_when_given_row_id() throws Exception {
		String strId = "Test Book.xlsx/Test Sheet/56/";
		ElementId elementId = ElementId.fromString(strId);
		assertEquals("Test Book.xlsx", elementId.getBook());
		assertEquals("Test Sheet", elementId.getSheet());
		assertEquals(56, elementId.getRow());
	}

	@Test
	public void fromString_should_return_ElementId_when_given_col_id() throws Exception {
		String strId = "Test Book.xlsx/Test Sheet/56/23/";
		ElementId elementId = ElementId.fromString(strId);
		assertEquals("Test Book.xlsx", elementId.getBook());
		assertEquals("Test Sheet", elementId.getSheet());
		assertEquals(56, elementId.getRow());
		assertEquals(23, elementId.getCol());
	}

	@Test
	public void toString_should_return_valid_id_when_given_book() throws Exception {
		ElementId id = new ElementId("Test Book.xlsx", null, -1, -1);
		assertEquals("Test Book.xlsx/", id.toString());
	}

	@Test
	public void toString_should_return_valid_id_when_given_sheet() throws Exception {
		ElementId id = new ElementId("Test Book.xlsx", "Test Sheet", -1, -1);
		assertEquals("Test Book.xlsx/Test Sheet/", id.toString());
	}

	@Test
	public void toString_should_return_valid_id_when_given_row() throws Exception {
		ElementId id = new ElementId("Test Book.xlsx", "Test Sheet", 56, -1);
		assertEquals("Test Book.xlsx/Test Sheet/56/", id.toString());
	}

	@Test
	public void toString_should_return_valid_id_when_given_col() throws Exception {
		ElementId id = new ElementId("Test Book.xlsx", "Test Sheet", 56, 23);
		assertEquals("Test Book.xlsx/Test Sheet/56/23/", id.toString());
	}
}

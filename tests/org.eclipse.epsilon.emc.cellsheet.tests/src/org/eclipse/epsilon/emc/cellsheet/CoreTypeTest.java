package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CoreTypeTest {

	@ParameterizedTest
	@CsvSource({ "Book, BOOK", "Sheet, SHEET", "Row, ROW", "Cell, CELL" })
	public void getTypename_should_return_typename_string(String typename, CoreType actual) throws Exception {
		assertEquals(typename, actual.getTypename());
	}

	@ParameterizedTest
	@CsvSource({ "Book, BOOK", "Sheet, SHEET", "Row, ROW", "Cell, CELL" })
	public void toString_should_return_typename_string(String typename, CoreType actual) throws Exception {
		assertEquals(typename, actual.toString());
	}

	@ParameterizedTest
	@CsvSource({ "BOOK, Book", "SHEET, Sheet", "ROW, Row", "CELL, Cell" })
	public void static_fromTypename_should_return_CoreType_when_given_typename(CoreType actual, String typename)
			throws Exception {
		assertEquals(actual, CoreType.fromTypename(typename));
	}

	@Test
	void static_fromTypename_should_return_null_when_given_bad_typename() throws Exception {
		assertNull(CoreType.fromTypename("Bad typename"));
	}
}

package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link ExcelSheet}
 * 
 * @author Jonathan Co
 *
 */
public class ExcelSheetTest {
	
	Set<Integer> expectedRows;		
	ExcelBook book;
	ExcelSheet sheet;

	@Before
	public void setup() throws Exception {
		this.book = ExcelTestUtil.getBook("ExcelSheetTest.xlsx");
		this.sheet = this.book.getSheet("ExcelSheetTest");
		expectedRows = new HashSet<>(Arrays.asList(0, 7, 18, 21, 27, 28));
	}

	@Test
	public void getName_should_return_ExcelSheetTest() throws Exception {
		assertEquals("ExcelSheetTest", sheet.getName());
	}

	@Test
	public void getBook_should_return_Book() throws Exception {
		assertEquals(book, sheet.getBook());
	}

	@Test
	public void getIndex_should_return_0() throws Exception {
		assertEquals(0, sheet.getIndex());
	}

	@Test
	public void getRow_should_return_ExcelRow_with_index_89() throws Exception {
		final ExcelRow row = sheet.getRow(89);
		assertEquals(89, row.getIndex());
		assertEquals(book, row.getBook());
		assertEquals(sheet, row.getSheet());
	}

	@Test
	public void rows_should_return_6_rows() throws Exception {
		final List<IRow> rows = sheet.rows();
		assertEquals(6, rows.size());
		
		for (IRow r : rows) {
			assertEquals(book, r.getBook());
			assertEquals(sheet, r.getSheet());
			assertThat(expectedRows, hasItem(r.getIndex()));
			expectedRows.remove(r.getIndex());
		}
		assertTrue(expectedRows.isEmpty());
	}
	
	@Test
	public void iterator_should_return_6_rows() throws Exception {
		Iterator<IRow> it = sheet.iterator();
		IRow r;
		while (it.hasNext()) {
			r = it.next();
			assertEquals(book, r.getBook());
			assertEquals(sheet, r.getSheet());
			assertThat(expectedRows, hasItem(r.getIndex()));
			expectedRows.remove(r.getIndex());
		}
		assertTrue(expectedRows.isEmpty());
	}
	
	@Test
	public void rowIterator_should_return_6_rows() throws Exception {
		Iterator<IRow> it = sheet.rowIterator();
		IRow r;
		while (it.hasNext()) {
			r = it.next();
			assertEquals(book, r.getBook());
			assertEquals(sheet, r.getSheet());
			assertThat(expectedRows, hasItem(r.getIndex()));
			expectedRows.remove(r.getIndex());
		}
		assertTrue(expectedRows.isEmpty());
	}

}

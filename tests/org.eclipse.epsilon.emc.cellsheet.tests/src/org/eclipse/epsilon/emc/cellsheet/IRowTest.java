package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * <p>
 * Unit tests for default method implementations in {@link IRow}
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public class IRowTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule().silent();

	@Spy
	IRow row;

	@Spy
	IRow other;

	@Spy
	ISheet sheet;

	@Spy
	IBook book;

	@Before
	public void setup() {
		when(row.getSheet()).thenReturn(sheet);
		when(other.getSheet()).thenReturn(sheet);

		when(sheet.getName()).thenReturn("Sheet");
		when(sheet.getBook()).thenReturn(book);
		when(sheet.compareTo(sheet)).thenReturn(0);

		when(book.getName()).thenReturn("Book.xlsx");
	}

	@Test
	public void compareTo_should_return_zero_when_given_same_instance() throws Exception {
		assertEquals(0, row.compareTo(row));
	}

	@Test
	public void compareTo_should_return_zero_when_given_equal_instance() throws Exception {
		when(row.getIndex()).thenReturn(45);
		when(other.getIndex()).thenReturn(45);

		assertEquals(0, row.compareTo(other));
	}

	@Test
	public void compareTo_should_return_positive_when_given_lesser_instance() throws Exception {
		when(row.getIndex()).thenReturn(100);
		when(other.getIndex()).thenReturn(50);
		assertEquals(1, row.compareTo(other));
	}

	@Test
	public void compareTo_should_return_negative_when_given_greater_instance() throws Exception {
		when(row.getIndex()).thenReturn(50);
		when(other.getIndex()).thenReturn(100);
		assertEquals(-1, row.compareTo(other));
	}

	@Test
	public void compareTo_should_return_positive_when_given_null() throws Exception {
		assertEquals(1, row.compareTo(null));
	}

	@Test
	public void compareTo_should_return_positive_when_sheet_is_greater() throws Exception {
		when(sheet.compareTo(sheet)).thenReturn(1);
		assertEquals(1, row.compareTo(other));
	}

	@Test
	public void compareTo_should_return_positive_when_sheet_is_lesser() throws Exception {
		when(sheet.compareTo(sheet)).thenReturn(-1);
		assertEquals(-1, row.compareTo(other));
	}

	@Test
	public void getType_should_return_TypeRow() throws Exception {
		mockito.silent();
		assertEquals(Type.ROW, row.getType());
	}

	@Test
	public void getKinds_should_contain_TypeRow() throws Exception {
		assertThat(Arrays.asList(row.getKinds()), hasItem(Type.ROW));
	}

	@Test
	public void getId_should_return_id() throws Exception {
		assertEquals("Book.xlsx/Sheet/0/", row.getId());
	}

	@Test
	public void getA1Ref_should_return_A() throws Exception {
		assertEquals("[Book.xlsx]'Sheet'!A$1", row.getA1Ref());
	}

	@Test
	public void getBook_should_return_parent() throws Exception {
		assertEquals(book, row.getBook());
	}

	@Test
	public void getA1Index_should_return_1() throws Exception {
		assertEquals(1, row.getA1Index());
	}
	
	@Test
	public void getA1Cell_should_delegate_with_2_when_given_C() throws Exception {
		row.getA1Cell("C");
		verify(row).getCell(2);
	}
}

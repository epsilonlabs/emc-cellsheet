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
 * Tests for default method implementations in {@link IRow}
 * 
 * @author Jonathan Co
 *
 */
public class IRowTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule().silent();

	@Spy
	IRow rowA;

	@Spy
	IRow rowB;

	@Spy
	ISheet sheet;

	@Before
	public void setup() {
		when(rowA.getSheet()).thenReturn(sheet);
		when(rowB.getSheet()).thenReturn(sheet);
		when(sheet.compareTo(sheet)).thenReturn(0);
	}

	@Test
	public void compareTo_should_return_zero_when_given_same_instance() throws Exception {
		assertEquals(0, rowA.compareTo(rowA));
	}

	@Test
	public void compareTo_should_return_zero_when_given_equal_instance() throws Exception {
		when(rowA.getIndex()).thenReturn(45);
		when(rowB.getIndex()).thenReturn(45);

		assertEquals(0, rowA.compareTo(rowB));
	}

	@Test
	public void compareTo_should_return_positive_when_given_lesser_instance() throws Exception {
		when(rowA.getIndex()).thenReturn(100);
		when(rowB.getIndex()).thenReturn(50);
		assertEquals(1, rowA.compareTo(rowB));
	}

	@Test
	public void compareTo_should_return_negative_when_given_greater_instance() throws Exception {
		when(rowA.getIndex()).thenReturn(50);
		when(rowB.getIndex()).thenReturn(100);
		assertEquals(-1, rowA.compareTo(rowB));
	}

	@Test
	public void compareTo_should_return_positive_when_given_null() throws Exception {
		assertEquals(1, rowA.compareTo(null));
	}

	@Test
	public void compareTo_should_return_positive_when_sheet_is_greater() throws Exception {
		when(sheet.compareTo(sheet)).thenReturn(1);
		assertEquals(1, rowA.compareTo(rowB));
	}

	@Test
	public void getType_should_return_TypeRow() throws Exception {
		mockito.silent();
		assertEquals(Type.ROW, rowA.getType());
	}

	@Test
	public void getKinds_should_contain_TypeRow() throws Exception {
		assertThat(Arrays.asList(rowA.getKinds()), hasItem(Type.ROW));
	}
	
	@Test
	public void getId_should_return_valid_id() throws Exception {
		IBook book = spy(IBook.class);
		when(sheet.getBook()).thenReturn(book);

		when(book.getName()).thenReturn("Test Book.xlsx");
		when(sheet.getName()).thenReturn("Test Sheet");
		when(rowA.getIndex()).thenReturn(4);
		
		assertEquals("Test Book.xlsx/Test Sheet/4/", rowA.getId());
	}
}

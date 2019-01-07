package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * <p>
 * Unit tests for default method implementations in {@link ISheet}
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public class ISheetTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule().silent();

	@Spy
	ISheet sheet;

	@Spy
	ISheet other;

	@Spy
	IBook book;

	@Before
	public void setup() {
		when(sheet.getBook()).thenReturn(book);
		when(other.getBook()).thenReturn(book);

		when(sheet.getName()).thenReturn("Sheet");
		when(other.getName()).thenReturn("Other");

		when(book.getName()).thenReturn("Book.xlsx");
	}

	@Test
	public void compareTo_should_return_zero_when_given_same_instance() throws Exception {
		assertEquals(0, sheet.compareTo(sheet));
	}

	@Test
	public void compareTo_should_return_zero_when_given_equal_instance() throws Exception {
		when(sheet.getIndex()).thenReturn(45);
		when(other.getIndex()).thenReturn(45);
		assertEquals(0, sheet.compareTo(other));
	}

	@Test
	public void compareTo_should_return_positive_when_given_lesser_instance() throws Exception {
		when(sheet.getIndex()).thenReturn(100);
		when(other.getIndex()).thenReturn(50);
		assertEquals(1, sheet.compareTo(other));
	}

	@Test
	public void compareTo_should_return_negative_when_given_greater_instance() throws Exception {
		when(sheet.getIndex()).thenReturn(50);
		when(other.getIndex()).thenReturn(100);
		assertEquals(-1, sheet.compareTo(other));
	}

	@Test
	public void compareTo_should_return_positive_when_given_null() throws Exception {
		assertEquals(1, sheet.compareTo(null));
	}

	@Test
	public void getType_should_return_TypeSheet() throws Exception {
		assertEquals(Type.SHEET, sheet.getType());
	}

	@Test
	public void getKinds_should_contain_TypeSheet() throws Exception {
		assertThat(Arrays.asList(sheet.getKinds()), hasItem(Type.SHEET));
	}

	@Test
	public void getId_should_return_id() throws Exception {
		assertEquals("Book.xlsx/Sheet/", sheet.getId());
	}

	@Test
	public void getA1Row_should_return_1_when_given_0() throws Exception {
		sheet.getA1Row(1);
		verify(sheet).getRow(0);
	}

	@Test
	public void getA1Ref_should_return_correctly() throws Exception {
		assertEquals("[Book.xlsx]'Sheet'", sheet.getA1Ref());
	}

}

package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AbstractSheetTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	AbstractSheet sheet;

	@Test
	public void getType_should_return_SHEET() throws Exception {
		assertEquals(CoreType.SHEET, sheet.getType());
	}

	@Test
	public void getKind_should_return_set_with_SHEET() throws Exception {
		final Set<ElementType> kinds = sheet.getKinds();
		assertEquals(1, kinds.size());
		assertEquals(CoreType.SHEET, kinds.iterator().next());
	}

	@Test
	public void compareTo_should_return_1_when_given_null() throws Exception {
		assertEquals(1, sheet.compareTo(null));
	}

	@Test
	public void compareTo_should_return_0_when_given_self() throws Exception {
		assertEquals(0, sheet.compareTo(sheet));
	}

	@Test
	public void compareTo_should_return_1_when_given_previous_sheet() throws Exception {
		final ISheet other = when(mock(ISheet.class).getIndex()).thenReturn(-1).getMock();
		assertEquals(1, sheet.compareTo(other));
	}

	@Test
	public void compareTo_should_return_neg1_when_given_next_sheet() throws Exception {
		final ISheet other = when(mock(ISheet.class).getIndex()).thenReturn(1).getMock();
		assertEquals(-1, sheet.compareTo(other));
	}

	@Test
	public void getId_should_return_ID_string() throws Exception {
		final IBook book = mock(IBook.class);
		when(book.getId()).thenReturn("Book1/");
		when(sheet.getBook()).thenReturn(book);
		assertEquals("Book1/0/", sheet.getId());
	}

	@Test
	public void getA1_should_return_A1_string() throws Exception {
		final IBook book = mock(IBook.class);
		when(book.getA1()).thenReturn("[Book1]");
		when(sheet.getBook()).thenReturn(book);
		when(sheet.getName()).thenReturn("Sheet1");
		assertEquals("[Book1]'Sheet1'", sheet.getA1());
	}
	
	@Test
	public void getA1Row_should_delegate_to_getRow() throws Exception {
		when(sheet.getRow(33)).thenAnswer(RETURNS_DEFAULTS);
		sheet.getA1Row(34);
		verify(sheet).getA1Row(34);
		verify(sheet).getRow(33);
	}
}

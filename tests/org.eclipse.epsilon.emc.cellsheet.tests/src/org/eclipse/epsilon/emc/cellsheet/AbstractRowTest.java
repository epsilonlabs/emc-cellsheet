package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AbstractRowTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	AbstractRow row;

	@Test
	public void getType_should_return_ROW() throws Exception {
		assertEquals(CoreType.ROW, row.getType());
	}

	@Test
	public void getKind_should_return_set_with_ROW() throws Exception {
		final Set<ElementType> kinds = row.getKinds();
		assertEquals(1, kinds.size());
		assertEquals(CoreType.ROW, kinds.iterator().next());
	}

	@Test
	public void compareTo_should_return_1_when_given_null() throws Exception {
		assertEquals(1, row.compareTo(null));
	}

	@Test
	public void compareTo_should_return_0_when_given_self() throws Exception {
		assertEquals(0, row.compareTo(row));
	}

	@Test
	public void compareTo_should_return_1_when_given_previous_row() throws Exception {
		final IRow other = when(mock(IRow.class).getIndex()).thenReturn(-1).getMock();
		when(row.getSheet()).thenAnswer(RETURNS_MOCKS);
		when(other.getSheet()).thenAnswer(RETURNS_MOCKS);
		when(row.compareTo(nullable(IRow.class))).thenCallRealMethod();
		assertEquals(1, row.compareTo(other));
	}

	@Test
	public void compareTo_should_return_neg1_when_given_next_row() throws Exception {
		final IRow other = when(mock(IRow.class).getIndex()).thenReturn(1).getMock();
		when(row.getSheet()).thenAnswer(RETURNS_MOCKS);
		when(other.getSheet()).thenAnswer(RETURNS_MOCKS);
		when(row.compareTo(nullable(IRow.class))).thenCallRealMethod();
		assertEquals(-1, row.compareTo(other));
	}
	
	@Test
	public void compareTo_should_delegate_to_parent_when_parents_different() throws Exception {
		final ISheet sheet = when(mock(ISheet.class).compareTo(nullable(ISheet.class))).thenReturn(1).getMock();
		final IRow other = when(mock(IRow.class).getSheet()).thenReturn(sheet).getMock();
		when(row.getSheet()).thenReturn(sheet);
		assertEquals(1, row.compareTo(other));
		verify(sheet).compareTo(nullable(ISheet.class));
	}

	@Test
	public void getId_should_return_ID_string() throws Exception {
		final ISheet sheet = when(mock(ISheet.class).getId()).thenReturn("Book1/Sheet1/").getMock();
		when(row.getSheet()).thenReturn(sheet);
		assertEquals("Book1/Sheet1/0/", row.getId());
		verify(sheet).getId();
	}

	@Test
	public void getA1_should_return_A1_string() throws Exception {
		final ISheet sheet = when(mock(ISheet.class).getA1()).thenReturn("[Book1]'Sheet1'").getMock();
		when(row.getSheet()).thenReturn(sheet);
		assertEquals("[Book1]'Sheet1'!A$1", row.getA1());
		verify(sheet).getA1();
	}
	
	@Test
	public void getA1Cell_should_delegate_to_getCell() throws Exception {
		when(row.getCell(1)).thenAnswer(RETURNS_DEFAULTS);
		row.getA1Cell("B");
		verify(row).getA1Cell("B");
		verify(row).getCell(1);
	}
	
	@Test
	public void getBook_should_delegate_to_getSheet() throws Exception {
		when(row.getSheet()).thenAnswer(RETURNS_MOCKS);
		assertNotNull(row.getBook());
		verify(row).getSheet();
	}

}

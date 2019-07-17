package org.eclipse.epsilon.labs.emc.cellsheet;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AbstractCellTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	AbstractCell cell;

	@Test
	public void getType_should_return_CELL() throws Exception {
		assertEquals(CoreType.CELL, cell.getType());
	}

	@Test
	public void getKind_should_return_set_with_CELL() throws Exception {
		final Set<ElementType> kinds = cell.getKinds();
		assertEquals(1, kinds.size());
		assertEquals(CoreType.CELL, kinds.iterator().next());
	}

	@Test
	public void compareTo_should_return_1_when_given_null() throws Exception {
		assertEquals(1, cell.compareTo(null));
	}

	@Test
	public void compareTo_should_return_0_when_given_self() throws Exception {
		assertEquals(0, cell.compareTo(cell));
	}

	@Test
	public void compareTo_should_return_1_when_given_previous_cell() throws Exception {
		final ICell other = when(mock(ICell.class).getColIndex()).thenReturn(-1).getMock();
		when(cell.getRow()).thenAnswer(RETURNS_MOCKS);
		when(other.getRow()).thenAnswer(RETURNS_MOCKS);
		when(cell.compareTo(nullable(ICell.class))).thenCallRealMethod();
		assertEquals(1, cell.compareTo(other));
	}

	@Test
	public void compareTo_should_return_neg1_when_given_next_cell() throws Exception {
		final ICell other = when(mock(ICell.class).getColIndex()).thenReturn(1).getMock();
		when(cell.getRow()).thenAnswer(RETURNS_MOCKS);
		when(other.getRow()).thenAnswer(RETURNS_MOCKS);
		when(cell.compareTo(nullable(ICell.class))).thenCallRealMethod();
		assertEquals(-1, cell.compareTo(other));
	}

	@Test
	public void compareTo_should_delegate_to_parent_when_parents_different() throws Exception {
		final IRow row = when(mock(IRow.class).compareTo(nullable(IRow.class))).thenReturn(1).getMock();
		final ICell other = when(mock(ICell.class).getRow()).thenReturn(row).getMock();
		when(cell.getRow()).thenReturn(row);
		assertEquals(1, cell.compareTo(other));
		verify(row).compareTo(nullable(IRow.class));
	}

	@Test
	public void getA1_should_return_A1_string() throws Exception {
		final ISheet sheet = when(mock(ISheet.class).getA1()).thenReturn("[Book1]'Sheet1").getMock();
		doReturn(sheet).when(cell).getSheet();
		doReturn("D").when(cell).getA1Col();
		doReturn(6).when(cell).getA1Row();
		assertEquals("[Book1]'Sheet1!D6", cell.getA1());
	}

	@Test
	public void getId_should_return_ID_string() throws Exception {
		final IRow row = when(mock(IRow.class).getId()).thenReturn("Book1/Sheet1/23/").getMock();
		when(cell.getRow()).thenReturn(row);
		when(cell.getColIndex()).thenReturn(42);
		assertEquals("Book1/Sheet1/23/42/", cell.getId());
	}

	@Test
	public void getSheet_should_delegate_to_getRow() throws Exception {
		final IRow row = when(mock(IRow.class).getSheet()).thenAnswer(RETURNS_MOCKS).getMock();
		when(cell.getRow()).thenReturn(row);
		assertNotNull(cell.getSheet());
		verify(cell).getRow();
	}

	@Test
	public void getBook_should_delegate_to_getSheet() throws Exception {
		final ISheet sheet = when(mock(ISheet.class).getBook()).thenAnswer(RETURNS_MOCKS).getMock();
		doReturn(sheet).when(cell).getSheet();
		assertNotNull(cell.getBook());
		verify(cell).getSheet();
	}

	@Test
	public void getA1Row_should_delegate_to_parent_row() throws Exception {
		final IRow row = when(mock(IRow.class).getA1Index()).thenReturn(4).getMock();
		doReturn(row).when(cell).getRow();
		assertEquals(4, cell.getA1Row());
	}

	@Test
	public void getA1Col_should_delegate_to_get() throws Exception {
		when(cell.getColIndex()).thenReturn(2);
		assertEquals("C", cell.getA1Col());
	}
	
	@Test
	public void iterator_should_return_singleton_iterator() throws Exception {
		when(cell.getCellValue()).thenAnswer(RETURNS_MOCKS);
		final Iterator<ICellValue> it = cell.iterator();
		assertNotNull(it.next());
		assertFalse(it.hasNext());
	}
}

package org.eclipse.epsilon.labs.emc.cellsheet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AbstractCellValueTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Mock(answer = Answers.CALLS_REAL_METHODS)
	AbstractCellValue cellValue;

	@Test
	public void getKinds_should_return_set_with_CELL_VALUE() throws Exception {
		assertTrue(cellValue.getKinds().contains(CoreType.CELL_VALUE));
	}

	@Test
	public void compareTo_should_delegate_to_parent_cell() throws Exception {
		final ICell cell = mock(ICell.class);
		when(cellValue.getCell()).thenReturn(cell);
		cellValue.compareTo(cellValue);
		verify(cell).compareTo(cell);
	}

	@Test
	public void getRow_should_delegate_to_getCell() throws Exception {
		final ICell cell = when(mock(ICell.class).getRow()).thenAnswer(RETURNS_MOCKS).getMock();
		when(cellValue.getCell()).thenReturn(cell);
		cellValue.getRow();
		verify(cellValue).getCell();
	}

	@Test
	public void getSheet_should_delegate_to_getCell() throws Exception {
		final ICell cell = when(mock(ICell.class).getSheet()).thenAnswer(RETURNS_MOCKS).getMock();
		when(cellValue.getCell()).thenReturn(cell);
		cellValue.getSheet();
		verify(cellValue).getCell();
	}

	@Test
	public void getBook_should_delegate_to_getCell() throws Exception {
		final ICell cell = when(mock(ICell.class).getBook()).thenAnswer(RETURNS_MOCKS).getMock();
		when(cellValue.getCell()).thenReturn(cell);
		cellValue.getBook();
		verify(cellValue).getCell();
	}

	@Test
	public void getId_should_return_ID_string() throws Exception {
		final ICell cell = when(mock(ICell.class).getId()).thenReturn("Book1/Sheet1/23/42/").getMock();
		when(cellValue.getCell()).thenReturn(cell);
		assertEquals("Book1/Sheet1/23/42/value/", cellValue.getId());
	}

	@Test
	public void iterator_should_return_singleton_iterator() throws Exception {
		when(cellValue.getAst()).thenAnswer(RETURNS_MOCKS);
		final Iterator<IAst<?>> it = cellValue.iterator();
		assertNotNull(it.next());
		assertFalse(it.hasNext());
	}

	@Test
	public void setType_should_change_type() throws Exception {
		cellValue.setType(CellValueType.NUMERIC);
		assertEquals(CellValueType.NUMERIC, cellValue.getType());
	}
}

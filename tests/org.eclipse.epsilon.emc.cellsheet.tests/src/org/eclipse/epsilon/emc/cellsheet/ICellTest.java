package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
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
 * Unit tests for default method implementations in {@link ICell}
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public class ICellTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule().silent();

	@Spy
	ICell cell;

	@Spy
	ICell other;

	@Spy
	IRow row;

	@Spy
	ISheet sheet;

	@Spy
	IBook book;

	@Before
	public void setup() {
		when(cell.getRow()).thenReturn(row);
		when(other.getRow()).thenReturn(row);

		when(row.getSheet()).thenReturn(sheet);
		when(row.compareTo(row)).thenReturn(0);

		when(sheet.getName()).thenReturn("Sheet");
		when(sheet.getBook()).thenReturn(book);
		when(sheet.compareTo(sheet)).thenReturn(0);

		when(book.getName()).thenReturn("Book.xlsx");
	}

	@Test
	public void getA1Col_should_return_A() throws Exception {
		assertEquals("A", cell.getA1Col());
	}

	@Test
	public void getA1RowIndex_should_return_1() throws Exception {
		assertEquals(1, cell.getA1RowIndex());
	}

	@Test
	public void getSheet_should_return_parent() throws Exception {
		assertEquals(sheet, cell.getSheet());
	}

	@Test
	public void getBook_should_return_parent() throws Exception {
		assertEquals(book, cell.getBook());
	}

	@Test
	public void compareTo_should_return_zero_when_given_same_instance() throws Exception {
		assertEquals(0, cell.compareTo(cell));
	}

	@Test
	public void compareTo_should_return_zero_when_given_equal_instance() throws Exception {
		when(cell.getColIndex()).thenReturn(45);
		when(other.getColIndex()).thenReturn(45);
		assertEquals(0, cell.compareTo(other));
	}

	@Test
	public void compareTo_should_return_positive_when_given_lesser_instance() throws Exception {
		when(cell.getColIndex()).thenReturn(100);
		when(other.getColIndex()).thenReturn(50);
		assertEquals(1, cell.compareTo(other));
	}

	@Test
	public void compareTo_should_return_negative_when_given_greater_instance() throws Exception {
		when(cell.getColIndex()).thenReturn(50);
		when(other.getColIndex()).thenReturn(100);
		assertEquals(-1, cell.compareTo(other));
	}

	@Test
	public void compareTo_should_return_positive_when_given_null() throws Exception {
		assertEquals(1, cell.compareTo(null));
	}

	@Test
	public void compareTo_should_return_positive_when_sheet_is_greater() throws Exception {
		when(row.compareTo(row)).thenReturn(1);
		assertEquals(1, cell.compareTo(other));
	}

	@Test
	public void getType_should_return_TypeSheet() throws Exception {
		assertEquals(Type.CELL, cell.getType());
	}

	@Test
	public void getKinds_should_contain_TypeSheet() throws Exception {
		assertThat(Arrays.asList(cell.getKinds()), hasItem(Type.CELL));
	}

	@Test
	public void getId_should_return_id() throws Exception {
		assertEquals("Book.xlsx/Sheet/0/0/", cell.getId());
	}

	@Test
	public void getA1Ref_should_return_A1() throws Exception {
		assertEquals("[Book.xlsx]'Sheet'!A1", cell.getA1Ref());
	}

	@Test
	public void getBooleanCellValue_should_return_when_does_contain_BooleanCellValue() throws Exception {
		ICellValue<?> cellValue = spy(IBooleanCellValue.class);
		doReturn(cellValue).when(cell).getCellValue();
		assertEquals(cellValue, cell.getBooleanCellValue());
	}

	@Test(expected = Exception.class)
	public void getBooleanCellValue_should_throw_exception_when_does_not_contain_BooleanCellValue() throws Exception {
		ICellValue<?> cellValue = spy(IStringCellValue.class);
		doReturn(cellValue).when(cell).getCellValue();
		cell.getBooleanCellValue();
	}

	@Test
	public void getFormulaCellValue_should_return_when_does_contain_FormulaCellValue() throws Exception {
		ICellValue<?> cellValue = spy(IFormulaCellValue.class);
		doReturn(cellValue).when(cell).getCellValue();
		assertEquals(cellValue, cell.getFormulaCellValue());
	}

	@Test(expected = Exception.class)
	public void getFormulaCellValue_should_throw_exception_when_does_not_contain_FormulaCellValue() throws Exception {
		ICellValue<?> cellValue = spy(INumericCellValue.class);
		doReturn(cellValue).when(cell).getCellValue();
		cell.getFormulaCellValue();
	}

	@Test
	public void getStringCellValue_should_return_when_does_contain_StringCellValue() throws Exception {
		ICellValue<?> cellValue = spy(IStringCellValue.class);
		doReturn(cellValue).when(cell).getCellValue();
		assertEquals(cellValue, cell.getStringCellValue());
	}

	@Test(expected = Exception.class)
	public void getStringCellValue_should_throw_exception_when_does_not_contain_StringCellValue() throws Exception {
		ICellValue<?> cellValue = spy(IBooleanCellValue.class);
		doReturn(cellValue).when(cell).getCellValue();
		cell.getStringCellValue();
	}

	@Test
	public void getNumericCellValue_should_return_when_does_contain_NumericCellValue() throws Exception {
		ICellValue<?> cellValue = spy(INumericCellValue.class);
		doReturn(cellValue).when(cell).getCellValue();
		assertEquals(cellValue, cell.getNumericCellValue());
	}

	@Test(expected = Exception.class)
	public void getNumericCellValue_should_throw_exception_when_does_not_contain_NumericCellValue() throws Exception {
		ICellValue<?> cellValue = spy(IStringCellValue.class);
		doReturn(cellValue).when(cell).getCellValue();
		cell.getNumericCellValue();
	}
}

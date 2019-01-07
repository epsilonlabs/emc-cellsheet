package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * <p>
 * Unit tests for default method implementations in {@link IFormulaTree}
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public class IFormulaTreeTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule().silent();

	@Spy
	IFormulaTree tree;

	@Spy
	IFormulaCellValue value;

	@Spy
	ICell cell;

	@Spy
	IRow row;

	@Spy
	ISheet sheet;

	@Spy
	IBook book;

	@Before
	public void setup() {
		when(tree.getChildren()).then(Mockito.RETURNS_SMART_NULLS);
		when(tree.getCellValue()).thenReturn(value);

		when(value.getCell()).thenReturn(cell);

		when(cell.getRow()).thenReturn(row);
		when(cell.compareTo(cell)).thenReturn(0);

		when(row.getSheet()).thenReturn(sheet);
		when(row.compareTo(row)).thenReturn(0);

		when(sheet.getName()).thenReturn("Sheet");
		when(sheet.getBook()).thenReturn(book);
		when(sheet.compareTo(sheet)).thenReturn(0);

		when(book.getName()).thenReturn("Book.xlsx");
	}

	@Test
	public void getType_should_return_TypeFromulaTree() throws Exception {
		assertEquals(Type.UNKNOWN_NODE, tree.getType());
	}

	@Test
	public void getKinds_should_contain_TypeFormulaTree() throws Exception {
		List<Type> kinds = Arrays.asList(tree.getKinds());
		assertThat(kinds, hasItem(Type.UNKNOWN_NODE));		
		assertThat(kinds, hasItem(Type.FORMULA_TREE));
	}
	
	@Test
	public void getCell_should_return_parent() throws Exception {
		assertEquals(cell, tree.getCell());
	}

	@Test
	public void getRow_should_return_parent() throws Exception {
		assertEquals(row, tree.getRow());
	}

	@Test
	public void getSheet_should_return_parent() throws Exception {
		assertEquals(sheet, tree.getSheet());
	}

	@Test
	public void getBook_should_return_parent() throws Exception {
		assertEquals(book, tree.getBook());
	}

	@Test
	public void isRoot_should_return_true_by_default() throws Exception {
		assertTrue(tree.isRoot());
	}
	
	@Test
	public void isRoot_should_return_false_when_has_a_parent() throws Exception {
		when(tree.getParent()).thenAnswer(Mockito.RETURNS_MOCKS);
		assertFalse(tree.isRoot());
	}
	
	@Test
	public void isLeaf_should_return_true_when_empty_children() throws Exception {
		assertTrue(tree.isLeaf());
	}
	
}

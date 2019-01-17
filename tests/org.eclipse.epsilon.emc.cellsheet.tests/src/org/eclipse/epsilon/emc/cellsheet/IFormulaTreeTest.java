package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
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
	
	@Test
	public void formatAsTree_should_return_correct_string() throws Exception {
		IFormulaTree arg1 = spy(IFormulaTree.class);
		IFormulaTree arg2 = spy(IFormulaTree.class);
		
		when(arg1.getParent()).thenReturn(tree);
		when(arg2.getParent()).thenReturn(tree);
		
		when(tree.getFormula()).thenReturn("4*5");
		
		when(tree.getToken()).thenReturn("*");
		when(arg1.getToken()).thenReturn("4");
		when(arg2.getToken()).thenReturn("5");
		
		when(tree.getType()).thenReturn(Type.OPERATOR_NODE);
		when(arg1.getType()).thenReturn(Type.NUMERIC_VALUE_NODE);
		when(arg2.getType()).thenReturn(Type.NUMERIC_VALUE_NODE);
		
		when(tree.getChildren()).thenReturn(Arrays.asList(arg1, arg2));
		
		String expected = 
				"4*5\n"
				+ "└── * : OperatorNode\n"
				+ "    ├── 4 : NumericValueNode\n"
				+ "    └── 5 : NumericValueNode\n";
				
		assertEquals(expected, tree.formatAsTree());
	}
	
}

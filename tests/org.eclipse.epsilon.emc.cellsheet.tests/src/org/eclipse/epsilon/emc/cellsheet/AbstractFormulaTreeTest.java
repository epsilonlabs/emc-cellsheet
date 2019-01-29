package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.LinkedList;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AbstractFormulaTreeTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Spy()
	AbstractFormulaTree tree;
	
	@Test
	public void getCellValue_should_get_assigned_when_root() throws Exception {
		final IFormulaCellValue cellValue = mock(IFormulaCellValue.class);
		tree.cellValue = cellValue;
		assertTrue(tree.isRoot());
		assertEquals(cellValue, tree.getCellValue());
	}
	
	@Test
	public void getCellValue_should_get_assigned_when_child() throws Exception {
		final IFormulaCellValue cellValue = mock(IFormulaCellValue.class);
		final AbstractFormulaTree parent = spy(AbstractFormulaTree.class);
		parent.cellValue = cellValue;
		
		parent.addChild(tree);
		assertFalse(tree.isRoot());
		assertEquals(cellValue, tree.getCellValue());
	}
	
	@Test
	public void setCellValue() throws Exception {
		final IFormulaCellValue cellValue = mock(IFormulaCellValue.class);
		assertNotSame(cellValue, tree.cellValue);
		tree.setCellValue(cellValue);
		assertEquals(cellValue, tree.cellValue);
	}

	@Test
	public void getParent_should_return_assigned_parent() throws Exception {
		final IFormulaTree parent = mock(IFormulaTree.class);
		tree.parent = parent;
		assertEquals(parent, tree.getParent());
	}
	
	@Test
	public void setParent() throws Exception {
		final IFormulaTree parent = mock(IFormulaTree.class);
		assertNotSame(parent, tree.parent);
		tree.setParent(parent);
		assertEquals(parent, tree.parent);
	}

	@Test
	public void getToken_should_return_assigned_token() throws Exception {
		final Token token = new Token();
		tree.token = token;
		assertEquals(token, tree.getToken());
	}
	
	@Test
	public void setToken() throws Exception {
		final Token token = new Token();
		assertNotSame(token, tree.token);
		tree.setToken(token);
		assertEquals(token, tree.token);
	}

	@Test
	public void getChildren_should_return_assigned_list() throws Exception {
		LinkedList<IFormulaTree> list = new LinkedList<>();
		tree.children = list;
		assertEquals(list, tree.getChildren());
	}
	
	@Test
	public void toString_should_return_correct_format() throws Exception {
		final Token token = new Token();
		tree.setToken(token);
		assertEquals(token.toString(), tree.toString());
	}
}

package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.eclipse.epsilon.emc.cellsheet.Token.TokenSubtype;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenType;
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
	public MockitoRule mockito = MockitoJUnit.rule();

	@Spy
	IFormulaTree tree;

	@Test
	public void getValue_should_return_test_string() throws Exception {
		when(tree.getToken()).thenReturn(new Token("test", TokenType.OPERAND, TokenSubtype.TEXT));
		assertEquals("test", tree.getValue());
	}
	
	@Test
	public void setValue_should_set_token_value() throws Exception {
		Token token = new Token("test", TokenType.OPERAND, TokenSubtype.TEXT);
		when(tree.getToken()).thenReturn(token);
		assertEquals("test", token.getValue());
		
		tree.setValue("changed");
		assertEquals("changed", token.getValue());
	}

	@Test
	public void getType_should_return_Type_OPERAND_when_token_has_OPERAND_type() throws Exception {
		when(tree.getToken()).thenReturn(new Token("+", TokenType.OPERAND, TokenSubtype.MATH));
		assertEquals(Type.OPERAND, tree.getType());
	}

	@Test
	public void getSubtype_should_return_Type_START_when_token_has_START_subtype() throws Exception {
		when(tree.getToken()).thenReturn(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		assertEquals(Type.START, tree.getSubtype());
	}

	@Test
	public void fromType_should_return_Type_OPERAND_when_given_TokenType_OPERAND() throws Exception {
		assertEquals(Type.OPERAND, IFormulaTree.fromTokenType(TokenType.OPERAND));
	}
	
	@Test
	public void setType_should_set_token_type() throws Exception {
		Token token = new Token("test", TokenType.OPERAND, TokenSubtype.TEXT);
		when(tree.getToken()).thenReturn(token);
		assertEquals(TokenType.OPERAND, token.getType());
		
		tree.setType(Type.FUNCTION);
		assertEquals(TokenType.FUNCTION, token.getType());
	}

	@Test
	public void fromType_should_return_Type_START_when_given_TokenSubtype_START() throws Exception {
		assertEquals(Type.START, IFormulaTree.fromTokenSubtype(TokenSubtype.START));
	}

	@Test
	public void toTokenType_should_return_TokenType_OPERAND_when_given_Type_OPERAND() throws Exception {
		assertEquals(TokenType.OPERAND, IFormulaTree.toTokenType(Type.OPERAND));
	}

	@Test
	public void toTokenSubtype_should_return_TokenSubtype_START_when_given_Type_START() throws Exception {
		assertEquals(TokenSubtype.START, IFormulaTree.toTokenSubtype(Type.START));
	}
	
	@Test
	public void setSubtype_should_set_token_subtype() throws Exception {
		Token token = new Token("test", TokenType.OPERAND, TokenSubtype.TEXT);
		when(tree.getToken()).thenReturn(token);
		assertEquals(TokenSubtype.TEXT, token.getSubtype());
		
		tree.setSubtype(Type.LOGICAL);
		assertEquals(TokenSubtype.LOGICAL, token.getSubtype());
	}

	@Test
	public void getKinds_should_return_UNKNOWN_NOTHING_FORMULA_TREE_types() throws Exception {
		when(tree.getToken()).thenReturn(new Token());
		assertArrayEquals(new Type[] { Type.UNKNOWN, Type.NOTHING, Type.FORMULA_TREE }, tree.getKinds());
	}

	@Test
	public void isRoot_should_return_true_when_parent_is_null() throws Exception {
		assertTrue(tree.isRoot());
	}

	@Test
	public void isRoot_should_return_false_when_parent_is_not_null() throws Exception {
		when(tree.getParent()).thenAnswer(Mockito.RETURNS_MOCKS);
		assertFalse(tree.isRoot());
	}

	@Test
	public void isLeaf_should_return_true_when_children_is_null() throws Exception {
		when(tree.getChildren()).thenReturn(null);
		assertTrue(tree.isLeaf());
	}

	@Test
	public void isLeaf_should_return_true_when_children_is_empty() throws Exception {
		when(tree.getChildren()).then(Mockito.RETURNS_DEFAULTS);
		assertTrue(tree.isLeaf());
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void isLeaf_should_return_false_when_children_is_not_empty() throws Exception {
		List children = mock(List.class);
		when(children.isEmpty()).thenReturn(false);
		when(tree.getChildren()).thenReturn(children);
		assertFalse(tree.isLeaf());
	}
	
	@Test
	public void addChild_should_add_child_to_parent_and_set_parent() throws Exception {
		IFormulaTree child = mock(IFormulaTree.class);
		tree.addChild(child);
		
		verify(child).setParent(tree);
	}
	
}

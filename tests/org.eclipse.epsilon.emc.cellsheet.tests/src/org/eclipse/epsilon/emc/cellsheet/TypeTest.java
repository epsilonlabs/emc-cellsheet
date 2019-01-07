package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * <p>
 * Unit tests for helper methods in {@link Type}
 * </p>
 * 
 * @author Jonathan Co
 */
public class TypeTest {

	@Test
	public void fromName_should_return_FORMULA_TREE_enum_when_given_FormulaTree() throws Exception {
		assertEquals(Type.FORMULA_TREE, Type.fromName("FormulaTree"));
	}

	@Test
	public void fromName_should_return_null_when_given_unknown_string() throws Exception {
		assertEquals(null, Type.fromName("NotARealType"));
	}

	@Test
	public void getName_should_return_FormulaTree_when_given_FORMULA_TREE_enum() throws Exception {
		assertEquals("FormulaTree", Type.FORMULA_TREE.getName());
	}

	@Test
	public void toString_should_return_FormulaTree_when_given_FORMULA_TREE_enum() throws Exception {
		assertEquals("FormulaTree", Type.FORMULA_TREE.toString());
	}

}

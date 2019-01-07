package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * <p>
 * Unit tests for default method implementations in {@link IFormulaCellValue}
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public class IFormulaCellValueTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule().silent();

	@Spy
	IFormulaCellValue value;
	
	@Mock
	IFormulaTree tree;
	
	@Before
	public void setup() {
		when(value.getFormulaTree()).thenReturn(tree);
	}

	@Test
	public void getType_should_return_FORMULA_CELL_VALUE_enum() throws Exception {
		assertEquals(Type.FORMULA_CELL_VALUE, value.getType());
	}

	@Test
	public void getKinds_should_contain_FORMULA_CELL_VALUE_enum() throws Exception {
		assertThat(Arrays.asList(value.getKinds()), hasItem(Type.FORMULA_CELL_VALUE));
	}
	
	@Test
	public void formatAsTree_should_delegate_to_formulaTree() throws Exception {
		value.formatAsTree();
		verify(tree).formatAsTree();
	}

}

package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
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

	String formula = "=IF(OR(COUNTBLANK([@[products]])>0,ISNA(VLOOKUP([@[products]],IBANRef!$A:$G,7,FALSE))),VLOOKUP([Level],LevelRef!$A:$B,2,FALSE),VLOOKUP([@[products]],IBANRef!$A:$G,7,FALSE))";

	@Before
	public void setup() {

	}

	@Test
	public void getType_should_return_FORMULA_CELL_VALUE_enum() throws Exception {
		assertEquals(Type.FORMULA_CELL_VALUE, value.getType());
	}

	@Test
	public void getKinds_should_contain_FORMULA_CELL_VALUE_enum() throws Exception {
		assertThat(Arrays.asList(value.getKinds()), hasItem(Type.FORMULA_CELL_VALUE));
	}

//	@Test
//	public void formatAsTree_should_delegate_to_formulaTree() throws Exception {
//		value.formatAsTree();
//		verify(tree).formatAsTree();
//	}

}

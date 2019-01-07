package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * <p>
 * Unit tests for default method implementations in {@link IBlankCellValue}
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public class IBlankCellValueTest {

	@Rule
	public MockitoRule mockito = MockitoJUnit.rule();

	@Spy
	IBlankCellValue value;

	@Test
	public void getType_should_return_TypeBlankCellValue() throws Exception {
		assertEquals(Type.BLANK_CELL_VALUE, value.getType());
	}

	@Test
	public void getKinds_should_contain_TypeBlankCellValue_and_TypeCellValue() throws Exception {
		assertThat(Arrays.asList(value.getKinds()), hasItems(Type.BLANK_CELL_VALUE, Type.CELL_VALUE));
	}

}

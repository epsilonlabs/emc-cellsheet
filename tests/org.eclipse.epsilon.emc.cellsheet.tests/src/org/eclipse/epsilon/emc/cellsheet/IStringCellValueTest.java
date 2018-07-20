package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Tests for default method implementations in {@link IStringCellValue}
 * 
 * @author Jonathan Co
 *
 */
public class IStringCellValueTest {

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule();

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  IStringCellValue value;

  @Test
  public void getType_should_return_TypeStringCellValue() throws Exception {
    assertEquals(Type.STRING_CELL_VALUE, value.getType());
  }

  @Test
  public void getKinds_should_contain_TypeStringCellValue_and_TypeCellValue() throws Exception {
    assertThat(Arrays.asList(value.getKinds()), hasItems(Type.STRING_CELL_VALUE, Type.CELL_VALUE));
  }

}

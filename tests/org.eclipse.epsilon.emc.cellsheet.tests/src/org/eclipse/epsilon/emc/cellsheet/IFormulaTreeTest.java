package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
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
 * Tests for default method implementations in {@link IFormulaTree}
 * 
 * @author Jonathan Co
 *
 */
public class IFormulaTreeTest {

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule();

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  IFormulaTree value;

  @Test
  public void getType_should_return_TypeFromulaTree() throws Exception {
    assertEquals(Type.FORMULA_TREE, value.getType());
  }

  @Test
  public void getKinds_should_contain_TypeFormulaTree() throws Exception {
    assertThat(Arrays.asList(value.getKinds()), hasItem(Type.FORMULA_TREE));
  }

}

package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Tests for default method implementations in {@link ISheet}
 * 
 * @author Jonathan Co
 *
 */
public class ISheetTest {

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule();

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  ISheet sheetA;

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  ISheet sheetB;

  @Test
  public void compareTo_should_return_zero_when_given_same_instance() throws Exception {
    assertEquals(0, sheetA.compareTo(sheetA));
  }

  @Test
  public void compareTo_should_return_zero_when_given_equal_instance() throws Exception {
    when(sheetA.getIndex()).thenReturn(45);
    when(sheetB.getIndex()).thenReturn(45);
    assertEquals(0, sheetA.compareTo(sheetB));
  }

  @Test
  public void compareTo_should_return_positive_when_given_lesser_instance() throws Exception {
    when(sheetA.getIndex()).thenReturn(100);
    when(sheetB.getIndex()).thenReturn(50);
    assertEquals(1, sheetA.compareTo(sheetB));
  }

  @Test
  public void compareTo_should_return_negative_when_given_greater_instance() throws Exception {
    when(sheetA.getIndex()).thenReturn(50);
    when(sheetB.getIndex()).thenReturn(100);
    assertEquals(-1, sheetA.compareTo(sheetB));
  }

  @Test
  public void compareTo_should_return_positive_when_given_null() throws Exception {
    assertEquals(1, sheetA.compareTo(null));
  }

  @Test
  public void getType_should_return_TypeSheet() throws Exception {
    assertEquals(Type.SHEET, sheetA.getType());
  }

  @Test
  public void getKinds_should_contain_TypeSheet() throws Exception {
    assertThat(Arrays.asList(sheetA.getKinds()), hasItem(Type.SHEET));
  }

}

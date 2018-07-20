package org.eclipse.epsilon.emc.cellsheet;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * Tests for default method implementations in {@link ICell}
 * 
 * @author Jonathan Co
 *
 */
public class ICellTest {

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule().silent();

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  ICell cellA;

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  ICell cellB;

  @Mock
  IRow row;

  @Mock
  ISheet sheet;

  @Before
  public void setup() {
    when(cellA.getRow()).thenReturn(row);
    when(cellB.getRow()).thenReturn(row);
    when(row.compareTo(row)).thenReturn(0);
    when(row.getSheet()).thenReturn(sheet);
    when(sheet.compareTo(sheet)).thenReturn(0);
  }

  @Test
  public void compareTo_should_return_zero_when_given_same_instance() throws Exception {
    assertEquals(0, cellA.compareTo(cellA));
  }

  @Test
  public void compareTo_should_return_zero_when_given_equal_instance() throws Exception {
    when(cellA.getColIndex()).thenReturn(45);
    when(cellB.getColIndex()).thenReturn(45);
    assertEquals(0, cellA.compareTo(cellB));
  }

  @Test
  public void compareTo_should_return_positive_when_given_lesser_instance() throws Exception {
    when(cellA.getColIndex()).thenReturn(100);
    when(cellB.getColIndex()).thenReturn(50);
    assertEquals(1, cellA.compareTo(cellB));
  }

  @Test
  public void compareTo_should_return_negative_when_given_greater_instance() throws Exception {
    when(cellA.getColIndex()).thenReturn(50);
    when(cellB.getColIndex()).thenReturn(100);
    assertEquals(-1, cellA.compareTo(cellB));
  }

  @Test
  public void compareTo_should_return_positive_when_given_null() throws Exception {
    assertEquals(1, cellA.compareTo(null));
  }

  @Test
  public void compareTo_should_return_positive_when_sheet_is_greater() throws Exception {
    when(row.compareTo(row)).thenReturn(1);
    assertEquals(1, cellA.compareTo(cellB));
  }

  @Test
  public void getType_should_return_TypeSheet() throws Exception {
    assertEquals(Type.CELL, cellA.getType());
  }

  @Test
  public void getKinds_should_contain_TypeSheet() throws Exception {
    assertThat(Arrays.asList(cellA.getKinds()), hasItem(Type.CELL));
  }

}

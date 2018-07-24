package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class AbstractIdResolverTest {

  public static final String BOOK_NAME = "TestBook";
  public static final String SHEET_NAME = "TestSheet";
  public static final int ROW_INDEX = 45;
  public static final String COLUMN_INDEX = "Y";

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule().silent();

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  AbstractIdResolver resolver;

  @Mock
  IBook book;

  @Mock
  ISheet sheet;

  @Mock
  IRow row;

  @Mock
  ICell cell;

  @Before
  public void setup() {
    when(book.getName()).thenReturn(BOOK_NAME);

    when(sheet.getName()).thenReturn(SHEET_NAME);
    when(sheet.getBook()).thenReturn(book);

    when(row.getIndex()).thenReturn(ROW_INDEX);
    when(row.getSheet()).thenReturn(sheet);
    when(row.getBook()).thenReturn(book);

    when(cell.getCol()).thenReturn(COLUMN_INDEX);
    when(cell.getRowIndex()).thenReturn(ROW_INDEX);
    when(cell.getRow()).thenReturn(row);
    when(cell.getSheet()).thenReturn(sheet);
    when(cell.getBook()).thenReturn(book);
  }

  @Test
  public void getId_should_return_id_when_given_book() throws Exception {
    assertEquals("[" + BOOK_NAME + "]", resolver.getId(book));
  }

  @Test
  public void getId_should_return_id_when_given_sheet() throws Exception {
    assertEquals("[" + BOOK_NAME + "]'" + SHEET_NAME + "'", resolver.getId(sheet));
  }

  @Test
  public void getId_should_return_id_when_given_row() throws Exception {
    assertEquals("[" + BOOK_NAME + "]'" + SHEET_NAME + "'!A$" + (ROW_INDEX + 1), resolver.getId(row));
  }

  @Test
  public void getId_should_return_id_when_given_cell() throws Exception {
    assertEquals("[" + BOOK_NAME + "]'" + SHEET_NAME + "'!" + COLUMN_INDEX + (ROW_INDEX + 1),
        resolver.getId(cell));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getId_should_throw_exception_when_given_sheet_with_null_book() throws Exception {
    when(sheet.getBook()).thenReturn(null);
    resolver.getId(sheet);
  }
}

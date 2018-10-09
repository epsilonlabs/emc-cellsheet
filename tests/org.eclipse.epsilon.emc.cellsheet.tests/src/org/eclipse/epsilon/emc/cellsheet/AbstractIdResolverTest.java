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
  public static final int ROW = 45;
  public static final String COL = "Y";

  @Rule
  public MockitoRule mockito = MockitoJUnit.rule().silent();

  @Mock(answer = Answers.CALLS_REAL_METHODS)
  IdUtil resolver;

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
    when(book.getSheet(SHEET_NAME)).thenReturn(sheet);
    when(book.getRow(SHEET_NAME, ROW)).thenReturn(row);
    when(book.getCell(SHEET_NAME, ROW, COL)).thenReturn(cell);

    when(sheet.getName()).thenReturn(SHEET_NAME);
    when(sheet.getBook()).thenReturn(book);

    when(row.getIndex()).thenReturn(ROW);
    when(row.getSheet()).thenReturn(sheet);
    when(row.getBook()).thenReturn(book);

    when(cell.getCol()).thenReturn(COL);
    when(cell.getRowIndex()).thenReturn(ROW);
    when(cell.getRow()).thenReturn(row);
    when(cell.getSheet()).thenReturn(sheet);
    when(cell.getBook()).thenReturn(book);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getElementById_should_throw_exception_when_given_bad_id() throws Exception {
    resolver.getElementById(book, "[]");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getElementById_should_throw_exception_when_given_bad_cell_id() throws Exception {
    resolver.getElementById(book,
        "[" + BOOK_NAME + "]'" + SHEET_NAME + "'!AAA");
  }

  @Test(expected = IllegalArgumentException.class)
  public void getElementById_should_throw_exception_when_given_null_book() throws Exception {
    resolver.getElementById(null,
        "[" + BOOK_NAME + "]'" + SHEET_NAME + "'!" + COL + (ROW + 1));
  }

  @Test
  public void getElementById_should_return_book_when_given_book_id() throws Exception {
    assertEquals(book, resolver.getElementById(book, "[" + BOOK_NAME + "]"));
  }

  @Test
  public void getElementById_should_return_null_when_given_different_book_id() throws Exception {
    assertNull(resolver.getElementById(book, "[anotherbook]"));
  }

  @Test
  public void getElementById_should_return_sheet_when_given_sheet_id() throws Exception {
    assertEquals(sheet, resolver.getElementById(book, "[" + BOOK_NAME + "]'" + SHEET_NAME + "'"));
  }

  @Test
  public void getElementById_should_return_row_when_given_row_id() throws Exception {
    assertEquals(row, resolver.getElementById(book,
        "[" + BOOK_NAME + "]'" + SHEET_NAME + "'!" + COL + "$" + (ROW + 1)));
  }

  @Test
  public void getElementById_should_return_cell_when_given_cell_id() throws Exception {
    assertEquals(cell, resolver.getElementById(book,
        "[" + BOOK_NAME + "]'" + SHEET_NAME + "'!" + COL + (ROW + 1)));
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
    assertEquals("[" + BOOK_NAME + "]'" + SHEET_NAME + "'!A$" + (ROW + 1),
        resolver.getId(row));
  }

  @Test
  public void getId_should_return_id_when_given_cell() throws Exception {
    assertEquals("[" + BOOK_NAME + "]'" + SHEET_NAME + "'!" + COL + (ROW + 1),
        resolver.getId(cell));
  }

  @Test(expected = IllegalArgumentException.class)
  public void getId_should_throw_exception_when_given_sheet_with_null_book() throws Exception {
    when(sheet.getBook()).thenReturn(null);
    resolver.getId(sheet);
  }
}

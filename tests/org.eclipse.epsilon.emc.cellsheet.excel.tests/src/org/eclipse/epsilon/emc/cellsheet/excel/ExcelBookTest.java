package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.io.File;
import java.util.Collection;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.emc.cellsheet.Type;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mockito;

/**
 * Unit tests for {@link ExcelBook}
 * 
 * @author Jonathan Co
 *
 */
public class ExcelBookTest {

  ExcelBook book;
  ExcelBook other;

  @Before
  public void setup() throws Exception {
    book = ExcelTestUtil.getBook(ExcelBookTest.class);
    other = ExcelTestUtil.getBook("Formula.xlsx");
  }

  @Test
  public void testLoadProperties() throws Exception {
    final String name = "Some model name";
    final String filepath = "./resources/ExcelBookTest.xlsx";

    final StringProperties props = new StringProperties();
    props.setProperty(ExcelBook.EXCEL_PROPERTY_NAME, name);
    props.setProperty(ExcelBook.EXCEL_PROPERTY_FILE, filepath);

    final ExcelBook bookload = new ExcelBook();
    bookload.load(props);
    assertEquals(name, bookload.getName());
    assertTrue(!bookload.getAllOfKind("Cell").isEmpty());
  }

  @Test
  @Ignore
  public void testSetExcelFile() {
    final String filepath = "./resources/TestFile.xlsx";
    final ExcelBook mBook = new ExcelBook();
    assertNull(mBook.excelFile);
    mBook.setExcelFile(filepath);
    assertNotNull(mBook.excelFile);
    assertEquals(mBook.excelFile.getAbsolutePath(), new File(filepath).getAbsolutePath());
  }

  @Test(expected = IllegalArgumentException.class)
  @Ignore
  public void testSetExcelFileBad() {
    final String filepath = "badFilePath";
    final ExcelBook mBook = new ExcelBook();
    assertNull(mBook.excelFile);
    mBook.setExcelFile(filepath);
  }
  
  @Test(expected = IndexOutOfBoundsException.class)
  public void getCell_should_throw_exception_when_given_negative_column_index() throws Exception {
    ExcelRow row = Mockito.mock(ExcelRow.class);
    when(row.getBook()).thenReturn(book);
    book.getCell(row, -100);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getCell_should_throw_exception_when_given_null_row() throws Exception {
    book.getCell(null, 100);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getCell_should_throw_exception_when_given_row_that_is_not_excel() throws Exception {
    IRow row = Mockito.mock(IRow.class, Answers.RETURNS_MOCKS);
    book.getCell(row, 100);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getCell_should_throw_exception_when_given_row_from_other_book() throws Exception {
    ExcelRow row = Mockito.mock(ExcelRow.class, Answers.RETURNS_MOCKS);
    book.getCell(row, 100);
  }
  
  @Test(expected = IndexOutOfBoundsException.class)
  public void getRow_should_throw_exception_when_given_negative_row_index() throws Exception {
    ExcelSheet sheet = mock(ExcelSheet.class);
    book.getRow(sheet, -4549);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getRow_should_throw_exception_when_given_non_ExcelSheet() throws Exception {
    ISheet sheet = mock(ISheet.class);
    book.getRow(sheet, 48);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getRow_should_throw_exception_when_given_sheet_from_other_book() throws Exception {
    ExcelSheet sheet = mock(ExcelSheet.class, Answers.RETURNS_MOCKS);
    book.getRow(sheet, 48);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void getRow_should_throw_exception_when_given_null_sheet() throws Exception {
    book.getRow((ExcelSheet) null, 48);
  }

  @Test
  public void owns_should_return_true_when_given_Book_from_same_model() throws Exception {
    assertTrue(book.owns(book));
  }

  @Test
  public void owns_should_return_true_when_given_Sheet_from_same_model() throws Exception {
    assertTrue(book.owns(book.getSheet(0)));
  }

  @Test
  public void owns_should_return_true_when_given_Row_same_model() throws Exception {
    assertTrue(book.owns(book.getRow(0, 0)));
  }

  @Test
  public void owns_should_return_true_when_given_Cell_same_model() throws Exception {
    assertTrue(book.owns(book.getCell(0, 0, 0)));
  }

  @Test
  public void owns_should_return_false_when_given_unsupported_class() throws Exception {
    assertFalse(book.owns(this));
  }

  @Test
  public void owns_should_return_false_when_given_Book_from_other_model() throws Exception {
    assertFalse(book.owns(other));
  }

  @Test
  public void owns_should_return_false_when_given_Sheet_from_other_model() throws Exception {
    assertFalse(book.owns(other.getSheet(0)));
  }

  @Test
  public void owns_should_return_false_when_given_Row_from_other_model() throws Exception {
    assertFalse(book.owns(other.getRow(0, 0)));
  }

  @Test
  public void owns_should_return_false_when_given_Cell_from_other_model() throws Exception {
    assertFalse(book.owns(other.getCell(0, 0, 0)));
  }

  @Test
  public void getAllOfType_should_return_Book_when_given_TypeBook() throws Exception {
    Collection<?> all = book.getAllOfType(Type.BOOK.getTypeName());
    assertEquals(1, all.size());
    assertEquals(book, all.iterator().next());
  }

  @Test
  public void getAllOfType_should_return_Sheet_when_given_TypeSheet() throws Exception {
    Collection<?> all = book.getAllOfType(Type.SHEET.getTypeName());
    assertEquals(2, all.size());
  }

  @Test
  public void getAllOfType_should_return_Row_when_given_TypeRow() throws Exception {
    Collection<?> all = book.getAllOfType(Type.ROW.getTypeName());
    assertEquals(9, all.size());
  }

  @Test
  public void getAllOfType_should_return_Cell_when_given_TypeCell() throws Exception {
    Collection<?> all = book.getAllOfType(Type.CELL.getTypeName());
    assertEquals(25, all.size());
  }

  @Test(expected = EolModelElementTypeNotFoundException.class)
  public void getAllOfType_should_throw_EolModelElementTypeNotFoundException_when_unknown_type_is_given()
      throws Exception {
    book.getAllOfType("badtype");
  }

  @Test
  public void getRow_should_return_empty_ExcelRow_when_given_row_index_is_empty() throws Exception {
    final ExcelSheet sheet = book.getSheet("Data");
    final ExcelRow row = book.getRow(sheet, 12345);
    assertEquals(12345, row.getIndex());
    assertTrue(row.cells().isEmpty());
  }

  @Test
  public void getElementId_should_return_book_id_when_given_book() throws Exception {
    assertEquals("[" + book.getName() + "]", book.getElementId(book));
  }

  @Test
  public void getElementId_should_return_sheet_id_when_given_sheet() throws Exception {
    final ExcelSheet sheet = book.getSheet("Data");
    assertEquals("[ExcelBookTest.xlsx]Data", book.getElementId(sheet));
  }

  @Test
  public void getElementId_should_return_row_id_when_given_row() throws Exception {
    final ExcelRow row = book.getRow("Data", 3);
    assertEquals("[ExcelBookTest.xlsx]Data!A$4", book.getElementId(row));
  }

  @Test
  public void getElementId_should_return_cell_id_when_given_cell() throws Exception {
    final ExcelCell cell = book.getCell("Data", 3, 0);
    assertEquals("[ExcelBookTest.xlsx]Data!A4", book.getElementId(cell));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getElementId_should_throw_excpetion_when_given_unsupported_type() throws Exception {
    book.getElementId(book.getCell("Data", 3, 0).getValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getElementId_should_throw_excpetion_when_given_unknown_type() throws Exception {
    book.getElementId(new Object());
  }

  @Test(expected = UnsupportedOperationException.class)
  public void setElementId_should_throw_exception_when_called() throws Exception {
    book.setElementId(null, null);
  }
  
  @Test
  public void getElementById_should_return_book_when_given_book_id() throws Exception {
    final String id = "[ExcelBookTest.xlsx]";
    assertEquals(id, book.getId());
    assertEquals(book, book.getElementById(id));
  }
  
  @Test
  public void getElementById_should_return_sheet_when_given_sheet_id() throws Exception {
    final String id = "[ExcelBookTest.xlsx]Data";
    final ExcelSheet sheet = book.getSheet("Data");
    assertEquals(id, sheet.getId());
    assertEquals(sheet, book.getElementById(id));
  }
  
  @Test
  public void getElementById_should_return_row_when_given_row_id() throws Exception {
    final String id = "[ExcelBookTest.xlsx]Data!A$60";
    final ExcelRow row = book.getRow("Data", 59);
    assertEquals(id, row.getId());
    assertEquals(row, book.getElementById(id));
  }

  @Test
  public void getElementById_should_return_cell_when_given_cell_id() throws Exception {
    final String id = "[ExcelBookTest.xlsx]Data!A4";
    final ExcelCell cell = book.getCell("Data", 3, 0);
    assertEquals(id, cell.getId());
    assertEquals(cell, book.getElementById(id));
  }

  @Test(expected = UnsupportedOperationException.class)
  public void getEnumerationValue_should_throw_exception_when_called() throws Exception {
    book.getEnumerationValue(null, null);
  }

  @Test
  public void testGetAllOfKind() throws Exception {
    assertEquals(2, book.getAllOfKind(Type.SHEET.getTypeName()).size());
  }
}

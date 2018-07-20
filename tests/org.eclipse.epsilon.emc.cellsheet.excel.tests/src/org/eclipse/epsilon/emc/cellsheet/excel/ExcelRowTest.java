package org.eclipse.epsilon.emc.cellsheet.excel;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for {@link ExcelRow}.
 * 
 * @author Jonathan Co
 *
 */
public class ExcelRowTest {

  ExcelBook book;
  ExcelRow row;

  @Before
  public void setup() throws Exception {
    this.book = ExcelTestUtil.getBook("ExcelRowTest.xlsx");
    this.row = this.book.getRow("ExcelRowTest", 3);
  }

  @Test
  public void getBook_should_return_correct_instance() throws Exception {
    assertEquals(book, row.getBook());
  }

  @Test
  public void getIndex_should_return_correct_index() throws Exception {
    assertEquals(3, row.getIndex());
  }

  @Test
  public void iterator_should_return_iterator_with_all_cells_in_row() throws Exception {
    final List<Integer> expected = Arrays.asList(0, 1, 2);
    final Iterator<ICell> it = row.iterator();
    ICell cell;
    while (it.hasNext()) {
      cell = it.next();
      assertEquals(row, cell.getRow());
      assertEquals(3, cell.getRowIndex());
      assertTrue(expected.contains(cell.getColIndex()));
    }
  }

  @Test
  public void cellIterator_should_return_iterator_with_all_cells_in_row() throws Exception {
    final List<Integer> expected = Arrays.asList(0, 1, 2);
    final Iterator<ExcelCell> it = row.cellIterator();
    ICell cell;
    while (it.hasNext()) {
      cell = it.next();
      assertEquals(row, cell.getRow());
      assertEquals(3, cell.getRowIndex());
      assertTrue(expected.contains(cell.getColIndex()));
    }
  }

  @Test
  public void cells_should_return_List_with_all_cells_in_row() throws Exception {
    final List<Integer> expected = Arrays.asList(0, 1, 2);
    for (ExcelCell cell : row.cells()) {
      assertEquals(row, cell.getRow());
      assertEquals(3, cell.getRowIndex());
      assertTrue(expected.contains(cell.getColIndex()));
    }
  }

  @Test
  public void getCell_should_return_cell_when_given_index() throws Exception {
    assertEquals(3, row.getCell(2).getRowIndex());
    assertEquals(2, row.getCell(2).getColIndex());
  }
}

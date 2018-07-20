package org.eclipse.epsilon.emc.cellsheet;

/**
 * Interfacing defining how IDs are resolved for Cellsheet model elements.
 * 
 * IDs take the form of {@code [ModelName]SheetName!Column($)Row}. Examples include:
 * <ul>
 * <li>{@code [Finance2018]} => refers to an {@link IBook} named Finance2018</li>
 * <li>{@code [Finance2018]Quarter 1} => refers to an {@link ISheet} named Quarter 1 in the model
 * Finance2018</li>
 * <li>{@code [Finance2018]Quarter 1!GH56} => refers to an {@link ICell} with address GH56 in the
 * sheet Quarter 1 in the model Finance2018</li> *
 * <li>{@code [Finance2018]Quarter 1!GH$56} => refers to an {@link IRow} with index 56 in the sheet
 * Quarter 1 in the model Finance2018. Note he use of {@code $} before the row index used to denote
 * a lock on the row</li>
 * 
 * </ul>
 * 
 * @author Jonathan Co
 *
 */
public interface IIdResolver {

  /**
   * Returns the ID of a book in the form of {@code [ModelName]}.
   * 
   * @param book The book to get the ID of
   * @return the ID of the book
   */
  String getId(IBook book);

  /**
   * Returns the ID of a sheet in the form of {@code [ModelName]SheetName}.
   * 
   * @param sheet The sheet to get the ID of
   * @return the ID of the sheet
   */
  String getId(ISheet sheet);

  /**
   * Returns the ID of a row in the form of {@code [ModelName]SheetName!A$RowIndex}.
   * 
   * @param row The row to get the ID of
   * @return the ID of the row
   */
  String getId(IRow row);

  /**
   * Returns the ID of a cell in the form of {@code [ModelName]SheetName!ColumnRowIndex}.
   * 
   * @param cell The cell to get the ID of
   * @return the ID of the cell
   */
  String getId(ICell cell);

  /**
   * Retrieves the model element with the given id from a book.
   * 
   * @param book The book to look in
   * @param id The id of the model element to retrieve
   * @return a model element that has the given id or {@code null} if it does not exist.
   */
  HasType getElementById(IBook book, String id);

}

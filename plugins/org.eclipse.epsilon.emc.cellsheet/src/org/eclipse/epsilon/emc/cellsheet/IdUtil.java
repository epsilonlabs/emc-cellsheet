package org.eclipse.epsilon.emc.cellsheet;

/**
 * ID Utility for Cellsheet model elements. Contains methods for building and
 * resolving model element IDs
 * 
 * IDs take the form of {@code [ModelName]SheetName!Column($)Row}. Examples
 * include:
 * <ul>
 * <li>{@code [Finance2018]} => refers to an {@link IBook} named
 * Finance2018</li>
 * <li>{@code [Finance2018]Quarter 1} => refers to an {@link ISheet} named
 * Quarter 1 in the model Finance2018</li>
 * <li>{@code [Finance2018]Quarter 1!GH56} => refers to an {@link ICell} with
 * address GH56 in the sheet Quarter 1 in the model Finance2018</li> *
 * <li>{@code [Finance2018]Quarter 1!GH$56} => refers to an {@link IRow} with
 * index 56 in the sheet Quarter 1 in the model Finance2018. Note he use of
 * {@code $} before the row index used to denote a lock on the row</li>
 * 
 * </ul>
 * 
 * @author Jonathan Co
 *
 */
public class IdUtil {
	public static final char DELIMITER = '/';
	
	public static final int BOOK_IDX = 0;
	public static final int SHEET_IDX = 1;
	public static final int ROW_IDX = 2;
	public static final int COL_IDX = 3;
	public static final int VALUE_IDX = 4;
	public static final int TREE_IDX = 5;
	public static final int TOKEN_IDX = 6;

	
}

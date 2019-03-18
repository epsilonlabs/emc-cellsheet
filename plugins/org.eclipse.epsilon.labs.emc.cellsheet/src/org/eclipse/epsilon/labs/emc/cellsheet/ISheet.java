package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * Sheets are core structural model elements that represent a single sheet in a
 * workbook.
 * </p>
 * 
 * <p>
 * Rows can be identified by using a tuple consisting of:
 * <ul>
 * <li>Book</li>
 * <li>Sheet Index/Name</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Each sheet contains a list of rows which are ordered but may not be
 * contiguous i.e. the sheet may have rows 1,2, 6. Rows may be missing from this
 * list as they are blank/contain no meaningful values and are blank.
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface ISheet extends HasId, HasA1, Comparable<ISheet>, Iterable<IRow> {

	public static final ElementType TYPE = CoreType.SHEET;
	public static final Set<ElementType> KINDS = new HashSet<>(Arrays.asList(TYPE));

	@Override
	default public ElementType getType() {
		return TYPE;
	}

	@Override
	default public Set<ElementType> getKinds() {
		return KINDS;
	}

	/**
	 * <p>
	 * Get the 0-based index of this sheet as defined by it's parent workbook
	 * </p>
	 * 
	 * @return the 0-based index of this sheet
	 */
	public int getIndex();

	/**
	 * <p>
	 * Get the name of this sheet as defined by it's parent workbook
	 * </p>
	 * 
	 * @return the name of this sheet as defined within it's workbook
	 */
	public String getName();

	/**
	 * <p>
	 * Get and retrieve a row within this sheet by it's 0-based index.
	 * </p>
	 * 
	 * <p>
	 * An {@link IRow} object should always be returned by this method even if the
	 * cells in the row contain only blank values.
	 * </p>
	 * 
	 * @param row 0-based index of the row to retrieve
	 * @return row corresponding to the given index
	 */
	public IRow getRow(int row);

	/**
	 * <p>
	 * Convenience method for getting and retrieving a row within this sheet by it's
	 * 1-based/A1 style index.
	 * </p>
	 * 
	 * <p>
	 * An {@link IRow} object should always be returned by this method even if the
	 * cells in the row contain only blank values.
	 * </p>
	 * 
	 * @param row 1-based/A1 style index of the row to retrieve
	 * @return row corresponding to the given index
	 */
	public IRow getA1Row(int row);

	/**
	 * <p>
	 * Get a list of all rows in this sheet.
	 * </p>
	 * 
	 * @return all rows in this sheet.
	 */
	public List<IRow> rows();

}

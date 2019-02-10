package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

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
	default IRow getA1Row(int row) {
		return getRow(row - 1);
	}

	/**
	 * <p>
	 * Get a list of all rows in this sheet.
	 * </p>
	 * 
	 * @return all rows in this sheet.
	 */
	public List<IRow> rows();

	/**
	 * <p>
	 * Get the {@link IBook} that this sheet is contained within.
	 * 
	 * @return the parent book of this sheet
	 */
	public IBook getBook();

	@Override
	default int compareTo(ISheet o) {
		if (null == o)
			return 1;
		if (this == o)
			return 0;
		return Integer.compare(this.getIndex(), o.getIndex());
	}

	@Override
	default ElementType getType() {
		return CoreType.SHEET;
	}

	@Override
	default ElementType[] getKinds() {
		return new ElementType[] { getType() };
	}

	@Override
	default String getId() {
		return getBook().getId() + getName() + "/";
	}

	@Override
	default String getA1Ref() {
		return getBook().getA1Ref() + "'" + getName() + "'";
	}
}

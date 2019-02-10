package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

/**
 * <p>
 * Rows are core structural model elements that represent a single row in a
 * workbook.
 * </p>
 * 
 * <p>
 * Rows can be identified by using a tuple consisting of:
 * <ul>
 * <li>Book</li>
 * <li>Sheet Index/Name</li>
 * <li>Row Index</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Each row contains a list of cells which are ordered but may not be contiguous
 * i.e. the row may have cells with column indexes A, B, E. Cells may be missing
 * from this list as they are blank/contain no meaningful values and are blank.
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface IRow extends HasId, HasA1, Comparable<IRow>, Iterable<ICell> {

	/**
	 * <p>
	 * Get the 0-based index of this row as defined by it's parent sheet
	 * </p>
	 * 
	 * @return the 0-based index of this row
	 */
	public int getIndex();

	/**
	 * <p>
	 * Get the 1-based/A1 style index of this row as defined by it's parent sheet
	 * </p>
	 * 
	 * @return the 1-based/A1 style index of this row
	 */
	default int getA1Index() {
		return getIndex() + 1;
	}

	/**
	 * <p>
	 * Get and retrieve a cell within this row by it's 0-based column index.
	 * </p>
	 * 
	 * <p>
	 * An {@link ICell} object should always be returned by this method even if the
	 * cell is blank.
	 * </p>
	 * 
	 * @param col 0-based index column of the cell to retrieve.
	 * @return cell corresponding to the given column index.
	 */
	public ICell getCell(int col);

	/**
	 * <p>
	 * Get and retrieve a cell within this row by it's Alpha-based/A1 style column
	 * index.
	 * </p>
	 * 
	 * <p>
	 * An {@link ICell} object should always be returned by this method even if the
	 * cell is blank.
	 * </p>
	 * 
	 * @param col Alpha-based/A1 style column of the cell to retrieve.
	 * @return cell corresponding to the given column index.
	 */
	default ICell getA1Cell(String column) {
		return getCell(ReferenceUtil.a1ToIndex(column));
	}

	/**
	 * <p>
	 * Get a list of all cells in this row.
	 * </p>
	 * 
	 * @return all cells in this row.
	 */
	public List<? extends ICell> cells();

	/**
	 * <p>
	 * Get the {@link ISheet} that this row is contained within.
	 * 
	 * @return the parent sheet of this row
	 */
	public ISheet getSheet();

	/**
	 * <p>
	 * Get the {@link IBook} that this row is contained within.
	 * 
	 * @return the parent book of this row
	 */
	default IBook getBook() {
		return getSheet().getBook();
	}

	@Override
	default int compareTo(IRow o) {
		if (null == o)
			return 1;
		if (this == o)
			return 0;

		int parent = this.getSheet().compareTo(o.getSheet());
		return parent == 0 ? Integer.compare(this.getIndex(), o.getIndex()) : parent;
	}

	@Override
	default ElementType getType() {
		return CoreType.ROW;
	}

	@Override
	default ElementType[] getKinds() {
		return new ElementType[] { getType() };
	}

	@Override
	default String getId() {
		return getSheet().getId() + getIndex() + "/";
	}

	@Override
	default String getA1Ref() {
		return getSheet().getA1Ref() + "!A$" + (getIndex() + 1);
	}
}

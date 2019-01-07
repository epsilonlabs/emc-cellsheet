package org.eclipse.epsilon.emc.cellsheet;

/**
 * <p>
 * Cell are core structural model elements representing discrete cells within a workbook.
 * </p>
 * 
 * <p>
 * Cells can be identified by using a tuple consisting of:
 * <ul>
 * <li>Book</li>
 * <li>Sheet Index/Name</li>
 * <li>Row Index</li>
 * <li>Column Index/Alpha</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Each Cell contains a {@link ICellValue} that represents the contents of the
 * cell. This can be one of {@link IBlankCellValue}, {@link IErrorCellValue},
 * {@link IBooleanCellValue}, {@link INumericCellValue}, {@link IDateCellValue},
 * {@link IStringCellValue} or {@link IFormulaCellValue}.
 * <p>
 * 
 * @author Jonathan Co
 *
 */
public interface ICell extends HasId, HasA1, Comparable<ICell> {

	/**
	 * <p>
	 * Model element type: {@link Type#CELL}
	 * </p>
	 */
	public static final Type TYPE = Type.CELL;

	/**
	 * <p>
	 * Model element kinds: [{@link Type#CELL}]
	 * </p>
	 */
	public static final Type[] KINDS = { TYPE };

	/**
	 * @return 0-based column index of this Cell
	 */
	public int getColIndex();

	/**
	 * @return Alpha-based column reference of this Cell
	 */
	default String getA1Col() {
		return ReferenceUtil.indexToA1(getColIndex());
	}

	/**
	 * @return 0-based row index of this Cell
	 */
	public int getRowIndex();

	/**
	 * @return A1 style row index of this Cell (1-based index)
	 */
	default int getA1RowIndex() {
		return getRowIndex() + 1;
	}

	/**
	 * @return the value element of this Cell
	 */
	public ICellValue<?> getCellValue();

	/**
	 * @return the value of this cell as a boolean
	 * @throws IllegalArgumentException if the cell value is not a boolean
	 */
	default IBooleanCellValue getBooleanCellValue() {
		ICellValue<?> value = getCellValue();
		if (!(value instanceof IBooleanCellValue)) {
			throw new IllegalArgumentException(toString() + " - Does not contain a Boolean value");
		}
		return (IBooleanCellValue) value;
	}

	/**
	 * @return the value of this cell as a formula
	 * @throws IllegalArgumentException if the cell value is not a formula
	 */
	default IFormulaCellValue getFormulaCellValue() {
		ICellValue<?> value = getCellValue();
		if (!(value instanceof IFormulaCellValue)) {
			throw new IllegalArgumentException(toString() + " - Does not contain a Formula value");
		}
		return (IFormulaCellValue) value;
	}

	/**
	 * @return the value of this cell as a String
	 * @throws IllegalArgumentException if the cell value is not a String
	 */
	default IStringCellValue getStringCellValue() {
		ICellValue<?> value = getCellValue();
		if (!(value instanceof IStringCellValue)) {
			throw new IllegalArgumentException(toString() + " - Does not contain a Formula value");
		}
		return (IStringCellValue) value;
	}

	/**
	 * @return the value of this cell as a number
	 * @throws IllegalArgumentException if the cell value is not a number
	 */
	default INumericCellValue getNumericCellValue() {
		ICellValue<?> value = getCellValue();
		if (!(value instanceof INumericCellValue)) {
			throw new IllegalArgumentException(toString() + " - Does not contain a Formula value");
		}
		return (INumericCellValue) value;
	}

	/**
	 * @return {@code true} if the cell contains no value, {@code false} otherwise
	 */
	public boolean isBlank();

	/**
	 * @return the Row this Cell belongs to
	 */
	public IRow getRow();

	/**
	 * @return the Sheet this Cell belongs to
	 */
	default ISheet getSheet() {
		return getRow().getSheet();
	}

	/**
	 * @return the Book this Cell belongs to
	 */
	default IBook getBook() {
		return getSheet().getBook();
	}

	@Override
	default int compareTo(ICell o) {
		if (null == o)
			return 1;
		if (this == o)
			return 0;

		int parent = this.getRow().compareTo(o.getRow());
		return parent == 0 ? Integer.compare(this.getColIndex(), o.getColIndex()) : parent;
	}

	@Override
	default Type getType() {
		return ICell.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return ICell.KINDS;
	}

	@Override
	default String getId() {
		return getRow().getId() + getColIndex() + "/";
	}

	@Override
	default String getA1Ref() {
		StringBuilder sb = new StringBuilder(getSheet().getA1Ref());
		sb.append("!");
		sb.append(getA1Col());
		sb.append(getRowIndex() + 1);
		return sb.toString();
	}

}

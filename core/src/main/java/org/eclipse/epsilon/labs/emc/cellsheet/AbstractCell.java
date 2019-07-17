package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractCell implements ICell {

	protected IRow row;
	protected ICellValue cellValue;
	protected int col;

	protected AbstractCell() {
		;
	}

	protected AbstractCell(AbstractCell.Builder<?, ?> b) {
		this.row = b.row;
		this.col = b.col;
	}

	@Override
	public ISheet getSheet() {
		return getRow().getSheet();
	}

	@Override
	public IBook getBook() {
		return getSheet().getBook();
	}

	@Override
	public IRow getRow() {
		return this.row;
	}
	
	@Override
	public int getRowIndex() {
		return this.row.getIndex();
	}

	@Override
	public int getColIndex() {
		return this.col;
	}

	@Override
	public ICellValue getCellValue() {
		if (cellValue == null) {
			this.cellValue = initCellValue();
		}
		return this.cellValue;
	}
	
	protected abstract ICellValue initCellValue();

	@Override
	public int getA1Row() {
		return getRow().getA1Index();
	}

	@Override
	public String getA1Col() {
		return ReferenceUtil.indexToA1(getColIndex());
	}

	@Override
	public int compareTo(ICell o) {
		if (null == o)
			return 1;
		if (this == o)
			return 0;

		int parent = getRow().compareTo(o.getRow());
		return parent == 0 ? Integer.compare(this.getColIndex(), o.getColIndex()) : parent;
	}

	@Override
	public String getId() {
		return getRow().getId() + getColIndex() + "/";
	}

	@Override
	public String getA1() {
		return String.format("%s!%s%d", getSheet().getA1(), getA1Col(), getA1Row());
	}

	@Override
	public Iterator<ICellValue> iterator() {
		return Collections.singleton(getCellValue()).iterator();
	}

	@Override
	public String toString() {
		return buildToString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getColIndex(), getRow());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCell other = (AbstractCell) obj;
		return getColIndex() == other.getColIndex() // Column index
				&& Objects.equals(getRow(), other.getRow()); // Parents
	}

	@SuppressWarnings("unchecked")
	public static abstract class Builder<T extends AbstractCell, B extends Builder<T, B>> {

		protected IRow row;
		protected int col;

		public B withRow(IRow row) {
			this.row = row;
			return (B) this;
		}

		public B withCol(int col) {
			this.col = col;
			return (B) this;
		}

		public abstract T build();
	}
}

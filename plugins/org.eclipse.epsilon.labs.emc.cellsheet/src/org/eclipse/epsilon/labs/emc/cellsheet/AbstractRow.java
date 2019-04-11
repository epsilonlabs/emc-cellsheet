package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Objects;

public abstract class AbstractRow implements IRow {

	protected ISheet sheet;
	protected int index;

	protected AbstractRow() {
		;
	}

	protected AbstractRow(AbstractRow.Builder<?, ?> b) {
		this.sheet = b.sheet;
		this.index = b.index;
	}

	@Override
	public IBook getBook() {
		return getSheet().getBook();
	}
	
	@Override
	public ISheet getSheet() {
		return sheet;
	}
	
	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public int getA1Index() {
		return getIndex() + 1;
	}

	@Override
	public ICell getA1Cell(String column) {
		return getCell(ReferenceUtil.a1ToIndex(column));
	}

	@Override
	public int compareTo(IRow o) {
		if (null == o)
			return 1;
		if (this == o)
			return 0;

		int parent = this.getSheet().compareTo(o.getSheet());
		return parent == 0 ? Integer.compare(this.getIndex(), o.getIndex()) : parent;
	}

	@Override
	public String getId() {
		return getSheet().getId() + getIndex() + "/";
	}

	@Override
	public String getA1() {
		return String.format("%s!A$%d", getSheet().getA1(), getA1Index());
	}

	@Override
	public String toString() {
		return buildToString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIndex(), getSheet());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractRow other = (AbstractRow) obj;
		return getIndex() == other.getIndex() // Row index
				&& Objects.equals(getSheet(), other.getSheet()); // Parents
	}

	@SuppressWarnings("unchecked")
	public static abstract class Builder<T extends AbstractRow, B extends Builder<T, B>> {

		protected ISheet sheet;
		protected int index;

		public Builder() {
			;
		}

		public Builder(T row) {
			this.sheet = row.sheet;
			this.index = row.index;
		}

		public B withSheet(ISheet sheet) {
			this.sheet = sheet;
			return (B) this;
		}

		public B withIndex(int index) {
			this.index = index;
			return (B) this;
		}

		public abstract T build();
	}

}

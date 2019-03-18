package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractCell implements ICell {

	@Override
	public ISheet getSheet() {
		return getRow().getSheet();
	}

	@Override
	public IBook getBook() {
		return getSheet().getBook();
	}

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

		int parent = this.getRow().compareTo(o.getRow());
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

}

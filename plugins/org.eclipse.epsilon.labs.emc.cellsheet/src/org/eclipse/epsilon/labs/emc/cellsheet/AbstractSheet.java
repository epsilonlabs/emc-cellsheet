package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Objects;

public abstract class AbstractSheet implements ISheet {

	@Override
	public IRow getA1Row(int row) {
		return getRow(row - 1);
	}

	@Override
	public int compareTo(ISheet o) {
		if (null == o)
			return 1;
		if (this == o)
			return 0;
		return Integer.compare(getIndex(), o.getIndex());
	}

	@Override
	public String getId() {
		return getBook().getId() + getIndex() + "/";
	}

	@Override
	public String getA1() {
		return String.format("%s'%s'", getBook().getA1(), getName());
	}

	@Override
	public String toString() {
		return buildToString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(getIndex(), getName(), getBook());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSheet other = (AbstractSheet) obj;
		return getIndex() == other.getIndex() // Sheet index
				&& Objects.equals(getName(), other.getName()) // Sheet name
				&& Objects.equals(getBook(), other.getBook()); // Parents
	}
}

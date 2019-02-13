package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractSheet implements ISheet {

	@Override
	public IRow getA1Row(int row) {
		return getRow(row - 1);
	}

	@Override
	public int compareTo(ISheet o) {
		return null == o ? 1 : Integer.compare(getIndex(), o.getIndex());
	}

	@Override
	public String getId() {
		return String.format("%s%s/", getBook().getId(), getName());
	}

	@Override
	public String getA1() {
		return String.format("%s'%s'", getBook().getA1(), getName());
	}

}

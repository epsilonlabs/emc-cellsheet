package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractRow implements IRow {

	@Override
	public IBook getBook() {
		return getSheet().getBook();
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
		return String.format("%s%s/", getSheet().getId(), getIndex());
	}

	@Override
	public String getA1() {
		return String.format("%s!A$%d", getSheet().getA1(), getA1Index());
	}

}

package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

public interface IRow extends HasId, Comparable<IRow>, Iterable<ICell> {

	public static final Type TYPE = Type.ROW;
	public static final Type[] KINDS = { TYPE };

	public int getIndex();
	
	public ICell getCell(int colIdx);
	public ICell getCell(String column);
	public List<? extends ICell> cells();

	public ISheet getSheet();
	public IBook getBook();

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
	default Type getType() {
		return IRow.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IRow.KINDS;
	}

}

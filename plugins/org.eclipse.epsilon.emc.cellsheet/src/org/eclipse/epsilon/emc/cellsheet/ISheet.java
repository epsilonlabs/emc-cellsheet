package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

public interface ISheet extends HasId, Comparable<ISheet>, Iterable<IRow> {

	public static final Type TYPE = Type.SHEET;
	public static final Type[] KIND = { TYPE };

	public int getIndex();
	public String getName();

	public IRow getRow(int rowIdx);
	public List<IRow> rows();
	
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
	default Type getType() {
		return ISheet.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return ISheet.KIND;
	}
	
	@Override
	default String getId() {
		return getBook().getId() + getName() + "/";
	}

}

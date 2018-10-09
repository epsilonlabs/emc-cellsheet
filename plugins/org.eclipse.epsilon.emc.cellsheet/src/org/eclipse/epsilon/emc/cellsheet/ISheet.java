package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

public interface ISheet extends HasId, HasType, Comparable<ISheet>, Iterable<IRow> {

	public static final Type TYPE = Type.SHEET;
	public static final Type[] KIND = { TYPE };

	public IBook getBook();

	public int getIndex();

	public String getName();

	public IRow getRow(int rowIdx);

	public Iterator<IRow> rowIterator();

	public List<IRow> rows();

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

}

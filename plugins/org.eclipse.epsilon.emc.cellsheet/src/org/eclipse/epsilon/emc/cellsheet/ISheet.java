package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

public interface ISheet extends HasId, HasType, Comparable<ISheet>, Iterable<IRow> {

	public static final Type TYPE = Type.SHEET;

	public IBook getBook();

	public int getIndex();

	public String getName();

	public IRow getRow(int rowIdx);

	public Iterator<? extends IRow> rowIterator();

	public List<? extends IRow> rows();
	
}

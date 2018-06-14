package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

public interface IRow extends HasId, HasType, Comparable<IRow>, Iterable<ICell> {

	public static final Type TYPE = Type.ROW;
	public static final Type[] KINDS = {TYPE};

	public List<? extends ICell> cells();
	
	public Iterator<? extends ICell> cellIterator();

	public ICell getCell(int colIdx);

	public int getIndex();

	public ISheet getSheet();

	public IBook getBook();
	
}

package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

public interface IRow extends HasId, Comparable<IRow>, Iterable<ICell> {

	public static final String TYPENAME = "Row";

	public List<? extends ICell> cells();
	
	public Iterator<? extends ICell> cellIterator();

	public ICell getCell(int colIdx);

	public int getIndex();

	public ISheet getSheet();

	public IBook getBook();
}

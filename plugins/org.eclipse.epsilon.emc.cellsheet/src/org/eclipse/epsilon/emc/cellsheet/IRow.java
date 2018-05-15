package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

public interface IRow extends HasId {

	public static final String TYPENAME = "Row";

	public List<ICell> cells();
	
	public Iterator<ICell> cellIterator();

	public ICell getCell(int colIdx);

	public int getIndex();

	public ISheet getSheet();

}

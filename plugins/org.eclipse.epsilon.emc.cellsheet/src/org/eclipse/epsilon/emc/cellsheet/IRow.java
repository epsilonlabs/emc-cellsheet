package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;

public interface IRow extends Iterable<ICell>, HasId {
	
	public static final String TYPENAME = "Row";

	public int getIndex();
	public void setIndex(int idx);
	
	public ISheet getSheet();
	public void setSheet();
	
	public ICell getCell(int colIdx);
	public Iterator<ICell> cellIterator();
	public void createCell(ICell cell);
	public void createCell(int colIdx, ICell cell);
}

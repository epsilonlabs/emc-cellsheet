package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;

public interface Row extends Iterable<Cell> {
	
	public static final String TYPENAME = "Row";

	public int getIndex();
	public void setIndex(int idx);
	
	public Sheet getSheet();
	public void setSheet();
	
	public Cell getCell(int colIdx);
	public Iterator<Cell> cellIterator();
	public void createCell(Cell cell);
	public void createCell(int colIdx, Cell cell);
}

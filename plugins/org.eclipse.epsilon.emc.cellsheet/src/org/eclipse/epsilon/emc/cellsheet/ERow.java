package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;

public interface ERow extends Iterable<ECell>, HasId {
	
	public static final String TYPENAME = "Row";

	public int getIndex();
	public void setIndex(int idx);
	
	public ESheet getSheet();
	public void setSheet();
	
	public ECell getCell(int colIdx);
	public Iterator<ECell> cellIterator();
	public void createCell(ECell cell);
	public void createCell(int colIdx, ECell cell);
}

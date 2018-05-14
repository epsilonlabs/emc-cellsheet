package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

public interface IRow extends HasId {

	public static final String TYPENAME = "Row";

	public List<ICell> cells();
	
	public Iterator<ICell> cellIterator();

	public void createCell(ICell cell);

	public void createCell(int colIdx, ICell cell);

	public ICell getCell(int colIdx);

	public int getIndex();

	public ISheet getSheet();

	public void setIndex(int idx);

	public void setSheet(ISheet sheet);
}

package org.eclipse.epsilon.emc.cellsheet;

import org.eclipse.epsilon.emc.cellsheet.cells.CellValue;

public interface ICell extends HasId, Comparable<ICell> {

	public static final String TYPENAME = "Cell";
	
	public ISheet getSheet();

	public int getColIdx();

	public IRow getRow();

	public int getRowIdx();

	public CellValue<?> getValue();
	
	public IBook getBook();

}

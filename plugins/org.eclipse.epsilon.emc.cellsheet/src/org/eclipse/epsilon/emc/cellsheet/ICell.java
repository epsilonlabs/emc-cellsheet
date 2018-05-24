package org.eclipse.epsilon.emc.cellsheet;

import org.eclipse.epsilon.emc.cellsheet.cells.CellValue;

public interface ICell extends HasId, Comparable<ICell> {

	public static final CellsheetType TYPE = CellsheetType.CELL;

	public ISheet getSheet();

	public int getColIndex();
	
	public String getCol();

	public IRow getRow();

	public int getRowIndex();

	public CellValue<?> getValue();
	
	public IBook getBook();
	

}

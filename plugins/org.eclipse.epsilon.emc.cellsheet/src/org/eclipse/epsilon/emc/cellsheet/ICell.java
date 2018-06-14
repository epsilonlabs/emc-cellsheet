package org.eclipse.epsilon.emc.cellsheet;

import org.eclipse.epsilon.emc.cellsheet.cells.ICellValue;

public interface ICell extends HasId, HasType, Comparable<ICell> {

	public ISheet getSheet();

	public int getColIndex();
	
	public String getCol();

	public IRow getRow();

	public int getRowIndex();

	public ICellValue<?> getValue();
	
	public IBook getBook();
	
	@Override
	default Type getType() {
		return Type.CELL;
	}
}

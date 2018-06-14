package org.eclipse.epsilon.emc.cellsheet;

import org.eclipse.epsilon.emc.cellsheet.cells.ICellValue;

public interface ICell extends HasId, HasType, Comparable<ICell> {

	public static final Type TYPE = Type.CELL;
	public static final Type[] KINDS = {TYPE};

	public ISheet getSheet();

	public int getColIndex();
	
	public String getCol();

	public IRow getRow();

	public int getRowIndex();

	public ICellValue<?> getValue();
	
	public IBook getBook();

}

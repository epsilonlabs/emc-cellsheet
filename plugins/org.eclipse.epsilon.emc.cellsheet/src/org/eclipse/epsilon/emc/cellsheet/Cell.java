package org.eclipse.epsilon.emc.cellsheet;

public interface Cell {

	public int getRowIdx();
	public Row getRow();
	public void setRow(Row row);
	public void setRow(int rowIdx);
	
	public int getColIdx();
	public Column getColumn();
	public void setColumn(Column column);
	public void setColumn(int colIdx);
	
	public Object getValue();
	public Object setValue();
}

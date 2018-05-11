package org.eclipse.epsilon.emc.cellsheet;

public interface ICell extends HasId {

	public static final String TYPENAME = "Cell";
	
	public int getRowIdx();
	public IRow getRow();
	public void setRow(IRow row);
	public void setRow(int rowIdx);
	
	public int getColIdx();
	public IColumn getColumn();
	public void setColumn(IColumn column);
	public void setColumn(int colIdx);
	
	public Object getValue();
	public Object setValue();
}

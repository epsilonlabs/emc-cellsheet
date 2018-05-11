package org.eclipse.epsilon.emc.cellsheet;

public interface ECell extends HasId {

	public static final String TYPENAME = "Cell";
	
	public int getRowIdx();
	public ERow getRow();
	public void setRow(ERow row);
	public void setRow(int rowIdx);
	
	public int getColIdx();
	public EColumn getColumn();
	public void setColumn(EColumn column);
	public void setColumn(int colIdx);
	
	public Object getValue();
	public Object setValue();
}

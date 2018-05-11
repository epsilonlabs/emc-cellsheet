package org.eclipse.epsilon.emc.cellsheet;

public interface ICell extends HasId {

	public static final String TYPENAME = "Cell";

	public int getColIdx();

	public IColumn getColumn();

	public IRow getRow();

	public int getRowIdx();

	public Object getValue();

	public void setColumn(IColumn column);

	public void setColumn(int colIdx);

	public void setRow(int rowIdx);

	public void setRow(IRow row);

	public Object setValue();
}

package org.eclipse.epsilon.emc.cellsheet;

public interface ICell extends HasId {

	public static final String TYPENAME = "Cell";
	
	public ISheet getSheet();

	public int getColIdx();

	public IRow getRow();

	public int getRowIdx();

	public Object getValue();

}

package org.eclipse.epsilon.emc.cellsheet;

public interface IColumn extends HasId {

	public static final String TYPENAME = "Column";

	public int getIndex();

	public ISheet getSheet();

	public void setIndex(int idx);

	public void setSheet();
}

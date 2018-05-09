package org.eclipse.epsilon.emc.cellsheet;

public interface Column {
	
	public static final String TYPENAME = "Column";

	public int getIndex();
	public void setIndex(int idx);
	
	public Sheet getSheet();
	public void setSheet();
}

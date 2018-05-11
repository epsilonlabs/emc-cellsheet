package org.eclipse.epsilon.emc.cellsheet;

public interface EColumn extends HasId {
	
	public static final String TYPENAME = "Column";

	public int getIndex();
	public void setIndex(int idx);
	
	public ESheet getSheet();
	public void setSheet();
}

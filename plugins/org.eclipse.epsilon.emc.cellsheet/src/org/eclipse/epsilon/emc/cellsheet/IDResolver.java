package org.eclipse.epsilon.emc.cellsheet;

public interface IDResolver {

	public String getID(ESheet sheet);
	public String getID(ERow row);
	public String getID(EColumn column);
	public String getID(ECell cell);
	
	public String getSheetPart(String id);
	public String getRowPart(String id);
	public String getColumnPart(String id);
	
}

package org.eclipse.epsilon.emc.cellsheet;

public interface IDResolver {

	public String getID(ISheet sheet);
	public String getID(IRow row);
	public String getID(IColumn column);
	public String getID(ICell cell);
	
	public String getSheetPart(String id);
	public String getRowPart(String id);
	public String getColumnPart(String id);
	
}

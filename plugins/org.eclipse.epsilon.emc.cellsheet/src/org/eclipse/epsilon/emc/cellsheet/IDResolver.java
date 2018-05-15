package org.eclipse.epsilon.emc.cellsheet;

public interface IDResolver {

	public String getColumnPart(String id);

	public String getID(ICell cell);

	public String getID(IRow row);

	public String getID(ISheet sheet);

	public String getRowPart(String id);

	public String getSheetPart(String id);

}

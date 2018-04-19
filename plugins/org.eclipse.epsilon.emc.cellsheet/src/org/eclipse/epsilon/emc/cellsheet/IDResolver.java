package org.eclipse.epsilon.emc.cellsheet;

public interface IDResolver {

	public String getID(Sheet sheet);
	public String getID(Row row);
	public String getID(Column column);
	public String getID(Cell cell);
	
	public String getSheetPart(String id);
	public String getRowPart(String id);
	public String getColumnPart(String id);
}

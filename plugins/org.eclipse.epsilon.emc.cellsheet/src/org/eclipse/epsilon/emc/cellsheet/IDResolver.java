package org.eclipse.epsilon.emc.cellsheet;

public interface IDResolver {
	
	public static final String DELIMITER = "/";
	
	public static final char NO_INDEX_LOCK = Character.MIN_VALUE;
	public static final char INDEX_LOCK = '$';
	public static final int NO_INDEX = -1;

	public int getColumnPart(String id);
	
	public String getColumnPartAlpha(String id);

	public String getID(Object object);
	
	public String getID(ICell cell);

	public String getID(IRow row);

	public String getID(ISheet sheet);

	public int getRowPart(String id);

	public String getSheetPart(String id);

}

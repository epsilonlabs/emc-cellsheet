package org.eclipse.epsilon.emc.cellsheet.excel;

import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IDResolver;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelIDResolver implements IDResolver {

	@Override
	public int getColumnPart(String id) {
		String colPart = id.split(DELIMITER)[1];
		if (colPart.charAt(0) == INDEX_LOCK)
			colPart = colPart.substring(1);
		return Integer.valueOf(colPart);
	}

	@Override
	public String getID(Object o) {
		if (o instanceof ExcelCell) return getID((ExcelCell) o);
		if (o instanceof ExcelRow) return getID((ExcelRow) o);
		if (o instanceof ExcelSheet) return getID((ExcelSheet) o);
		throw new IllegalArgumentException();
	}
	
	@Override
	public String getID(ICell cell) {
		return buildId(cell.getSheet(), cell.getRowIdx(), cell.getColIdx(), false, false);
	}

	@Override
	public String getID(IRow row) {
		return buildId(row.getSheet(), row.getIndex(), NO_INDEX, false, false);
	}

	@Override
	public String getID(ISheet sheet) {
		return buildId(sheet, NO_INDEX, NO_INDEX, false, false);
	}

	@Override
	public int getRowPart(String id) {
		String rowPart = id.split(DELIMITER)[1];
		if (rowPart.charAt(0) == INDEX_LOCK)
			rowPart = rowPart.substring(1);
		return Integer.valueOf(rowPart);
	}

	@Override
	public String getSheetPart(String id) {
		String[] split = id.split(DELIMITER);
		return split[0];
	}

	@Override
	public String getColumnPartAlpha(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String buildId(ISheet sheet, int row, int col, boolean rowLock, boolean colLock) {
		StringBuilder sb = new StringBuilder();
		
		// Handle sheets
		if (sheet == null) return null;
		sb.append(sheet.getName());
		
		// Handle rows
		if (row == NO_INDEX) return sb.toString();
		sb.append(DELIMITER);
		if (rowLock) sb.append(INDEX_LOCK);
		sb.append(row);
		
		// Handle columns
		if (col == NO_INDEX) return sb.toString();
		sb.append(DELIMITER);
		if (colLock) sb.append(INDEX_LOCK);
		sb.append(col);
		
		return sb.toString();
	}

}

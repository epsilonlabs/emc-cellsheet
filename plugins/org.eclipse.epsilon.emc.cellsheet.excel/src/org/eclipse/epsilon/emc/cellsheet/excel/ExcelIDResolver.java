package org.eclipse.epsilon.emc.cellsheet.excel;

import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IColumn;
import org.eclipse.epsilon.emc.cellsheet.IDResolver;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelIDResolver implements IDResolver {

	public static final String NULL = "-1"; // TODO: change representation to something more general
	
	@Override
	public String getID(ISheet sheet) {
		return sheet.getName();
	}

	@Override
	public String getID(IRow row) {
		return this.getID(row.getSheet()) + "!$" + row.getIndex() + NULL;
	}

	@Override
	public String getID(IColumn column) {
		return this.getID(column.getSheet()) + "!" + NULL + "$" + column.getIndex();
	}

	@Override
	public String getID(ICell cell) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSheetPart(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRowPart(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnPart(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static class ExcelID  {
		public static final String NULL = "NULL";
		
		private String sheetName = null;
		private int rowIdx = -1;
		private int colIdx = -1;

		public String getSheetName() {
			return sheetName;
		}
		public void setSheetName(String sheetName) {
			this.sheetName = sheetName;
		}
		public int getRowIdx() {
			return rowIdx;
		}
		public void setRowIdx(int rowIdx) {
			this.rowIdx = rowIdx;
		}
		public int getColIdx() {
			return colIdx;
		}
		public void setColIdx(int colIdx) {
			this.colIdx = colIdx;
		}
	}
	
	public static ExcelID fromString(String idStr) {
		final ExcelID id = new ExcelID();
		return id;
	}
}

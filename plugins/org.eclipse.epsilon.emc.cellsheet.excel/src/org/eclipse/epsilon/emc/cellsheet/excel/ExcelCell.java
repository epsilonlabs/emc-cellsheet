package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.ICellValue;

public class ExcelCell implements ICell, HasDelegate<Cell> {

	protected ExcelRow row;
	protected int col;

	protected ICellValue<?> cellValue = null;

	ExcelCell(ExcelRow row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public int getColIndex() {
		return col;
	}

	@Override
	public ExcelRow getRow() {
		return row;
	}

	@Override
	public ICellValue<?> getCellValue() {
		if (cellValue == null) {
			switch (getDelegate().getCellTypeEnum()) {
			case BOOLEAN:
				cellValue = new ExcelBooleanCellValue(this);
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(getDelegate())) {
					cellValue = new ExcelDateCellValue(this);
				} else {
					cellValue = new ExcelNumericCellValue(this);
				}
				break;
			case STRING:
				cellValue = new ExcelStringCellValue(this);
				break;
			case FORMULA:
				cellValue = new ExcelFormulaCellValue(this);
				break;
			case BLANK:
				cellValue = new ExcelBlankCellValue(this);
				break;
			case ERROR:
				cellValue = new ExcelErrorCellValue(this);
				break;
			default:
				throw new IllegalStateException("Cell Value type not supported yet: " + getDelegate().getCellTypeEnum());
			}
		}
		return cellValue;
	}

	@Override
	public boolean isBlank() {
		return getDelegate().getCellTypeEnum() == CellType.BLANK;
	}

	@Override
	public Cell getDelegate() {
		return row.getDelegate().getCell(col, MissingCellPolicy.CREATE_NULL_AS_BLANK);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getClass().getSimpleName()).append("@").append(hashCode()).append("]");
		sb.append("(id: ").append(getId());
		sb.append(", excelRef: ").append(getA1Ref());
		sb.append(")");
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + ((row == null) ? 0 : row.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelCell other = (ExcelCell) obj;
		if (col != other.col)
			return false;
		if (row == null) {
			if (other.row != null)
				return false;
		} else if (!row.equals(other.row))
			return false;
		return true;
	}
	
}

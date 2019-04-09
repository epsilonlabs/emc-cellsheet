package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.eclipse.epsilon.labs.emc.cellsheet.AbstractCell;
import org.eclipse.epsilon.labs.emc.cellsheet.CellValueType;

@SuppressWarnings("deprecation")
public class ExcelCell extends AbstractCell implements HasDelegate<Cell> {

	protected ExcelRow row;
	protected int col;

	ExcelCell(ExcelRow row, int col) {
		this.row = row;
		this.col = col;
	}

	@Override
	public ExcelCellValue getCellValue() {
		final CellValueType type;
		final CellType delegateType = getDelegate().getCellTypeEnum();
		switch (delegateType) {
		case NUMERIC:
			type = DateUtil.isCellDateFormatted(getDelegate()) ? CellValueType.DATE : CellValueType.NUMERIC;
			break;
		default:
			type = CellValueType.valueOf(delegateType.name());
			break;
		}
		return new ExcelCellValue(this, type);
	}

	@Override
	public int getRowIndex() {
		return row.getIndex();
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
	public boolean isBlank() {
		return getDelegate().getCellTypeEnum() == CellType.BLANK;
	}

	@Override
	public Cell getDelegate() {
		return row.getDelegate().getCell(col, MissingCellPolicy.CREATE_NULL_AS_BLANK);
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

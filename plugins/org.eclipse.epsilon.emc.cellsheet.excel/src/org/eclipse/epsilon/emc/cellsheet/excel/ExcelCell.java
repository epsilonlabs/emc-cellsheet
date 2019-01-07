package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.ICellValue;

public class ExcelCell implements ICell, HasDelegate<Cell> {

	protected ExcelBook book;
	protected ExcelSheet sheet;
	protected ExcelRow row;
	protected Cell delegate;

	protected ICellValue<?> cellValue = null;

	ExcelCell(ExcelRow row, Cell delegate) {
		this.book = row.getBook();
		this.sheet = row.getSheet();
		this.row = row;
		this.delegate = delegate;
	}

	@Override
	public int getColIndex() {
		return this.delegate.getColumnIndex();
	}

	@Override
	public ExcelRow getRow() {
		return row;
	}

	@Override
	public int getRowIndex() {
		return this.delegate.getRowIndex();
	}

	@Override
	public ICellValue<?> getCellValue() {
		if (cellValue == null) {
			switch (delegate.getCellTypeEnum()) {
			case BOOLEAN:
				cellValue = new ExcelBooleanCellValue(this);
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(delegate)) {
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
				throw new IllegalStateException("Cell Value type not supported yet: " + delegate.getCellTypeEnum());
			}
		}
		return cellValue;
	}

	@Override
	public boolean isBlank() {
		return delegate.getCellTypeEnum() == CellType.BLANK;
	}

	@Override
	public Cell getDelegate() {
		return this.delegate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delegate == null) ? 0 : delegate.hashCode());
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
		if (delegate == null) {
			if (other.delegate != null)
				return false;
		} else if (!delegate.equals(other.delegate))
			return false;
		return true;
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

}

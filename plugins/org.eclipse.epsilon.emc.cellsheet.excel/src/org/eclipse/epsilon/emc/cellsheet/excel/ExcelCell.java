package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.eclipse.epsilon.emc.cellsheet.AbstractCell;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IColumn;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelCell extends AbstractCell implements HasRaw<Cell> {

	protected Cell raw;

	public ExcelCell(ISheet sheet, Cell raw) {
		super(sheet, new ExcelRow(sheet, raw.getRow()), null);
		this.raw = raw;
	}

	@Override
	public int getColIdx() {
		return this.raw.getColumnIndex();
	}

	@Override
	public IColumn getColumn() {
		// FIXME: Remove columns?
		throw new UnsupportedOperationException();
	}

	@Override
	public IRow getRow() {
		return this.row;
	}

	@Override
	public int getRowIdx() {
		return this.row.getIndex();
	}

	@Override
	public Object getValue() {
		switch (this.raw.getCellTypeEnum()) {
		case BOOLEAN:
			return this.raw.getBooleanCellValue();
		case NUMERIC:
			return this.raw.getNumericCellValue();
		case STRING:
			return this.raw.getStringCellValue();
		default:
			return null;
		}
	}

	@Override
	public void setColumn(IColumn column) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setColumn(int colIdx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRow(int rowIdx) {
		this.raw.getRow().setRowNum(rowIdx);
	}

	@Override
	public void setRow(IRow row) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValue() {
		// TODO
	}

	@Override
	public String getId() {
		// FIXME
		return this.raw.getAddress().toString();
	}

	@Override
	public void setId() {
		// TODO Auto-generated method stub

	}

	@Override
	public Cell getRaw() {
		return this.raw;
	}

	@Override
	public void setRaw(Cell raw) {
		this.raw = raw;
	}

}

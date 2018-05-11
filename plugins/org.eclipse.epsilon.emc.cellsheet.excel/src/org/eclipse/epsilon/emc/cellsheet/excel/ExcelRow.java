package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Row;
import org.eclipse.epsilon.emc.cellsheet.AbstractRow;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.ICell;

public class ExcelRow extends AbstractRow implements HasRaw<Row> {

	protected Row raw;

	protected ExcelRow(ExcelSheet sheet, Row raw) {
		super(sheet);
		this.raw = raw;
	}

	@Override
	public void createCell(ICell cell) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createCell(int colIdx, ICell cell) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIndex() {
		return this.raw.getRowNum();
	}

	@Override
	public Row getRaw() {
		return this.raw;
	}

	@Override
	public void setIndex(int idx) {
		this.raw.setRowNum(idx);
	}

	@Override
	public void setRaw(Row raw) {
		this.raw = raw;
	}

	@Override
	public String toString() {
		return this.getSheet().toString() + "!-1$" + this.getIndex();
	}

}

package org.eclipse.epsilon.emc.cellsheet.excel.cell;

import org.eclipse.epsilon.emc.cellsheet.cells.ICellValue;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelCell;

public abstract class AbstractExcelValue<T> implements ICellValue<T> {
	
	protected ExcelCell cell;

	protected AbstractExcelValue(ExcelCell cell) {
		this.cell = cell;
	}
	
	@Override
	public String getValue() {
		return cell.getDelegate().getStringCellValue();
	}

	@Override
	public ExcelCell getCell() {
		return this.cell;
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
}

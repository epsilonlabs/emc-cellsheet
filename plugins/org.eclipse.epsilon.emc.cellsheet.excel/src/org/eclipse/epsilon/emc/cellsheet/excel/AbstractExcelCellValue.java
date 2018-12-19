package org.eclipse.epsilon.emc.cellsheet.excel;

import org.eclipse.epsilon.emc.cellsheet.ICellValue;

public abstract class AbstractExcelCellValue<T> implements ICellValue<T> {

	protected ExcelCell cell;

	AbstractExcelCellValue(ExcelCell cell) {
		this.cell = cell;
	}

	@Override
	public ExcelCell getCell() {
		return this.cell;
	}

}

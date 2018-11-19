package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Date;

import org.eclipse.epsilon.emc.cellsheet.IDateCellValue;

public class ExcelDateCellValue extends AbstractExcelCellValue<Date> implements IDateCellValue {
	ExcelDateCellValue(ExcelCell cell) {
		super(cell);
	}

	@Override
	public Date getValue() {
		return cell.getDelegate().getDateCellValue();
	}

	@Override
	public String toString() {
		return getValue().toInstant().toString();
	}
	
}

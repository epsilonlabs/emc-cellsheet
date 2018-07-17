package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.eclipse.epsilon.emc.cellsheet.IBooleanCellValue;

public class ExcelBooleanCellValue extends AbstractExcelCellValue<Boolean> implements IBooleanCellValue {

	ExcelBooleanCellValue(ExcelCell cell) {
		super(cell);
		if (cell.delegate.getCellTypeEnum() != CellType.BOOLEAN) 
			throw new IllegalArgumentException("Delegate cell must have a boolean value");
	}
	
	@Override
	public Boolean getValue() {
		return cell.getDelegate().getBooleanCellValue();
	}

	@Override
	public String toString() {
		return getValue().toString();
	}
}

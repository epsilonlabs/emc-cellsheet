package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.eclipse.epsilon.emc.cellsheet.INumericCellValue;

public class ExcelNumericValue extends AbstractExcelCellValue<Double> implements INumericCellValue {

	ExcelNumericValue(ExcelCell cell) {
		super(cell);
		if (cell.delegate.getCellTypeEnum() != CellType.NUMERIC) 
			throw new IllegalArgumentException("Delegate cell must have a Numeric value");
	}
	
	@Override
	public Double getValue() {
		return cell.getDelegate().getNumericCellValue();
	}
	
}

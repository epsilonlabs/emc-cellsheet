package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.FormulaError;
import org.eclipse.epsilon.emc.cellsheet.IErrorCellValue;

public class ExcelErrorCellValue extends AbstractExcelCellValue<String> implements IErrorCellValue {

	ExcelErrorCellValue(ExcelCell cell) {
		super(cell);
	}

	@Override
	public String getValue() {
		return FormulaError.forInt(cell.getDelegate().getErrorCellValue()).name();
	}

}

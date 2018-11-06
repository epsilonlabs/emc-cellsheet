package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.eclipse.epsilon.emc.cellsheet.IBlankCellValue;

public class ExcelBlankCellValue extends AbstractExcelCellValue<Void> implements IBlankCellValue {

	public ExcelBlankCellValue(ExcelCell cell) {
		super(cell);
		if (cell.delegate.getCellTypeEnum() != CellType.BLANK)
			throw new IllegalArgumentException("Delegate cell must have a Formula/String value");
	}

	@Override
	public Void getValue() {
		return null;
	}

	@Override
	public String toString() {
		return null;
	}

}

package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.eclipse.epsilon.emc.cellsheet.CellValueType;

public class ExcelCellValueFactory {

	private ExcelCellValueFactory() {
		;
	}

	public static ExcelCellValue newInstance(ExcelCell cell) {
		final CellValueType type;
		final CellType delegateType = cell.getDelegate().getCellTypeEnum();

		if (delegateType == CellType.NUMERIC) {
			type = DateUtil.isCellDateFormatted(cell.getDelegate()) ? CellValueType.DATE : CellValueType.NUMERIC;
		} else {
			type = CellValueType.valueOf(delegateType.name());
		}

		return new ExcelCellValue(cell, type);
	}

	public static ExcelCellValue newInstance(CellValueType type) {
		return new ExcelCellValue(type);
	}

	public static ExcelCellValue newInstance() {
		return new ExcelCellValue();
	}

}

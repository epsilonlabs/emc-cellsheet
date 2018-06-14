package org.eclipse.epsilon.emc.cellsheet.excel.cell;

import org.eclipse.epsilon.emc.cellsheet.cells.IStringCellValue;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelCell;

public class ExcelStringValue extends AbstractExcelValue<String> implements IStringCellValue {
	
	public ExcelStringValue(ExcelCell cell) {
		super(cell);
	}

	@Override
	public String getResolvedValue() {
		return this.cell.getDelegate().getStringCellValue();
	}

	@Override
	public Type getType() {
		return Type.STRING;
	}
}

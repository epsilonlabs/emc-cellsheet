package org.eclipse.epsilon.emc.cellsheet.excel.cell;

import org.eclipse.epsilon.emc.cellsheet.cells.StringValue;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelCell;

public class ExcelStringValue extends AbstractExcelValue<String> implements StringValue {
	
	public ExcelStringValue(ExcelCell cell) {
		super(cell);
	}

	@Override
	public String getResolvedValue() {
		return this.cell.getRaw().getStringCellValue();
	}

	@Override
	public Type getType() {
		return Type.STRING;
	}
}

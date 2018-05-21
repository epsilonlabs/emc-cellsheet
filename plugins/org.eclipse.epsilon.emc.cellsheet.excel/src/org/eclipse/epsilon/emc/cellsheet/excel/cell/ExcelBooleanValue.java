package org.eclipse.epsilon.emc.cellsheet.excel.cell;

import org.eclipse.epsilon.emc.cellsheet.cells.BooleanValue;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelCell;

public class ExcelBooleanValue extends AbstractExcelValue<Boolean> implements BooleanValue {

	public ExcelBooleanValue(ExcelCell cell) {
		super(cell);
	}

	@Override
	public Boolean getResolvedValue() {
		return this.cell.getRaw().getBooleanCellValue();
	}
	
	@Override
	public Type getType() {
		return Type.BOOLEAN;
	}

}

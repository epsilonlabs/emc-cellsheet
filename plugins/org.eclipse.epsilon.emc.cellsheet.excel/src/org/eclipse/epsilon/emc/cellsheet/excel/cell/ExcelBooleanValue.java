package org.eclipse.epsilon.emc.cellsheet.excel.cell;

import org.eclipse.epsilon.emc.cellsheet.cells.IBooleanCellValue;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelCell;

public class ExcelBooleanValue extends AbstractExcelValue<Boolean> implements IBooleanCellValue {

	public ExcelBooleanValue(ExcelCell cell) {
		super(cell);
	}

	@Override
	public Boolean getResolvedValue() {
		return this.cell.getDelegate().getBooleanCellValue();
	}
	
	@Override
	public Type getType() {
		return Type.BOOLEAN;
	}

}

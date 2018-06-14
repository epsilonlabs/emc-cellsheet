package org.eclipse.epsilon.emc.cellsheet.excel.cell;

import org.eclipse.epsilon.emc.cellsheet.cells.INumericCellValue;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelCell;

public class ExcelNumericValue extends AbstractExcelValue<Double> implements INumericCellValue {

	public ExcelNumericValue(ExcelCell cell) {
		super(cell);
	}
	
	@Override
	public String getValue() {
		return getResolvedValue().toString();
	}

	@Override
	public Double getResolvedValue() {
		return this.cell.getDelegate().getNumericCellValue();
	}

	@Override
	public Type getType() {
		return Type.NUMERIC;
	}
}

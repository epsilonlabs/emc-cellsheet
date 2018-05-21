package org.eclipse.epsilon.emc.cellsheet.excel.cell;

import org.eclipse.epsilon.emc.cellsheet.cells.NumericValue;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelCell;

public class ExcelNumericValue extends AbstractExcelValue<Double> implements NumericValue {

	public ExcelNumericValue(ExcelCell cell) {
		super(cell);
	}
	
	@Override
	public String getValue() {
		return getResolvedValue().toString();
	}

	@Override
	public Double getResolvedValue() {
		return this.cell.getRaw().getNumericCellValue();
	}

	@Override
	public Type getType() {
		return Type.NUMERIC;
	}
}

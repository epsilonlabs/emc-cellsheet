package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Date;

import org.apache.poi.ss.usermodel.FormulaError;
import org.eclipse.epsilon.emc.cellsheet.AbstractCellValue;
import org.eclipse.epsilon.emc.cellsheet.CellValueType;
import org.eclipse.epsilon.emc.cellsheet.IAst;

public class ExcelCellValue extends AbstractCellValue {

	protected ExcelCell cell;

	public ExcelCellValue() {
		super();
		this.cell = null;
	}

	public ExcelCellValue(CellValueType type) {
		super(type);
	}

	public ExcelCellValue(ExcelCell cell, CellValueType type) {
		super(type);
		this.cell = cell;
	}

	@Override
	public ExcelCell getCell() {
		return this.cell;
	}

	public void setCell(ExcelCell cell) {
		this.cell = cell;
	}

	@Override
	public boolean getBooleanValue() {
		if (type == CellValueType.BOOLEAN) {
			return cell.getDelegate().getBooleanCellValue();
		}
		return false;
	}

	@Override
	public double getNumericValue() {
		if (type == CellValueType.NUMERIC) {
			return cell.getDelegate().getNumericCellValue();
		}
		return 0;
	}

	@Override
	public Date getDateValue() {
		if (type == CellValueType.DATE) {
			return cell.getDelegate().getDateCellValue();
		}
		return null;
	}

	@Override
	public String getStringValue() {
		if (cell == null) {
			return "";
		}

		switch (type) {
		case BOOLEAN:
			return Boolean.toString(getBooleanValue());
		case NUMERIC:
			return Double.toString(getNumericValue());
		case DATE:
			return getDateValue().toString();
		case FORMULA:
			return getFormula();
		case ERROR:
			return getErrorValue();
		default:
			return cell.getDelegate().getStringCellValue();
		}
	}

	@Override
	public String getFormula() {
		if (type == CellValueType.FORMULA) {
			return cell.getDelegate().getCellFormula();
		}
		return String.format("\"%s\"", getStringValue());
	}

	@Override
	public String getErrorValue() {
		if (type == CellValueType.ERROR) {
			return FormulaError.forInt(cell.getDelegate().getErrorCellValue()).name();
		}
		return null;
	}

	@Override
	public IAst getAst() {
		return ExcelAstFactory.newInstance(this);
	}

}

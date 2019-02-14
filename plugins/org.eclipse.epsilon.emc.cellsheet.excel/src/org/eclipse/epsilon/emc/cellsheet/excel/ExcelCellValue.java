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
	public boolean getBoolean() {
		if (type == CellValueType.BOOLEAN) {
			return cell.getDelegate().getBooleanCellValue();
		}
		return false;
	}

	@Override
	public double getNumber() {
		if (type == CellValueType.NUMERIC) {
			return cell.getDelegate().getNumericCellValue();
		}
		return 0;
	}

	@Override
	public Date getDate() {
		if (type == CellValueType.DATE) {
			return cell.getDelegate().getDateCellValue();
		}
		return null;
	}

	@Override
	public String getString() {
		if (cell == null) {
			return "";
		}

		switch (type) {
		case BOOLEAN:
			return Boolean.toString(getBoolean());
		case NUMERIC:
			return Double.toString(getNumber());
		case DATE:
			return getDate().toString();
		case FORMULA:
			return getFormula();
		case ERROR:
			return getError();
		default:
			return cell.getDelegate().getStringCellValue();
		}
	}

	@Override
	public String getFormula() {
		if (type == CellValueType.FORMULA) {
			return cell.getDelegate().getCellFormula();
		}
		return getString();
	}

	@Override
	public String getError() {
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

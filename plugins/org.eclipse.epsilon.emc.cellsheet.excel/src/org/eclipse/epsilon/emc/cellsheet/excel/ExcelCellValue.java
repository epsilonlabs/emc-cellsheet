package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Date;

import org.apache.poi.ss.usermodel.FormulaError;
import org.eclipse.epsilon.emc.cellsheet.AbstractCellValue;
import org.eclipse.epsilon.emc.cellsheet.AstSupertype;
import org.eclipse.epsilon.emc.cellsheet.AstType;
import org.eclipse.epsilon.emc.cellsheet.CellValueType;

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
		case BLANK:
		case NONE:
			return "";
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
		switch (type) {
		case FORMULA:
			return cell.getDelegate().getCellFormula();
		case DATE:
		case STRING:
			return String.format("\"%s\"", getStringValue());
		default:
			return getStringValue();
		}
	}

	@Override
	public String getErrorValue() {
		if (type == CellValueType.ERROR) {
			return FormulaError.forInt(cell.getDelegate().getErrorCellValue()).name();
		}
		return null;
	}

	@Override
	public ExcelAst getAst() {
		if (type == CellValueType.FORMULA)
			return ExcelAstFactory.newInstance(this);

		final ExcelAst.Builder b = new ExcelAst.Builder().withToken(getStringValue())
				.withSupertype(AstSupertype.OPERAND).withCellValue(this);
		switch (type) {
		case BOOLEAN:
			b.withType(AstType.LOGICAL);
			break;
		case NUMERIC:
			b.withType(AstType.NUMBER);
			break;
		case ERROR:
			b.withType(AstType.ERROR);
			break;
		case DATE:
		case STRING:
			b.withType(AstType.TEXT);
			break;
		case BLANK:
		case NONE:
			b.withSupertype(AstSupertype.NOOP).withType(AstType.NOTHING);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return b.build();
	}

}

package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import static org.eclipse.epsilon.labs.emc.cellsheet.CellValueType.DATE;
import static org.eclipse.epsilon.labs.emc.cellsheet.CellValueType.FORMULA;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.eclipse.epsilon.labs.emc.cellsheet.AbstractCell;
import org.eclipse.epsilon.labs.emc.cellsheet.CellValueType;
import org.eclipse.epsilon.labs.emc.cellsheet.ICellValue;

public class ExcelCell extends AbstractCell implements HasDelegate<Cell> {

	protected ExcelCell() {
		;
	}

	protected ExcelCell(ExcelCell.Builder b) {
		super(b);
	}

	@Override
	public Cell getDelegate() {
		return getRow().getDelegate().getCell(col, MissingCellPolicy.CREATE_NULL_AS_BLANK);
	}
	
	@Override
	public ExcelBook getBook() {
		return (ExcelBook) super.getBook();
	}
	
	@Override
	public ExcelSheet getSheet() {
		return (ExcelSheet) super.getSheet();
	}
	
	@Override
	public ExcelRow getRow() {
		return (ExcelRow) row;
	}

	@Override
	protected ICellValue initCellValue() {
		final ExcelCellValue.Builder b = new ExcelCellValue.Builder().withCell(this);

		// Determine CellValue type
		CellValueType cellType = CellValueType.valueOf(getDelegate().getCellType().name());
		if (cellType == CellValueType.NUMERIC && DateUtil.isCellDateFormatted(getDelegate()))
			cellType = DATE;
		b.withType(cellType);

		// Determine CellValue value
		CellValueType valueType = cellType == FORMULA
				? CellValueType.valueOf(getDelegate().getCachedFormulaResultType().name())
				: cellType;

		switch (valueType) {
		case BOOLEAN:
			b.withValue(getDelegate().getBooleanCellValue());
			break;
		case NUMERIC:
			b.withValue(getDelegate().getNumericCellValue());
			break;
		case DATE:
			b.withValue(getDelegate().getDateCellValue());
			break;
		case STRING:
			b.withValue(getDelegate().getStringCellValue());
			break;
		case ERROR:
			b.withValue(FormulaError.forInt(getDelegate().getErrorCellValue()).name());
			break;
		case BLANK:
		case NONE:
			break;
		default:
			throw new AssertionError(
					"Error building ExcelCellValue unexpected cell type when setting value: " + valueType);
		}

		// Finally get the formula if applicable
		if (cellType == FORMULA)
			b.withFormula(getDelegate().getCellFormula());

		return b.build();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + ((row == null) ? 0 : row.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelCell other = (ExcelCell) obj;
		if (col != other.col)
			return false;
		if (row == null) {
			if (other.row != null)
				return false;
		} else if (!row.equals(other.row))
			return false;
		return true;
	}

	public static class Builder extends AbstractCell.Builder<ExcelCell, Builder> {
		@Override
		public ExcelCell build() {
			if (this.row.getClass() != ExcelRow.class) {
				throw new IllegalArgumentException("Sheet must be of type " + ExcelSheet.class.getCanonicalName());
			}
			return new ExcelCell(this);
		}
	}

}

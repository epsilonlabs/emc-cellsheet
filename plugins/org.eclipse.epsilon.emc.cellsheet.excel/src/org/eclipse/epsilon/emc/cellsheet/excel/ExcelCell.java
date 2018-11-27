package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.ICellValue;

public class ExcelCell implements ICell, HasDelegate<Cell> {

	protected ExcelBook book;
	protected ExcelSheet sheet;
	protected ExcelRow row;
	protected Cell delegate;

	protected ICellValue<?> cellValue = null;

	ExcelCell(ExcelRow row, Cell delegate) {
		this.book = row.getBook();
		this.sheet = row.getSheet();
		this.row = row;
		this.delegate = delegate;
	}

	@Override
	public int getColIndex() {
		return this.delegate.getColumnIndex();
	}

	@Override
	public String getCol() {
		return CellReference.convertNumToColString(this.getColIndex());
	}

	@Override
	public ExcelRow getRow() {
		return row;
	}

	@Override
	public int getRowIndex() {
		return this.delegate.getRowIndex();
	}

	@Override
	public ICellValue<?> getValue() {
		if (cellValue == null) {
			switch (delegate.getCellTypeEnum()) {
			case BOOLEAN:
				cellValue = new ExcelBooleanCellValue(this);
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(delegate)) {
					cellValue = new ExcelDateCellValue(this);
				} else {
					cellValue = new ExcelNumericCellValue(this);
				}
				break;
			case STRING:
				cellValue = new ExcelStringCellValue(this);
				break;
			case FORMULA:
				cellValue = new ExcelFormulaCellValue(this);
				break;
			case BLANK:
				cellValue = new ExcelBlankCellValue(this);
				break;
			case ERROR:
				cellValue = new ExcelErrorCellValue(this);
				break;
			default:
				throw new IllegalStateException("Cell Value type not supported yet: " + delegate.getCellTypeEnum());
			}
		}
		return cellValue;
	}

	@Override
	public ExcelBooleanCellValue getBooleanCellValue() {
		if (delegate.getCellTypeEnum() != CellType.BOOLEAN) {
			throw new IllegalStateException("Not a boolean");
		}
		return (ExcelBooleanCellValue) getValue();
	}

	@Override
	public ExcelFormulaCellValue getFormulaCellValue() {
		if (delegate.getCellTypeEnum() != CellType.FORMULA) {
			throw new IllegalStateException("Not a Formula");
		}
		return (ExcelFormulaCellValue) getValue();
	}

	@Override
	public ExcelStringCellValue getStringCellValue() {
		if (delegate.getCellTypeEnum() != CellType.STRING || delegate.getCellTypeEnum() != CellType.FORMULA) {
			throw new IllegalStateException("Not a String");
		}
		return (ExcelStringCellValue) getValue();
	}

	@Override
	public ExcelNumericCellValue getNumericCellValue() {
		if (delegate.getCellTypeEnum() != CellType.NUMERIC) {
			throw new IllegalStateException("Not a numeric");
		}
		return (ExcelNumericCellValue) getValue();
	}

	@Override
	public boolean isBlank() {
		return delegate.getCellTypeEnum() == CellType.BLANK;
	}

	@Override
	public Cell getDelegate() {
		return this.delegate;
	}

	@Override
	public ExcelSheet getSheet() {
		return sheet;
	}

	@Override
	public ExcelBook getBook() {
		return this.book;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getClass().getSimpleName()).append("@").append(hashCode()).append("]");
		sb.append("(id: ").append(getId());
		sb.append(", excelRef: ").append("[").append(getBook().getName()).append("]'").append(sheet.getName())
				.append("'!");
		sb.append("$").append(getCol()).append("$").append(getRowIndex() + 1);
		sb.append(")");
		return sb.toString();
	}

	// private void moveSelf(int rowIdx, int colIdx) {
	// final Row row = getRowIdx() == rowIdx ? raw.getRow()
	// : raw.getSheet().getRow(rowIdx);
	//
	// final Cell newCell = row.getCell(colIdx) == null ? row.createCell(colIdx)
	// : row.getCell(colIdx);
	//
	// // Copy cell style
	// final CellStyle newCellStyle =
	// raw.getSheet().getWorkbook().createCellStyle();
	// newCellStyle.cloneStyleFrom(raw.getCellStyle());
	// newCell.setCellStyle(newCellStyle);
	//
	// // Copy comment if it exists
	// if (raw.getCellComment() != null)
	// newCell.setCellComment(raw.getCellComment());
	//
	// if (raw.getHyperlink() != null)
	// newCell.setHyperlink(raw.getHyperlink());
	//
	// // Set cell values
	// newCell.setCellType(raw.getCellTypeEnum());
	// switch (raw.getCellTypeEnum()) {
	// case BOOLEAN:
	// newCell.setCellValue(raw.getBooleanCellValue());
	// break;
	// case NUMERIC:
	// newCell.setCellValue(raw.getNumericCellValue());
	// break;
	// case STRING:
	// newCell.setCellValue(raw.getStringCellValue());
	// break;
	// case FORMULA:
	// newCell.setCellValue(raw.getCellFormula());
	// break
	// ;
	// case ERROR:
	// newCell.setCellErrorValue(raw.getErrorCellValue());
	// break;
	// default:
	// newCell.setCellValue(raw.getStringCellValue());
	// break;
	// }
	//
	// row.removeCell(raw);
	// this.setRaw(newCell);
	// this.row = this.getRow();
	// this.column = this.getColumn();
	// }
}

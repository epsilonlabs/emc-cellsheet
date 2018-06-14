package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.eclipse.epsilon.emc.cellsheet.AbstractCell;
import org.eclipse.epsilon.emc.cellsheet.HasDelegate;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.cells.ICellValue;
import org.eclipse.epsilon.emc.cellsheet.excel.cell.ExcelBooleanValue;
import org.eclipse.epsilon.emc.cellsheet.excel.cell.ExcelFormulaValue;
import org.eclipse.epsilon.emc.cellsheet.excel.cell.ExcelNumericValue;
import org.eclipse.epsilon.emc.cellsheet.excel.cell.ExcelStringValue;

public class ExcelCell extends AbstractCell implements ICell, HasDelegate<Cell> {

	protected ExcelBook book;
	protected Cell delegate;

	ExcelCell(ExcelBook book, Cell delegate) {
		this.book = book;
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
		return this.book._rows.get(this.delegate.getRow());
	}

	@Override
	public int getRowIndex() {
		return this.delegate.getRowIndex();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ICellValue getValue() {
		switch (this.delegate.getCellTypeEnum()) {
		case BOOLEAN:
			return new ExcelBooleanValue(this);
		case NUMERIC:
			return new ExcelNumericValue(this);
		case STRING:
		case BLANK:
			return new ExcelStringValue(this);
		case FORMULA:
			return new ExcelFormulaValue(this);
		default:
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public String getId() {
		return this.book.getIDResolver().getID(this);
	}

	@Override
	public Cell getDelegate() {
		return this.delegate;
	}

	@Override
	public void setDelegate(Cell delegate) {
		this.delegate = delegate;
	}

	@Override
	public ExcelSheet getSheet() {
		return this.book._sheets.get(this.delegate.getSheet());
	}

	@Override
	public ExcelBook getBook() {
		return this.book;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] [%s]", this.getId(), this.getValue().toString());
	}
	
//	private void moveSelf(int rowIdx, int colIdx) {
//	final Row row = getRowIdx() == rowIdx ? raw.getRow() 
//			: raw.getSheet().getRow(rowIdx);
//	
//	final Cell newCell = row.getCell(colIdx) == null ? row.createCell(colIdx) 
//			: row.getCell(colIdx);
//	
//	// Copy cell style
//	final CellStyle newCellStyle = raw.getSheet().getWorkbook().createCellStyle();
//	newCellStyle.cloneStyleFrom(raw.getCellStyle());
//	newCell.setCellStyle(newCellStyle);
//	
//	// Copy comment if it exists
//	if (raw.getCellComment() != null)
//		newCell.setCellComment(raw.getCellComment());
//	
//	if (raw.getHyperlink() != null)
//		newCell.setHyperlink(raw.getHyperlink());
//	
//	// Set cell values
//	newCell.setCellType(raw.getCellTypeEnum());
//	switch (raw.getCellTypeEnum()) {
//	case BOOLEAN:
//		newCell.setCellValue(raw.getBooleanCellValue());
//		break;
//	case NUMERIC:
//		newCell.setCellValue(raw.getNumericCellValue());
//		break;
//	case STRING:
//		newCell.setCellValue(raw.getStringCellValue());
//		break;
//	case FORMULA:
//		newCell.setCellValue(raw.getCellFormula());
//		break
//		;
//	case ERROR:
//		newCell.setCellErrorValue(raw.getErrorCellValue());
//		break;
//	default:
//		newCell.setCellValue(raw.getStringCellValue());
//		break;
//	}
//	
//	row.removeCell(raw);
//	this.setRaw(newCell);
//	this.row = this.getRow();
//	this.column = this.getColumn();
//}
}

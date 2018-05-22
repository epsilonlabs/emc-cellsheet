package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.cells.CellValue;
import org.eclipse.epsilon.emc.cellsheet.excel.cell.ExcelBooleanValue;
import org.eclipse.epsilon.emc.cellsheet.excel.cell.ExcelFormulaValue;
import org.eclipse.epsilon.emc.cellsheet.excel.cell.ExcelNumericValue;
import org.eclipse.epsilon.emc.cellsheet.excel.cell.ExcelStringValue;

public class ExcelCell implements ICell, HasRaw<Cell> {

	protected ExcelBook book;
	protected Cell raw;

	ExcelCell(ExcelBook book, Cell raw) {
		this.book = book;
		this.raw = raw;
	}

	@Override
	public int getColIdx() {
		return this.raw.getColumnIndex();
	}

	@Override
	public ExcelRow getRow() {
		return this.book._rows.get(this.raw.getRow());
	}

	@Override
	public int getRowIdx() {
		return this.raw.getRowIndex();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public CellValue getValue() {
		switch (this.raw.getCellTypeEnum()) {
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
	public Cell getRaw() {
		return this.raw;
	}

	@Override
	public void setRaw(Cell raw) {
		this.raw = raw;
	}

	@Override
	public ExcelSheet getSheet() {
		return this.book._sheets.get(this.raw.getSheet());
	}

	@Override
	public ExcelBook getBook() {
		return this.book;
	}

	@Override
	public int compareTo(ICell o) {
		if (null == o) return 1;
		if (this == o) return 0;
		
		int parent = this.getRow().compareTo(o.getRow());
		return parent == 0 ? Integer.compare(this.getColIdx(), o.getColIdx()) : parent;
	}
	
	@Override
	public String toString() {
		return String.format("[%s] [%s]", this.getId(), this.getValue().toString());
	}
	
	public String getExcelId() {
		return String.format("%s!%s%s", this.getSheet().getName(), CellReference.convertNumToColString(this.getColIdx()), this.getRowIdx()+1);
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

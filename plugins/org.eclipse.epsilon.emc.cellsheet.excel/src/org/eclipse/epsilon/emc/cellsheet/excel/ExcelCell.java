package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.eclipse.epsilon.emc.cellsheet.AbstractCell;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelCell extends AbstractCell implements HasRaw<Cell> {

	protected Cell raw;

	public ExcelCell(ISheet sheet, Cell raw) {
		super(sheet, new ExcelRow(sheet, raw.getRow()));
		this.raw = raw;
	}

	@Override
	public int getColIdx() {
		return this.raw.getColumnIndex();
	}

	@Override
	public IRow getRow() {
		return this.row;
	}

	@Override
	public int getRowIdx() {
		return this.row.getIndex();
	}

	@Override
	public Object getValue() {
		switch (this.raw.getCellTypeEnum()) {
		case BOOLEAN:
			return this.raw.getBooleanCellValue();
		case NUMERIC:
			return this.raw.getNumericCellValue();
		case STRING:
			return this.raw.getStringCellValue();
		case FORMULA:
			return this.raw.getCellFormula();
		default:
			return null;
		}
	}

	@Override
	public String getId() {
		// FIXME
		return this.raw.getAddress().toString();
	}

	@Override
	public void setId() {
		// TODO Auto-generated method stub

	}

	@Override
	public Cell getRaw() {
		return this.raw;
	}

	@Override
	public void setRaw(Cell raw) {
		this.raw = raw;
	}

//	private void moveSelf(int rowIdx, int colIdx) {
//		final Row row = getRowIdx() == rowIdx ? raw.getRow() 
//				: raw.getSheet().getRow(rowIdx);
//		
//		final Cell newCell = row.getCell(colIdx) == null ? row.createCell(colIdx) 
//				: row.getCell(colIdx);
//		
//		// Copy cell style
//		final CellStyle newCellStyle = raw.getSheet().getWorkbook().createCellStyle();
//		newCellStyle.cloneStyleFrom(raw.getCellStyle());
//		newCell.setCellStyle(newCellStyle);
//		
//		// Copy comment if it exists
//		if (raw.getCellComment() != null)
//			newCell.setCellComment(raw.getCellComment());
//		
//		if (raw.getHyperlink() != null)
//			newCell.setHyperlink(raw.getHyperlink());
//		
//		// Set cell values
//		newCell.setCellType(raw.getCellTypeEnum());
//		switch (raw.getCellTypeEnum()) {
//		case BOOLEAN:
//			newCell.setCellValue(raw.getBooleanCellValue());
//			break;
//		case NUMERIC:
//			newCell.setCellValue(raw.getNumericCellValue());
//			break;
//		case STRING:
//			newCell.setCellValue(raw.getStringCellValue());
//			break;
//		case FORMULA:
//			newCell.setCellValue(raw.getCellFormula());
//			break
//			;
//		case ERROR:
//			newCell.setCellErrorValue(raw.getErrorCellValue());
//			break;
//		default:
//			newCell.setCellValue(raw.getStringCellValue());
//			break;
//		}
//		
//		row.removeCell(raw);
//		this.setRaw(newCell);
//		this.row = this.getRow();
//		this.column = this.getColumn();
//	}
	
}

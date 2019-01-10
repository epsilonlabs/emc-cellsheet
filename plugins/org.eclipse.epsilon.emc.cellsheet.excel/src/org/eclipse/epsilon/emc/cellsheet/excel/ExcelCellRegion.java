package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.ICellRegion;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelCellRegion implements ICellRegion {

	protected ExcelBook book;
	protected ExcelSheet sheet;
	protected int startRowIdx;
	protected int endRowIdx;
	protected int startColIdx;
	protected int endColIdx;

	public ExcelCellRegion(ExcelBook book, ExcelSheet sheet, int startRowIdx, int endRowIdx, int startColIdx,
			int endColIdx) {
		this.book = book;
		this.sheet = sheet;
		this.startRowIdx = startRowIdx;
		this.endRowIdx = endRowIdx;
		this.startColIdx = startColIdx;
		this.endColIdx = endColIdx;
	}

	@Override
	public IBook getBook() {
		return this.getBook();
	}

	@Override
	public ISheet getSheet() {
		return this.sheet;
	}

	public int getStartRowIdx() {
		return this.startRowIdx;
	}

	public void setStartRowIdx(int startRowIdx) {
		this.startRowIdx = startRowIdx;
	}

	public int getEndRowIdx() {
		return this.endRowIdx;
	}

	public void setEndRowIdx(int endRowIdx) {
		this.endRowIdx = endRowIdx;
	}

	public int getStartColIdx() {
		return this.startColIdx;
	}

	public void setStartColIdx(int startColIdx) {
		this.startColIdx = startColIdx;
	}

	public int getEndColIdx() {
		return this.endColIdx;
	}

	public void setEndColIdx(int endColIdx) {
		this.endColIdx = endColIdx;
	}

	@Override
	public <T extends ICell> List<ICell> cells() {
		final List<ICell> cells = new ArrayList<>();
		for (int r = startRowIdx; r < endRowIdx; r++) {
			for (int c = startColIdx; c < endColIdx; c++) {
				cells.add(sheet.getRow(r).getCell(c));
			}
		}
		return cells;
	}

	@Override
	public <T extends ICell> Iterator<ICell> cellIterator() {
		return this.cells().iterator();
	}

}

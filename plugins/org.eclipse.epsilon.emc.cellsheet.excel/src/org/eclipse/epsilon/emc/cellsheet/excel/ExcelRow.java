package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Row;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelRow implements IRow, HasRaw<Row> {

	protected ExcelBook book;
	protected Row raw;

	ExcelRow(ExcelBook book, Row raw) {
		this.book = book;
		this.raw = raw;
	}

	@Override
	public Iterator<ICell> cellIterator() {
		return cells().iterator();
	}

	@Override
	public List<ICell> cells() {
		this.raw.cellIterator().forEachRemaining(c -> this.book.getCell(c));
		return book._cells.values().stream()
				.filter(c -> this.equals(c.getRow()))
				.sorted()
				.collect(Collectors.toList());
	}

	@Override
	public int compareTo(IRow o) {
		if (null == o) return 1;
		if (this == o) return 0;
		
		int parent = this.getSheet().compareTo(o.getSheet());
		return parent == 0 ? Integer.compare(this.getIndex(), o.getIndex()) : parent;
	}

	@Override
	public IBook getBook() {
		return this.book;
	}

	@Override
	public ICell getCell(int colIdx) {
		return this.book.getCell(this.raw.getCell(colIdx));
	}

	@Override
	public String getId() {
		return this.book.getIDResolver().getID(this);

	}

	@Override
	public int getIndex() {
		return this.raw.getRowNum();
	}

	@Override
	public Row getRaw() {
		return this.raw;
	}

	@Override
	public ISheet getSheet() {
		return this.book._sheets.get(raw.getSheet());
	}

	@Override
	public void setRaw(Row raw) {
		this.raw = raw;
	}

}

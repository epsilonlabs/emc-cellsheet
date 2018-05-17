package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelSheet implements ISheet, HasRaw<Sheet>{
	
	protected ExcelBook book;
	protected Sheet raw;

	ExcelSheet(ExcelBook book, Sheet sheet) {
		this.book = book;
		this.raw = sheet;
	}

	@Override
	public ExcelBook getBook() {
		return this.book;
	}

	@Override
	public String getId() {
		return book.getIDResolver().getID(this);
	}

	@Override
	public int getIndex() {
		return book.getRaw().getSheetIndex(this.raw);
	}

	@Override
	public String getName() {
		return raw.getSheetName();
	}

	@Override
	public Sheet getRaw() {
		return this.raw;
	}

	@Override
	public IRow getRow(int rowIdx) {
		return book.getRow(this, rowIdx);
	}

	@Override
	public Iterator<IRow> rowIterator() {
		return rows().iterator();
	}
	
	@Override
	public List<IRow> rows() {
		return book._rows.values().stream()
			.filter(r -> this.equals(r.getSheet()))
			.sorted()
			.collect(Collectors.toList());
	}

	@Override
	public void setId() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setRaw(Sheet raw) {
		this.raw = raw;
	}

	@Override
	public int compareTo(ISheet o) {
		if (o == null) return 1;
		if (this == o) return 0;
		return Integer.compare(this.getIndex(), o.getIndex());
	}
}

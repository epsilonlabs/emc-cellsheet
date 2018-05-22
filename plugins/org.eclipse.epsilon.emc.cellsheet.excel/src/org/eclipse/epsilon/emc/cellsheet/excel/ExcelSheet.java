package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelSheet implements ISheet, HasRaw<Sheet> {

	protected ExcelBook book;
	protected Sheet raw;

	ExcelSheet(ExcelBook book, Sheet sheet) {
		this.book = book;
		this.raw = sheet;
	}

	@Override
	public int compareTo(ISheet o) {
		if (o == null)
			return 1;
		if (this == o)
			return 0;
		return Integer.compare(this.getIndex(), o.getIndex());
	}

	@Override
	public ExcelBook getBook() {
		return this.book;
	}

	@Override
	public String getId() {
		return this.book.getIDResolver().getID(this);
	}

	@Override
	public int getIndex() {
		return this.book.getRaw().getSheetIndex(this.raw);
	}

	@Override
	public String getName() {
		return this.raw.getSheetName();
	}

	@Override
	public Sheet getRaw() {
		return this.raw;
	}

	@Override
	public ExcelRow getRow(int rowIdx) {
		return this.book.getRow(this.raw.getRow(rowIdx));
	}

	@Override
	public Iterator<IRow> iterator() {
		return new TransformIterator<ExcelRow, IRow>(this.rowIterator(), 
				new Transformer<ExcelRow, IRow>() {
					@Override
					public IRow transform(ExcelRow input) {
						return input;
					}
				});
	}

	@Override
	public Iterator<ExcelRow> rowIterator() {
		return this.rows().iterator();
	}

	@Override
	public List<ExcelRow> rows() {
		this.raw.rowIterator().forEachRemaining(r -> this.book.getRow(r));
		return this.book._rows.values().stream().filter(r -> this.equals(r.getSheet())).sorted()
				.collect(Collectors.toList());
	}

	@Override
	public void setRaw(Sheet raw) {
		this.raw = raw;
	}

	@Override
	public String toString() {
		return this.getId();
	}
}

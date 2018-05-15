package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.epsilon.emc.cellsheet.AbstractSheet;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelSheet extends AbstractSheet implements ISheet, HasRaw<Sheet> {

	protected Sheet raw;

	public ExcelSheet(IBook book, Sheet sheet) {
		super(book);
		this.raw = sheet;
	}

	@Override
	public ExcelBook getBook() {
		return (ExcelBook) this.book;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getIndex() {
		return this.raw.getWorkbook().getSheetIndex(this.raw);
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
	public IRow getRow(int rowIdx) {
		if (rowIdx < 0)
			throw new IndexOutOfBoundsException();
		return new ExcelRow(this, this.raw.getRow(rowIdx));
	}

	@Override
	public Iterator<IRow> rowIterator() {
		return new TransformIterator<Row, IRow>(this.raw.rowIterator(), new Transformer<Row, ExcelRow>() {
			@Override
			public ExcelRow transform(Row row) {
				return new ExcelRow(ExcelSheet.this, row);
			}
		});
	}

	@Override
	public List<IRow> rows() {
		final List<IRow> rows = new ArrayList<IRow>();
		final Iterator<IRow> it = rowIterator();

		while (it.hasNext()) {
			final ExcelRow next = (ExcelRow) it.next();
			rows.add(next);
		}

		return rows;
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
	public String toString() {
		return this.book.toString() + "::" + this.getName();
	}
}

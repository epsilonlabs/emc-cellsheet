package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.epsilon.emc.cellsheet.HasDelegate;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelSheet implements ISheet, HasDelegate<Sheet> {

	protected ExcelBook book;
	protected Sheet delegate;

	ExcelSheet(ExcelBook book, Sheet sheet) {
		this.book = book;
		this.delegate = sheet;
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
		return this.book.getDelegate().getSheetIndex(this.delegate);
	}

	@Override
	public String getName() {
		return this.delegate.getSheetName();
	}

	@Override
	public Sheet getDelegate() {
		return this.delegate;
	}

	@Override
	public ExcelRow getRow(int rowIdx) {
		return this.book.getRow(this.delegate.getRow(rowIdx));
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
		this.delegate.rowIterator().forEachRemaining(r -> this.book.getRow(r));
		return this.book._rows.values().stream().filter(r -> this.equals(r.getSheet())).sorted()
				.collect(Collectors.toList());
	}

	@Override
	public void setDelegate(Sheet delegate) {
		this.delegate = delegate;
	}

	@Override
	public String toString() {
		return this.getId();
	}
}

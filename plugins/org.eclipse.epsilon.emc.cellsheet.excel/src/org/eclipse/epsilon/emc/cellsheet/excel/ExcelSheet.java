package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
		return book;
	}

	@Override
	public String getId() {
		return book.getId() + "/" + getName();
	}

	@Override
	public int getIndex() {
		return book.getDelegate().getSheetIndex(this.delegate);
	}

	@Override
	public String getName() {
		return delegate.getSheetName();
	}

	@Override
	public Sheet getDelegate() {
		return delegate;
	}

	@Override
	public ExcelRow getRow(int rowIdx) {
		return book.getRow(this, rowIdx);
	}

	@Override
	public Iterator<IRow> iterator() {
		return new TransformIterator<Row, IRow>(delegate.iterator(), new Transformer<Row, IRow>() {
			@Override
			public IRow transform(Row row) {
				return ExcelSheet.this.getRow(row.getRowNum());
			}
		});
	}

	@Override
	public List<IRow> rows() {
		return IteratorUtils.toList(iterator());
	}

	@Override
	public String toString() {
		return getId();
	}
}

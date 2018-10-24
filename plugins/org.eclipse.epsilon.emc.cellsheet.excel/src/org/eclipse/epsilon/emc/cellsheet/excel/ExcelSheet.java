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
import org.eclipse.epsilon.emc.cellsheet.IdUtil;

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
		return IdUtil.getId(this);
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
		return this.book.getRow(this, rowIdx);
	}

	@Override
	public Iterator<IRow> iterator() {
		return new TransformIterator<Row, IRow>(delegate.iterator(), new Transformer<Row, ExcelRow>() {
			@Override
			public ExcelRow transform(Row row) {
				return book.getRow(row);
			}
		});
	}

	@Override
	public List<IRow> rows() {
		return IteratorUtils.toList(iterator());
	}

	@Override
	public String toString() {
		return this.getId();
	}
}

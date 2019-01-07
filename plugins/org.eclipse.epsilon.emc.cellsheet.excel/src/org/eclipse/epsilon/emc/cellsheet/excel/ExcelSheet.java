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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((delegate == null) ? 0 : delegate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelSheet other = (ExcelSheet) obj;
		if (delegate == null) {
			if (other.delegate != null)
				return false;
		} else if (!delegate.equals(other.delegate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getClass().getSimpleName()).append("@").append(hashCode()).append("]");
		sb.append("(id: ").append(getId());
		sb.append(", excelRef: ").append(getA1Ref());
		sb.append(")");
		return sb.toString();
	}
}

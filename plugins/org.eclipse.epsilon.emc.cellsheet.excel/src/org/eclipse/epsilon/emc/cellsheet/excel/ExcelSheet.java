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
	protected int index;

	ExcelSheet(ExcelBook book, int index) {
		this.book = book;
		this.index = index;
	}

	@Override
	public ExcelBook getBook() {
		return book;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public String getName() {
		return getDelegate().getSheetName();
	}

	@Override
	public ExcelRow getRow(int row) {
		if (row < 0) {
			throw new IndexOutOfBoundsException("index must be positive, was given: " + row);
		}
		if (getDelegate().getRow(row) == null) {
			getDelegate().createRow(row);
		}
		return new ExcelRow(this, row);
	}

	@Override
	public Iterator<IRow> iterator() {
		return new TransformIterator<Row, IRow>(getDelegate().iterator(), new Transformer<Row, IRow>() {
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
	public Sheet getDelegate() {
		return book.getDelegate().getSheetAt(index);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + index;
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
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (index != other.index)
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

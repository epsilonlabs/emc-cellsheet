package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.labs.emc.cellsheet.AbstractSheet;
import org.eclipse.epsilon.labs.emc.cellsheet.CoreType;
import org.eclipse.epsilon.labs.emc.cellsheet.IRow;

import com.google.common.collect.Iterators;

public class ExcelSheet extends AbstractSheet implements HasDelegate<Sheet> {

	public ExcelSheet() {
		super();
	}

	public ExcelSheet(ExcelSheet.Builder b) {
		super(b);
	}
	
	@Override
	public Sheet getDelegate() {
		return getBook().getDelegate().getSheetAt(index);
	}

	@Override
	public ExcelBook getBook() {
		return (ExcelBook) book;
	}

	@Override
	public ExcelRow getRow(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("index must be positive, was given: " + index);
		}

		// Book is cached, filter for row from cache
		if (getBook().isCached()) {
			try {
				return getBook().getAllOfType(CoreType.ROW)
								.stream()
								.map(r -> (ExcelRow) r)
								.filter(r -> r.getIndex() == index && r.getSheet().equals(this))
								.findFirst()
								.orElse(null);
			} catch (EolModelElementTypeNotFoundException e) {
				throw new AssertionError(e);
			}
		}

		final Row row = getDelegate().getRow(index);
		return row == null ? null : new ExcelRow.Builder().withSheet(this).withIndex(index).build();
	}

	@Override
	public Iterator<IRow> iterator() {
		// Cached
		if (getBook().isCached()) {
			try {
				return getBook().getAllOfType(CoreType.ROW)
								.stream()
								.map(r -> (IRow) r)
								.filter(r -> r.getSheet().equals(this))
								.iterator();
			} catch (EolModelElementTypeNotFoundException e) {
				throw new AssertionError(e);
			}
		}

		// Not cached
		final ExcelRow.Builder b = new ExcelRow.Builder().withSheet(this);
		return Iterators.transform(getDelegate().iterator(), r -> b.withIndex(r.getRowNum()).build());
	}

	@Override
	public List<IRow> rows() {
		return IteratorUtils.toList(iterator());
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

	public static class Builder extends AbstractSheet.Builder<ExcelSheet, Builder> {

		@Override
		public ExcelSheet build() {
			if (this.book.getClass() != ExcelBook.class) {
				throw new IllegalArgumentException("Book must be of type " + ExcelBook.class.getCanonicalName());
			}
			return new ExcelSheet(this);
		}
	}

}

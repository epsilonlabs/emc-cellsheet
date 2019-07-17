package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.labs.emc.cellsheet.AbstractRow;
import org.eclipse.epsilon.labs.emc.cellsheet.CoreType;
import org.eclipse.epsilon.labs.emc.cellsheet.ICell;

import com.google.common.collect.Iterators;

public class ExcelRow extends AbstractRow implements HasDelegate<Row> {

	public ExcelRow() {
		;
	}

	public ExcelRow(ExcelRow.Builder b) {
		super(b);
	}

	@Override
	public Row getDelegate() {
		return getSheet().getDelegate().getRow(index);
	}

	@Override
	public ExcelBook getBook() {
		return (ExcelBook) super.getBook();
	}

	@Override
	public ExcelSheet getSheet() {
		return (ExcelSheet) sheet;
	}

	@Override
	public ExcelCell getCell(int col) {
		if (col < 0) {
			throw new IndexOutOfBoundsException("col index must be positive, was given: " + col);
		}

		// Cached
		if (getBook().isCached()) {
			try {
				return getBook().getAllOfType(CoreType.CELL)
								.stream()
								.map(c -> (ExcelCell) c)
								.filter(c -> c.getColIndex() == col && c.getRow().equals(this))
								.findFirst()
								.orElse(null);
			} catch (EolModelElementTypeNotFoundException e) {
				throw new AssertionError(e);
			}
		}

		final Cell cell = getDelegate().getCell(col);
		return cell == null ? null : new ExcelCell.Builder().withRow(this).withCol(col).build();
	}

	@Override
	public Iterator<ICell> iterator() {
		// Cached
		if (getBook().isCached()) {
			try {
				return getBook().getAllOfType(CoreType.CELL)
								.stream()
								.map(c -> (ICell) c)
								.filter(c -> c.getRow().equals(this))
								.iterator();
			} catch (EolModelElementTypeNotFoundException e) {
				throw new AssertionError(e);
			}
		}

		// Not Cached
		final ExcelCell.Builder b = new ExcelCell.Builder().withRow(this).withCol(index);
		return Iterators.transform(getDelegate().iterator(), c -> b.withRow(this).withCol(c.getColumnIndex()).build());
	}

	@Override
	public List<ICell> cells() {
		return IteratorUtils.toList(iterator());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result + ((sheet == null) ? 0 : sheet.hashCode());
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
		ExcelRow other = (ExcelRow) obj;
		if (index != other.index)
			return false;
		if (sheet == null) {
			if (other.sheet != null)
				return false;
		} else if (!sheet.equals(other.sheet))
			return false;
		return true;
	}

	public static class Builder extends AbstractRow.Builder<ExcelRow, Builder> {
		@Override
		public ExcelRow build() {
			if (this.sheet.getClass() != ExcelSheet.class) {
				throw new IllegalArgumentException("Sheet must be of type " + ExcelSheet.class.getCanonicalName());
			}
			return new ExcelRow(this);
		}
	}
}

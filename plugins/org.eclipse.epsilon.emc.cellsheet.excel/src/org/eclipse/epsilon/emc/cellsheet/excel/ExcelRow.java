package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.epsilon.emc.cellsheet.AbstractRow;
import org.eclipse.epsilon.emc.cellsheet.ICell;

public class ExcelRow extends AbstractRow implements HasDelegate<Row> {

	protected ExcelSheet sheet;
	protected int index;

	ExcelRow(ExcelSheet sheet, int index) {
		this.sheet = sheet;
		this.index = index;
	}

	@Override
	public List<ICell> cells() {
		return IteratorUtils.toList(iterator());
	}

	@Override
	public ExcelCell getCell(int col) {
		if (col < 0) {
			throw new IndexOutOfBoundsException("col index must be positive, was given: " + col);
		}
		return new ExcelCell(this, col);
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public Row getDelegate() {
		return sheet.getDelegate().getRow(index);
	}

	@Override
	public ExcelSheet getSheet() {
		return sheet;
	}

	@Override
	public Iterator<ICell> iterator() {
		return new TransformIterator<Cell, ICell>(getDelegate().iterator(), new Transformer<Cell, ExcelCell>() {
			@Override
			public ExcelCell transform(Cell cell) {
				return ExcelRow.this.getCell(cell.getColumnIndex());
			}
		});
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

}

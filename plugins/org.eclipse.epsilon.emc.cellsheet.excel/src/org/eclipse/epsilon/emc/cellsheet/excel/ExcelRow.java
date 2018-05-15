package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.epsilon.emc.cellsheet.AbstractRow;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

public class ExcelRow extends AbstractRow implements HasRaw<Row> {

	protected Row raw;

	public ExcelRow(ISheet sheet, Row raw) {
		super(sheet);
		this.raw = raw;
	}

	@Override
	public int getIndex() {
		return this.raw.getRowNum();
	}

	@Override
	public Row getRaw() {
		return this.raw;
	}

	@Override
	public void setRaw(Row raw) {
		this.raw = raw;
	}

	@Override
	public String toString() {
		return this.getSheet().toString() + "!-1$" + this.getIndex();
	}

	@Override
	public List<ICell> cells() {
		final List<ICell> cells = new ArrayList<ICell>();
		final Iterator<ICell> it = this.cellIterator();

		while (it.hasNext()) {
			final ICell next = it.next();
			cells.add(next);
		}

		return cells;
	}

	@Override
	public ICell getCell(int colIdx) {
		if (colIdx < 0)
			throw new IndexOutOfBoundsException();
		final Cell cell = this.raw.getCell(colIdx);
		return cell == null ? null : new ExcelCell(this.sheet, this.raw.getCell(colIdx));
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId() {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<ICell> cellIterator() {
		return new TransformIterator<Cell, ICell>(this.raw.cellIterator(), new Transformer<Cell, ExcelCell>() {
			@Override
			public ExcelCell transform(Cell cell) {
				return new ExcelCell(ExcelRow.this.sheet, cell);
			}
		});
	}

}

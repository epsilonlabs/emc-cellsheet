package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.emc.cellsheet.HasDelegate;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.IdUtil;

public class ExcelRow implements IRow, HasDelegate<Row> {

	protected ExcelBook book;
	protected ExcelSheet sheet;
	protected Row delegate;

	ExcelRow(ExcelSheet sheet, Row delegate) {
		this.book = sheet.getBook();
		this.sheet = sheet;
		this.delegate = delegate;
	}

	@Override
	public Iterator<ExcelCell> cellIterator() {
		return cells().iterator();
	}

	@Override
	public List<ExcelCell> cells() {
		final List<ExcelCell> cells = new ArrayList<>();
		this.delegate.cellIterator().forEachRemaining(c -> cells.add(book.getCell(this, c.getColumnIndex())));
		return cells;
	}

	@Override
	public ExcelBook getBook() {
		return this.book;
	}

	@Override
	public ExcelCell getCell(int colIdx) {
		return book.getCell(this, colIdx);
	}

	@Override
	public ExcelCell getCell(String column) {
		return this.getCell(CellReference.convertColStringToIndex(column));
	}

	@Override
	public String getId() {
		return IdUtil.getId(this);
	}

	@Override
	public int getIndex() {
		return this.delegate.getRowNum();
	}

	@Override
	public Row getDelegate() {
		return this.delegate;
	}

	@Override
	public ExcelSheet getSheet() {
		return sheet;
	}

	@Override
	public void setDelegate(Row delegate) {
		this.delegate = delegate;
	}

	@Override
	public Iterator<ICell> iterator() {
		return new TransformIterator<ExcelCell, ICell>(this.cellIterator(), new Transformer<ExcelCell, ICell>() {
			@Override
			public ICell transform(ExcelCell input) {
				return input;
			}
		});
	}

	@Override
	public String toString() {
		return this.getId();
	}

}

package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
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
	public List<ICell> cells() {
		return IteratorUtils.toList(iterator());
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
	public Iterator<ICell> iterator() {
		return new TransformIterator<Cell, ICell>(delegate.iterator(), new Transformer<Cell, ExcelCell>() {
			@Override
			public ExcelCell transform(Cell input) {
				return book.getCell(input);
			}
		});
	}

	@Override
	public String toString() {
		return this.getId();
	}

}

package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Row;
import org.eclipse.epsilon.emc.cellsheet.HasDelegate;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IRow;

public class ExcelRow implements IRow, HasDelegate<Row> {

	protected ExcelBook book;
	protected Row delegate;

	ExcelRow(ExcelBook book, Row delegate) {
		this.book = book;
		this.delegate = delegate;
	}

	@Override
	public Iterator<ExcelCell> cellIterator() {
		return cells().iterator();
	}

	@Override
	public List<ExcelCell> cells() {
		this.delegate.cellIterator().forEachRemaining(c -> this.book.getCell(c));
		return book._cells.values().stream()
				.filter(c -> this.equals(c.getRow()))
				.sorted()
				.collect(Collectors.toList());
	}

	@Override
	public ExcelBook getBook() {
		return this.book;
	}

	@Override
	public ExcelCell getCell(int colIdx) {
		return this.book.getCell(this.delegate.getCell(colIdx));
	}

	@Override
	public String getId() {
		return this.getBook()._idResolver.getId(this);
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
		return this.book._sheets.get(delegate.getSheet());
	}

	@Override
	public void setDelegate(Row delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public Iterator<ICell> iterator() {
		return new TransformIterator<ExcelCell, ICell>(this.cellIterator(), 
				new Transformer<ExcelCell, ICell>() {
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

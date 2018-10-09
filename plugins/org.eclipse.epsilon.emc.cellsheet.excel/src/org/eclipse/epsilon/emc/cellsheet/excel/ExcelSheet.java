package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		return this.rows().iterator();
	}

	@Override
	public Iterator<IRow> rowIterator() {
		return this.rows().iterator();
	}

	@Override
	public List<IRow> rows() {
		final List<IRow> rows = new ArrayList<>();
		this.delegate.rowIterator().forEachRemaining(r -> rows.add(this.book.getRow(this, r.getRowNum())));
		return rows;
	}

	@Override
	public String toString() {
		return this.getId();
	}
}

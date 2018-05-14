package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractCell implements ICell {

	protected IBook book;
	protected ISheet sheet;
	protected IRow row;
	protected IColumn column;

	protected AbstractCell(ISheet sheet, IRow row, IColumn column) {
		this.sheet = sheet;
		this.row = row;
		this.column = column;
	}

	@Override
	public ISheet getSheet() {
		return this.sheet;
	}

	@Override
	public IColumn getColumn() {
		return this.column;
	}

	@Override
	public IRow getRow() {
		return this.row;
	}

}

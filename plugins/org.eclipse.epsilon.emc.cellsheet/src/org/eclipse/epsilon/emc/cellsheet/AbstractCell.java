package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractCell implements ICell {

	protected IBook book;
	protected ISheet sheet;
	protected IRow row;

	protected AbstractCell(ISheet sheet, IRow row) {
		this.book = sheet.getBook();
		this.sheet = sheet;
		this.row = row;
	}

	@Override
	public ISheet getSheet() {
		return this.sheet;
	}


	@Override
	public IRow getRow() {
		return this.row;
	}

}

package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractSheet implements ISheet {

	protected IBook book;

	protected AbstractSheet(IBook book) {
		this.book = book;
	}

	@Override
	public IBook getBook() {
		return this.book;
	}

	@Override
	public void setBook(IBook book) {
		this.book = book;
	}

}

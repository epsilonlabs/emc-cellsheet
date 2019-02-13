package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractCellValue implements ICellValue {

	@Override
	public IRow getRow() {
		return getCell().getRow();
	}

	@Override
	public ISheet getSheet() {
		return getCell().getSheet();
	}

	@Override
	public IBook getBook() {
		return getCell().getBook();
	}

	@Override
	public String getId() {
		return String.format("%svalue/", getCell().getId());
	}

}

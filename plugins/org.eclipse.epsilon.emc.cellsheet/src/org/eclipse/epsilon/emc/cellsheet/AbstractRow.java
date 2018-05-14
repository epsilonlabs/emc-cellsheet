package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractRow implements IRow {

	protected ISheet sheet;

	protected AbstractRow(ISheet sheet) {
		this.sheet = sheet;
	}

	@Override
	public ISheet getSheet() {
		return this.sheet;
	}

}

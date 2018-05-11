package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

public abstract class AbstractRow implements IRow {

	protected ISheet sheet;

	protected AbstractRow(ISheet sheet) {
		this.sheet = sheet;
	}

	@Override
	public List<ICell> cells() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ICell getCell(int colIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISheet getSheet() {
		return this.sheet;
	}

	@Override
	public void setId() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSheet(ISheet sheet) {
		this.sheet = sheet;
	}

}

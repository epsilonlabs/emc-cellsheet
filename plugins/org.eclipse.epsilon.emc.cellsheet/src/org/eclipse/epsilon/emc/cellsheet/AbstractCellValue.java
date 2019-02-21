package org.eclipse.epsilon.emc.cellsheet;

import java.util.Collections;
import java.util.Iterator;

public abstract class AbstractCellValue implements ICellValue {

	protected CellValueType type;

	protected AbstractCellValue() {
		this(CellValueType.NONE);
	}

	protected AbstractCellValue(CellValueType type) {
		this.type = type;
	}

	@Override
	public CellValueType getType() {
		return type;
	}

	@Override
	public void setType(CellValueType type) {
		this.type = type;
	}

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

	@Override
	public int compareTo(ICellValue o) {
		return getCell().compareTo(o.getCell());
	}

	@Override
	public Iterator<IAst> iterator() {
		return Collections.singleton(getAst()).iterator();
	}

	@Override
	public String toString() {
		return buildToString();
	}
}

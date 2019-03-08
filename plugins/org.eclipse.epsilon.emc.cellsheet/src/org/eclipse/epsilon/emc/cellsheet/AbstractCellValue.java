package org.eclipse.epsilon.emc.cellsheet;

import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

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
		return getCell().getId() + "value/";
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

	@Override
	public int hashCode() {
		return Objects.hash(type, getCell());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractCellValue other = (AbstractCellValue) obj;
		return type == other.type // Quick check if super type same
				&& Objects.equals(getCell(), other.getCell()); // Parents
	}

}

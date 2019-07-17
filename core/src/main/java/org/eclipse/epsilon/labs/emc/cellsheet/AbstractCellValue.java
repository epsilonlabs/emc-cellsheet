package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;

public abstract class AbstractCellValue implements ICellValue {

	protected ICell cell;
	protected IAst<?> ast;

	protected CellValueType type;
	protected String formula;
	protected Object value;

	protected AbstractCellValue() {
		;
	}

	protected AbstractCellValue(AbstractCellValue.Builder<?, ?> b) {
		this.cell = b.cell;
		this.type = b.type;
		this.formula = b.formula;
		this.value = b.value;
	}

	@Override
	public boolean getBooleanValue() {
		return type == CellValueType.BOOLEAN || type == CellValueType.FORMULA ? (boolean) value : false;
	}

	@Override
	public double getNumericValue() {
		return type == CellValueType.NUMERIC || type == CellValueType.FORMULA ? (double) value : 0;
	}

	@Override
	public Date getDateValue() {
		return type == CellValueType.DATE ? (Date) value : null;
	}

	@Override
	public String getStringValue() {
		return value == null ? "" : String.valueOf(value);
	}

	@Override
	public String getErrorValue() {
		return type == CellValueType.ERROR ? getStringValue() : null;
	}

	@Override
	public String getFormula() {
		if (type == CellValueType.FORMULA)
			return formula;
		if (type == CellValueType.STRING || type == CellValueType.DATE)
			return String.format("\"%s\"", value);
		return getStringValue();
	}

	@Override
	public IAst<?> getAst() {
		if (ast == null)
			this.ast = initAst();
		return this.ast;
	}

	protected abstract IAst<?> initAst();

	@Override
	public ICell getCell() {
		return this.cell;
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
	public Iterator<IAst<?>> iterator() {
		return Collections.<IAst<?>>singleton(getAst()).iterator();
	}

	@Override
	public String toString() {
		return buildToString();
	}

	@Override
	public boolean isBlank() {
		return type == CellValueType.BLANK || type == CellValueType.NONE || getStringValue().isEmpty();
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

	@SuppressWarnings("unchecked")
	public static abstract class Builder<T extends AbstractCellValue, B extends Builder<T, B>> {

		protected ICell cell;

		protected CellValueType type = CellValueType.NONE;
		protected String formula = null;
		protected Object value = null;

		public B withCell(ICell cell) {
			this.cell = cell;
			return (B) this;
		}

		public B withType(CellValueType type) {
			this.type = type;
			return (B) this;
		}

		public B withFormula(String formula) {
			this.formula = formula;
			return (B) this;
		}

		public B withValue(Object value) {
			this.value = value;
			return (B) this;
		}

		public abstract T build();
	}
}

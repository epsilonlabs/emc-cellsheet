package org.eclipse.epsilon.emc.cellsheet;

public interface ICell extends HasId, Comparable<ICell> {

	public static final Type TYPE = Type.CELL;
	public static final Type[] KINDS = { TYPE };

	public int getColIndex();
	public String getCol();
	public int getRowIndex();
	
	public ICellValue<?> getCellValue();
	public IBooleanCellValue getBooleanCellValue();
	public IFormulaCellValue getFormulaCellValue();
	public IStringCellValue getStringCellValue();
	public INumericCellValue getNumericCellValue();
	
	public boolean isBlank();

	public IRow getRow();
	public ISheet getSheet();
	public IBook getBook();

	@Override
	default int compareTo(ICell o) {
		if (null == o)
			return 1;
		if (this == o)
			return 0;

		int parent = this.getRow().compareTo(o.getRow());
		return parent == 0 ? Integer.compare(this.getColIndex(), o.getColIndex()) : parent;
	}

	@Override
	default Type getType() {
		return ICell.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return ICell.KINDS;
	}
	
	@Override
	default String getId() {
		return getRow().getId() + getColIndex() + "/";
	}
	
	/**
	 * @return common reference for a cell in A1 style notation with sheet and book name
	 */
	default String getExternalRef() {
		return getExternalRef(false);
	}
	
	/**
	 * @return common reference for a cell in A1 absolute style notation with sheet and book names
	 */
	default String getExternalRef(boolean absolute) {
		StringBuilder sb = new StringBuilder(getSheet().getExternalRef());
		sb.append("!");
		if (absolute) sb.append("$");
		sb.append(getCol());
		if (absolute) sb.append("$");
		sb.append(getRowIndex()+1);
		return sb.toString();
	}
}

package org.eclipse.epsilon.emc.cellsheet;

public enum CellValueType implements ElementType {

	NONE("NoneCellValue"),
	NUMERIC("NumericCellValue"),
	STRING("StringCellValue"),
	FORMULA("FormulaCellValue"),
	BOOLEAN("BooleanCellValue"),
	BLANK("BlankCellValue"),
	ERROR("ErrorCellValue"),
	DATE("DateCellValue");

	private final String typename;

	private CellValueType(String typename) {
		this.typename = typename;
		ElementType.addToMap(typename, this);
	}

	@Override
	public String toString() {
		return this.typename;
	}

	@Override
	public String getTypename() {
		return this.typename;
	}

	/**
	 * Get a {@link CellValueType} from it's String value
	 * 
	 * @param typename
	 * @return
	 */
	public static CellValueType fromTypename(String typename) {
		return (CellValueType) ElementType.fromTypename(typename);
	}
}

package org.eclipse.epsilon.emc.cellsheet;

public enum CellValueType implements ElementType {

	SUPER("CellValue"),
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
		for (CellValueType type : CellValueType.values()) {
			if (type.typename.equals(typename))
				return type;
		}
		return null;
	}
}

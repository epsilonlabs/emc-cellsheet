package org.eclipse.epsilon.emc.cellsheet;

public enum CoreType implements ElementType {

	// Core structural types
	BOOK("Book"),
	SHEET("Sheet"),
	ROW("Row"),
	CELL("Cell"),
	CELL_VALUE("CellValue"),
	AST("AST");

	private final String typename;

	private CoreType(String typename) {
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
	 * Get a {@link CoreType} from it's String value
	 * 
	 * @param typename
	 * @return
	 */
	public static CoreType fromTypename(String typename) {
		return (CoreType) ElementType.fromTypename(typename);
	}

}

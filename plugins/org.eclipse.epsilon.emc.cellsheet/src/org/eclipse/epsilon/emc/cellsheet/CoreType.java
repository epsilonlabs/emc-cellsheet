package org.eclipse.epsilon.emc.cellsheet;

public enum CoreType implements ElementType {

	// Core structural types
	BOOK("Book"), SHEET("Sheet"), ROW("Row"), CELL("Cell");

	private final String typename;

	private CoreType(String typename) {
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
	 * Get a {@link CoreType} from it's String value
	 * 
	 * @param typename
	 * @return
	 */
	public static CoreType fromTypename(String typename) {
		for (CoreType type : CoreType.values()) {
			if (type.typename.equals(typename))
				return type;
		}
		return null;
	}

}

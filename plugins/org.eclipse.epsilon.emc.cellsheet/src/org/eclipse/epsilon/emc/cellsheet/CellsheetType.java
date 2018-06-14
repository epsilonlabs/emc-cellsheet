package org.eclipse.epsilon.emc.cellsheet;

public enum CellsheetType {

	BOOK("Book"),
	SHEET("Sheet"),
	ROW("Row"),
	CELL("Cell");
	
	private final String typename;
	
	private CellsheetType(String typename) {
		this.typename = typename;
	}
	
	@Override
	public String toString() {
		return this.typename;
	}
	
	public String getTypeName() {
		return this.typename;
	}
	
	public static CellsheetType fromTypeName(String typename) {
		for (CellsheetType ct : CellsheetType.values()) {
			if (ct.typename.equals(typename)) return ct;
		}
		return null;
	}
}

package org.eclipse.epsilon.emc.cellsheet;

public enum Type {

	BOOK("Book"),
	SHEET("Sheet"),
	ROW("Row"),
	CELL("Cell"),
	
	// Cell Value Types
	CELL_VALUE("CellValue"),
	BOOLEAN_CELL_VALUE("BooleanCellValue"),
	STRING_CELL_VALUE("StringCellValue"),
	NUMERIC_CELL_VALUE("NumericCellValue"),
	FORMULA_CELL_VALUE("FormulaCellValue"),
	BLANK_CELL_VALUE("BlankCellValue"),
	
	// Related to Formula
	FORMULA_TREE("FormulaTree");
	
	private final String typename;
	
	private Type(String typename) {
		this.typename = typename;
	}
	
	@Override
	public String toString() {
		return this.typename;
	}
	
	public String getTypeName() {
		return this.typename;
	}
	
	public static Type fromTypeName(String typename) {
		for (Type ct : Type.values()) {
			if (ct.typename.equals(typename)) return ct;
		}
		return null;
	}
}

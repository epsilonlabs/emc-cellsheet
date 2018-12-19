package org.eclipse.epsilon.emc.cellsheet;

public enum Type {

	// @formatter:off
	BOOK("Book"),
	SHEET("Sheet"),
	ROW("Row"),
	CELL("Cell"),

	// Cell Value Types
	CELL_VALUE("CellValue"),
	BOOLEAN_CELL_VALUE("BooleanCellValue"),
	STRING_CELL_VALUE("StringCellValue"),
	NUMERIC_CELL_VALUE("NumericCellValue"),
	DATE_CELL_VALUE("DateCellValue"),
	FORMULA_CELL_VALUE("FormulaCellValue"),
	ERROR_CELL_VALUE("ErrorCellValue"),
	BLANK_CELL_VALUE("BlankCellValue"),

	// Related to Formula
	FORMULA_TREE("FormulaTree"),
	
	NUMERIC_VALUE_NODE("NumericValueNode"),
	INT_VALUE_NODE("IntValueNode"),
	DOUBLE_VALUE_NODE("DoubleValueNode"),
	STRING_VALUE_NODE("StringValueNode"),
	BOOLEAN_VALUE_NODE("BooleanValueNode"),
	CELL_REF_NODE("CellRefNode"),
	ARRAY_REF_NODE("ArrayRefNode"),
	OPERATOR_NODE("OperatorNode"),
	FUNCTION_NODE("FunctionNode"),
	UNKNOWN_NODE("UnknownNode");
	// @formatter:on

	private final String name;

	private Type(String typename) {
		this.name = typename;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public String getName() {
		return this.name;
	}

	public static Type fromName(String typename) {
		for (Type ct : Type.values()) {
			if (ct.name.equals(typename))
				return ct;
		}
		return null;
	}
}

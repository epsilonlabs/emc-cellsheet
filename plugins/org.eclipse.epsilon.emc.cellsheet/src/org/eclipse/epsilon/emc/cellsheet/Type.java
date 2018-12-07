package org.eclipse.epsilon.emc.cellsheet;

public enum Type {

	// @formatter:off
	BOOK("Book"), SHEET("Sheet"), ROW("Row"), CELL("Cell"),

	// Cell Value Types
	CELL_VALUE("CellValue"), BOOLEAN_CELL_VALUE("BooleanCellValue"), STRING_CELL_VALUE("StringCellValue"),
	NUMERIC_CELL_VALUE("NumericCellValue"), DATE_CELL_VALUE("DateCellValue"), FORMULA_CELL_VALUE("FormulaCellValue"), ERROR_CELL_VALUE("ErrorCellValue"), BLANK_CELL_VALUE("BlankCellValue"),

	// Related to Formula
	FORMULA_TREE("FormulaTree"),

	FORMULA_TOKEN("FormulaToken"), AREA_REF_TOKEN("AreaRefToken"), CELL_REF_TOKEN("CellRefToken"),
	FUNCTION_TOKEN("FunctionToken"), OPERATION_TOKEN("OperationToken"), OPERAND_TOKEN("OperandToken"),
	NUMERIC_TOKEN("NumericToken"), STRING_TOKEN("StringToken");
	// @formatter:off

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

package org.eclipse.epsilon.emc.cellsheet;

import org.eclipse.epsilon.eol.models.IModel;

/**
 * <p>
 * Enumeration of all model element types in a Cellsheet model
 * </p>
 * 
 * <p>
 * String values defined in each enum are used to reference these types
 * externally in EOL scripts or methods such as {@link IModel#hasType(String)}
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public enum Type {

	// Core structural types
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

	// Formula and related types
	FORMULA_TREE("FormulaTree"),
	// Sub-types for use with sub-trees in formula AST's
	@Deprecated
	ROOT_NODE("RootNode"),
	@Deprecated
	NUMERIC_VALUE_NODE("NumericValueNode"),
	@Deprecated
	INT_VALUE_NODE("IntValueNode"),
	@Deprecated
	DOUBLE_VALUE_NODE("DoubleValueNode"),
	@Deprecated
	STRING_VALUE_NODE("StringValueNode"),
	@Deprecated
	BOOLEAN_VALUE_NODE("BooleanValueNode"),
	@Deprecated
	CELL_REF_NODE("CellRefNode"),
	@Deprecated
	ARRAY_REF_NODE("ArrayRefNode"),
	@Deprecated
	OPERATOR_NODE("OperatorNode"),
	@Deprecated
	FUNCTION_NODE("FunctionNode"),
	@Deprecated
	UNKNOWN_NODE("UnknownNode"),

	NOOP("Noop"),
	OPERAND("Operand"),
	FUNCTION("Function"),
	SUBEXPRESSION("Subexpresssion"),
	ARGUMENT("Argument"),
	OPERATOR_PREFIX("OperatorPrefix"),
	OPERATOR_INFIX("OperatorInfix"),
	OPERATOR_POSTFIX("OperatorPostfix"),
	WHITESPACE("Whitespace"),
	UNKNOWN("Unknown"),

	NOTHING("Nothing"),
	START("Start"),
	ARRAY_START("ArrayStart"),
	ARRAY_ROW_START("ArrayRowStart"),
	STOP("Stop"),
	TEXT("Text"),
	ARRAY_STOP("ArrayStop"),
	ARRAY_ROW_STOP("ArrayRowStop"),
	NUMBER("Number"),
	LOGICAL("Logical"),
	ERROR("Error"),
	RANGE("Range"),
	
	@Deprecated
	MATH("Math"),
	
	// New operators
	NEGATION("Negation"),
	PERCENT("Percent"),
	EXPONENTION("Exponenetion"),
	MULTIPLICATION("Multiplication"),
	DIVISION("Divsion"),
	ADDITION("Addition"),
	SUBTRACTION("Subtraction"),
	
	CONCATENATION("Concatenation"),
	
	EQ("EQ"),
	LT("LT"),
	GT("GT"),
	LTE("LTE"),
	GTE("GTE"),
	NEQ("NEQ"),
	
	INTERSECTION("Intersection"),
	UNION("Union");

	private final String typename;

	private Type(String typename) {
		this.typename = typename;
	}

	@Override
	public String toString() {
		return this.typename;
	}

	public String getTypename() {
		return this.typename;
	}

	/**
	 * Get a {@link Type} from it's String value
	 * @param typename
	 * @return
	 */
	public static Type fromTypename(String typename) {
		for (Type type : Type.values()) {
			if (type.typename.equals(typename))
				return type;
		}
		return null;
	}
	
}

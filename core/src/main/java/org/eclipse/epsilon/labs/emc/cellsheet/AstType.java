package org.eclipse.epsilon.labs.emc.cellsheet;

public enum AstType implements ElementType {

	// Default
	NOTHING("Nothing"), WHITESPACE("Whitespace"),

	// Operands
	TEXT("Text"), NUMBER("Number"), LOGICAL("Logical"), ERROR("Error"), RANGE("Range"), REF("Ref"),

	// Functions
	FUNCTION("Function"),

	// Operators
	PLUS("Plus"), NEGATION("Negation"), PERCENT("Percent"), EXPONENTION("Exponention"),
	MULTIPLICATION("Multiplication"), DIVISION("Division"), ADDITION("Addition"), SUBTRACTION("Subtraction"),

	CONCATENATION("Concatenation"),

	EQ("EQ"), LT("LT"), GT("GT"), LTE("LTE"), GTE("GTE"), NEQ("NEQ"),

	INTERSECTION("Intersection"), UNION("Union");

	private final String typename;

	private AstType(String typename) {
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
	 * Get a {@link AstType} from it's String value
	 * 
	 * @param typename
	 * @return
	 */
	public static AstType fromTypename(String typename) {
		return (AstType) ElementType.fromTypename(typename);
	}
}

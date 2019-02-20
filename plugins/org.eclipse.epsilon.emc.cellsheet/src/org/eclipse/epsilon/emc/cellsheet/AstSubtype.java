package org.eclipse.epsilon.emc.cellsheet;

public enum AstSubtype implements ElementType {

	// Default
	NOTHING("Nothing"),
	
	// Operands
	TEXT("Text"),
	NUMBER("Number"),
	LOGICAL("Logical"),
	ERROR("Error"),
	RANGE("Range"),
	REF("Ref"),

	// Operators
	PLUS("Plus"),
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

	private AstSubtype(String typename) {
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
	 * Get a {@link AstSubtype} from it's String value
	 * 
	 * @param typename
	 * @return
	 */
	public static AstSubtype fromTypename(String typename) {
		return (AstSubtype) ElementType.fromTypename(typename);
	}
}

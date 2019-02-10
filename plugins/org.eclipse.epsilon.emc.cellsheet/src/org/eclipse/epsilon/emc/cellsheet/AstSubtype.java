package org.eclipse.epsilon.emc.cellsheet;

public enum AstSubtype implements ElementType {

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
	REF("Ref"),

	@Deprecated
	MATH("Math"),

	// New operators
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
		for (AstSubtype type : AstSubtype.values()) {
			if (type.typename.equals(typename))
				return type;
		}
		return null;
	}
}

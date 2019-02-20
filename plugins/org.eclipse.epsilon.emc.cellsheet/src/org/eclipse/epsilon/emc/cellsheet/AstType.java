package org.eclipse.epsilon.emc.cellsheet;

public enum AstType implements ElementType {

	NOOP("Noop"),
	OPERAND("Operand"),
	FUNCTION("Function"),
	OPERATOR_PREFIX("OperatorPrefix"),
	OPERATOR_INFIX("OperatorInfix"),
	OPERATOR_POSTFIX("OperatorPostfix"),
	WHITESPACE("Whitespace"),
	UNKNOWN("Unknown");

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

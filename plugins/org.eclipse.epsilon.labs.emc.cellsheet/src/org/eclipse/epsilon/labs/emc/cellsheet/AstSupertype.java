package org.eclipse.epsilon.labs.emc.cellsheet;

public enum AstSupertype implements ElementType {

	NOOP("Noop"),
	OPERAND("Operand"),
	OPERATION("Operation"),
	OPERATOR_PREFIX("OperatorPrefix"),
	OPERATOR_INFIX("OperatorInfix"),
	OPERATOR_POSTFIX("OperatorPostfix"),
	UNKNOWN("Unknown");

	private final String typename;

	private AstSupertype(String typename) {
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
	 * Get a {@link AstSupertype} from it's String value
	 * 
	 * @param typename
	 * @return
	 */
	public static AstSupertype fromTypename(String typename) {
		return (AstSupertype) ElementType.fromTypename(typename);
	}
}

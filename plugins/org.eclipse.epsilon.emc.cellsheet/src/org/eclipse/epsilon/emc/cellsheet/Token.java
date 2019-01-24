package org.eclipse.epsilon.emc.cellsheet;

import java.util.EnumSet;
import java.util.Objects;

public class Token {

	private static final EnumSet<TokenType> EXPR = EnumSet.of(TokenType.FUNCTION, TokenType.SUBEXPRESSION);
	private static final EnumSet<TokenSubtype> EXPR_START = EnumSet.of(TokenSubtype.START, TokenSubtype.ARRAY_START,
			TokenSubtype.ARRAY_ROW_START);
	private static final EnumSet<TokenSubtype> EXPR_STOP = EnumSet.of(TokenSubtype.STOP, TokenSubtype.ARRAY_STOP,
			TokenSubtype.ARRAY_ROW_STOP);

	private String value = "";
	private TokenType type = TokenType.UNKNOWN;
	private TokenSubtype subtype = TokenSubtype.NOTHING;

	/**
	 * Default Constructor
	 */
	public Token() {
	}

	public Token(String value, TokenType type) {
		this(value, type, null);
	}

	public Token(String value, TokenType type, TokenSubtype subtype) {
		this.value = value;
		this.type = type;
		this.subtype = subtype == null ? TokenSubtype.NOTHING : subtype;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TokenType getType() {
		return type;
	}

	public void setType(TokenType type) {
		this.type = type;
	}

	public TokenSubtype getSubtype() {
		return subtype;
	}

	public void setSubtype(TokenSubtype subtype) {
		this.subtype = subtype;
	}

	public boolean isExprStart() {
		return (EXPR.contains(type) && EXPR_START.contains(subtype));
	}

	public boolean isExprEnd() {
		return (EXPR.contains(type) && EXPR_STOP.contains(subtype)) ;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s", type, subtype, value);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Token token = (Token) o;
		return Objects.equals(value, token.value) && type == token.type && subtype == token.subtype;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, type, subtype);
	}

	public static enum TokenSubtype {

		NOTHING,
		START,
		ARRAY_START,
		ARRAY_ROW_START,
		STOP,
		ARRAY_STOP,
		ARRAY_ROW_STOP,
		TEXT,
		NUMBER,
		LOGICAL,
		ERROR,
		RANGE,
		MATH,
		CONCATENATION,
		INTERSECTION,
		UNION;

	}

	public static enum TokenType {

		NOOP,
		OPERAND,
		FUNCTION,
		SUBEXPRESSION,
		ARGUMENT,
		OPERATOR_PREFIX,
		OPERATOR_INFIX,
		OPERATOR_POSTFIX,
		WHITESPACE,
		UNKNOWN;

	}
}

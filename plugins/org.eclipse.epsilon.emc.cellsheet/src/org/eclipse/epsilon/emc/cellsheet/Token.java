package org.eclipse.epsilon.emc.cellsheet;

import java.util.EnumSet;
import java.util.Objects;

public class Token {
	
	public static final Token FUNCTION_STOP = new Token(")", TokenType.FUNCTION, TokenSubtype.STOP);
	public static final Token SUBEXPRESSION_START = new Token("(", TokenType.SUBEXPRESSION, TokenSubtype.START);
	public static final Token SUBEXPRESSION_STOP = new Token(")", TokenType.SUBEXPRESSION, TokenSubtype.STOP);
	public static final Token ARGUMENT = new Token(",", TokenType.ARGUMENT);
	
	public static final EnumSet<TokenType> EXPR = EnumSet.of(TokenType.FUNCTION, TokenType.SUBEXPRESSION);
	public static final EnumSet<TokenSubtype> EXPR_START = EnumSet.of(TokenSubtype.START, TokenSubtype.ARRAY_START,
			TokenSubtype.ARRAY_ROW_START);
	public static final EnumSet<TokenSubtype> EXPR_STOP = EnumSet.of(TokenSubtype.STOP, TokenSubtype.ARRAY_STOP,
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
	
	public Token(char value, TokenType type, TokenSubtype subtype) {
		this(String.valueOf(value), type, subtype);
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

	public boolean isExpr() {
		return EXPR.contains(type);
	}
	
	public boolean isExprStart() {
		return (EXPR.contains(type) && EXPR_START.contains(subtype));
	}

	public boolean isExprEnd() {
		return (EXPR.contains(type) && EXPR_STOP.contains(subtype));
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
	
	public boolean equals(TokenType type, TokenSubtype subtype) {
		return this.type == type && this.subtype == subtype;
	}

	@Override
	public int hashCode() {
		return Objects.hash(value, type, subtype);
	}

	public Integer getPrecedence() {
		return subtype.getPrecedence();
	}
	
	public static enum TokenSubtype {

		NOTHING,
		START(-2),
		ARRAY_START(-2),
		ARRAY_ROW_START(-2),
		STOP,
		ARRAY_STOP,
		ARRAY_ROW_STOP,
		TEXT,
		NUMBER,
		LOGICAL,
		ERROR,

		INTERSECTION(7),
		UNION(7),
		RANGE(7),

		// New operators
		NEGATION(6),
		
		PERCENT(5),
		
		EXPONENTION(4),
		
		MULTIPLICATION(3),
		DIVISION(3),
		
		ADDITION(2),
		SUBTRACTION(2),

		CONCATENATION(1),

		EQ(0),
		LT(0),
		GT(0),
		LTE(0),
		GTE(0),
		NEQ(0);

		private final int precedence;

		private TokenSubtype() {
			this.precedence = -1;
		}

		private TokenSubtype(int precdence) {
			this.precedence = precdence;
		}

		public int getPrecedence() {
			return precedence;
		}
		
		public int compare(TokenSubtype other) {
			return Integer.compare(precedence, other.precedence);
		}
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

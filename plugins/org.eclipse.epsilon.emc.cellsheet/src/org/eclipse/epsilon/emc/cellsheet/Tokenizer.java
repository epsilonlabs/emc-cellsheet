package org.eclipse.epsilon.emc.cellsheet;

import java.util.*;
import java.util.regex.Pattern;

import org.eclipse.epsilon.emc.cellsheet.Token.TokenType;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenSubtype;

/**
 * A Tokenizer for Excel formulae.
 * 
 * The tokenizer is based on the C# tokenizer found at
 * https://ewbi.blogs.com/develops/2007/03/excel_formula_p.html written by Eric
 * Bachtal
 * 
 * @author Jonathan Co
 */
public class Tokenizer {

	static final char QUOTE_DOUBLE = '"';
	static final char QUOTE_SINGLE = '\'';
	static final char BRACKET_CLOSE = ']';
	static final char BRACKET_OPEN = '[';
	static final char BRACE_OPEN = '{';
	static final char BRACE_CLOSE = '}';
	static final char PAREN_OPEN = '(';
	static final char PAREN_CLOSE = ')';
	static final char SEMICOLON = ';';
	static final char WHITESPACE = ' ';
	static final char COMMA = ',';
	static final char ERROR_START = '#';

	static final String OPERATORS_SN = "+-";
	static final String OPERATORS_INFIX = "+-*/^&=><";
	static final String OPERATORS_POSTFIX = "%";
	static final String[] ERRORS = { "#NULL!", "#DIV/0!", "#VALUE!", "#REF!", "#NAME?", "#NUM!", "#N/A" };
	static final String[] COMPARATORS_MULTI = { ">=", "<=", "<>" };

	static final Pattern SN_PATTERN = Pattern.compile("^[1-9]{1}(\\.[0-9]+)?E{1}$");

	/**
	 * Utility method for tokenizing a formula.
	 * 
	 * @param formula input to tokenize
	 * @return List of Token objects
	 */
	public static List<Token> parse(String formula) {
		return (new Tokenizer(formula)).parse();
	}

	private String formula;
	private List<Token> tokens = null;

	/**
	 * Constructor
	 * 
	 * @param formula formula to parse
	 */
	public Tokenizer(String formula) {
		if (formula == null || formula.trim().isEmpty()) {
			throw new IllegalArgumentException("formula cannot be null or empty string");
		}
		this.formula = formula.trim();
		this.tokens = parse();
	}

	/**
	 * @return the formula tokenized by this formula
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * @return list of tokens
	 */
	public List<Token> getTokens() {
		return tokens;
	}

	/**
	 * Parse the held formula into the token list
	 * 
	 * @return token list
	 */
	private List<Token> parse() {
		// Setup
		int index = 0;

		if (formula.charAt(0) == '=') {
			index = 1;
		}

		List<Token> tokens = new LinkedList<>();
		List<Token> tokensCopy = new LinkedList<>();
		Deque<Token> stack = new LinkedList<>();

		boolean inString = false;
		boolean inPath = false;
		boolean inRange = false;
		boolean inError = false;

		StringBuilder value = new StringBuilder();

		/*
		 * state-dependent character evaluation (order is important)
		 */
		while (index < formula.length()) {
			// double-quoted strings
			// embeds are doubled
			// end marks token
			if (inString) {
				if (formula.charAt(index) == QUOTE_DOUBLE) {
					if (((index + 2) <= formula.length()) && (formula.charAt(index + 1) == QUOTE_DOUBLE)) {
						value.append(QUOTE_DOUBLE);
						index++;
					} else {
						inString = false;
						tokens.add(dumpToken(value, TokenType.OPERAND, TokenSubtype.TEXT));
					}
				} else {
					value.append(formula.charAt(index));
				}
				index++;
				continue;
			}

			// single-quoted strings (links)
			// embeds are double
			// end does not mark a token
			if (inPath) {
				if (formula.charAt(index) == QUOTE_SINGLE) {
					if (((index + 2) <= formula.length()) && (formula.charAt(index + 1) == QUOTE_SINGLE)) {
						value.append(QUOTE_SINGLE);
						index++;
					} else {
						inPath = false;
					}
				} else {
					value.append(formula.charAt(index));
				}
				index++;
				continue;
			}

			// bracketed strings (R1C1 range index or linked workbook name)
			// no embeds (changed to "()" by Excel)
			// end does not mark a token
			if (inRange) {
				if (formula.charAt(index) == BRACKET_CLOSE) {
					inRange = false;
				}
				value.append(formula.charAt(index));
				index++;
				continue;
			}

			// error values
			// end marks a token, determined from absolute list of values
			if (inError) {
				value.append(formula.charAt(index));
				index++;
				if (Arrays.asList(ERRORS).contains(value.toString())) {
					inError = false;
					tokens.add(dumpToken(value, TokenType.OPERAND, TokenSubtype.ERROR));
				}
				continue;
			}

			// scientific notation check
			if (OPERATORS_SN.indexOf(formula.charAt(index)) != -1) {
				if (value.length() > 1) {
					if (SN_PATTERN.matcher(value).matches()) {
						value.append(formula.charAt(index));
						index++;
						continue;
					}
				}
			}

			// independent character evaluation (order not important)
			// establish state-dependent character evaluations
			if (formula.charAt(index) == QUOTE_DOUBLE) {
				if (value.length() > 0) { // unexpected
					tokens.add(dumpToken(value, TokenType.UNKNOWN));
				}
				inString = true;
				index++;
				continue;
			}

			if (formula.charAt(index) == QUOTE_SINGLE) {
				if (value.length() > 0) { // unexpected
					tokens.add(dumpToken(value, TokenType.UNKNOWN));
				}
				inPath = true;
				index++;
				continue;
			}

			if (formula.charAt(index) == BRACKET_OPEN) {
				inRange = true;
				value.append(BRACKET_OPEN);
				index++;
				continue;
			}

			if (formula.charAt(index) == ERROR_START) {
				if (value.length() > 0) { // unexpected
					tokens.add(dumpToken(value, TokenType.UNKNOWN));
				}
				inError = true;
				value.append(ERROR_START);
				index++;
				continue;
			}

			// mark start and end of arrays and array rows
			if (formula.charAt(index) == BRACE_OPEN) {
				if (value.length() > 0) { // unexpected
					tokens.add(dumpToken(value, TokenType.UNKNOWN));
				}

				Token arrayStart = new Token("ARRAY", TokenType.FUNCTION, TokenSubtype.START);
				Token arrayRowStart = new Token("ARRAYROW", TokenType.FUNCTION, TokenSubtype.START);
				tokens.add(arrayStart);
				tokens.add(arrayRowStart);
				stack.push(arrayStart);
				stack.push(arrayRowStart);
				index++;
				continue;
			}

			if (formula.charAt(index) == SEMICOLON) {
				if (value.length() > 0) {
					tokens.add(dumpToken(value, TokenType.OPERAND));
				}

				tokens.add(new Token("", stack.pop().getType(), TokenSubtype.STOP)); // tokens.add(stack.pop());
				tokens.add(new Token(",", TokenType.ARGUMENT));

				Token arrayRowStart = new Token("ARRAYROW", TokenType.FUNCTION, TokenSubtype.START);
				tokens.add(arrayRowStart);
				stack.push(arrayRowStart);
				index++;
				continue;
			}

			if (formula.charAt(index) == BRACE_CLOSE) {
				if (value.length() > 0) {
					tokens.add(dumpToken(value, TokenType.OPERAND));
				}
				tokens.add(new Token("", stack.pop().getType(), TokenSubtype.STOP));
				tokens.add(new Token("", stack.pop().getType(), TokenSubtype.STOP));
				index++;
				continue;
			}

			// trim white-space
			if (formula.charAt(index) == WHITESPACE) {
				if (value.length() > 0) {
					tokens.add(dumpToken(value, TokenType.OPERAND));
				}
				tokens.add(new Token("", TokenType.WHITESPACE));
				index++;
				while ((formula.charAt(index) == WHITESPACE) && (index < formula.length())) {
					index++;
				}
				continue;
			}

			// multi-character comparators
			if ((index + 2) <= formula.length()) {
				if (Arrays.asList(COMPARATORS_MULTI).contains(formula.substring(index, index + 2))) {
					if (value.length() > 0) {
						tokens.add(dumpToken(value, TokenType.OPERAND));
					}
					tokens.add(new Token(formula.substring(index, index + 2), TokenType.OPERATOR_INFIX,
							TokenSubtype.LOGICAL));
					index += 2;
					continue;
				}
			}

			// standard infix operators
			if (OPERATORS_INFIX.indexOf(formula.charAt(index)) != -1) {
				if (value.length() > 0) {
					tokens.add(dumpToken(value, TokenType.OPERAND));
				}
				tokens.add(new Token(String.valueOf(formula.charAt(index)), TokenType.OPERATOR_INFIX));
				index++;
				continue;
			}

			// standard postfix operators (only one)
			if (OPERATORS_POSTFIX.indexOf(formula.charAt(index)) != -1) {
				if (value.length() > 0) {
					tokens.add(dumpToken(value, TokenType.OPERAND));
				}
				tokens.add(new Token(String.valueOf(formula.charAt(index)), TokenType.OPERATOR_POSTFIX));
				index++;
				continue;
			}

			// start subexpression or function
			if (formula.charAt(index) == PAREN_OPEN) {
				Token start;
				if (value.length() > 0) {
					start = dumpToken(value, TokenType.FUNCTION, TokenSubtype.START);
				} else {
					start = new Token("", TokenType.SUBEXPRESSION, TokenSubtype.START);
				}
				tokens.add(start);
				stack.push(start);
				index++;
				continue;
			}

			// function, subexpression, or array parameters, or operand unions
			if (formula.charAt(index) == COMMA) {
				if (value.length() > 0) {
					tokens.add(dumpToken(value, TokenType.OPERAND));
				}
				if (stack.peek().getType() != TokenType.FUNCTION) {
					tokens.add(new Token(",", TokenType.OPERATOR_INFIX, TokenSubtype.UNION));
				} else {
					tokens.add(new Token(",", TokenType.ARGUMENT));
				}
				index++;
				continue;
			}

			// stop subexpression
			if (formula.charAt(index) == PAREN_CLOSE) {
				if (value.length() > 0) {
					tokens.add(dumpToken(value, TokenType.OPERAND));
				}
				tokens.add(new Token("", stack.pop().getType(), TokenSubtype.STOP));
				index++;
				continue;
			}

			// token accumulation
			value.append(formula.charAt(index));
			index++;
		}

		// dump remaining accumulation
		if (value.length() > 0) {
			tokens.add(dumpToken(value, TokenType.OPERAND));
		}

		/*
		 * move tokenList to new set, excluding unnecessary white-space tokens and
		 * converting necessary ones to intersections
		 */
		for (int i = 0, n = tokens.size(); i < n; i++) {
			Token token = tokens.get(i);

			if (token == null)
				continue;

			if (token.getType() != TokenType.WHITESPACE) {
				tokensCopy.add(token);
				continue;
			}

			if (i <= 0 || i >= n - 1)
				continue;

			Token previous = i < 1 ? null : tokens.get(i - 1);
			if (previous == null)
				continue;
			if (!(((previous.getType() == TokenType.FUNCTION) && (previous.getSubtype() == TokenSubtype.STOP))
					|| ((previous.getType() == TokenType.SUBEXPRESSION) && (previous.getSubtype() == TokenSubtype.STOP))
					|| (previous.getType() == TokenType.OPERAND)))
				continue;

			Token next = i >= n - 1 ? null : tokens.get(i + 1);
			if (next == null)
				continue;
			if (!(((next.getType() == TokenType.FUNCTION) && (next.getSubtype() == TokenSubtype.STOP))
					|| ((next.getType() == TokenType.SUBEXPRESSION) && (next.getSubtype() == TokenSubtype.STOP))
					|| (next.getType() == TokenType.OPERAND)))
				continue;

			tokensCopy.add(new Token("", TokenType.OPERATOR_INFIX, TokenSubtype.INTERSECTION));
		}

		// Wipe original list
		tokens.clear();

		/*
		 * move tokens to final list, switching infix "-" operators to prefix when
		 * appropriate, switching infix "+" operators to noop when appropriate,
		 * identifying operand and infix-operator subtypes, and pulling "@" from
		 * function names
		 */
		for (int i = 0, n = tokensCopy.size(); i < n; i++) {
			Token token = tokensCopy.get(i);

			if (token == null)
				continue;

			Token previous = i > 0 ? tokensCopy.get(i - 1) : null;

			if (token.getType() == TokenType.OPERATOR_INFIX && token.getValue().equals("-")) {
				if (i < 1) {
					token.setType(TokenType.OPERATOR_PREFIX);
				} else if (((previous.getType() == TokenType.FUNCTION) && (previous.getSubtype() == TokenSubtype.STOP))
						|| ((previous.getType() == TokenType.SUBEXPRESSION)
								&& (previous.getSubtype() == TokenSubtype.STOP))
						|| (previous.getType() == TokenType.OPERATOR_POSTFIX)
						|| (previous.getType() == TokenType.OPERAND))
					token.setSubtype(TokenSubtype.MATH);
				else
					token.setType(TokenType.OPERATOR_PREFIX);

				tokens.add(token);
				continue;
			}

			if (token.getType() == TokenType.OPERATOR_INFIX && token.getValue().equals("+")) {
				if (i < 1)
					continue;
				else if (((previous.getType() == TokenType.FUNCTION) && (previous.getSubtype() == TokenSubtype.STOP))
						|| ((previous.getType() == TokenType.SUBEXPRESSION)
								&& (previous.getSubtype() == TokenSubtype.STOP))
						|| (previous.getType() == TokenType.OPERATOR_POSTFIX)
						|| (previous.getType() == TokenType.OPERAND))
					token.setSubtype(TokenSubtype.MATH);
				else
					continue;

				tokens.add(token);
				continue;
			}

			if (token.getType() == TokenType.OPERATOR_INFIX && token.getSubtype() == TokenSubtype.NOTHING) {
				if ("<>=".indexOf(token.getValue().charAt(0)) != -1)
					token.setSubtype(TokenSubtype.LOGICAL);
				else if (token.getValue().equals("&"))
					token.setSubtype(TokenSubtype.CONCATENATION);
				else
					token.setSubtype(TokenSubtype.MATH);

				tokens.add(token);
				continue;
			}

			if (token.getType() == TokenType.OPERAND && token.getSubtype() == TokenSubtype.NOTHING) {
				try {
					Double.parseDouble(token.getValue());
					token.setSubtype(TokenSubtype.NUMBER);
				} catch (NumberFormatException e) {
					if (token.getValue().equals("TRUE") || token.getValue().equals("FALSE")) {
						token.setSubtype(TokenSubtype.LOGICAL);
					} else {
						token.setSubtype(TokenSubtype.RANGE);
					}
				}
				tokens.add(token);
				continue;
			}

			if (token.getType() == TokenType.FUNCTION) {
				if (token.getValue().length() > 0) {
					if (token.getValue().charAt(0) == '@') {
						token.setValue(token.getValue().substring(1));
					}
				}
			}

			tokens.add(token);
		}

		return this.tokens;
	}

	private Token dumpToken(StringBuilder sb, TokenType type, TokenSubtype subtype) {
		String value = sb.toString();
		sb.setLength(0);
		return new Token(value, type, subtype);
	}

	private Token dumpToken(StringBuilder sb, TokenType type) {
		return dumpToken(sb, type, null);
	}

}

package org.eclipse.epsilon.emc.cellsheet;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.eclipse.epsilon.emc.cellsheet.Token.TokenSubtype;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenType;

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

	public static void recompose(List<Token> tokens) {
		if (tokens == null) {
			throw new IllegalArgumentException();
		}

		// Re-add missing Function start open parens
		final ListIterator<Token> it = tokens.listIterator();
		Token token;
		while (it.hasNext()) {
			token = it.next();

			if (token.getType() == TokenType.FUNCTION && token.getSubtype() == TokenSubtype.START) {
				it.add(new Token(PAREN_OPEN, TokenType.FUNCTION, TokenSubtype.START));
				continue;
			}
		}

		// Remove unnecessary open and close parens from start and end
		if (String.valueOf(PAREN_OPEN).equals(tokens.get(0).getValue())) {
			final Deque<Integer> opens = new LinkedList<>();
			for (int i = 0, n = tokens.size(); i < n; i++) {
				String value = tokens.get(i).getValue();

				if (String.valueOf(PAREN_OPEN).equals(value)) {
					opens.push(i);
					continue;
				}

				if (String.valueOf(PAREN_CLOSE).equals(value)) {
					if (opens.pop() == 0 && i == n - 1) {
						tokens.remove(0);
						tokens.remove(tokens.size() - 1);
					}
					continue;
				}
			}
		}
	}

	/**
	 * 
	 * @param tokens
	 * @return
	 */
	public static String toString(List<Token> tokens) {
		recompose(tokens);
		return tokens.stream().map(Token::getValue).collect(Collectors.joining());
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
						value.insert(0,  QUOTE_DOUBLE);
						value.append(QUOTE_DOUBLE);
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
						value.insert(0, QUOTE_SINGLE);
						value.append(QUOTE_SINGLE);
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

				Token arrayStart = new Token(String.valueOf(BRACE_OPEN), TokenType.FUNCTION, TokenSubtype.ARRAY_START);
				Token arrayRowStart = new Token("", TokenType.FUNCTION, TokenSubtype.ARRAY_ROW_START);
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

				tokens.add(new Token("", stack.pop().getType(), TokenSubtype.ARRAY_ROW_STOP)); // tokens.add(stack.pop());
				tokens.add(new Token(";", TokenType.ARGUMENT));

				Token arrayRowStart = new Token("", TokenType.FUNCTION, TokenSubtype.ARRAY_ROW_START);
				tokens.add(arrayRowStart);
				stack.push(arrayRowStart);
				index++;
				continue;
			}

			if (formula.charAt(index) == BRACE_CLOSE) {
				if (value.length() > 0) {
					tokens.add(dumpToken(value, TokenType.OPERAND));
				}
				tokens.add(new Token("", stack.pop().getType(), TokenSubtype.ARRAY_ROW_STOP));
				tokens.add(new Token("}", stack.pop().getType(), TokenSubtype.ARRAY_STOP));
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
					Token comparator = new Token(formula.substring(index, index + 2), TokenType.OPERATOR_INFIX);
					switch (comparator.getValue()) {
					case "<=":
						comparator.setSubtype(TokenSubtype.LTE);
						break;
					case ">=":
						comparator.setSubtype(TokenSubtype.GTE);
						break;
					default:
						comparator.setSubtype(TokenSubtype.NEQ);
						break;

					}

					tokens.add(comparator);

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
				tokens.add(new Token(String.valueOf(formula.charAt(index)), TokenType.OPERATOR_POSTFIX,
						TokenSubtype.PERCENT));
				index++;
				continue;
			}

			// start subexpression or function
			if (formula.charAt(index) == PAREN_OPEN) {
				Token start;
				if (value.length() > 0) {
					start = dumpToken(value, TokenType.FUNCTION, TokenSubtype.START);
				} else {
					start = new Token(PAREN_OPEN, TokenType.SUBEXPRESSION, TokenSubtype.START);
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
				tokens.add(new Token(PAREN_CLOSE, stack.pop().getType(), TokenSubtype.STOP));
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
			if (previous == null || !isTerminal(previous))
				continue;

			Token next = i >= n - 1 ? null : tokens.get(i + 1);
			if (next == null || !isTerminal(next))
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
					token.setSubtype(TokenSubtype.NEGATION);
				} else if (isTerminal(previous)) {
					token.setSubtype(TokenSubtype.SUBTRACTION);
				} else {
					token.setType(TokenType.OPERATOR_PREFIX);
					token.setSubtype(TokenSubtype.NEGATION);
				}
				tokens.add(token);
				continue;
			}

			if (token.getType() == TokenType.OPERATOR_INFIX && token.getValue().equals("+")) {
				if (i < 1)
					continue;
				else if (isTerminal(previous))
					token.setSubtype(TokenSubtype.ADDITION);
				else
					continue;

				tokens.add(token);
				continue;
			}

			if (token.getType() == TokenType.OPERATOR_INFIX && token.getSubtype() == TokenSubtype.NOTHING) {
				// Multiplication
				if (token.getValue().equals("*"))
					token.setSubtype(TokenSubtype.MULTIPLICATION);

				// Division
				else if (token.getValue().equals("/"))
					token.setSubtype(TokenSubtype.DIVISION);

				// Exponention
				else if (token.getValue().equals("^"))
					token.setSubtype(TokenSubtype.EXPONENTION);

				// Concatenation
				else if (token.getValue().equals("&"))
					token.setSubtype(TokenSubtype.CONCATENATION);

				if ("<>=".indexOf(token.getValue().charAt(0)) != -1) {
					switch (token.getValue()) {
					case "=":
						token.setSubtype(TokenSubtype.EQ);
						break;
					case "<":
						token.setSubtype(TokenSubtype.LT);
						break;
					case ">":
						token.setSubtype(TokenSubtype.GT);
						break;
					default:
						break;
					}
				}

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

		return tokens;
	}

	private Token dumpToken(StringBuilder sb, TokenType type, TokenSubtype subtype) {
		String value = sb.toString();
		sb.setLength(0);
		return new Token(value, type, subtype);
	}

	private Token dumpToken(StringBuilder sb, TokenType type) {
		return dumpToken(sb, type, null);
	}

	private boolean isTerminal(Token token) {
		return token.isExprEnd() || token.getType() == TokenType.OPERATOR_POSTFIX
				|| token.getType() == TokenType.OPERAND;
	}

}

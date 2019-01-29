package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.epsilon.emc.cellsheet.Token.TokenSubtype;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenType;
import org.junit.Test;

/**
 * Unit tests for Tokenizer
 * 
 * @author Jonathan Co
 *
 */
public class TokenizerTest {

	@Test
	public void toString_multi_statement() throws Exception {
		String formula = "=IF(OR(COUNTBLANK([@[products]])>0,ISNA(VLOOKUP([@[products]],IBANRef!$A:$G,7,FALSE))),VLOOKUP([Level],LevelRef!$A:$B,2,FALSE),VLOOKUP([@[products]],IBANRef!$A:$G,7,FALSE))";
		List<Token> actual = Tokenizer.parse(formula);
		String rebuilt = Tokenizer.toString(actual);
		assertEquals(formula.substring(1), rebuilt);
	}

	@Test
	public void parse_IF_statement() {
		String formula = "=IF(\"a\"={\"a\",\"b\";\"c\",#N/A;-1,TRUE}, \"yes\", \"no\") &   \"  more \"\"test\"\" text\"";

		// Build the expected token list
		List<Token> expected = new LinkedList<>();
		expected.add(new Token("IF", Token.TokenType.FUNCTION, Token.TokenSubtype.START));
		expected.add(new Token("a", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token("=", Token.TokenType.OPERATOR_INFIX, Token.TokenSubtype.EQ));
		expected.add(new Token("{", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_START));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_START));
		expected.add(new Token("a", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("b", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_STOP));
		expected.add(new Token(";", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_START));
		expected.add(new Token("c", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("#N/A", Token.TokenType.OPERAND, Token.TokenSubtype.ERROR));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_STOP));
		expected.add(new Token(";", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_START));
		expected.add(new Token("-", Token.TokenType.OPERATOR_PREFIX, Token.TokenSubtype.NEGATION));
		expected.add(new Token("1", Token.TokenType.OPERAND, Token.TokenSubtype.NUMBER));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("TRUE", Token.TokenType.OPERAND, Token.TokenSubtype.LOGICAL));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_STOP));
		expected.add(new Token("}", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_STOP));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("yes", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("no", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.STOP));
		expected.add(new Token("&", Token.TokenType.OPERATOR_INFIX, Token.TokenSubtype.CONCATENATION));
		expected.add(new Token("  more \"test\" text", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));

		assertEquals(expected, Tokenizer.parse(formula));
	}

	@Test
	public void parse_IF_statement_no_starting_equals() {
		String formula = "IF(\"a\"={\"a\",\"b\";\"c\",#N/A;-1,TRUE}, \"yes\", \"no\") &   \"  more \"\"test\"\" text\"";

		// Build the expected token list
		List<Token> expected = new LinkedList<>();
		expected.add(new Token("IF", Token.TokenType.FUNCTION, Token.TokenSubtype.START));
		expected.add(new Token("a", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token("=", Token.TokenType.OPERATOR_INFIX, Token.TokenSubtype.EQ));
		expected.add(new Token("{", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_START));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_START));
		expected.add(new Token("a", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("b", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_STOP));
		expected.add(new Token(";", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_START));
		expected.add(new Token("c", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("#N/A", Token.TokenType.OPERAND, Token.TokenSubtype.ERROR));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_STOP));
		expected.add(new Token(";", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_START));
		expected.add(new Token("-", Token.TokenType.OPERATOR_PREFIX, Token.TokenSubtype.NEGATION));
		expected.add(new Token("1", Token.TokenType.OPERAND, Token.TokenSubtype.NUMBER));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("TRUE", Token.TokenType.OPERAND, Token.TokenSubtype.LOGICAL));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_ROW_STOP));
		expected.add(new Token("}", Token.TokenType.FUNCTION, Token.TokenSubtype.ARRAY_STOP));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("yes", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token(",", Token.TokenType.ARGUMENT, Token.TokenSubtype.NOTHING));
		expected.add(new Token("no", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));
		expected.add(new Token("", Token.TokenType.FUNCTION, Token.TokenSubtype.STOP));
		expected.add(new Token("&", Token.TokenType.OPERATOR_INFIX, Token.TokenSubtype.CONCATENATION));
		expected.add(new Token("  more \"test\" text", Token.TokenType.OPERAND, Token.TokenSubtype.TEXT));

		assertEquals(expected, Tokenizer.parse(formula));
	}

	@Test
	public void parse_SUM_R1C1() {
		String formula = "=+ AName- (-+-+-2^6) = {\"A\",\"B\"} + @SUM(R1C1) + (@ERROR.TYPE(#VALUE!) = 2)";

		List<Token> expected = new LinkedList<>();
		expected.add(new Token("AName", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("-", TokenType.OPERATOR_INFIX, TokenSubtype.SUBTRACTION));
		expected.add(new Token("", TokenType.SUBEXPRESSION, TokenSubtype.START));
		expected.add(new Token("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.add(new Token("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.add(new Token("-", TokenType.OPERATOR_PREFIX, TokenSubtype.NEGATION));
		expected.add(new Token("2", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("^", TokenType.OPERATOR_INFIX, TokenSubtype.EXPONENTION));
		expected.add(new Token("6", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.SUBEXPRESSION, TokenSubtype.STOP));
		expected.add(new Token("=", TokenType.OPERATOR_INFIX, TokenSubtype.EQ));
		expected.add(new Token("{", TokenType.FUNCTION, TokenSubtype.ARRAY_START));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.ARRAY_ROW_START));
		expected.add(new Token("A", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("B", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.ARRAY_ROW_STOP));
		expected.add(new Token("}", TokenType.FUNCTION, TokenSubtype.ARRAY_STOP));
		expected.add(new Token("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION));
		expected.add(new Token("SUM", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R1C1", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION));
		expected.add(new Token("", TokenType.SUBEXPRESSION, TokenSubtype.START));
		expected.add(new Token("ERROR.TYPE", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("#VALUE!", TokenType.OPERAND, TokenSubtype.ERROR));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("=", TokenType.OPERATOR_INFIX, TokenSubtype.EQ));
		expected.add(new Token("2", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.SUBEXPRESSION, TokenSubtype.STOP));

		assertEquals(expected, Tokenizer.parse(formula));
	}

	@Test
	public void parse_IF_R1C1() {
		String formula = "=IF(R13C3>DATE(2002,1,6),0,IF(ISERROR(R[41]C[2]),0,IF(R13C3>=R[41]C[2],0,IF(AND(R[23]C[11]>=55,R[24]C[11]>=20),R53C3,0))))";

		List<Token> expected = new LinkedList<>();
		expected.add(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R13C3", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">", TokenType.OPERATOR_INFIX, TokenSubtype.GT));
		expected.add(new Token("DATE", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("2002", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("6", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("0", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("ISERROR", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R[41]C[2]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("0", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R13C3", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">=", TokenType.OPERATOR_INFIX, TokenSubtype.GTE));
		expected.add(new Token("R[41]C[2]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("0", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("AND", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R[23]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">=", TokenType.OPERATOR_INFIX, TokenSubtype.GTE));
		expected.add(new Token("55", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R[24]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">=", TokenType.OPERATOR_INFIX, TokenSubtype.GTE));
		expected.add(new Token("20", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R53C3", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("0", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));

		assertEquals(expected, Tokenizer.parse(formula));
	}

	@Test
	public void parse_array_formula() {
		String formula = "{=MAX(IF(ISERROR(SEARCH(H5&\"*\",files)),0,ROW(files)-ROW(INDEX(files,1,1))+1))}";

		List<Token> expected = new LinkedList<>();
		expected.add(new Token("{", TokenType.FUNCTION, TokenSubtype.ARRAY_START));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.ARRAY_ROW_START));
		expected.add(new Token("=", TokenType.OPERATOR_INFIX, TokenSubtype.EQ));
		expected.add(new Token("MAX", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("ISERROR", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("SEARCH", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("H5", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("&", TokenType.OPERATOR_INFIX, TokenSubtype.CONCATENATION));
		expected.add(new Token("*", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("files", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("0", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("ROW", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("files", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("-", TokenType.OPERATOR_INFIX, TokenSubtype.SUBTRACTION));
		expected.add(new Token("ROW", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("INDEX", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("files", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION));
		expected.add(new Token("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.ARRAY_ROW_STOP));
		expected.add(new Token("}", TokenType.FUNCTION, TokenSubtype.ARRAY_STOP));

		assertEquals(expected, Tokenizer.parse(formula));
	}

	@Test
	public void parse_array_formula_with_equals() {
		String formula = "={=MAX(IF(ISERROR(SEARCH(H5&\"*\",files)),0,ROW(files)-ROW(INDEX(files,1,1))+1))}";

		List<Token> expected = new LinkedList<>();
		expected.add(new Token("{", TokenType.FUNCTION, TokenSubtype.ARRAY_START));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.ARRAY_ROW_START));
		expected.add(new Token("=", TokenType.OPERATOR_INFIX, TokenSubtype.EQ));
		expected.add(new Token("MAX", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("ISERROR", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("SEARCH", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("H5", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("&", TokenType.OPERATOR_INFIX, TokenSubtype.CONCATENATION));
		expected.add(new Token("*", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("files", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("0", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("ROW", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("files", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("-", TokenType.OPERATOR_INFIX, TokenSubtype.SUBTRACTION));
		expected.add(new Token("ROW", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("INDEX", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("files", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION));
		expected.add(new Token("1", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.ARRAY_ROW_STOP));
		expected.add(new Token("}", TokenType.FUNCTION, TokenSubtype.ARRAY_STOP));

		assertEquals(expected, Tokenizer.parse(formula));
	}

	@Test
	public void parse_long_IF() {
		String formula = "=IF(R[39]C[11]>65,R[25]C[42],ROUND((R[11]C[11]*IF(OR(AND(R[39]C[11]>=55, R[40]C[11]>=20),AND(R[40]C[11]>=20,R11C3=\"YES\")),R[44]C[11],R[43]C[11]))+(R[14]C[11] *IF(OR(AND(R[39]C[11]>=55,R[40]C[11]>=20),AND(R[40]C[11]>=20,R11C3=\"YES\")), R[45]C[11],R[43]C[11])),0))";

		List<Token> expected = new LinkedList<>();
		expected.add(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R[39]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">", TokenType.OPERATOR_INFIX, TokenSubtype.GT));
		expected.add(new Token("65", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R[25]C[42]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("ROUND", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("", TokenType.SUBEXPRESSION, TokenSubtype.START));
		expected.add(new Token("R[11]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("*", TokenType.OPERATOR_INFIX, TokenSubtype.MULTIPLICATION));
		expected.add(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("OR", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("AND", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R[39]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">=", TokenType.OPERATOR_INFIX, TokenSubtype.GTE));
		expected.add(new Token("55", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R[40]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">=", TokenType.OPERATOR_INFIX, TokenSubtype.GTE));
		expected.add(new Token("20", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("AND", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R[40]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">=", TokenType.OPERATOR_INFIX, TokenSubtype.GTE));
		expected.add(new Token("20", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R11C3", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("=", TokenType.OPERATOR_INFIX, TokenSubtype.EQ));
		expected.add(new Token("YES", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R[44]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R[43]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.SUBEXPRESSION, TokenSubtype.STOP));
		expected.add(new Token("+", TokenType.OPERATOR_INFIX, TokenSubtype.ADDITION));
		expected.add(new Token("", TokenType.SUBEXPRESSION, TokenSubtype.START));
		expected.add(new Token("R[14]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("*", TokenType.OPERATOR_INFIX, TokenSubtype.MULTIPLICATION));
		expected.add(new Token("IF", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("OR", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("AND", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R[39]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">=", TokenType.OPERATOR_INFIX, TokenSubtype.GTE));
		expected.add(new Token("55", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R[40]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">=", TokenType.OPERATOR_INFIX, TokenSubtype.GTE));
		expected.add(new Token("20", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("AND", TokenType.FUNCTION, TokenSubtype.START));
		expected.add(new Token("R[40]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(">=", TokenType.OPERATOR_INFIX, TokenSubtype.GTE));
		expected.add(new Token("20", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R11C3", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("=", TokenType.OPERATOR_INFIX, TokenSubtype.EQ));
		expected.add(new Token("YES", TokenType.OPERAND, TokenSubtype.TEXT));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R[45]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("R[43]C[11]", TokenType.OPERAND, TokenSubtype.RANGE));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.SUBEXPRESSION, TokenSubtype.STOP));
		expected.add(new Token(",", TokenType.ARGUMENT, TokenSubtype.NOTHING));
		expected.add(new Token("0", TokenType.OPERAND, TokenSubtype.NUMBER));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));
		expected.add(new Token("", TokenType.FUNCTION, TokenSubtype.STOP));

		assertEquals(expected, Tokenizer.parse(formula));
	}
}

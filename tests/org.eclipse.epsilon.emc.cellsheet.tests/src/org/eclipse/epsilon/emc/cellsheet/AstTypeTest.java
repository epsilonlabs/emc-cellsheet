package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AstTypeTest {

	@ParameterizedTest
	@CsvSource({ "FormulaTree, SUPER", "Noop, NOOP", "Operand, OPERAND", "Function, FUNCTION",
			"Subexpresssion, SUBEXPRESSION", "Argument, ARGUMENT", "OperatorPrefix, OPERATOR_PREFIX",
			"OperatorInfix, OPERATOR_INFIX", "OperatorPostfix, OPERATOR_POSTFIX", "Whitespace, WHITESPACE",
			"Unknown, UNKNOWN", })
	public void getTypename_should_return_typename_string(String typename, AstType actual) throws Exception {
		assertEquals(typename, actual.getTypename());
	}

	@ParameterizedTest
	@CsvSource({ "FormulaTree, SUPER", "Noop, NOOP", "Operand, OPERAND", "Function, FUNCTION",
			"Subexpresssion, SUBEXPRESSION", "Argument, ARGUMENT", "OperatorPrefix, OPERATOR_PREFIX",
			"OperatorInfix, OPERATOR_INFIX", "OperatorPostfix, OPERATOR_POSTFIX", "Whitespace, WHITESPACE",
			"Unknown, UNKNOWN", })
	public void toString_should_return_typename_string(String typename, AstType actual) throws Exception {
		assertEquals(typename, actual.toString());
	}

	@ParameterizedTest
	@CsvSource({ "SUPER, FormulaTree", "NOOP, Noop", "OPERAND, Operand", "FUNCTION, Function",
			"SUBEXPRESSION, Subexpresssion", "ARGUMENT, Argument", "OPERATOR_PREFIX, OperatorPrefix",
			"OPERATOR_INFIX, OperatorInfix", "OPERATOR_POSTFIX, OperatorPostfix", "WHITESPACE, Whitespace",
			"UNKNOWN, Unknown", })
	public void static_fromTypename_should_return_AstType_when_given_typename(AstType actual, String typename)
			throws Exception {
		assertEquals(actual, AstType.fromTypename(typename));
	}

	@Test
	void static_fromTypename_should_return_null_when_given_bad_typename() throws Exception {
		assertNull(AstType.fromTypename("Bad typename"));
	}

}

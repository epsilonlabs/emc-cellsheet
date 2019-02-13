package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AstSubtypeTest {
	@ParameterizedTest
	@CsvSource({ "Nothing, NOTHING", "Start, START", "ArrayStart, ARRAY_START", "ArrayRowStart, ARRAY_ROW_START",
			"Stop, STOP", "ArrayStop, ARRAY_STOP", "ArrayRowStop, ARRAY_ROW_STOP", "Text, TEXT", "Number, NUMBER",
			"Logical, LOGICAL", "Error, ERROR", "Range, RANGE", "Ref, REF", "Plus, PLUS", "Negation, NEGATION",
			"Percent, PERCENT", "Exponenetion, EXPONENTION", "Multiplication, MULTIPLICATION", "Divsion, DIVISION",
			"Addition, ADDITION", "Subtraction, SUBTRACTION", "Concatenation, CONCATENATION", "EQ, EQ", "LT, LT",
			"GT, GT", "LTE, LTE", "GTE, GTE", "NEQ, NEQ", "Intersection, INTERSECTION", "Union, UNION", })
	public void getTypename_should_return_typename_string(String typename, AstSubtype actual) throws Exception {
		assertEquals(typename, actual.getTypename());
	}

	@ParameterizedTest
	@CsvSource({ "Nothing, NOTHING", "Start, START", "ArrayStart, ARRAY_START", "ArrayRowStart, ARRAY_ROW_START",
			"Stop, STOP", "ArrayStop, ARRAY_STOP", "ArrayRowStop, ARRAY_ROW_STOP", "Text, TEXT", "Number, NUMBER",
			"Logical, LOGICAL", "Error, ERROR", "Range, RANGE", "Ref, REF", "Plus, PLUS", "Negation, NEGATION",
			"Percent, PERCENT", "Exponenetion, EXPONENTION", "Multiplication, MULTIPLICATION", "Divsion, DIVISION",
			"Addition, ADDITION", "Subtraction, SUBTRACTION", "Concatenation, CONCATENATION", "EQ, EQ", "LT, LT",
			"GT, GT", "LTE, LTE", "GTE, GTE", "NEQ, NEQ", "Intersection, INTERSECTION", "Union, UNION", })
	public void toString_should_return_typename_string(String typename, AstSubtype actual) throws Exception {
		assertEquals(typename, actual.toString());
	}

	@ParameterizedTest
	@CsvSource({ "NOTHING, Nothing", "START, Start", "ARRAY_START, ArrayStart", "ARRAY_ROW_START, ArrayRowStart",
			"STOP, Stop", "ARRAY_STOP, ArrayStop", "ARRAY_ROW_STOP, ArrayRowStop", "TEXT, Text", "NUMBER, Number",
			"LOGICAL, Logical", "ERROR, Error", "RANGE, Range", "REF, Ref", "PLUS, Plus", "NEGATION, Negation",
			"PERCENT, Percent", "EXPONENTION, Exponenetion", "MULTIPLICATION, Multiplication", "DIVISION, Divsion",
			"ADDITION, Addition", "SUBTRACTION, Subtraction", "CONCATENATION, Concatenation", "EQ, EQ", "LT, LT",
			"GT, GT", "LTE, LTE", "GTE, GTE", "NEQ, NEQ", "INTERSECTION, Intersection", "UNION, Union", })
	public void static_fromTypename_should_return_AstSubtype_when_given_typename(AstSubtype actual, String typename)
			throws Exception {
		assertEquals(actual, AstSubtype.fromTypename(typename));
	}

	@Test
	void static_fromTypename_should_return_null_when_given_bad_typename() throws Exception {
		assertNull(AstSubtype.fromTypename("Bad typename"));
	}
}

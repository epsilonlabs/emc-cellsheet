package org.eclipse.epsilon.emc.cellsheet;

import static org.eclipse.epsilon.emc.cellsheet.AstSubtype.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class AstSubtypeTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "Nothing", NOTHING }, { "Start", START }, { "ArrayStart", ARRAY_START },
				{ "ArrayRowStart", ARRAY_ROW_START }, { "Stop", STOP }, { "ArrayStop", ARRAY_STOP },
				{ "ArrayRowStop", ARRAY_ROW_STOP }, { "Text", TEXT }, { "Number", NUMBER }, { "Logical", LOGICAL },
				{ "Error", ERROR }, { "Range", RANGE }, { "Ref", REF }, { "Plus", PLUS }, { "Negation", NEGATION },
				{ "Percent", PERCENT }, { "Exponenetion", EXPONENTION }, { "Multiplication", MULTIPLICATION },
				{ "Divsion", DIVISION }, { "Addition", ADDITION }, { "Subtraction", SUBTRACTION },
				{ "Concatenation", CONCATENATION }, { "EQ", EQ }, { "LT", LT }, { "GT", GT }, { "LTE", LTE },
				{ "GTE", GTE }, { "NEQ", NEQ }, { "Intersection", INTERSECTION }, { "Union", UNION } });
	}

	@Parameter(0)
	public String typename;

	@Parameter(1)
	public AstSubtype type;

	@Test
	public void getTypename_should_return_typename_string() throws Exception {
		assertEquals(typename, type.getTypename());
	}

	@Test
	public void toString_should_return_typename_string() throws Exception {
		assertEquals(typename, type.toString());
	}

	@Test
	public void static_fromTypename_should_return_AstSubtype_when_given_typename() throws Exception {
		assertEquals(type, AstSubtype.fromTypename(typename));
	}

	@Test
	public void static_fromTypename_should_return_null_when_given_bad_typename() throws Exception {
		assertNull(AstSubtype.fromTypename("Bad typename"));
	}

}
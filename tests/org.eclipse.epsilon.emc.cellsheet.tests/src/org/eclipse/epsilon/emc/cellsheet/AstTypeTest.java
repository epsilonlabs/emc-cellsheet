package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import static org.eclipse.epsilon.emc.cellsheet.AstType.*;

@RunWith(Parameterized.class)
public class AstTypeTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "FormulaTree", SUPER }, { "Noop", NOOP }, { "Operand", OPERAND },
				{ "Function", FUNCTION }, { "Subexpresssion", SUBEXPRESSION }, { "Argument", ARGUMENT },
				{ "OperatorPrefix", OPERATOR_PREFIX }, { "OperatorInfix", OPERATOR_INFIX },
				{ "OperatorPostfix", OPERATOR_POSTFIX }, { "Whitespace", WHITESPACE }, { "Unknown", UNKNOWN } });
	}

	@Parameter(0)
	public String typename;

	@Parameter(1)
	public AstType type;

	@Test
	public void getTypename_should_return_typename_string() throws Exception {
		assertEquals(typename, type.getTypename());
	}

	@Test
	public void toString_should_return_typename_string() throws Exception {
		assertEquals(typename, type.toString());
	}

	@Test
	public void static_fromTypename_should_return_AstType_when_given_typename() throws Exception {
		assertEquals(type, AstType.fromTypename(typename));
	}

	@Test
	public void static_fromTypename_should_return_null_when_given_bad_typename() throws Exception {
		assertNull(AstType.fromTypename("Bad typename"));
	}

}

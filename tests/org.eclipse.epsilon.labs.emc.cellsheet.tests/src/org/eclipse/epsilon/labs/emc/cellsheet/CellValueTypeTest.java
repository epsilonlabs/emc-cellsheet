package org.eclipse.epsilon.labs.emc.cellsheet;

import static org.eclipse.epsilon.labs.emc.cellsheet.CellValueType.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CellValueTypeTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "NoneCellValue", NONE }, { "NumericCellValue", NUMERIC },
				{ "StringCellValue", STRING }, { "FormulaCellValue", FORMULA }, { "BooleanCellValue", BOOLEAN },
				{ "BlankCellValue", BLANK }, { "ErrorCellValue", ERROR }, { "DateCellValue", DATE } });
	}

	@Parameter(0)
	public String typename;

	@Parameter(1)
	public CellValueType type;

	@Test
	public void getTypename_should_return_typename_string() throws Exception {
		assertEquals(typename, type.getTypename());
	}

	@Test
	public void toString_should_return_typename_string() throws Exception {
		assertEquals(typename, type.toString());
	}

	@Test
	public void static_fromTypename_should_return_CellValueType_when_given_typename() throws Exception {
		assertEquals(type, CellValueType.fromTypename(typename));
	}

	@Test
	public void static_fromTypename_should_return_null_when_given_bad_typename() throws Exception {
		assertNull(CellValueType.fromTypename("Bad typename"));
	}

}

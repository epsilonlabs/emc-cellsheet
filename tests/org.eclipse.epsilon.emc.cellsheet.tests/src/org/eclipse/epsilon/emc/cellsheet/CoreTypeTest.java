package org.eclipse.epsilon.emc.cellsheet;

import static org.eclipse.epsilon.emc.cellsheet.CoreType.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CoreTypeTest {
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { "Book", BOOK }, { "Sheet", SHEET }, { "Row", ROW }, { "Cell", CELL },
				{ "CellValue", CELL_VALUE }, { "AST", AST } });
	}

	@Parameter(0)
	public String typename;

	@Parameter(1)
	public CoreType type;

	@Test
	public void getTypename_should_return_typename_string() throws Exception {
		assertEquals(typename, type.getTypename());
	}

	@Test
	public void toString_should_return_typename_string() throws Exception {
		assertEquals(typename, type.toString());
	}

	@Test
	public void static_fromTypename_should_return_CoreType_when_given_typename() throws Exception {
		assertEquals(type, CoreType.fromTypename(typename));
	}

	@Test
	public void static_fromTypename_should_return_null_when_given_bad_typename() throws Exception {
		assertNull(CoreType.fromTypename("Bad typename"));
	}

}

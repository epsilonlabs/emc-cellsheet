package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.*;
import org.eclipse.epsilon.emc.cellsheet.Type;
import org.junit.Test;

public class TypeTest {

	@Test
	public void fromTypename_should_return_correct_type() throws Exception {
		for (Type type : Type.values()) {
			assertEquals(type, Type.fromName(type.getName()));
		}
	}

}

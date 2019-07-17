package org.eclipse.epsilon.labs.emc.cellsheet;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ElementTypeTest {

	@Test
	public void getTypeMap_should_return_map() throws Exception {
		final List<ElementType> expected = new ArrayList<>();
		Collections.addAll(expected, CoreType.values());
		Collections.addAll(expected, AstSupertype.values());
		Collections.addAll(expected, AstType.values());

		final Map<String, ElementType> map = ElementType.getTypeMap();
		for (ElementType e : expected) {
			assertEquals(e, map.get(e.getTypename()));
		}
	}
}

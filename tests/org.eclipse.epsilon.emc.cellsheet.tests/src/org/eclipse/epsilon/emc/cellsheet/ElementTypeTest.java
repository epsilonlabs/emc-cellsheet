package org.eclipse.epsilon.emc.cellsheet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class ElementTypeTest {

	@Test
	public void getTypeMap_should_return_map() throws Exception {
		final List<ElementType> expected = new ArrayList<>();
		Collections.addAll(expected, CoreType.values());
		Collections.addAll(expected, AstType.values());
		Collections.addAll(expected, AstSubtype.values());

		final Map<String, ElementType> map = ElementType.getTypeMap();
		for (ElementType e : expected) {
			assertEquals(e, map.get(e.getTypename()));
		}
	}
}

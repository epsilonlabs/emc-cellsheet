package org.eclipse.epsilon.emc.cellsheet;

import java.util.HashMap;
import java.util.Map;

public interface ElementType {

	static Map<String, ElementType> map = new HashMap<>();

	static Map<String, ElementType> getTypeMap() {
		return map;
	}

	static void addToMap(String typename, ElementType type) {
		map.put(typename, type);
	}

	static ElementType fromTypename(String typename) {
		return map.get(typename);
	}

	public String getTypename();

}

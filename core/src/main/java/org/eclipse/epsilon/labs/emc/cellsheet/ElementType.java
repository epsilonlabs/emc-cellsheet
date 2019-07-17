package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public interface ElementType {

	static Map<String, ElementType> map = new HashMap<>();

	public static Map<String, ElementType> getTypeMap() {
		return ImmutableMap.copyOf(map);
	}

	static void addToMap(String typename, ElementType type) {
		map.put(typename, type);
	}

	static ElementType fromTypename(String typename) {
		return map.get(typename);
	}

	public String getTypename();

}

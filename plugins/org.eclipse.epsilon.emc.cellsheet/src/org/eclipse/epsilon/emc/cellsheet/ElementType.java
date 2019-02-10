package org.eclipse.epsilon.emc.cellsheet;

public interface ElementType {

	public String getTypename();
	
	public static ElementType fromTypename(String typename) {
		ElementType type = CoreType.fromTypename(typename);
		type = type == null ? CellValueType.fromTypename(typename) : null;
		type = type == null ? AstType.fromTypename(typename) : null;
		type = type == null ? AstSubtype.fromTypename(typename) : null;
		return type;
	}
}

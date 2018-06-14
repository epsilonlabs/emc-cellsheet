package org.eclipse.epsilon.emc.cellsheet;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.models.Model;

public abstract class AbstractBook extends Model implements HasType, IBook {
	
	public static final Type TYPE = Type.BOOK;
	public static final Type[] KINDS = {TYPE};
	
	@Override
	public Object getTypeOf(Object obj) {		
		Type type = typeOf(obj);
		if (type == null) throw new IllegalArgumentException("Object not a model element");
		return type;
	}
	
	@Override
	public String getTypeNameOf(Object obj) {
		return typeOf(obj).getTypeName();
	}
	
	@Override
	public boolean isOfType(Object obj, String typename) throws EolModelElementTypeNotFoundException {
		final Type type = Type.fromTypeName(typename);
		if (type == null) throw new EolModelElementTypeNotFoundException(this.name, typename);
		
		final Type instanceType = typeOf(obj);
		return type == instanceType;
	}

	@Override
	public boolean hasType(String type) {
		for (Type ct : Type.values()) {
			if (ct.getTypeName().equals(type)) return true;
		}
		return false;
	}

	@Override
	public Type getType() {
		return AbstractBook.TYPE;
	}

	@Override
	public Type[] getKinds() {
		return AbstractBook.KINDS;
	}
	
	public Type typeOf(Object obj) {
		return obj instanceof HasType ? ((HasType) obj).getType() : null;
	}

}

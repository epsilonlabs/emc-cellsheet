package org.eclipse.epsilon.emc.cellsheet;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.models.Model;

/**
 * Default implementation of an {@link IBook}.
 * 
 * Majority of methods delegate back to the original interface's default methods. Abstract class was created to allow
 * inheritance of default methods from the {@link Model}.
 * 
 * @author Jonathan Co
 *
 */
public abstract class AbstractBook extends Model implements IBook {

	@Override
	public Object getTypeOf(Object obj) {
		return IBook.super.getTypeOf(obj);
	}

	@Override
	public String getTypeNameOf(Object obj) {
		return IBook.super.getTypeNameOf(obj);
	}

	@Override
	public boolean isOfType(Object obj, String typename) throws EolModelElementTypeNotFoundException {
		return IBook.super.isOfType(obj, typename);
	}

	@Override
	public boolean hasType(String type) {
		return IBook.super.hasType(type);
	}
	
}

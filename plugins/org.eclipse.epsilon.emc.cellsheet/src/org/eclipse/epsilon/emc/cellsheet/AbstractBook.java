package org.eclipse.epsilon.emc.cellsheet;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.CachedModel;

public abstract class AbstractBook extends CachedModel<HasId> implements IBook {

	@Override
	public IBook getBook() {
		return this;
	}

	/*
	 * MODEL UPDATE
	 */
	@Override
	public void setElementId(Object instance, String newId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isInstantiable(String type) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected HasId createInstanceInModel(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean deleteElementInModel(Object instance) throws EolRuntimeException {
		throw new UnsupportedOperationException();
	}

	/*
	 * TYPE RELATED
	 */

	@Override
	public boolean hasType(String type) {
		return ElementType.fromTypename(type) != null;
	}

	@Override
	public Object getTypeOf(Object instance) {
		return getHasTypeOrThrow(instance).getType();
	}

	@Override
	public String getTypeNameOf(Object instance) {
		return ((ElementType) getTypeOf(instance)).getTypename();
	}

	@Override
	public boolean isOfType(Object instance, String typename) throws EolModelElementTypeNotFoundException {
		return isOfKindOrType(instance, typename, false);
	}

	@Override
	public boolean isOfType(Object instance, ElementType type) throws EolModelElementTypeNotFoundException {
		return isOfType(instance, type.getTypename());
	}

	@Override
	public boolean isOfKind(Object instance, String typename) throws EolModelElementTypeNotFoundException {
		return isOfKindOrType(instance, typename, true);
	}

	@Override
	public boolean isOfKind(Object instance, ElementType kind) throws EolModelElementTypeNotFoundException {
		return isOfKind(instance, kind.getTypename());
	}

	@Override
	protected Collection<String> getAllTypeNamesOf(Object instance) {
		return getHasTypeOrThrow(instance).getKinds().stream().map(k -> k.getTypename()).collect(Collectors.toList());
	}

	@Override
	protected Object getCacheKeyForType(String typename) throws EolModelElementTypeNotFoundException {
		return getElementTypeOrThrow(typename);
	}

	@Override
	public Collection<HasId> getAllOfType(ElementType type) throws EolModelElementTypeNotFoundException {
		return getAllOfType(type.getTypename());
	}

	@Override
	public Collection<HasId> getAllOfKind(ElementType kind) throws EolModelElementTypeNotFoundException {
		return getAllOfKind(kind.getTypename());
	}

	/*
	 * IDENTIFICATION
	 */

	@Override
	public String getId() {
		return String.format("%s/", name);
	}

	@Override
	public String getA1() {
		return String.format("[%s]", name);
	}

	/*
	 * UTILITY
	 */

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		throw new UnsupportedOperationException();
	}

	public boolean isLoaded() {
		return allContentsAreCached;
	}

	/**
	 * If {@link #isModelElement(Object)} returns false throw exception
	 * 
	 * @param instance
	 * @throws IllegalArgumentException
	 */
	protected HasType getHasTypeOrThrow(Object instance) {
		if (!(instance instanceof HasType)) {
			throw new IllegalArgumentException("Not a valid Cellsheet model element: " + instance + " ( "
					+ instance.getClass().getCanonicalName() + ")");
		}
		return (HasType) instance;
	}

	/**
	 * Retrieve {@link ElementType} if exists else throw an exception
	 * 
	 * @param typename
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 */
	protected ElementType getElementTypeOrThrow(String typename) throws EolModelElementTypeNotFoundException {
		final ElementType type = ElementType.fromTypename(typename);
		if (type == null) {
			throw new EolModelElementTypeNotFoundException(name, typename);
		}
		return type;
	}

	/**
	 * 
	 * @param instance
	 * @param typename
	 * @param isKind
	 * @return
	 * @throws EolModelElementTypeNotFoundException
	 */
	protected boolean isOfKindOrType(Object instance, String typename, boolean isKind)
			throws EolModelElementTypeNotFoundException {
		final ElementType type = getElementTypeOrThrow(typename);
		final HasType hasType = getHasTypeOrThrow(instance);
		return isKind ? hasType.getKinds().contains(type) : hasType.getType() == type;
	}

}

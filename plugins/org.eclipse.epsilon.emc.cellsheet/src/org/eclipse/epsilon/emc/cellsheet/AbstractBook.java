package org.eclipse.epsilon.emc.cellsheet;

import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.CachedModel;

import com.google.common.net.UrlEscapers;

public abstract class AbstractBook extends CachedModel<HasId> implements IBook {

	public static final String PROPERTY_NAME_DEFAULT = "Cellsheet";
	@Override
	public IBook getBook() {
		return this;
	}

	@Override
	public String getElementId(Object instance) {
		return getHasIdOrThrow(instance).getId();
	}

	@Override
	public Object getElementById(String id) {

		// Sanitise if relative ID
		id = id.charAt(0) == '/' ? getName() + id : id;
		id.replaceAll("\\s+", "_");

		final Iterator<String> parts = Arrays.stream(id.split("/")).iterator();

		HasId result = null;

		try {
			// Resolve book
			if (parts.hasNext() && getName().equals(parts.next()))
				result = this;
			else
				return null;

			// Resolve sheet
			if (parts.hasNext())
				result = ((IBook) result).getSheet(parts.next());
			else
				return result;

			// Resolve row
			if (parts.hasNext())
				result = ((ISheet) result).getRow(Integer.parseInt(parts.next()));
			else
				return result;

			// Resolve cell
			if (parts.hasNext())
				result = ((IRow) result).getCell(Integer.parseInt(parts.next()));
			else
				return result;

			// Resolve cellValue
			if (parts.hasNext()) {
				if (!parts.next().equals("value")) {
					throw new IllegalArgumentException("Bad ID Given: " + id);
				}
				result = ((ICell) result).getCellValue();
			} else {
				return result;
			}

			// Resolve AST
			if (parts.hasNext()) {
				Integer.parseInt(parts.next());
				result = ((ICellValue) result).getAst();
			} else {
				return result;
			}

			while (parts.hasNext()) {
				result = ((IAst) result).getChildAt(Integer.parseInt(parts.next()));
			}

			return result;

		} catch (NumberFormatException e) {
			// Skip to throw IAE
		}

		throw new IllegalArgumentException("Bad ID Given: " + id);
	}

	@Override
	public boolean owns(Object instance) {
		return instance instanceof HasId ? getHasTypeOrThrow(instance).getBook() == this : false;
	}

	@Override
	protected Collection<HasId> allContentsFromModel() {
		return new AllContentsCollection();
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
		return UrlEscapers.urlPathSegmentEscaper().escape(name) + "/";
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
	protected HasId getHasIdOrThrow(Object instance) {
		if (!(instance instanceof HasId)) {
			throw new IllegalArgumentException("Not a valid Cellsheet model element with ID: " + instance + " ( "
					+ instance.getClass().getCanonicalName() + ")");
		}
		return (HasId) instance;
	}

	/**
	 * If {@link #isModelElement(Object)} returns false throw exception
	 * 
	 * @param instance
	 * @throws IllegalArgumentException
	 */
	protected HasId getHasTypeOrThrow(Object instance) {
		if (!(instance instanceof HasId)) {
			throw new IllegalArgumentException("Not a valid Cellsheet model element: " + instance + " ( "
					+ instance.getClass().getCanonicalName() + ")");
		}
		return (HasId) instance;
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
		if (instance == null)
			return false;
		
		final ElementType type = getElementTypeOrThrow(typename);
		final HasId hasId = getHasTypeOrThrow(instance);
		return isKind ? hasId.getKinds().contains(type) : hasId.getType() == type;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected class AllContentsCollection extends AbstractCollection<HasId> {

		@Override
		public Iterator<HasId> iterator() {
			return new AllContentsIterator(Collections.singleton(AbstractBook.this).iterator());
		}

		@Override
		public int size() {
			int count = 0;
			final Iterator it = iterator();
			while (it.hasNext()) {
				count++;
				it.next();
			}
			return count;
		}
	}

	@SuppressWarnings({ "rawtypes" })
	protected class AllContentsIterator implements Iterator {

		private Deque<Iterator> stack = new LinkedList<>();
		private Iterator<?> iterator;
		private HasId current = null;

		public AllContentsIterator(Iterator<?> start) {
			this.iterator = start;
		}

		@Override
		public boolean hasNext() {
			if (iterator == null) {
				return false;
			}

			if (iterator.hasNext()) {
				return true;
			}

			if (stack.isEmpty()) {
				return false;
			}

			iterator = stack.pop();
			return hasNext();
		}

		@Override
		public HasId next() {
			if (iterator == null) {
				throw new NoSuchElementException();
			}

			if (iterator.hasNext()) {
				current = (HasId) iterator.next();
			} else {
				iterator = stack.pop();
				return next();
			}

			if (current instanceof Iterable) {
				stack.push(iterator);
				iterator = ((Iterable<?>) current).iterator();
			}

			return current;
		}

	}
	
	@Override
	public void load() throws EolModelLoadingException {
		// Force load of all type classes
		CoreType.values();
		CellValueType.values();
		AstSupertype.values();
		AstType.values();
		super.load();
	}
	
	@Override
	public String toString() {
		return buildToString();
	}
}

package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

import org.eclipse.epsilon.common.util.Multimap;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.CachedModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

import com.google.common.net.UrlEscapers;

public abstract class AbstractBook extends CachedModel<HasId> implements IBook {

	public static final String PROPERTY_NAME_DEFAULT = "Cellsheet";
	public static final String PROPERTY_PRE_CACHE = "pre_cache";

	protected boolean preCache;
	
	protected String bookname;

	protected AbstractBook() {
		// Ensure classloader loads all types
		CoreType.values();
		CellValueType.values();
		AstSupertype.values();
		AstType.values();
	}
	
	@Override
	public String getBookname() {
		return this.bookname;
	}
	
	@Override
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

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
		id = id.charAt(0) == '/' ? getBookname() + id : id;
		id.replaceAll("\\s+", "_");

		final Iterator<String> parts = Arrays.stream(id.split("/")).iterator();

		HasId result = null;

		try {
			// Resolve book
			if (parts.hasNext() && getBookname().equals(parts.next()))
				result = this;
			else
				return null;

			// Resolve sheet
			if (parts.hasNext())
				result = ((IBook) result).getSheet(Integer.parseInt(parts.next()));
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
				result = ((IAst<?>) result).getChildAt(Integer.parseInt(parts.next()));
			}

			return result;

		} catch (NumberFormatException e) {
			// Skip to throw IAE
		}

		throw new IllegalArgumentException("Bad ID Given: " + id);
	}

	@Override
	public boolean owns(Object instance) {
		return instance instanceof HasId ? getHasIdOrThrow(instance).getBook() == this : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Collection<HasId> allContentsFromModel() {
		Profiler.profileStart(this, "allContentsFromModel");
		final Collection<HasId> allContents = new ArrayList<HasId>();
		final Deque<Iterator<HasId>> stack = new LinkedList<>();

		Iterator<? extends HasId> it = iterator();
		HasId next = null;
		try {
			allContents.add(this);
			if (isPreCacheEnabled()) {
				addToCache(getType().getTypename(), this);
			}

			while (it != null) {
				while (it.hasNext()) {
					next = it.next();
					allContents.add(next);

					// Build the cache as we go
					if (isPreCacheEnabled()) {
						addToCache(next.getType().getTypename(), next);
					}
					stack.offer(((Iterable<HasId>) next).iterator());
				}
				it = stack.poll();
			}
		} catch (Exception e) {
			throw new AssertionError(e);
		}
		Profiler.profileStop(this, "allContentsFromModel");
		return allContents;
	}

	@Override
	public Collection<HasId> getAllOfKindFromModel(String typename) throws EolModelElementTypeNotFoundException {
		final ElementType type = getElementTypeOrThrow(typename);
		Profiler.profileStart(this, "getAllOfKindFromModel");
		final List<HasId> kinds = allContents()	.stream()
												.filter(e -> e.getKinds().contains(type))
												.collect(Collectors.toList());
		Profiler.profileStop(this, "getAllOfKindFromModel");
		return kinds;
	}

	@Override
	public Collection<HasId> getAllOfTypeFromModel(String typename) throws EolModelElementTypeNotFoundException {
		Profiler.profileStart(this, "getAllOfTypeFromModel");
		final ElementType type = getElementTypeOrThrow(typename);
		final List<HasId> types = allContents()	.stream()
												.filter(Objects::nonNull)
												.filter(e -> e.getType() == type)
												.collect(Collectors.toList());
		Profiler.profileStop(this, "getAllOfTypeFromModel");
		return types;
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
		return getHasIdOrThrow(instance).getType();
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
		return getHasIdOrThrow(instance).getKinds().stream().map(k -> k.getTypename()).collect(Collectors.toList());
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
	public Collection<HasId> getAllOfType(String type) throws EolModelElementTypeNotFoundException {
		Profiler.profileCaller();
		Profiler.profileStart(this, "getAllOfType(" + type + ")");
		final Collection<HasId> allOfType = super.getAllOfType(type);
		Profiler.profileStop(this, "getAllOfType(" + type + ")");
		return allOfType;
	}

	@Override
	public Collection<HasId> getAllOfKind(ElementType kind) throws EolModelElementTypeNotFoundException {
		return getAllOfKind(kind.getTypename());
	}

	@Override
	public Collection<HasId> getAllOfKind(String kind) throws EolModelElementTypeNotFoundException {
		Profiler.profileCaller();
		Profiler.profileStart(this, "getAllOfKind(" + kind + ")");
		final Collection<HasId> allOfKind = super.getAllOfKind(kind);
		Profiler.profileStop(this, "getAllOfKind(" + kind + ")");
		return allOfKind;
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
		final HasId hasId = getHasIdOrThrow(instance);
		return isKind ? hasId.getKinds().contains(type) : hasId.getType() == type;
	}

	/*
	 * IDENTIFICATION
	 */

	@Override
	public String getId() {
		return UrlEscapers.urlPathSegmentEscaper().escape(bookname) + "/";
	}

	@Override
	public String getA1() {
		return String.format("[%s]", bookname);
	}

	/*
	 * UTILITY
	 */

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		throw new UnsupportedOperationException();
	}

	public boolean isCached() {
		return allContentsAreCached;
	}

	@Override
	public void setConcurrent(boolean concurrent) {
		this.concurrent = concurrent;
		typeCache = typeCache != null ? new Multimap<>(isConcurrent(), typeCache) : new Multimap<>(isConcurrent());
		kindCache = typeCache;

		if (concurrent) {
			allContentsCache = allContentsCache != null ? new ConcurrentLinkedQueue<>(allContentsCache)
					: new ConcurrentLinkedQueue<>();
		} else {
			allContentsCache = allContentsCache != null ? new ArrayList<>(allContentsCache) : new ArrayList<>();
		}
	}

	public void setPreCache(boolean preCache) {
		this.preCache = preCache && isCachingEnabled();
	}

	public boolean isPreCacheEnabled() {
		return preCache;
	}

	@Override
	protected void addToCache(String type, HasId instance) throws EolModelElementTypeNotFoundException {
		if (allContentsAreCached)
			allContentsCache.add(instance);

		for (ElementType t : instance.getKinds()) {
			typeCache.put(t, instance);
		}
	}

	@Override
	protected void removeFromCache(HasId instance) throws EolModelElementTypeNotFoundException {
		if (allContentsAreCached)
			allContentsCache.remove(instance);

		for (ElementType t : instance.getKinds()) {
			typeCache.remove(t, instance);
		}
	}

	@Override
	public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
		super.load(properties, resolver);
		this.setPreCache(properties.getBooleanProperty(PROPERTY_PRE_CACHE, false));
		this.bookname = properties.getProperty(PROPERTY_NAME);
	}

	@Override
	public void load() throws EolModelLoadingException {
		super.load();
		if (preCache) {
			doPreCache();
		}
	}

	@Override
	public void clearCache() {
		super.clearCache();
		if (preCache) {
			ElementType.getTypeMap().values().forEach(t -> typeCache.put(t, Collections.emptyList()));
		}
	}

	protected abstract void doPreCache() throws EolModelLoadingException;

	@Override
	public String toString() {
		return buildToString();
	}
}

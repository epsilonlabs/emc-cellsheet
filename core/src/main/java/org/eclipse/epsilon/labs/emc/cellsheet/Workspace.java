/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.ForwardingIterator;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.io.Files;
import com.google.common.net.UrlEscapers;
import com.google.common.primitives.Ints;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.CachedModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;

public class Workspace extends CachedModel<CellsheetElement> implements CellsheetElement {

    public static final String SCHEME = "cellsheet";
    public static final String PROPERTY_MODEL_URI = "modelUri";

    private static final Set<String> ALLOWED_SCHEMES = ImmutableSet.of(SCHEME, AstPayload.SCHEME);

    private static Logger logger = LoggerFactory.getLogger(Workspace.class);

    protected Map<String, BookProvider> providerRegistry = new HashMap<>();
    protected Map<String, Book> books = new LinkedHashMap<>();

    private String id = null;

    public Workspace() {
    }

    public Workspace(String name) {
        this.name = name;
    }

    /**
     * Returns the book with the given name or {@code null} if it does
     * not exist.
     *
     * @param bookName the name of the book
     * @return book with the given name or {@code null} if none exists
     */
    public Book getBook(String bookName) {
        return books.get(bookName);
    }

    /**
     * Returns all the books in this workspace. Returned books will be insertion
     * order based.
     *
     * @return all books in this workspace
     */
    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    /**
     * Add a book to this workspace
     *
     * @param book the book to add
     */
    public void addBook(Book book) {
        books.put(book.getBookName(), book);
        book.setWorkspace(this);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        this.id = null;
    }

    @Override
    @Nonnull
    public Iterator<Book> iterator() {
        return books.values().iterator();
    }

    @Override
    protected Collection<CellsheetElement> allContentsFromModel() {
        return new AllContentsCollection(this);
    }

    public Collection<CellsheetElement> getAllOfType(CellsheetType type) throws EolModelElementTypeNotFoundException {
        checkNotNull(type);
        return getAllOfType(type.getTypeName());
    }

    @Override
    protected Collection<CellsheetElement> getAllOfTypeFromModel(String typeName) throws EolModelElementTypeNotFoundException {
        if (!hasType(typeName))
            throw new EolModelElementTypeNotFoundException(getName(), typeName);
        CellsheetType type = CellsheetType.fromTypeName(typeName);
        return allContents().stream()
                .filter(input -> input.getType() == type)
                .collect(Collectors.toList());
    }

    public Collection<CellsheetElement> getAllOfKind(CellsheetType kind) throws EolModelElementTypeNotFoundException {
        checkNotNull(kind);
        return getAllOfKind(kind.getTypeName());
    }

    @Override
    protected Collection<CellsheetElement> getAllOfKindFromModel(String kindName) throws EolModelElementTypeNotFoundException {
        if (!hasType(kindName))
            throw new EolModelElementTypeNotFoundException(getName(), kindName);
        CellsheetType kind = CellsheetType.fromTypeName(kindName);
        return allContents().stream()
                .filter(input -> input.getKinds().contains(kind))
                .collect(Collectors.toList());
    }

    @Override
    protected CellsheetElement createInstanceInModel(String type) throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
        super.load(properties, resolver);
        checkState(!providerRegistry.isEmpty(), "No BookProvider's registered");

        // Get the model uris to load. If none are specified then by default add
        // in a null value and create a new one by default.
        String[] modelUris = properties.hasProperty(PROPERTY_MODEL_URI)
                ? properties.getProperty(PROPERTY_MODEL_URI).split(";")
                : new String[]{null};

        for (String uri : modelUris) {
            final BookProvider provider;

            if (uri == null) {
                provider = providerRegistry.get(null);
                checkState(providerRegistry.containsKey(null),
                        "No backend with 'supportsDefault()==true' registered");
                logger.debug("No modelUri property set, using default provider '{}'", provider.getClass().getCanonicalName());
            } else {
                String ext = Files.getFileExtension(uri);
                checkArgument(!Strings.isNullOrEmpty(ext),
                        "Unknown extension given for '%s'",
                        uri);
                provider = providerRegistry.get(ext);
                checkState(providerRegistry.containsKey(ext),
                        "No backend registered for extension %s",
                        ext);
            }

            Book book = provider.buildBook(uri, this);
            addBook(book);
        }

        load();
    }

    @Override
    protected void loadModel() throws EolModelLoadingException {
        for (Book book : books.values()) {
            book.load();
        }
    }

    @Override
    protected void disposeModel() {
        books.values().forEach(Book::dispose);
        books.clear();
    }

    @Override
    protected boolean deleteElementInModel(Object instance) throws EolRuntimeException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object getCacheKeyForType(String typeName) throws EolModelElementTypeNotFoundException {
        if (!hasType(typeName))
            throw new EolModelElementTypeNotFoundException(name, typeName);
        return CellsheetType.fromTypeName(typeName);
    }

    @Override
    protected Collection<String> getAllTypeNamesOf(Object instance) {
        if (instance instanceof CellsheetElement) {
            return ((CellsheetElement) instance).getKinds().stream()
                    .map(CellsheetType::getTypeName)
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Object getEnumerationValue(String enumeration, String label) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getTypeNameOf(Object instance) {
        return instance instanceof CellsheetElement ? ((CellsheetElement) instance).getType().getTypeName() : null;
    }


    @Override
    public String getElementId(Object instance) {
        return instance instanceof CellsheetElement ? ((CellsheetElement) instance).getId() : null;
    }

    @Override
    public void setElementId(Object instance, String newId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean owns(Object instance) {
        if (instance instanceof CellsheetElement) {
            // May need to change to getWorkspace
            return getElementById(((CellsheetElement) instance).getId()) != null;
        }
        return false;
    }

    @Override
    public boolean isInstantiable(String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasType(String type) {
        return CellsheetType.fromTypeName(type) != null;
    }

    @Override
    public boolean store(String location) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean store() {
        throw new UnsupportedOperationException();
    }

    @Nonnull
    @Override
    public String getId() {
        if (id == null) {
            if (name == null) return CellsheetElement.super.getId();
            id = SCHEME + "://" + UrlEscapers.urlPathSegmentEscaper().escape(getName());
        }
        return id;
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.WORKSPACE;
    }

    @Nonnull
    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.WORKSPACE, CellsheetType.CELLSHEET_ELEMENT);
    }

    @SuppressWarnings("unchecked")
    public void registerProvider(BookProvider backend) {
        if (backend.supportsDefault())
            providerRegistry.putIfAbsent(null, backend);
        backend.getExtensions().forEach(e -> registerProvider((String) e, backend));
    }

    public void registerProvider(String extension, BookProvider backend) {
        if (providerRegistry.containsKey(extension)) {
            throw new IllegalStateException(String.format("Extension '.%s' already registered with %s",
                    extension,
                    providerRegistry.get(extension).getClass().getCanonicalName()));
        }
        providerRegistry.put(extension, backend);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("name", getName())
                .add("cached", isCachingEnabled())
                .add("type", getType().getTypeName())
                .add("kinds", getKinds().stream().map(CellsheetType::getTypeName).collect(Collectors.joining(",")))
                .toString();
    }

    @Override
    public Object getElementById(String id) {
        if (id == null) return null;

        try {
            URI uri = new URI(id);
            String scheme = uri.getScheme();
            checkArgument(ALLOWED_SCHEMES.contains(scheme),
                    "Scheme must be one of [%s], given",
                    String.join(",", ALLOWED_SCHEMES),
                    id);
            switch (scheme) {
                case SCHEME:
                    return resolveCellsheetId(uri);
                case AstPayload.SCHEME:
                    return resolveAstPayloadId(uri);
                default:
                    throw new AssertionError();
            }

        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(String.format("Malformed ID given [%s]", id), e);
        }
    }

    private Object resolveCellsheetId(URI uri) {
        // Check if part of this workspace
        if (!Objects.equals(getName(), uri.getAuthority()))
            return null;

        if (Strings.isNullOrEmpty(uri.getPath())) return this;

        String[] uriParts = uri.getPath().split("/");
        Iterator<String> it = Arrays.stream(uriParts).iterator();
        it.next(); // Skip one as first entry is always empty

        // Process the ID
        Integer idx; // holder for index variables
        Book book;
        Sheet sheet;
        Row row;
        Cell cell;
        Ast ast;

        // Book part
        book = books.get(it.next());
        if (book == null) return null;
        if (!it.hasNext()) return book;

        // Sheet part
        idx = Ints.tryParse(it.next());
        checkNotNull(idx, "Sheet index must be an integer [%s]", id);
        sheet = book.getSheet(idx);
        if (sheet == null) return null;
        if (!it.hasNext()) return sheet;

        // Row
        idx = Ints.tryParse(it.next());
        checkNotNull(idx, "Row index must be an integer [%s]", id);
        row = sheet.getRow(idx);
        if (row == null) return null;
        if (!it.hasNext()) return row;

        // Cell
        idx = Ints.tryParse(it.next());
        checkNotNull(idx, "Cell index must be an integer [%s]", id);
        cell = row.getCell(idx);
        if (cell == null) return null;
        if (!it.hasNext()) return cell;

        // Cell parts
        String cellPart = it.next();
        switch (cellPart) {
            case "asts":
                checkArgument(it.hasNext(), "Missing AST key in ID [%s]", id);
                String astKey = it.next();
                ast = cell.getAst(astKey);
                while (it.hasNext())
                    ast = ast.childAt(Integer.valueOf(it.next()));
                return ast;
            case "format": // TODO: not supporting cell formats fully atm
            default:
                throw new IllegalArgumentException(String.format("Unrecognised cell part given [%s]", id));
        }

    }

    private AstPayload resolveAstPayloadId(URI uri) throws NumberFormatException {
        String[] uriParts = uri.getPath().split("/");
        checkArgument(uriParts.length == 2, "Malformed ID given %s", id);

        CellsheetType type = CellsheetType.fromTypeName(uriParts[0]);
        String uuid = uriParts[1];
        checkArgument(type != null, "Malformed ID given %s", id);

        return AstPayloads.fromUuid(type, id);
    }

    class AllContentsCollection extends AbstractCollection<CellsheetElement> {

        private final Workspace workspace;

        private AllContentsCollection(Workspace workspace) {
            this.workspace = workspace;
        }

        @Override
        public int size() {
            return Iterators.size(iterator());
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<CellsheetElement> iterator() {
            return new AllContentsIterator(workspace);
        }

        @Override
        public boolean add(CellsheetElement cellsheetElement) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends CellsheetElement> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    class AllContentsIterator extends ForwardingIterator<CellsheetElement> {

        private Iterator<CellsheetElement> delegate;

        AllContentsIterator(Workspace workspace) {
            this.delegate = Iterators.singletonIterator(workspace);
        }

        @Override
        public CellsheetElement next() {
            CellsheetElement next = super.next();
            Iterator<CellsheetElement> toAdd = Iterators.unmodifiableIterator(next.iterator());
            if (toAdd.hasNext())
                this.delegate = Iterators.concat(delegate, toAdd);
            return next;
        }

        @Override
        protected Iterator<CellsheetElement> delegate() {
            return delegate;
        }
    }
}

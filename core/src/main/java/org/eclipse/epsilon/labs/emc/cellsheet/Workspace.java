package org.eclipse.epsilon.labs.emc.cellsheet;

import com.google.common.base.Splitter;
import com.google.common.collect.ForwardingDeque;
import com.google.common.collect.Queues;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.CachedModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

import java.util.*;
import java.util.stream.Collectors;

public class Workspace extends CachedModel<HasId> implements HasId {

    public static final String PROPERTY_EXTENSION = "backendRegistry";
    public static final String PROPERTY_MODEL_URIS = "modelUri";

    private Set<CellsheetBackend> backendRegistry = new HashSet<>();

    protected Map<String, Token> tokens = new HashMap<>();
    protected Map<String, Book> books = new HashMap<>();

    public Token getToken(String value) {
        return tokens.get(value);
    }

    public void addToken(Token token) {
        tokens.put(token.getValue(), token);
    }

    public Map<String, Book> getBooks() {
        return books;
    }

    @Override
    public Iterator<Book> iterator() {
        return books.values().iterator();
    }

    @Override
    protected Collection<HasId> allContentsFromModel() {
        List<HasId> list = new ArrayList<>();

        final Deque<Iterator> iterators = new ForwardingDeque<Iterator>() {
            final Deque<Iterator> delegate = Queues.newArrayDeque();

            @Override
            public boolean offer(Iterator o) {
                if (o == null) return false;
                return delegate.offer(o);
            }

            @Override
            protected Deque<Iterator> delegate() {
                return delegate;
            }
        };

        Iterator it;
        HasId current;
        iterators.add(iterator());

        while (iterator().hasNext()) {
            it = iterators.pop();
            while (it.hasNext()) {
                current = (HasId) it.next();
                list.add(current);
                iterators.offer(current.iterator());
            }
        }

        return list;
    }

    @Override
    protected Collection<HasId> getAllOfTypeFromModel(String typeName) throws EolModelElementTypeNotFoundException {
        CellsheetType type = CellsheetType.fromTypeName(typeName);
        if (type == null) {
            throw new EolModelElementTypeNotFoundException(name, typeName);
        }
        return allContents()
                .stream()
                .filter(i -> i.getType() == type)
                .collect(Collectors.toList());
    }

    @Override
    protected Collection<HasId> getAllOfKindFromModel(String kindName) throws EolModelElementTypeNotFoundException {
        CellsheetType kind = CellsheetType.fromTypeName(kindName);
        if (kind == null) {
            throw new EolModelElementTypeNotFoundException(name, kindName);
        }
        return allContents()
                .stream()
                .filter(i -> i.getKinds().contains(kind))
                .collect(Collectors.toList());
    }

    @Override
    protected HasId createInstanceInModel(String type) throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
        super.load(properties, resolver);

        if (backendRegistry.isEmpty())
            throw new IllegalStateException("No CellSheetBackend registered");

        // Create Books
        String modelUrisProp = properties.getProperty(PROPERTY_MODEL_URIS);
        if (modelUrisProp == null)
            throw new IllegalArgumentException("No " + PROPERTY_MODEL_URIS + " specified");

        List<String> modelUris = Splitter.on(",").trimResults().splitToList(modelUrisProp);

        for (String uri : modelUris) {
            CellsheetBackend backend = backendRegistry
                    .stream()
                    .filter(f -> f.isApplicable(uri))
                    .findFirst()
                    .orElse(null);

            if (backend == null)
                throw new IllegalArgumentException("No CellSheetBackend registered applicable for model URI: " + uri);
            Book book = backend.getBuilder()
                    .withWorkspace(this)
                    .withModelUri(uri)
                    .build();
            books.put(book.getBookName(), book);
        }

        // Do the loading
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
    }

    @Override
    protected boolean deleteElementInModel(Object instance) throws EolRuntimeException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object getCacheKeyForType(String typeName) throws EolModelElementTypeNotFoundException {
        CellsheetType type = CellsheetType.fromTypeName(typeName);
        if (type == null) throw new EolModelElementTypeNotFoundException(name, typeName);
        return type;
    }

    @Override
    protected Collection<String> getAllTypeNamesOf(Object instance) {
        if (instance instanceof HasId) {
            return ((HasId) instance).getKinds().stream().map(CellsheetType::getTypeName).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getTypeNameOf(Object instance) {
        return instance instanceof HasId ? ((HasId) instance).getType().getTypeName() : null;
    }

    @Override
    public Object getElementById(String id) {
//        URI uri = null;
//        try {
//            uri = new URI(id);
//        } catch(URISyntaxException e) {
//            throw new IllegalArgumentException("Malformed ID given: ", e);
//        }
//
//        if (!uri.getScheme().equals("cellsheet")) throw new IllegalArgumentException("Non-Cellsheet ID given");
//
//        ListIterator<String> pathParts = Arrays.asList(uri.getPath().split("/")).listIterator();
//
//        if (!pathParts.hasNext()) {
//            return this;
//        }
//
//        Book book = books.get(pathParts.next());
//        if (pathParts.hasNext() ) {
//            if (book == null)
//                throw new IllegalArgumentException("No Book with name: " + pathParts.previous());
//        } else {
//            return book;
//        }
//
//        Integer sheetIndex = Ints.tryParse(pathParts.next());
//        if (sheetIndex == null) throw new IllegalArgumentException("Bad ID given, sheet part invalid");
//
//        Sheet sheet = book.getSheet(Ints.tryParse(pathParts.next()));
//

        throw new UnsupportedOperationException();
    }

    @Override
    public String getElementId(Object instance) {
        return instance instanceof HasId ? ((HasId) instance).getId() : null;
    }

    @Override
    public void setElementId(Object instance, String newId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean owns(Object instance) {
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

    @Override
    public String getId() {
        return "cellsheet:///" + getName();
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.WORKSPACE;
    }

    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.WORKSPACE, CellsheetType.HAS_ID);
    }

    public void registerBookFactory(CellsheetBackend backend) {
        backendRegistry.add(backend);
    }
}

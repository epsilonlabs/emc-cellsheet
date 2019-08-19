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
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ForwardingIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.net.UrlEscapers;
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
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class Workspace extends CachedModel<CellsheetElement> implements CellsheetElement {

    public static final String PROPERTY_EXTENSION = "backendRegistry";
    public static final String PROPERTY_MODEL_URIS = "modelUri";

    private static Logger logger = LoggerFactory.getLogger(Workspace.class);
    protected Map<String, Book> books = new HashMap<>();
    private Set<CellsheetBackend> backendRegistry = new HashSet<>();

    public Token getToken(String value) {
        return Tokens.getToken(value);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books.values());
    }

    public void addBook(Book book) {
        books.put(book.getBookName(), book);
        book.setWorkspace(this);
    }

    @Override
    @Nonnull
    public Iterator<Book> iterator() {
        return books.values().iterator();
    }

    @Override
    protected Collection<CellsheetElement> allContentsFromModel() {
        AllContentsIterator it = new AllContentsIterator(this);
        return Lists.newArrayList(it);
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
            addBook(book);
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
        return name == null ? CellsheetElement.super.getId() : "cellsheet://" + UrlEscapers.urlPathSegmentEscaper().escape(getName());
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

    public void registerBookFactory(CellsheetBackend backend) {
        backendRegistry.add(backend);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("name", getName())
                .add("cached", isCachingEnabled())
                .add("concurrent", isConcurrent())
                .add("type", getType().getTypeName())
                .add("kinds", getKinds().stream().map(CellsheetType::getTypeName).collect(Collectors.joining(",")))
                .toString();
    }

    @Override
    public Object getElementById(String id) {
        if (id == null) return null;

        // Validation Checks
        final Iterator<String> it;
        try {
            URI uri = new URI(id);
            if (!uri.getScheme().equals("cellsheet"))
                throw new IllegalArgumentException(String.format("Non-Cellsheet ID given %s", id));
            // Check workspace
            String workspacePart = URLDecoder.decode(uri.getAuthority(), "UTF-8");
            if (!workspacePart.equals(getName()))
                return null; // Not an ID for this workspace
            if (Strings.isNullOrEmpty(uri.getPath())) return this;
            it = Arrays.stream(uri.getPath().split("/")).iterator();
            it.next();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(String.format("Malformed ID given %s", id), e);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 not supported");
        }

        /*
         * Processing
         */
        String part;
        try {
            // Book
            Book book;
            part = URLDecoder.decode(it.next(), "UTF-8");
            book = books.get(part);
            if (book == null) return null;
            if (!it.hasNext()) return book;

            // Sheet
            Sheet sheet;
            part = it.next();
            sheet = book.getSheet(Integer.valueOf(part));
            if (sheet == null) return null;
            if (!it.hasNext()) return sheet;

            // Row
            Row row;
            part = it.next();
            row = sheet.getRow(Integer.valueOf(part));
            if (row == null) return null;
            if (!it.hasNext()) return row;

            // Cell
            Cell cell;
            part = it.next();
            cell = row.getCell(Integer.valueOf(part));
            if (cell == null) return null;
            if (!it.hasNext()) return cell;

            // Cell parts
            part = it.next();
            if (!it.hasNext())
                throw new IllegalArgumentException(String.format("Malformed ID given %s, missing cell parts", id));
            switch (part) {
                case "asts":
                    return resolveAstIdPath(cell, it);
                case "cellFormat":
                default:
                    throw new UnsupportedOperationException(String.format("[%s] cell part is not supported yet", part));
            }

        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 not supported");
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("Malformed ID given %s", id), e);
        }
    }

    private Ast resolveAstIdPath(Cell cell, Iterator<String> it) throws NumberFormatException {
        int astIdx = Integer.valueOf(it.next());
        Ast ast = (Ast) cell.getAsts().get(astIdx);
        while (it.hasNext()) ast = ast.childAt(Integer.valueOf(it.next()));
        return ast;
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

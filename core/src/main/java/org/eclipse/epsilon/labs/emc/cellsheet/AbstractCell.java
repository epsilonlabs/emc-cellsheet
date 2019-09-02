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

import com.google.common.collect.BiMap;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Common implementation of a {@link Cell}
 *
 * @param <T> type of data to hold in cell
 */
public abstract class AbstractCell<T> implements Cell<T> {

    protected T value;
    protected Row row;
    protected int colIndex;
    final AstMap asts = new AstMap();

    /**
     * Returns the parent {@link Book} of this cell
     *
     * @return the parent book
     */
    public Book getBook() {
        return row.getBook();
    }

    /**
     * Returns the parent {@link Sheet} of this cell
     *
     * @return the parent sheet
     */
    public Sheet getSheet() {
        return row.getSheet();
    }

    /**
     * Returns the parent {@link Row} of this cell
     *
     * @return the parent row
     */
    public Row getRow() {
        return row;
    }

    /**
     * Returns the 0-based column index of this cell
     *
     * @return 0-based column index of this cell
     */
    public int getColIndex() {
        return colIndex;
    }

    /**
     * Returns the alpha column index of this cell
     *
     * @return alpha column index of this cell
     */
    public String getA1ColIndex() {
        return ReferenceUtil.indexToA1(getColIndex());
    }

    /**
     * Returns the 0-based row index of this cell
     *
     * @return 0-based row index of this cell
     */
    public int getRowIndex() {
        return getRow().getRowIndex();
    }

    /**
     * Returns the 1-based row index of this cell
     *
     * @return 1-based row index of this cell
     */
    public int getA1RowIndex() {
        return getRow().getA1RowIndex();
    }

    /**
     * Returns the raw value of this cell
     *
     * @return raw value of this cell
     */
    public T getValue() {
        return value;
    }

    /**
     * Returns any AST associated with this cell.
     * <p>The first element of the returned collection will always be the root AST</p>
     *
     * @return the ASTs associated with this cell
     */
    public Collection<Ast> getAsts() {
        return asts.values();
    }

    /**
     * Returns the default Ast derived from the value of this cell
     *
     * @return the default AST derived from the value of this cell
     */
    public Ast getRoot() {
        return asts.computeIfAbsent(Cell.ROOT_AST_KEY, k -> buildRoot());
    }

    protected abstract Ast buildRoot();

    /**
     * Associate an Ast with this Cell and the given key removing any previous
     * cell association
     * <p>
     * All Asts are mapped with a key for later retrieval. The default Ast
     * corresponding to the default value of this cell is always mapped with
     * the {@value #ROOT_AST_KEY}
     * </p>
     *
     * @param key the key to associate this ast to
     * @param ast the AST to associate with this cell. Must be a root Ast
     */
    @Override
    public void putAst(String key, Ast ast) {
        checkArgument(!ROOT_AST_KEY.equals(key), ROOT_AST_KEY + " is a reserved key");
        if (ast.getCell() != null) ast.getCell().removeAst(ast);
        asts.put(key, ast);
    }

    @Override
    public Ast removeAst(Ast ast) {
        return asts.remove(ast.getCellKey());
    }

    @Override
    public Ast removeAst(String key) {
        return asts.remove(key);
    }

    /**
     * Retrieve the Ast with the given key
     *
     * @param key the key
     * @return the Ast or {@code null} if no such mapping exists
     * @throws IndexOutOfBoundsException if the position is out of bounds
     */
    public Ast getAst(String key) {
        return asts.get(key);
    }

    @Override
    public String getAstKey(Ast ast) {
        return asts.delegate.inverse().get(ast);
    }

    @Nonnull
    @Override
    public Iterator<CellsheetElement> iterator() {
        return ImmutableList.<CellsheetElement>copyOf(asts.values()).iterator();
    }

    private class AstMap extends ForwardingMap<String, Ast> {

        private final BiMap<String, Ast> delegate = HashBiMap.create(1);

        @Override
        public Ast put(String key, Ast value) {
            checkArgument(value.isRoot(), "Only root Ast's can be mapped to a cell");
            super.remove(key);
            value.cell = AbstractCell.this;
            value.position = 0;
            return super.put(key, value);
        }

        @Override
        public Ast remove(Object object) {
            Ast removed = super.remove(object);
            if (removed != null) {
                removed.cell = null;
                removed.position = Ast.UNASSIGNED;
            }
            return removed;
        }

        @Override
        protected Map<String, Ast> delegate() {
            return delegate;
        }
    }

}

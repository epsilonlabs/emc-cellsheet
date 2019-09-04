/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
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
 * Common implementation fromToken a {@link Cell}
 *
 * @param <T> type fromToken data to hold in cell
 */
public abstract class AbstractCell<T> implements Cell<T> {

    final AstMap asts = new AstMap();
    protected T value;
    protected Row row;
    protected int colIndex;

    @Override
    public Book getBook() {
        return row.getBook();
    }

    @Override
    public Sheet getSheet() {
        return row.getSheet();
    }

    @Override
    public Row getRow() {
        return row;
    }

    @Override
    public int getColIndex() {
        return colIndex;
    }

    @Override
    public String getA1ColIndex() {
        return ReferenceUtil.indexToA1(getColIndex());
    }

    @Override
    public int getRowIndex() {
        return getRow().getRowIndex();
    }

    @Override
    public int getA1RowIndex() {
        return getRow().getA1RowIndex();
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public Collection<Ast> getAsts() {
        return getInternalAsts().values();
    }

    @Override
    public Ast getRoot() {
        return getInternalAsts().get(Cell.ROOT_AST_KEY);
    }

    protected abstract Ast buildRoot();

    @Override
    public void putAst(String key, Ast ast) {
        checkArgument(!ROOT_AST_KEY.equals(key), ROOT_AST_KEY + " is a reserved key");
        if (ast.getCell() != null) ast.getCell().removeAst(ast);
        getInternalAsts().put(key, ast);
    }

    @Override
    public Ast removeAst(Ast ast) {
        return getInternalAsts().remove(ast.getCellKey());
    }

    @Override
    public Ast removeAst(String key) {
        return getInternalAsts().remove(key);
    }

    @Override
    public Ast getAst(String key) {
        return getInternalAsts().get(key);
    }

    @Override
    public String getAstKey(Ast ast) {
        return getInternalAsts().delegate.inverse().get(ast);
    }

    private AstMap getInternalAsts() {
        if (!asts.containsKey(Cell.ROOT_AST_KEY)) {
            asts.put(Cell.ROOT_AST_KEY, buildRoot());
        }
        return asts;
    }

    @Nonnull
    @Override
    public Iterator<CellsheetElement> iterator() {
        return ImmutableList.<CellsheetElement>copyOf(getAsts()).iterator();
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

/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.test;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.ImmutableList;
import org.eclipse.epsilon.labs.emc.cellsheet.*;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DummyCell implements Cell {

    Row row;
    int colIndex;
    List<Ast> asts = new DummyAstList();

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

    public void setRow(Row row) {
        this.row = row;
    }

    @Override
    public int getColIndex() {
        return colIndex;
    }

    public void setColIndex(int colIndex) {
        this.colIndex = colIndex;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public List<Ast> getAsts() {
        return asts;
    }

    @Nonnull
    @Override
    public Iterator<CellsheetElement> iterator() {
        return ImmutableList.<CellsheetElement>copyOf(getAsts()).iterator();
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.BLANK_CELL;
    }

    class DummyAstList extends ForwardingList<Ast> {
        private final List<Ast> delegate = new ArrayList<>();

        @Override
        public Ast get(int index) {
            while (delegate.size() < index + 1) {
                DummyAst ast = new DummyAst();
                ast.setPosition(index);
                ast.setCell(DummyCell.this);
                delegate.add(ast);
            }
            return delegate.get(index);
        }

        @Override
        protected List<Ast> delegate() {
            return delegate;
        }
    }
}

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
import org.eclipse.epsilon.labs.emc.cellsheet.*;

import java.util.Iterator;
import java.util.List;

public class DummyCell implements Cell {

    Row row;
    int colIndex;

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
        return new DummyAstList();
    }

    @Override
    public Iterator<HasId> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.BLANK_CELL;
    }

    class DummyAstList extends ForwardingList<Ast> {

        @Override
        public Ast get(int index) {
            DummyAst ast = new DummyAst();
            ast.setCell(DummyCell.this);
            ast.setPosition(index);
            return ast;
        }

        @Override
        protected List<Ast> delegate() {
            throw new UnsupportedOperationException();
        }
    }
}

/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.test;

import org.eclipse.epsilon.labs.emc.cellsheet.*;

import javax.annotation.Nonnull;

public class DummyCell extends AbstractCell<Object> {

    Row row;
    int colIndex;

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
    protected Ast buildRoot() {
        return new DummyAst();
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.BLANK_CELL;
    }

}

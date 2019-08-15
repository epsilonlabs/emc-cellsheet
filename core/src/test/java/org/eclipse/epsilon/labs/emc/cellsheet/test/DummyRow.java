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

import org.eclipse.epsilon.labs.emc.cellsheet.Cell;
import org.eclipse.epsilon.labs.emc.cellsheet.Row;
import org.eclipse.epsilon.labs.emc.cellsheet.Sheet;

import java.util.Iterator;
import java.util.List;

public class DummyRow implements Row {

    Sheet sheet;
    int rowIndex;

    @Override
    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(DummySheet sheet) {
        this.sheet = sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public Cell getCell(int colIndex) {
        DummyCell cell = new DummyCell();
        cell.setRow(this);
        cell.setColIndex(colIndex);
        return cell;
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public List<Cell> getCells() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Cell> iterator() {
        throw new UnsupportedOperationException();
    }
}

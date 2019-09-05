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

import com.google.common.collect.ImmutableList;
import org.eclipse.epsilon.labs.emc.cellsheet.Cell;
import org.eclipse.epsilon.labs.emc.cellsheet.Row;
import org.eclipse.epsilon.labs.emc.cellsheet.Sheet;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DummyRow implements Row {

    Sheet sheet;
    int rowIndex;
    List<DummyCell> cells = new ArrayList<>();

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
        while (cells.size() < colIndex + 1) {
            DummyCell cell = new DummyCell();
            cell.setRow(this);
            cell.setColIndex(colIndex);
            cells.add(cell);
        }
        return cells.get(colIndex);
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
        return ImmutableList.copyOf(cells);
    }

    @Nonnull
    @Override
    public Iterator<Cell> iterator() {
        return getCells().iterator();
    }
}

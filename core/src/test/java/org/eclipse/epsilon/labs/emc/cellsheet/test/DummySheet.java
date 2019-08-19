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
import org.eclipse.epsilon.labs.emc.cellsheet.Book;
import org.eclipse.epsilon.labs.emc.cellsheet.Row;
import org.eclipse.epsilon.labs.emc.cellsheet.Sheet;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DummySheet implements Sheet {

    Book book;
    int sheetIndex;
    List<DummyRow> rows = new ArrayList<>();

    @Override
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public Row getRow(int rowIndex) {
        while (rows.size() < rowIndex + 1) {
            DummyRow row = new DummyRow();
            row.setSheet(this);
            row.setRowIndex(rowIndex);
            rows.add(row);
        }
        return rows.get(rowIndex);
    }

    @Override
    public String getSheetName() {
        return "Dummy Sheet " + sheetIndex;
    }

    @Override
    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }

    @Override
    public List<Row> getRows() {
        return ImmutableList.copyOf(rows);
    }

    @Nonnull
    @Override
    public Iterator<Row> iterator() {
        return getRows().iterator();
    }
}

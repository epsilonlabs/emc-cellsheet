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

import org.eclipse.epsilon.labs.emc.cellsheet.Book;
import org.eclipse.epsilon.labs.emc.cellsheet.Row;
import org.eclipse.epsilon.labs.emc.cellsheet.Sheet;

import java.util.Iterator;
import java.util.List;

public class DummySheet implements Sheet {

    Book book;
    int sheetIndex;

    @Override
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public Row getRow(int rowIndex) {
        DummyRow row = new DummyRow();
        row.setSheet(this);
        row.setRowIndex(rowIndex);
        return row;
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Row> iterator() {
        throw new UnsupportedOperationException();
    }
}

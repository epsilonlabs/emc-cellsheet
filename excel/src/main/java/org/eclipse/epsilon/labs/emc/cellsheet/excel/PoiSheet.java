/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import org.eclipse.epsilon.labs.emc.cellsheet.Book;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Row;
import org.eclipse.epsilon.labs.emc.cellsheet.Sheet;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public class PoiSheet implements Sheet, PoiDelegate<org.apache.poi.ss.usermodel.Sheet> {

    private PoiBook book;
    private String sheetName;
    private int sheetIndex;

    @Override
    public org.apache.poi.ss.usermodel.Sheet getDelegate() {
        return book.getDelegate().getSheetAt(sheetIndex);
    }

    @Override
    public PoiBook getBook() {
        return book;
    }

    @Override
    public PoiRow getRow(int rowIndex) {
        if (getDelegate().getRow(rowIndex) == null)
            getDelegate().createRow(rowIndex);

        return new PoiRow.Builder()
                .withSheet(this)
                .withRowIndex(rowIndex)
                .build();
    }

    @Override
    public String getSheetName() {
        return sheetName;
    }

    @Override
    public int getSheetIndex() {
        return sheetIndex;
    }

    @Override
    public List<Row> getRows() {
        return ImmutableList.copyOf(iterator());
    }

    @Override
    public Iterator<Row> iterator() {
        return Iterators.transform(
                getDelegate().rowIterator(),
                r -> getRow(r.getRowNum())
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("book", book)
                .add("sheetIndex", sheetIndex)
                .add("sheetName", sheetName)
                .add("type", getType().getTypeName())
                .add("kinds", getKinds().stream().map(CellsheetType::getTypeName).collect(Collectors.joining(",")))
                .toString();
    }

    public static class Builder implements Sheet.Builder<PoiSheet, Builder> {

        PoiBook book;
        String sheetName;
        int sheetIndex;

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder withBook(Book book) throws ClassCastException {
            checkArgument(book instanceof PoiBook, "Must be instance of %s", PoiBook.class.getCanonicalName());
            this.book = (PoiBook) book;
            return self();
        }

        @Override
        public Builder withSheetName(String sheetName) {
            this.sheetName = sheetName;
            return self();
        }

        @Override
        public Builder withSheetIndex(int sheetIndex) {
            this.sheetIndex = sheetIndex;
            return self();
        }

        @Override
        public PoiSheet build() {
            PoiSheet sheet = new PoiSheet();
            sheet.book = book;
            sheet.sheetName = sheetName;
            sheet.sheetIndex = sheetIndex;
            return sheet;
        }
    }

}

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

import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.labs.emc.cellsheet.Book;
import org.eclipse.epsilon.labs.emc.cellsheet.CellFormat;
import org.eclipse.epsilon.labs.emc.cellsheet.Sheet;
import org.eclipse.epsilon.labs.emc.cellsheet.Workspace;

import java.util.Iterator;
import java.util.List;

public class DummyBook implements Book {

    Workspace workspace;
    String bookName;

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    @Override
    public List<CellFormat> getCellFormats() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Sheet getSheet(int sheetIndex) {
        DummySheet sheet = new DummySheet();
        sheet.setBook(this);
        sheet.setSheetIndex(sheetIndex);
        return sheet;
    }

    @Override
    public Sheet getSheet(String sheetName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getBookName() {
        return bookName;
    }

    @Override
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String getModelUri() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setModelUri(String modelUri) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void load() throws EolModelLoadingException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Sheet> getSheets() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Sheet> iterator() {
        throw new UnsupportedOperationException();
    }
}

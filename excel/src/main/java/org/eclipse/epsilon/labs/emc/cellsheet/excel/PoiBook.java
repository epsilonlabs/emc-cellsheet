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
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.labs.emc.cellsheet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public class PoiBook implements Book, PoiDelegate<Workbook> {

    private static Logger logger = LoggerFactory.getLogger(PoiBook.class);

    private Workbook delegate;
    private FormulaParsingWorkbook delegateFpw;
    private WorkbookEvaluator delegateEvaluator;

    private Workspace workspace;
    private String modelUri;
    private String bookName;

    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public void setWorkspace(Workspace workspace) {
        this.workspace = workspace;
    }

    @Override
    public String getModelUri() {
        return modelUri;
    }

    @Override
    public void setModelUri(String modelUri) {
        this.modelUri = modelUri;
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
    public PoiSheet getSheet(int sheetIndex) {
        if (sheetIndex < 0 || sheetIndex >= delegate.getNumberOfSheets())
            return null;

        org.apache.poi.ss.usermodel.Sheet raw = delegate.getSheetAt(sheetIndex);
        return new PoiSheet.Builder()
                .withBook(this)
                .withSheetName(raw.getSheetName())
                .withSheetIndex(sheetIndex)
                .build();
    }

    @Override
    public PoiSheet getSheet(String sheetName) {
        org.apache.poi.ss.usermodel.Sheet raw = delegate.getSheet(sheetName);
        return raw == null ? null : new PoiSheet.Builder()
                .withBook(this)
                .withSheetName(sheetName)
                .withSheetIndex(delegate.getSheetIndex(raw))
                .build();
    }

    @Override
    public List<Sheet> getSheets() {
        return ImmutableList.copyOf(iterator());
    }

    @Nonnull
    @Override
    public Iterator<Sheet> iterator() {
        return Iterators.transform(
                getDelegate().sheetIterator(),
                s -> getSheet(s.getSheetName())
        );
    }

    @Override
    public List<CellFormat> getCellFormats() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void load() throws EolModelLoadingException {
        logger.debug("Loading PoiBook with [modelUri: {}, bookName: {}]", modelUri, bookName);

        final File file = Strings.isNullOrEmpty(modelUri) ? null : new File(modelUri);
        if (Strings.isNullOrEmpty(bookName)) {
            bookName = file == null
                    ? "PoiBook" + Integer.toHexString(System.identityHashCode(this)) + ".xlsx"
                    : file.getName();
            logger.debug("Derived bookName: [{}]", bookName);
        }

        try {
            if (file == null) {
                logger.debug("No modelUri set, creating in-memory XSSF-based book");
                delegate = WorkbookFactory.create(true);
            } else {
                delegate = WorkbookFactory.create(file);
            }
        } catch (Exception e) {
            throw new EolModelLoadingException(e, workspace);
        }

        delegate.setMissingCellPolicy(Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        logger.debug("...Loading Finished");
    }

    @Override
    public void dispose() {
        if (delegate != null) {
            try {
                delegate.close();
                delegate = null;
                delegateFpw = null;
                delegateEvaluator = null;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    @Override
    public Workbook getDelegate() {
        return delegate;
    }

    protected void setDelegate(Workbook delegate) {
        this.delegate = delegate;
    }

    public FormulaParsingWorkbook getFpw() {
        checkArgument(delegate != null, "Delegate not initialised");
        if (delegateFpw == null) {
            if (delegate instanceof HSSFWorkbook)
                delegateFpw = HSSFEvaluationWorkbook.create((HSSFWorkbook) delegate);
            if (delegate instanceof XSSFWorkbook)
                delegateFpw = XSSFEvaluationWorkbook.create((XSSFWorkbook) delegate);
            if (delegate instanceof SXSSFWorkbook)
                delegateFpw = SXSSFEvaluationWorkbook.create((SXSSFWorkbook) delegate);
        }
        return delegateFpw;
    }

    public WorkbookEvaluator getInternalEvaluator() {
        checkArgument(delegate != null, "Delegate not initialised");
        if (delegateEvaluator == null) {
            delegateEvaluator = ((BaseFormulaEvaluator) delegate
                    .getCreationHelper()
                    .createFormulaEvaluator())
                    ._getWorkbookEvaluator();
        }
        return delegateEvaluator;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("workspace", workspace)
                .add("bookName", bookName)
                .add("modelUri", modelUri)
                .add("delegate", delegate)
                .add("type", getType().getTypeName())
                .add("kinds", getKinds().stream().map(CellsheetType::getTypeName).collect(Collectors.joining(",")))
                .toString();
    }

    /**
     * Builder for POI Based Books
     */
    public static class Builder implements Book.Builder<PoiBook, Builder> {

        Workspace workspace;
        String bookName;
        String modelUri;

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder withWorkspace(Workspace workspace) {
            this.workspace = workspace;
            return self();
        }

        @Override
        public Builder withBookName(String bookName) {
            this.bookName = bookName;
            return self();
        }

        @Override
        public Builder withModelUri(String modelUri) {
            this.modelUri = modelUri;
            return self();
        }

        @Override
        public PoiBook build() {
            PoiBook poiBook = new PoiBook();
            poiBook.setWorkspace(workspace);
            poiBook.modelUri = modelUri;
            poiBook.bookName = bookName;
            return poiBook;
        }
    }

}

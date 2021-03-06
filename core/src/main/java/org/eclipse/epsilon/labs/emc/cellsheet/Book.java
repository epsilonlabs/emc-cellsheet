/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;

import com.google.common.net.UrlEscapers;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public interface Book extends HasA1<Sheet> {

    Workspace getWorkspace();

    void setWorkspace(Workspace workspace);

    List<CellFormat> getCellFormats();

    Sheet getSheet(int sheetIndex);

    Sheet getSheet(String sheetName);

    String getBookName();

    void setBookName(String bookName);

    String getModelUri();

    void setModelUri(String modelUri);

    void load() throws EolModelLoadingException;

    void dispose();

    List<Sheet> getSheets();

    @Override
    default String getQualifiedA1() {
        if (getBookName() == null) return HasA1.super.getQualifiedA1();
        return "[" + getBookName() + "]";
    }

    @Override
    default String getRelativeA1() {
        return getQualifiedA1();
    }

    @Nonnull
    @Override
    default String getId() {
        return getWorkspace() == null
                ? HasA1.super.getId()
                : (getWorkspace().getId() + "/" + UrlEscapers.urlPathSegmentEscaper().escape(getBookName()));
    }

    @Nonnull
    @Override
    default CellsheetType getType() {
        return CellsheetType.BOOK;
    }

    @Nonnull
    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.HAS_A1, CellsheetType.CELLSHEET_ELEMENT);
    }

    interface Builder<T extends Book, B extends Builder<T, B>> extends CellsheetBuilder {

        B self();

        B withWorkspace(Workspace workspace);

        B withBookName(String bookName);

        B withModelUri(String modelUri);

        T build();
    }
}
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

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public interface Sheet extends HasA1<Row> {

    Book getBook();

    Row getRow(int rowIndex);

    String getSheetName();

    int getSheetIndex();

    List<Row> getRows();

    @Override
    default String getQualifiedA1() {
        if (getBook() == null || getSheetName() == null)
            return HasA1.super.getQualifiedA1();
        return getBook().getQualifiedA1() + "'" + getSheetName() + "'";
    }

    @Override
    default String getRelativeA1() {
        if (getSheetName() == null) return HasA1.super.getRelativeA1();
        return "'" + getSheetName() + "'";
    }

    @Nonnull
    @Override
    default String getId() {
        return getBook() == null
                ? HasA1.super.getId()
                : (getBook().getId() + "/" + getSheetIndex());
    }

    @Nonnull
    @Override
    default CellsheetType getType() {
        return CellsheetType.SHEET;
    }

    @Nonnull
    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.HAS_A1, CellsheetType.CELLSHEET_ELEMENT);
    }

    interface Builder<T extends Sheet, B extends Builder<T, B>> extends CellsheetBuilder {

        B self();

        B withBook(Book book);

        B withSheetName(String sheetName);

        B withSheetIndex(int sheetIndex);

        T build();
    }
}
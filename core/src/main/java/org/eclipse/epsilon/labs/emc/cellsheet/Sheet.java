/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface Sheet extends HasA1 {

    Book getBook();

    Row getRow(int rowIndex);

    String getSheetName();

    int getSheetIndex();

    List<Row> getRows();

    @Nonnull
    @Override
    Iterator<Row> iterator();

    @Override
    default String getA1() {
        if (getBook() == null || getSheetName() == null)
            return HasA1.super.getA1();
        return getBook().getA1() + "'" + getSheetName() + "'";
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
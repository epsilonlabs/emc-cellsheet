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

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface Row extends HasA1 {

    default Book getBook() {
        return getSheet().getBook();
    }

    Sheet getSheet();

    Cell getCell(int colIndex);

    int getRowIndex();

    default int getA1RowIndex() {
        return getRowIndex() + 1;
    }

    List<Cell> getCells();

    @Override
    Iterator<Cell> iterator();

    @Override
    default String getA1() {
        if (getSheet() == null || getRowIndex() < 0) return HasA1.super.getA1();
        return getSheet().getA1() + "!$A" + getA1RowIndex();
    }

    @Override
    default String getId() {
        if (getSheet() == null || getRowIndex() < 0) return HasA1.super.getId();
        return getSheet().getId() + "/" + getRowIndex();
    }

    @Override
    default CellsheetType getType() {
        return CellsheetType.ROW;
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.HAS_A1, CellsheetType.HAS_ID);
    }

    interface Builder<T extends Row, B extends Builder<T, B>> extends CellsheetBuilder {

        B self();

        B withSheet(Sheet sheet);

        B withRowIndex(int rowIndex);

        T build();
    }
}
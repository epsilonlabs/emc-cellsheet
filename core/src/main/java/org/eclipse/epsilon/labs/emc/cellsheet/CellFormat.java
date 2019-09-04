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
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

public interface CellFormat extends CellsheetElement {

    Book getBook();

    String getValue();

    @Nonnull
    @Override
    default Iterator<CellsheetElement> iterator() {
        return Collections.emptyIterator();
    }

    @Nonnull
    @Override
    default String getId() {
        return getBook().getId() + "/cellFormats/" + hashCode(); // TODO: Should not rely on this
    }

    @Nonnull
    @Override
    default CellsheetType getType() {
        return CellsheetType.CELL_FORMAT;
    }

    @Nonnull
    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.CELL_FORMAT, CellsheetType.CELLSHEET_ELEMENT);
    }
}
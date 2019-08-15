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
import java.util.Set;

public interface CellFormat extends HasId {

    Book getBook();

    String getValue();

    @Override
    Iterator<Void> iterator();

    @Override
    default String getId() {
        return getBook().getId() + "/cellFormats/" + hashCode(); // TODO: Should not rely on this
    }

    @Override
    default CellsheetType getType() {
        return CellsheetType.CELL_FORMAT;
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.CELL_FORMAT, CellsheetType.HAS_ID);
    }
}
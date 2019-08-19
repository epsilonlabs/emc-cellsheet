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

/**
 * Model Type representing a Blank Cell
 * <p>
 * A Blank Cell is defined as a cell with no value but may have formatting
 * associated with it. Value is defined as any non-empty string or number.
 * Therefore whitespace is a non-empty value.
 * </p>
 */
public interface BlankCell extends Cell<Void> {

    @Override
    default Void getValue() {
        return null;
    }

    @Nonnull
    @Override
    default CellsheetType getType() {
        return CellsheetType.BLANK_CELL;
    }
}
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
 * Model Type representing a Cell with a Boolean value
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public interface BooleanCell extends Cell<Boolean> {

    @Nonnull
    @Override
    default CellsheetType getType() {
        return CellsheetType.BOOLEAN_CELL;
    }
}
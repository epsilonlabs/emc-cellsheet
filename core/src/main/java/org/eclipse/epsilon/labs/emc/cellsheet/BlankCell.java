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

public interface BlankCell extends Cell<Void> {

    @Override
    default Void getValue() {
        return null;
    }

    @Override
    default CellsheetType getType() {
        return CellsheetType.BLANK_CELL;
    }
}
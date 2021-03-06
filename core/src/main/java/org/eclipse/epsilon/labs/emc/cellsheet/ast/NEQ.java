/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;

import javax.annotation.Nonnull;

public class NEQ extends InfixOperator {

    public static final String TOKEN = "<>";

    public NEQ() {
        super(TOKEN);
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.NEQ;
    }
}

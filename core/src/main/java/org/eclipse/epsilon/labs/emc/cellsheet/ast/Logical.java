/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;

import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;

public class Logical extends Operand {

    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";

    public Logical(boolean value) {
        super(Boolean.toString(value).toUpperCase());
    }

    public Logical(String token) {
        super(checkString(token));
    }

    private static String checkString(String token) {
        checkArgument(token.equalsIgnoreCase(TRUE) || token.equalsIgnoreCase(FALSE),
                "Token must be TRUE or FALSE, given %s",
                token);
        return token.toUpperCase();
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.LOGICAL;
    }
}

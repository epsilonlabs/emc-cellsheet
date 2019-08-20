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
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

import javax.annotation.Nonnull;

/**
 * AST Node representing a text operand in a spreadsheet function.
 * <p>
 * No check is performed to determine if the token value given is wrapped in
 * double quotes ("). when used as an argument in a function, the string returned
 * by {@link #getTokenValue()} needs to be manually wrapped´
 * </p>
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public class Text extends Operand {

    /**
     * Constructor that accepts a string.
     *
     * @param token token as a string
     */
    public Text(String token) {
        super(token);
    }

    /**
     * Constructor that accepts a pre-built token
     *
     * @param token a pre-built token
     */
    public Text(Token token) {
        super(token);
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.TEXT;
    }
}
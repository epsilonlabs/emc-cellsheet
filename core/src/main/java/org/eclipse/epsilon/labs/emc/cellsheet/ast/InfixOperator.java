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
import java.util.EnumSet;
import java.util.Set;

public abstract class InfixOperator extends AbstractAst {

    protected InfixOperator() {
        super();
    }

    protected InfixOperator(Token token) {
        super(token);
    }

    protected InfixOperator(String token) {
        super(token);
    }

    @Nonnull
    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.INFIX_OPERATOR, CellsheetType.AST, CellsheetType.CELLSHEET_ELEMENT);
    }
}
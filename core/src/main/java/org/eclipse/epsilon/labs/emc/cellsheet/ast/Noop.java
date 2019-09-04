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

import org.eclipse.epsilon.labs.emc.cellsheet.AstPayload;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Set;

public class Noop extends AstPayload {

    public Noop(String token) {
        super(token);
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.NOOP;
    }

    @Nonnull
    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.AST_PAYLOAD, CellsheetType.CELLSHEET_ELEMENT);
    }

    @Override
    public CellsheetType getSuperType() {
        return CellsheetType.NOOP;
    }

}

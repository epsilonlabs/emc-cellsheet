/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.test;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.AbstractAst;

import java.util.EnumSet;
import java.util.Set;

public class DummyAst extends AbstractAst {

    @Override
    public Ast childAt(int position) {
        Ast child = position < children.size() ? child = children.get(position) : null;
        if (child == null) {
            child = new DummyAst();
            child.setParent(this);
            child.setPosition(position);
        }
        return child;
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.UNKNOWN;
    }

    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.UNKNOWN, CellsheetType.AST, CellsheetType.HAS_ID);
    }
}

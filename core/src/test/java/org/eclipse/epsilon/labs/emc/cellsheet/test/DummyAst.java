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

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Set;

public class DummyAst extends Ast {

    public DummyAst() {
        this.evaluator = new DummyAstEvaluator();
    }

    public DummyAst(String token) {
        super(token);
    }

    @Override
    public Ast childAt(int position) {
        while (children.size() < position + 1) {
            DummyAst ast = new DummyAst();
            ast.setPosition(position);
            ast.setParent(this);
            addChild(ast);
        }
        return children.get(position);
    }

    @Override
    public String getFormula() {
        throw new UnsupportedOperationException();
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.UNKNOWN;
    }

    @Nonnull
    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.UNKNOWN, CellsheetType.AST, CellsheetType.CELLSHEET_ELEMENT);
    }
}

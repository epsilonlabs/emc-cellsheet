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
import org.eclipse.epsilon.labs.emc.cellsheet.AstPayload;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Noop;

public class DummyAst extends Ast {

    private static final AstPayload DEFAULT_PAYLOAD = new Noop("dummy");

    public DummyAst() {
        super(DEFAULT_PAYLOAD);
    }

    @Override
    public Ast childAt(int position) {
        while (getChildren().size() < position + 1) {
            DummyAst ast = new DummyAst();
            ast.setPosition(position);
            ast.setParent(this);
            addChild(ast);
        }
        return super.childAt(position);
    }

    @Override
    public String getFormula() {
        throw new UnsupportedOperationException();
    }

}

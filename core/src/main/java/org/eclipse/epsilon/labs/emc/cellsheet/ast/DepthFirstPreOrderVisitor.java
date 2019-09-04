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

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;

import java.util.LinkedList;
import java.util.List;

public class DepthFirstPreOrderVisitor implements Ast.Visitor<Void> {

    private final List<Ast.Visitor> visitors = new LinkedList<>();

    private DepthFirstPreOrderVisitor() {
    }

    @Override
    public Void visit(Ast ast) throws Exception {
        for (Ast.Visitor v : visitors) {
            ast.accept(v);
        }
        for (Ast child : ast.getChildren()) {
            child.accept(this);
        }
        return null;
    }

    public static class DepthFirstPreOrderVisitorBuilder {
        List<Ast.Visitor> visitors = new LinkedList<>();

        public DepthFirstPreOrderVisitorBuilder with(Ast.Visitor visitor) {
            visitors.add(visitor);
            return this;
        }

        public DepthFirstPreOrderVisitor build() {
            DepthFirstPreOrderVisitor v = new DepthFirstPreOrderVisitor();
            v.visitors.addAll(this.visitors);
            return v;
        }
    }
}

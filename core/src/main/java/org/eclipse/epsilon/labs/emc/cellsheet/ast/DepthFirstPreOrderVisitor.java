package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;

import java.util.LinkedList;
import java.util.List;

public class DepthFirstPreOrderVisitor implements Ast.Visitor {

    private final List<Ast.Visitor> visitors = new LinkedList<>();

    private DepthFirstPreOrderVisitor() {}

    @Override
    public void visit(Ast ast) throws Exception {
        for (Ast.Visitor v : visitors) {
            ast.accept(v);
        }
        for (Ast child : ast.getChildren()) {
            child.accept(this);
        }
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

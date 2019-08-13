package org.eclipse.epsilon.labs.emc.cellsheet.test;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.AbstractAst;

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
}

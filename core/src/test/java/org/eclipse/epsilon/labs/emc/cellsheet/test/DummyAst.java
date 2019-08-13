package org.eclipse.epsilon.labs.emc.cellsheet.test;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.AbstractAst;

public class DummyAst extends AbstractAst {

    @Override
    public Ast childAt(int position) {
        DummyAst ast = new DummyAst();
        ast.setParent(this);
        ast.setPosition(position);
        return ast;
    }
}

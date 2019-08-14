package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;

import java.util.Iterator;

public class ToFormulaAstVisitor implements Ast.Visitor {

    private final StringBuilder sb = new StringBuilder();

    @Override
    public void visit(Ast ast) throws Exception {
        if (ast.getChildren().isEmpty()) {
            add(ast);
            return;
        }

        switch (ast.getType()) {
            case INFIX_OPERATOR:
                open().recurse(ast, 0).add(ast).recurse(ast, 1).close();
                break;

            case PREFIX_OPERATOR:
                add(ast);
                if (ast.childAt(0).getChildren().isEmpty())
                    recurse(ast, 0);
                else
                    open().recurse(ast, 0).close();
                break;

            case POSTFIX_OPERATOR:
                if (ast.childAt(0).getChildren().isEmpty())
                    recurse(ast, 0);
                else
                    open().recurse(ast, 0).close();
                add(ast);
                break;

            default:
                add(ast).open().recurse(ast).close();
                break;
        }
    }

    ToFormulaAstVisitor open() {
        sb.append('(');
        return this;
    }

    ToFormulaAstVisitor close() {
        sb.append(')');
        return this;
    }

    ToFormulaAstVisitor add(Ast ast) {
        sb.append(ast.getToken().getValue());
        return this;
    }

    ToFormulaAstVisitor recurse(Ast root, int childPosition) throws Exception {
        root.childAt(childPosition).accept(this);
        return this;
    }

    ToFormulaAstVisitor recurse(Ast root) throws Exception {
        Iterator<Ast> it = root.getChildren().iterator();
        while (it.hasNext()) {
            it.next().accept(this);
            if (it.hasNext()) sb.append(',');
        }
        return this;
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}

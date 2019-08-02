package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

import java.util.EnumSet;
import java.util.Set;

public abstract class PostfixOperator extends AbstractAst {

    public PostfixOperator() {
        super();
    }

    public PostfixOperator(Token token) {
        super(token);
    }

    public PostfixOperator(String token) {
        super(token);
    }

    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.POSTFIX_OPERATOR, CellsheetType.AST, CellsheetType.HAS_ID);
    }
}
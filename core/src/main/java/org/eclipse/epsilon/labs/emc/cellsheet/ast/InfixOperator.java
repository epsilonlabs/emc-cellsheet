package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

import java.util.EnumSet;
import java.util.Set;

public abstract class InfixOperator extends AbstractAst {

    protected InfixOperator() {
        super();
    }

    protected InfixOperator(Token token) {
        super(token);
    }

    protected InfixOperator(String token) {
        super(token);
    }

    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.INFIX_OPERATOR, CellsheetType.AST, CellsheetType.HAS_ID);
    }
}
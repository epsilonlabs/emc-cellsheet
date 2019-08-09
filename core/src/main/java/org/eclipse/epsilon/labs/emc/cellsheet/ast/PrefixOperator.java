package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

import java.util.EnumSet;
import java.util.Set;

public abstract class PrefixOperator extends AbstractAst {

    protected PrefixOperator() {
        super();
    }

    protected PrefixOperator(Token token) {
        super(token);
    }

    protected PrefixOperator(String token) {
        super(token);
    }

    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.PREFIX_OPERATOR, CellsheetType.AST, CellsheetType.HAS_ID);
    }
}
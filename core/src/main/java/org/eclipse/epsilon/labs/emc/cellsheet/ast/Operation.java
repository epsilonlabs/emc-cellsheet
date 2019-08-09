package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

import java.util.EnumSet;
import java.util.Set;

public abstract class Operation extends AbstractAst {

    protected Operation() {
        super();
    }

    protected Operation(Token token) {
        super(token);
    }

    protected Operation(String token) {
        super(token);
    }

    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.OPERATION, CellsheetType.AST, CellsheetType.HAS_ID);
    }
}
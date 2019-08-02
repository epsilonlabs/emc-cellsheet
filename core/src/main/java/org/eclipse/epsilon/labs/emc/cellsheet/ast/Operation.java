package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

import java.util.EnumSet;
import java.util.Set;

public abstract class Operation extends AbstractAst {

    public Operation() {
        super();
    }

    public Operation(Token token) {
        super(token);
    }

    public Operation(String token) {
        super(token);
    }

    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.OPERATION, CellsheetType.AST, CellsheetType.HAS_ID);
    }
}
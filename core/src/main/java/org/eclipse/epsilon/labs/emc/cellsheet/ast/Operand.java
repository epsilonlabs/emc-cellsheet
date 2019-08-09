package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

import java.util.EnumSet;
import java.util.Set;

public abstract class Operand extends AbstractAst {

    protected Operand() {
        super();
    }

    protected Operand(Token token) {
        super(token);
    }

    protected Operand(String token) {
        super(token);
    }

    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.OPERAND, CellsheetType.AST, CellsheetType.HAS_ID);
    }
}
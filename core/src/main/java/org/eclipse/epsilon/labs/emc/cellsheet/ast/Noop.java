package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

public class Noop extends AbstractAst {

    public Noop() {
        super();
    }

    public Noop(Token token) {
        super(token);
    }

    public Noop(String token) {
        super(token);
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.NOOP;
    }
}
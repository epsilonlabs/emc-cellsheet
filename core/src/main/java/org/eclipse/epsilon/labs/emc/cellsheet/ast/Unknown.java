package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

public class Unknown extends AbstractAst {

    public Unknown() {
        super();
    }

    public Unknown(Token token) {
        super(token);
    }

    public Unknown(String token) {
        super(token);
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.UNKNOWN;
    }
}
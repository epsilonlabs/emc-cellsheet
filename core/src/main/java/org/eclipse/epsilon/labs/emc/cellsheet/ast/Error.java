package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

public class Error extends Operand {

    public Error(Token token) {
        super(token);
    }

    public Error(String token) {
        super(token);
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.ERROR;
    }
}
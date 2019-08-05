package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

public class Function extends Operation {

    public Function(Token token) {
        super(token);
    }

    public Function(String token) {
        super(token);
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.FUNCTION;
    }
}
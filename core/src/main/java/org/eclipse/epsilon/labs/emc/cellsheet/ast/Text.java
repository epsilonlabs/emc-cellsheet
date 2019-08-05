package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

public class Text extends Operand {

    public Text(String token) {
        super(token);
    }

    public Text(Token token) {
        super(token);
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.TEXT;
    }
}
package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

public class Range extends Operand {

    public Range(Token token) {
        super(token);
    }

    public Range(String token) {
        super(token);
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.RANGE;
    }
}
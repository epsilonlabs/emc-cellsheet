package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;

public class Ref extends Operand {

    public Ref(Token token) {
        super(token);
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.REF;
    }
}
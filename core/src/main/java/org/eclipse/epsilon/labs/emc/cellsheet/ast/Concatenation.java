package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;
import org.eclipse.epsilon.labs.emc.cellsheet.TokenFactory;

public class Concatenation extends InfixOperator {

    public static final Token TOKEN = TokenFactory.getInstance().getToken("&");

    public Concatenation() {
        super(TOKEN);
    }

    @Override
    public void setToken(Token token) {
        throw new UnsupportedOperationException();
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.CONCATENATION;
    }
}
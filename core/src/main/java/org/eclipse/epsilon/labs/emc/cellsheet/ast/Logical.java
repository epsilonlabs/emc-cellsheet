package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;
import org.eclipse.epsilon.labs.emc.cellsheet.TokenFactory;

import static com.google.common.base.Preconditions.checkArgument;

public class Logical extends Operand {

    public static final Token TRUE = TokenFactory.getInstance().getToken("TRUE");
    public static final Token FALSE = TokenFactory.getInstance().getToken("FALSE");

    public Logical(boolean value) {
        super(value ? TRUE : FALSE);
    }

    public Logical(Token token) {
        checkArgument(token.equals(TRUE)||token.equals(FALSE));
        this.token = token;
    }

    @Override
    public void setToken(Token token) {
        checkArgument(token.equals(TRUE)||token.equals(FALSE));
        super.setToken(token);
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.LOGICAL;
    }
}
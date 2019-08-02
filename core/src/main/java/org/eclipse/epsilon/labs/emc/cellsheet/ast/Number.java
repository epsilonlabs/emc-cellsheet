package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import com.google.common.primitives.Doubles;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;
import org.eclipse.epsilon.labs.emc.cellsheet.TokenFactory;

import static com.google.common.base.Preconditions.checkArgument;

public class Number extends Operand {

    public Number(String token) {
        checkArgument(Doubles.tryParse(token) != null);
        this.token = TokenFactory.getInstance().getToken(token);
    }

    public Number(double token) {
        this.token = TokenFactory.getInstance().getToken(String.valueOf(token));
    }

    public Number(Token token) {
        checkArgument(Doubles.tryParse(token.getValue()) != null);
        this.token = token;
    }

    @Override
    public void setToken(Token token) {
        checkArgument(Doubles.tryParse(token.getValue()) != null);
        super.setToken(token);
    }

    @Override
    public CellsheetType getType() {
        return CellsheetType.NUMBER;
    }
}
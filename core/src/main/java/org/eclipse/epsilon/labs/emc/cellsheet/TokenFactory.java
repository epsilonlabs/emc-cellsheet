package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.HashMap;
import java.util.Map;

public class TokenFactory {

    private static TokenFactory instance = new TokenFactory();
    private Map<String, Token> tokens = new HashMap<>();

    public static TokenFactory getInstance() {
        return instance;
    }

    public Token getToken(String value) {
        return tokens.computeIfAbsent(value, k -> new Token(value));
    }

}

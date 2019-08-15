/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
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

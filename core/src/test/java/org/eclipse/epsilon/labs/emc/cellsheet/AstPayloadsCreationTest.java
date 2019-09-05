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

import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(Parameterized.class)
public class AstPayloadsCreationTest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                        {CellsheetType.NUMBER, Number.class, "123.456", false},
                        {CellsheetType.TEXT, Text.class, "Hello World", false},
                        {CellsheetType.LOGICAL, Logical.class, "FALSE", false},
                        {CellsheetType.RANGE, Range.class, "A1:A5", false},
                        {CellsheetType.REF, Ref.class, "A1", false},
                        {CellsheetType.ADDITION, Addition.class, Addition.TOKEN, true},
                        {CellsheetType.CONCATENATION, Concatenation.class, Concatenation.TOKEN, true},
                        {CellsheetType.DIVISION, Division.class, Division.TOKEN, true},
                        {CellsheetType.EQ, EQ.class, EQ.TOKEN, true},
                        {CellsheetType.GTE, GTE.class, GTE.TOKEN, true},
                        {CellsheetType.GT, GT.class, GT.TOKEN, true},
                        {CellsheetType.LTE, LTE.class, LTE.TOKEN, true},
                        {CellsheetType.LT, LT.class, LT.TOKEN, true},
                        {CellsheetType.MULTIPLICATION, Multiplication.class, Multiplication.TOKEN, true},
                        {CellsheetType.NEQ, NEQ.class, NEQ.TOKEN, true},
                        {CellsheetType.PERCENT, Percent.class, Percent.TOKEN, true},
                        {CellsheetType.EXPONENTIATION, Exponentiation.class, Exponentiation.TOKEN, true},
                        {CellsheetType.SUBTRACTION, Subtraction.class, Subtraction.TOKEN, true},
                        {CellsheetType.NEGATION, Negation.class, Negation.TOKEN, true},
                        {CellsheetType.PLUS, Plus.class, Plus.TOKEN, true},
                        {CellsheetType.UNION, Union.class, Union.TOKEN, true},
                        {CellsheetType.INTERSECTION, Intersection.class, Intersection.TOKEN, true},
                        {CellsheetType.FUNCTION, Function.class, "VLOOKUP", false}
                }
        );
    }

    private CellsheetType type;
    private Class payloadClazz;
    private String token;
    private boolean ignoreToken;

    public AstPayloadsCreationTest(CellsheetType type, Class payloadClazz, String token, boolean ignoreToken) {
        this.type = type;
        this.payloadClazz = payloadClazz;
        this.token = token;
        this.ignoreToken = ignoreToken;
    }

    @Test
    public void of_should_create_correct_payload_given_type_and_token() {
        AstPayload payload = ignoreToken ? AstPayloads.fromToken(type) : AstPayloads.fromToken(type, token);
        assertThat(payload).isNotNull().isInstanceOf(payloadClazz);
        assertThat(payload.getToken()).isEqualTo(token);
    }
}

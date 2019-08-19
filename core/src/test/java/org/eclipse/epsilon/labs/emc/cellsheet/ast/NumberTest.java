/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NumberTest {

    @Test
    public void constructor_should_succeed_given_numeric_string() {
        Ast ast = new Number("123.456");
        assertThat(ast.getTokenValue()).isEqualTo("123.456");
    }

    @Test
    public void constructor_should_succeed_given_double() {
        Ast ast = new Number(123.456);
        assertThat(ast.getTokenValue()).isEqualTo("123.456");
    }

    @Test
    public void constructor_should_succeed_given_token_with_numeric_value() {
        Token token = new Token("123.456");
        Ast ast = new Number(token);
        assertThat(ast.getTokenValue()).isEqualTo("123.456");
    }

    @Test
    public void constructor_should_fail_given_non_numeric_string() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Number("Not a number"));
    }

    @Test
    public void getType_should_return_NUMBER() {
        Ast ast = new Number(0);
        assertThat(ast.getType()).isEqualTo(CellsheetType.NUMBER);
    }

    @Test
    public void getKind_should_return_correct_types() {
        Ast ast = new Number(0);
        assertThat(ast.getKinds()).containsExactlyInAnyOrder(
                CellsheetType.NUMBER,
                CellsheetType.OPERAND,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.AST);
    }
}

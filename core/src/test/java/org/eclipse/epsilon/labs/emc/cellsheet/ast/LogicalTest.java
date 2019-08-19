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

public class LogicalTest {

    @Test
    public void constructor_should_succeed_given_true() {
        Ast ast = new Logical(true);
        assertThat(ast.getTokenValue()).isEqualTo("TRUE");
    }

    @Test
    public void constructor_should_succeed_given_FALSE() {
        Ast ast = new Logical(false);
        assertThat(ast.getTokenValue()).isEqualTo("FALSE");
    }

    @Test
    public void constructor_should_throw_iaexception_when_given_non_bool_token() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Logical(new Token("NOT A BOOL")));
    }

    @Test
    public void getType_should_return_LOGICAL() {
        Ast ast = new Logical(true);
        assertThat(ast.getType()).isEqualTo(CellsheetType.LOGICAL);
    }

    @Test
    public void getKind_should_return_correct_types() {
        Ast ast = new Logical(true);
        assertThat(ast.getKinds()).containsExactlyInAnyOrder(
                CellsheetType.LOGICAL,
                CellsheetType.OPERAND,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.AST);
    }
}

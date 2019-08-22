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
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TextTest {

    @Test
    public void constructor_should_succeed_given_string() {
        Ast ast = new Text("Hello World");
        assertThat(ast.getTokenValue()).isEqualTo("Hello World");
    }

    @Test
    public void constructor_should_succeed_given_token() {
        Ast ast = new Text("Hello World");
        assertThat(ast.getTokenValue()).isEqualTo("Hello World");
    }

    @Test
    public void getType_should_return_TEXT() {
        Ast ast = new Text("Hello World");
        assertThat(ast.getType()).isEqualTo(CellsheetType.TEXT);
    }

    @Test
    public void getKind_should_return_correct_types() {
        Ast ast = new Text("Hello World");
        assertThat(ast.getKinds()).containsExactlyInAnyOrder(
                CellsheetType.TEXT,
                CellsheetType.OPERAND,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.AST);
    }
}

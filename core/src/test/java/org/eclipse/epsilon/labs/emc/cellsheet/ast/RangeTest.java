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

public class RangeTest {

    @Test
    public void constructor_should_succeed_given_string() {
        Ast ast = new Range("A1:B5");
        assertThat(ast.getTokenValue()).isEqualTo("A1:B5");
    }

    @Test
    public void constructor_should_succeed_given_token() {
        Ast ast = new Range(new Token("A1:B5"));
        assertThat(ast.getTokenValue()).isEqualTo("A1:B5");
    }

    @Test
    public void getType_should_return_RANGE() {
        Ast ast = new Range("A1:B5");
        assertThat(ast.getType()).isEqualTo(CellsheetType.RANGE);
    }

    @Test
    public void getKind_should_return_correct_types() {
        Ast ast = new Range("A1:B5");
        assertThat(ast.getKinds()).containsExactlyInAnyOrder(
                CellsheetType.RANGE,
                CellsheetType.OPERAND,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.AST);
    }
}

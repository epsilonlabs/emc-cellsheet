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
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class PercentTest {

    private Ast ast = new Percent();

    @Test
    public void getToken_should_return_correct_token() {
        assertThat(ast.getTokenValue()).isEqualTo("%");
    }

    @Test
    public void getType_should_return_PERCENT() {
        assertThat(ast.getType()).isEqualTo(CellsheetType.PERCENT);
    }

    @Test
    public void getKind_should_return_correct_types() {
        assertThat(ast.getKinds()).containsExactlyInAnyOrder(
                CellsheetType.PERCENT,
                CellsheetType.POSTFIX_OPERATOR,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.AST);
    }

    @Test
    public void setToken() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> ast.setToken(""));
    }
}

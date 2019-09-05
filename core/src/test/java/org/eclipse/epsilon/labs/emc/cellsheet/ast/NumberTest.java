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

import org.eclipse.epsilon.labs.emc.cellsheet.AstPayload;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NumberTest {

    @Test
    public void constructor_should_succeed_given_numeric_string() {
        AstPayload payload = new Number("123.456");
        assertThat(payload.getToken()).isEqualTo("123.456");
    }

    @Test
    public void constructor_should_succeed_given_double() {
        AstPayload payload = new Number(123.456);
        assertThat(payload.getToken()).isEqualTo("123.456");
    }

    @Test
    public void constructor_should_fail_given_non_numeric_string() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Number("Not a fromToken"));
    }

    @Test
    public void getType_should_return_NUMBER() {
        AstPayload payload = new Number(0);
        assertThat(payload.getType()).isEqualTo(CellsheetType.NUMBER);
    }

    @Test
    public void getKind_should_return_correct_types() {
        AstPayload payload = new Number(0);
        assertThat(payload.getKinds()).containsExactlyInAnyOrder(
                CellsheetType.NUMBER,
                CellsheetType.OPERAND,
                CellsheetType.AST_PAYLOAD,
                CellsheetType.CELLSHEET_ELEMENT);
    }
}

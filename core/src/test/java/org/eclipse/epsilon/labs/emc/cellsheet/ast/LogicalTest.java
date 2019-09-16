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
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType.*;

public class LogicalTest {

    @Test
    public void constructor_should_succeed_given_true() {
        AstPayload payload = new Logical(true);
        assertThat(payload.getToken()).isEqualTo("TRUE");
    }

    @Test
    public void constructor_should_succeed_given_FALSE() {
        AstPayload payload = new Logical(false);
        assertThat(payload.getToken()).isEqualTo("FALSE");
    }

    @Test
    public void constructor_should_throw_iaexception_when_given_non_bool_token() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new Logical("NOT A BOOL"));
    }

    @Test
    public void getType_should_return_LOGICAL() {
        AstPayload payload = new Logical(true);
        assertThat(payload.getType()).isEqualTo(LOGICAL);
    }

    @Test
    public void getKind_should_return_correct_types() {
        AstPayload payload = new Logical(true);
        assertThat(payload.getKinds()).containsExactlyInAnyOrder(
                LOGICAL,
                OPERAND,
                AST_PAYLOAD,
                CELLSHEET_ELEMENT);
    }
}

/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.AstPayload;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ErrorTest {

    @Test
    public void constructor_should_succeed_when_given_string() {
        String token = "#REF";
        AstPayload payload = new Error(token);
        assertThat(payload.getToken()).isEqualTo(token);
    }

    @Test
    public void getType_should_return_ERROR() {
        AstPayload payload = new Error("");
        assertThat(payload.getType()).isEqualTo(CellsheetType.ERROR);
    }

    @Test
    public void getKind_should_return_correct_types() {
        AstPayload payload = new Error("");
        assertThat(payload.getKinds()).containsExactlyInAnyOrder(
                CellsheetType.ERROR,
                CellsheetType.OPERAND,
                CellsheetType.AST_PAYLOAD,
                CellsheetType.CELLSHEET_ELEMENT);
    }
}

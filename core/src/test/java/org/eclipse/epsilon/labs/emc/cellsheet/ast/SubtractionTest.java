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
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType.*;

public class SubtractionTest {

    private AstPayload payload = new Subtraction();

    @Test
    public void getToken_should_return_correct_token() {
        assertThat(payload.getToken()).isEqualTo("-");
    }

    @Test
    public void getType_should_return_SUBTRACTION() {
        assertThat(payload.getType()).isEqualTo(SUBTRACTION);
    }

    @Test
    public void getKind_should_return_correct_types() {
        assertThat(payload.getKinds()).containsExactlyInAnyOrder(
                SUBTRACTION,
                INFIX_OPERATOR,
                AST_PAYLOAD,
                CELLSHEET_ELEMENT);
    }

}

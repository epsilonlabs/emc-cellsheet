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

public class RefTest {

    @Test
    public void constructor_should_succeed_given_string() {
        AstPayload payload = new Ref("A1");
        assertThat(payload.getToken()).isEqualTo("A1");
    }

    @Test
    public void getType_should_return_REF() {
        AstPayload payload = new Ref("A1");
        assertThat(payload.getType()).isEqualTo(REF);
    }

    @Test
    public void getKind_should_return_correct_types() {
        AstPayload ast = new Ref("A1");
        assertThat(ast.getKinds()).containsExactlyInAnyOrder(
                REF,
                OPERAND,
                AST_PAYLOAD,
                CELLSHEET_ELEMENT);
    }
}

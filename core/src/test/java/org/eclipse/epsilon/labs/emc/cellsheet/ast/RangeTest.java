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
import static org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType.*;

public class RangeTest {

    @Test
    public void constructor_should_succeed_given_string() {
        AstPayload payload = new Range("A1:B5");
        assertThat(payload.getToken()).isEqualTo("A1:B5");
    }

    @Test
    public void getType_should_return_RANGE() {
        AstPayload payload = new Range("A1:B5");
        assertThat(payload.getType()).isEqualTo(RANGE);
    }

    @Test
    public void getKind_should_return_correct_types() {
        AstPayload payload = new Range("A1:B5");
        assertThat(payload.getKinds()).containsExactlyInAnyOrder(
                RANGE,
                OPERAND,
                AST_PAYLOAD,
                CELLSHEET_ELEMENT);
    }
}

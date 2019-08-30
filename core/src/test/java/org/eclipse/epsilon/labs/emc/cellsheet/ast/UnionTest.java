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

public class UnionTest {

    private AstPayload pay = new Union();

    @Test
    public void getToken_should_return_correct_token() {
        assertThat(pay.getToken()).isEqualTo(",");
    }

    @Test
    public void getType_should_return_UNION() {
        assertThat(pay.getType()).isEqualTo(CellsheetType.UNION);
    }

    @Test
    public void getKind_should_return_correct_types() {
        assertThat(pay.getKinds()).containsExactlyInAnyOrder(
                CellsheetType.UNION,
                CellsheetType.INFIX_OPERATOR,
                CellsheetType.AST_PAYLOAD,
                CellsheetType.CELLSHEET_ELEMENT);
    }

}

/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AstPayloadsTest {

    @Test
    public void empty_should_return_the_empty_payload() {
        AstPayload empty = AstPayloads.empty();
        assertThat(empty.getType()).isEqualTo(CellsheetType.UNKNOWN);
        assertThat(empty.getToken()).isBlank();
    }

    @Test
    public void fromUuid_should_contain_previously_created() {
        AstPayload payload = AstPayloads.fromToken(CellsheetType.REF, "A1");
        String uuid = AstPayload.tokenToUUID("A1");
        AstPayload fromUuid = AstPayloads.fromUuid(CellsheetType.REF, uuid);
        assertThat(payload).isEqualTo(fromUuid);
    }
}

/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;

import org.junit.Test;

import javax.annotation.Nonnull;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AstPayloadTest {

    @Test
    public void constructor_should_throw_npe_when_given_null_token() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(
                () -> new AstPayload(null) {

                    @Nonnull
                    @Override
                    public CellsheetType getType() {
                        return null;
                    }

                    @Nonnull
                    @Override
                    public Set<CellsheetType> getKinds() {
                        return null;
                    }

                    @Override
                    public CellsheetType getSuperType() {
                        return null;
                    }
                }
        );
    }

    @Test
    public void tokenToUUID_should_return_same_uuid() {
        String token = "A1:A2";
        String uuidA = AstPayload.tokenToUUID(token);
        String uuidB = AstPayload.tokenToUUID(token);
        assertThat(uuidA).isEqualTo(uuidB);
    }

    @Test
    public void getId_should_return_correctly_formed_id() {
        AstPayload payload = AstPayloads.fromToken(CellsheetType.REF, "A1:A2");
        String uuid = payload.getUuid();
        assertThat(payload.getId()).isEqualTo("cellsheet-payload://ref/" + uuid);
    }
}

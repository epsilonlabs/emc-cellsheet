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

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ReferenceUtilTest {

    @Test
    public void convertColStringToIndex_should_return_0_when_given_A() {
        assertThat(ReferenceUtil.a1ToIndex("A")).isZero();
    }

    @Test
    public void convertColStringToIndex_should_return_25_when_given_Z() {
        assertThat(ReferenceUtil.a1ToIndex("Z")).isEqualTo(25);
    }

    @Test
    public void convertColStringToIndex_should_return_26_when_given_AA() {
        assertThat(ReferenceUtil.a1ToIndex("AA")).isEqualTo(26);
    }

    @Test
    public void convertColStringToIndex_should_return_702_when_given_AAA() {
        assertThat(ReferenceUtil.a1ToIndex("AAA")).isEqualTo(702);
    }

    @Test
    public void convertColStringToIndex_should_return_30_when_given_dollarAE() {
        assertThat(ReferenceUtil.a1ToIndex("$AE")).isEqualTo(30);
    }

    @Test
    public void convertColStringToIndex_should_return_throw_exception_when_given_bad_format() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> ReferenceUtil.a1ToIndex("A$E"));
    }

    @Test
    public void colIndexToString_should_return_A_when_given_0() {
        assertThat(ReferenceUtil.indexToA1(0)).isEqualTo("A");
    }

    @Test
    public void colIndexToString_should_return_Z_when_given_25() {
        assertThat(ReferenceUtil.indexToA1(25)).isEqualTo("Z");
    }

    @Test
    public void colIndexToString_should_return_AA_when_given_26() {
        assertThat(ReferenceUtil.indexToA1(26)).isEqualTo("AA");
    }

    @Test
    public void colIndexToString_should_return_AE_when_given_702() {
        assertThat(ReferenceUtil.indexToA1(702)).isEqualTo("AAA");
    }
}

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

import static org.assertj.core.api.Assertions.assertThat;

public class AstEvalTest {

    @Test
    public void constructor_should_succeed_when_given_string_only() {
        String value = "Some string to put in";
        AstEval eval = new AstEval(value);
        assertThat(eval.getText()).isEqualTo(value);
        assertThat(eval.getNumber()).isEqualTo(0.0);
        assertThat(eval.isText()).isTrue();
        assertThat(eval.isNumber()).isFalse();
        assertThat(eval.isError()).isFalse();
    }

    @Test
    public void constructor_should_succeed_when_given_string_and_error() {
        String value = "An error string";
        AstEval eval = new AstEval(value, true);
        assertThat(eval.getText()).isEqualTo(value);
        assertThat(eval.getNumber()).isEqualTo(0.0);
        assertThat(eval.isText()).isFalse();
        assertThat(eval.isNumber()).isFalse();
        assertThat(eval.isError()).isTrue();
    }

    @Test
    public void constructor_should_succeed_when_given_double() {
        double value = 123.456;
        AstEval eval = new AstEval(value);
        assertThat(eval.getText()).isEqualTo("123.456");
        assertThat(eval.getNumber()).isEqualTo(value);
        assertThat(eval.isText()).isFalse();
        assertThat(eval.isNumber()).isTrue();
        assertThat(eval.isError()).isFalse();
    }

    @Test
    public void constructor_should_succeed_when_given_null_string() {
        AstEval eval = new AstEval(null);
        assertThat(eval.getText()).isEqualTo("");
        assertThat(eval.getNumber()).isEqualTo(0.0);
        assertThat(eval.isText()).isTrue();
        assertThat(eval.isNumber()).isFalse();
        assertThat(eval.isError()).isFalse();
    }
}

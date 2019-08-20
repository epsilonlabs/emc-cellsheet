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
import static org.mockito.Mockito.mock;

public class AstEvalTest {

    @Test
    public void constructor_should_succeed_for_text_eval() {
        String value = "Some string to put in";
        AstEval eval = AstEvalFactory.text(value);
        assertThat(eval.getText()).isEqualTo(value);
        assertThat(eval.isText()).isTrue();
        assertThat(eval.isNumber()).isFalse();
        assertThat(eval.isBoolean()).isFalse();
        assertThat(eval.isRef()).isFalse();
        assertThat(eval.isError()).isFalse();

    }

    @Test
    public void constructor_should_succeed_for_number_eval() {
        double value = 123.456;
        AstEval eval = AstEvalFactory.number(value);
        assertThat(eval.getNumber()).isEqualTo(value);
        assertThat(eval.isText()).isFalse();
        assertThat(eval.isNumber()).isTrue();
        assertThat(eval.isBoolean()).isFalse();
        assertThat(eval.isRef()).isFalse();
        assertThat(eval.isError()).isFalse();
    }

    @Test
    public void constructor_should_succeed_for_boolean_eval() {
        AstEval eval = AstEvalFactory.bool(false);
        assertThat(eval.getBoolean()).isFalse();
        assertThat(eval.isText()).isFalse();
        assertThat(eval.isNumber()).isFalse();
        assertThat(eval.isBoolean()).isTrue();
        assertThat(eval.isRef()).isFalse();
        assertThat(eval.isError()).isFalse();
    }

    @Test
    public void constructor_should_succeed_for_ref_eval() {
        Cell value = mock(Cell.class);
        AstEval eval = AstEvalFactory.ref(value);
        assertThat(eval.getRef()).isEqualTo(value);
        assertThat(eval.isText()).isFalse();
        assertThat(eval.isNumber()).isFalse();
        assertThat(eval.isBoolean()).isFalse();
        assertThat(eval.isRef()).isTrue();
        assertThat(eval.isError()).isFalse();
    }

    @Test
    public void constructor_should_succeed_for_error_eval() {
        String value = "#N/A";
        AstEval eval = AstEvalFactory.error(value);
        assertThat(eval.getError()).isEqualTo(value);
        assertThat(eval.isText()).isFalse();
        assertThat(eval.isNumber()).isFalse();
        assertThat(eval.isBoolean()).isFalse();
        assertThat(eval.isRef()).isFalse();
        assertThat(eval.isError()).isTrue();
    }
}

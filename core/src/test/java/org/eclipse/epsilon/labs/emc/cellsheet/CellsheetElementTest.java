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
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class CellsheetElementTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private CellsheetElement element;

    @Test
    public void getId_should_return_unassigned_by_default() {
        assertThat(element.getId()).isEqualTo(CellsheetElement.UNASSIGNED);
    }

    @Test
    public void iterator_should_return_empty_iterator_by_default() {
        assertThat(element.iterator()).hasSize(0);
    }
}

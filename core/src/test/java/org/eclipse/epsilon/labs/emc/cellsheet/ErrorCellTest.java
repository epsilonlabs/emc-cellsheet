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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class ErrorCellTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    NumericCell cell;

    @Test
    public void getType_should_return_NUMERICCELL() {
        assertThat(cell.getType()).isEqualTo(CellsheetType.NUMERIC_CELL);
    }

    @Test
    public void getKinds_should_return_NUMERICCELL_CELL_HASA1_HASID() {
        assertThat(cell.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.NUMERIC_CELL,
                CellsheetType.CELL,
                CellsheetType.HAS_ID,
                CellsheetType.HAS_A1);
    }
}

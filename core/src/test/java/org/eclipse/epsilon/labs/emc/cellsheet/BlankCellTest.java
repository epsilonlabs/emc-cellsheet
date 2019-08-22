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
public class BlankCellTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    BlankCell cell;

    @Test
    public void getValue_should_return_null() {
        assertThat(cell.getValue()).isNull();
    }

    @Test
    public void getType_should_return_BLANKCELL() {
        assertThat(cell.getType()).isEqualTo(CellsheetType.BLANK_CELL);
    }

    @Test
    public void getKinds_should_return_BLANKCELL_CELL_HASA1_HASID() {
        assertThat(cell.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.BLANK_CELL,
                CellsheetType.CELL,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.HAS_A1);
    }
}

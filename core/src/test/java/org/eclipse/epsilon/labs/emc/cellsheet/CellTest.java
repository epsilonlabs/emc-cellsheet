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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class CellTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private Cell cell;

    @Test
    public void getId_should_return_id() {
        Row row = mock(Row.class);
        when(row.getId()).thenReturn("cellsheet://Workspace%1/Example%20Book%201.xlsx/0/0");
        when(cell.getRow()).thenReturn(row);
        assertThat(cell.getId()).isEqualTo("cellsheet://Workspace%1/Example%20Book%201.xlsx/0/0/0");
    }

    @Test
    public void getA1_should_return_correct_a1() {
        Row row = mock(Row.class);
        Sheet sheet = mock(Sheet.class);
        when(row.getA1RowIndex()).thenCallRealMethod();
        when(sheet.getA1()).thenReturn("[Example Workbook 1.xlsx]'Example Sheet 1'");
        when(cell.getRow()).thenReturn(row);
        when(cell.getSheet()).thenReturn(sheet);
        assertThat(cell.getA1()).isEqualTo("[Example Workbook 1.xlsx]'Example Sheet 1'!A1");
    }

    @Test
    public void getKinds_should_return_correct_types() {
        when(cell.getType()).thenReturn(CellsheetType.BLANK_CELL);
        assertThat(cell.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.BLANK_CELL,
                CellsheetType.CELL,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.HAS_A1);
    }
}

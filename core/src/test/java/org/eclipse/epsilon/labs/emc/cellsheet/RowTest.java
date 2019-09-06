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

import org.eclipse.epsilon.labs.emc.cellsheet.test.DummySheet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class RowTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private Row row;

    @Test
    public void getId_should_return_id() {
        Sheet sheet = mock(Sheet.class);
        when(sheet.getId()).thenReturn("cellsheet://Workspace%201/Example%20Book%201.xlsx/0");
        when(row.getSheet()).thenReturn(sheet);
        assertThat(row.getId()).isEqualTo("cellsheet://Workspace%201/Example%20Book%201.xlsx/0/0");
    }

    @Test
    public void getId_should_return_unassigned_when_dangling() {
        assertThat(row.getId()).isEqualTo(CellsheetElement.UNASSIGNED);
    }

    @Test
    public void getQualifiedA1_should_return_qualified_a1() {
        Sheet sheet = mock(Sheet.class);
        when(sheet.getQualifiedA1()).thenReturn("[Example Workbook 1.xlsx]'Example Sheet 1'");
        when(row.getSheet()).thenReturn(sheet);
        assertThat(row.getQualifiedA1()).isEqualTo("[Example Workbook 1.xlsx]'Example Sheet 1'!A$1");
    }

    @Test
    public void getQualifiedA1_should_return_unassigned_when_dangling() {
        assertThat(row.getQualifiedA1()).isEqualTo(HasA1.UNASSIGNED);
    }

    @Test
    public void getRelativeA1_should_return_relative_a1() {
        DummySheet sheet = new DummySheet();
        sheet.setSheetName("Example Sheet 1");
        when(row.getSheet()).thenReturn(sheet);
        assertThat(row.getRelativeA1()).isEqualTo("'Example Sheet 1'!A$1");
    }

    @Test
    public void getRelativeA1_should_return_relative_a1_without_sheet_when_sheet_is_null() {
        when(row.getRowIndex()).thenReturn(3);
        assertThat(row.getRelativeA1()).isEqualTo("A$4");
    }

    @Test
    public void getType_should_return_correct_type() {
        assertThat(row.getType()).isEqualTo(CellsheetType.ROW);
    }

    @Test
    public void getKinds_should_return_correct_types() {
        assertThat(row.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.ROW,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.HAS_A1);
    }
}

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
public class SheetTest {

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private Sheet sheet;

    @Test
    public void getId_should_return_id() {
        Book book = mock(Book.class);
        when(book.getId()).thenReturn("cellsheet://Workspace%1/Example%20Book%201.xlsx");
        when(sheet.getBook()).thenReturn(book);
        assertThat(sheet.getId()).isEqualTo("cellsheet://Workspace%1/Example%20Book%201.xlsx/0");
    }

    @Test
    public void getId_should_return_unassigned_when_dangling() {
        assertThat(sheet.getId()).isEqualTo(CellsheetElement.UNASSIGNED);
    }

    @Test
    public void getA1_should_return_a1() {
        Book book = mock(Book.class);
        when(book.getA1()).thenReturn("[Example Workbook 1.xlsx]");
        when(sheet.getBook()).thenReturn(book);
        when(sheet.getSheetName()).thenReturn("Example Sheet 1");
        assertThat(sheet.getA1()).isEqualTo("[Example Workbook 1.xlsx]'Example Sheet 1'");
    }

    @Test
    public void getA1_should_return_unassigned_when_dangling() {
        assertThat(sheet.getA1()).isEqualTo(HasA1.UNASSIGNED);
    }

    @Test
    public void getType_should_return_correct_type() {
        assertThat(sheet.getType()).isEqualTo(CellsheetType.SHEET);
    }

    @Test
    public void getKinds_should_return_correct_types() {
        assertThat(sheet.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.SHEET,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.HAS_A1);
    }
}

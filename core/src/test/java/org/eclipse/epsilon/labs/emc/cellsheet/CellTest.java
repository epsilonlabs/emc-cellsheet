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

import org.eclipse.epsilon.labs.emc.cellsheet.test.DummyBook;
import org.eclipse.epsilon.labs.emc.cellsheet.test.DummyCell;
import org.eclipse.epsilon.labs.emc.cellsheet.test.DummyRow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class CellTest {

    DummyCell cell;

    @Before
    public void setUp() {
        Workspace ws = new Workspace();
        ws.setName("Default Dummy Workspace 1");
        ws.addBook(new DummyBook());
        cell = (DummyCell) ws.getBooks().get(0)
                .getSheet(0)
                .getRow(0)
                .getCell(0);
    }

    @Test
    public void getId_should_return_id() {
        assertThat(cell.getId()).isEqualTo("cellsheet://Default%20Dummy%20Workspace%201/Default%20Dummy%20Book%201.xlsx/0/0/0");
    }

    @Test
    public void getId_should_return_unassigned_when_dangling() {
        cell.setRow(null);
        assertThat(cell.getId()).isEqualTo(CellsheetElement.UNASSIGNED);
    }

    @Test
    public void getQualifiedA1_should_return_qualified_a1() {
        assertThat(cell.getQualifiedA1()).isEqualTo("[Default Dummy Book 1.xlsx]'Default Dummy Sheet 0'!A1");
    }

    @Test
    public void getQualifiedA1_should_return_unassigned_when_dangling() {
        cell.setRow(null);
        assertThat(cell.getQualifiedA1()).isEqualTo(HasA1.UNASSIGNED);
    }

    @Test
    public void getRelativeA1_should_return_full_relative_a1_with_sheet() {
        assertThat(cell.getRelativeA1()).isEqualTo("'Default Dummy Sheet 0'!A1");
    }

    @Test
    public void getRelativeA1_should_return_relative_a1_without_sheet_when_sheet_is_null() {
        ((DummyRow) cell.getRow()).setSheet(null);
        assertThat(cell.getRelativeA1()).isEqualTo("A1");
    }

    @Test
    public void getRelativeA1_should_return_unassigned_when_dangling() {
        cell.setRow(null);
        assertThat(cell.getRelativeA1()).isEqualTo(HasA1.UNASSIGNED);
    }

    @Test
    public void getKinds_should_return_correct_types() {
        assertThat(cell.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.BLANK_CELL,
                CellsheetType.CELL,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.HAS_A1);
    }

    @Test
    public void getRowIndex_should_return_correct_index() {
        assertThat(cell.getRowIndex()).isEqualTo(0);
        Row newRow = cell.getSheet().getRow(52);
        cell.setRow(newRow);
        assertThat(cell.getRowIndex()).isEqualTo(52);
    }

    @Test
    public void addAst_should_add_ast_and_set_ast_position() {
        Ast root = cell.getRoot();
        assertThat(cell.getAst("root")).isSameAs(root);

        Ast other = new Ast();
        assertThat(other.getPosition()).isEqualTo(Ast.UNASSIGNED);
        cell.putAst("other", other);
        assertThat(other.getPosition()).isEqualTo(0);
    }
}

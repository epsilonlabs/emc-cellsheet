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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.labs.emc.cellsheet.test.DummyBook;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

public class WorkspaceTest {

    private static final String modelName = "Workspace 1";

    private static final int EXPECTED_COUNT = 532;

    private int count;

    private Workspace workspace;
    private DummyBook book;

    private Set<Workspace> workspaces;
    private Set<Book> books;
    private Set<Sheet> sheets;
    private Set<Row> rows;
    private Set<Cell> cells;
    private Set<Ast> asts;

    @Before
    public void setUp() {
        workspaces = new HashSet<>();
        books = new HashSet<>();
        sheets = new HashSet<>();
        rows = new HashSet<>();
        cells = new HashSet<>();
        asts = new HashSet<>();

        workspace = new Workspace();
        workspace.setName(modelName);
        workspaces.add(workspace);

        book = new DummyBook();
        book.setBookName("WorkspaceTest Book 1.xlsx");
        workspace.addBook(book);
        books.add(book);

        for (int s = 0; s < 5; s++) {
            Sheet sheet = book.getSheet(s);
            sheets.add(sheet);
            for (int r = 0; r < 5; r++) {
                Row row = sheet.getRow(r);
                rows.add(row);
                for (int c = 0; c < 5; c++) {
                    Cell cell = row.getCell(c);
                    cells.add(cell);
                    Ast ast = cell.getRoot();
                    asts.add(ast);
                    asts.add(ast.childAt(0));
                    asts.add(ast.childAt(1));
                }
            }
        }

        count = workspaces.size()
                + books.size()
                + sheets.size()
                + rows.size()
                + cells.size()
                + asts.size();

        assertThat(count).isEqualTo(EXPECTED_COUNT);
    }

    @Test
    public void getId_should_return_id_when_name_is_assigned() {
        assertThat(workspace.getId()).isNotBlank().isEqualTo("cellsheet://Workspace%201");
    }

    @Test
    public void getId_should_return_null_when_name_is_not_assigned() {
        assertThat(new Workspace().getId()).isNull();
    }

    @Test
    public void getElementById_should_return_null_when_given_null() {
        assertThat(workspace.getElementId(null)).isNull();
    }

    @Test
    public void getElementById_should_throw_IAException_when_given_wrong_scheme() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> workspace.getElementById("http://Workspace%201"));
    }

    @Test
    public void getElementById_should_throw_URISyntaxtException_when_given_bad_uri() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> workspace.getElementById("cellsheet://Workspace 1"));
    }

    @Test
    public void getElementById_should_return_self_when_given_workspace_id() {
        Object element = workspace.getElementById(workspace.getId());
        assertThat(element).isInstanceOf(Workspace.class).isSameAs(workspace);
    }

    @Test
    public void getElementById_should_return_Book_when_given_book_id() {
        Object element = workspace.getElementById(book.getId());
        assertThat(element).isInstanceOf(Book.class).isEqualToComparingFieldByFieldRecursively(book);
    }

    @Test
    public void getElementById_should_return_Sheet_when_given_sheet_id() {
        Sheet sheet = book.getSheet(52);
        Object element = workspace.getElementById(sheet.getId());
        assertThat(element).isInstanceOf(Sheet.class).isEqualToComparingFieldByFieldRecursively(sheet);
    }

    @Test
    public void getElementById_should_return_Row_when_given_row_id() {
        Row row = book.getSheet(52).getRow(100);
        Object element = workspace.getElementById(row.getId());
        assertThat(element).isInstanceOf(Row.class).isEqualToComparingFieldByFieldRecursively(row);
    }

    @Test
    public void getElementById_should_return_Cell_when_given_cell_id() {
        Cell cell = book.getSheet(52).getRow(100).getCell(34);
        Object element = workspace.getElementById(cell.getId());
        assertThat(element).isInstanceOf(Cell.class).isEqualToComparingFieldByFieldRecursively(cell);
    }

    @Test
    public void getElementById_should_return_Ast_when_given_ast_id() {
        Ast ast = (Ast) book.getSheet(52).getRow(100).getCell(34).getAsts().get(45);
        Object element = workspace.getElementById(ast.getId());
        assertThat(element).isInstanceOf(Ast.class).isEqualToComparingFieldByFieldRecursively(ast);
    }

    @Test
    public void getElementById_should_return_Ast_when_given_ast_child_id() {
        Ast ast = ((Ast) book.getSheet(52).getRow(100).getCell(34).getAsts().get(45)).childAt(23);
        Object element = workspace.getElementById(ast.getId());
        assertThat(element).isInstanceOf(Ast.class).isEqualToComparingFieldByFieldRecursively(ast);
    }

    @Test
    public void getElementById_should_null_when_given_id_of_another_model() {
        assertThat(workspace.getElementById("cellsheet://Workspace%202/Another.xlsx")).isNull();
    }

    @Test
    public void getElementById_should_throw_IAException_when_given_bad_id() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> workspace.getElementById(book.getId() + "/notright"));
    }

    @Test
    public void getElementById_should_throw_IAException_when_given_bad_cell_part_id() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> workspace.getElementById(
                        book.getSheet(0)
                                .getRow(0)
                                .getCell(0).getId() + "/asts"));
    }

    @Test
    public void getBooks_should_return_all_books() {
        assertThat(workspace.getBooks()).containsExactly(book);
    }

    @Test
    public void getToken_should_return_token_when_given_tokenstr() {
        String tokenValue = "Some token value";
        Token expected = Tokens.getToken(tokenValue);
        assertThat(workspace.getToken(tokenValue)).isEqualTo(expected);
    }

    @Test
    public void createInstance_should_throw_unsupported_op_exception() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> workspace.createInstance(""));
    }

    @Test
    public void dispose_should_clear_all_values() {
        assertThat(workspace.getBooks()).isNotEmpty();
        workspace.dispose();
        assertThat(workspace.getBooks()).isEmpty();
    }

    @Test
    public void delete_should_throw_unsupported_op_exception() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> workspace.deleteElement(null));
    }

    @Test
    public void getCacheKeyForType_should_return_CELL_for_Cell_str() throws Exception {
        assertThat(workspace.getCacheKeyForType("Cell"))
                .isEqualTo(CellsheetType.CELL);
    }

    @Test
    public void getCacheKeyForType_should_throw_exception_when_given_bad_value() {
        assertThatExceptionOfType(EolModelElementTypeNotFoundException.class)
                .isThrownBy(() -> workspace.getCacheKeyForType("NotAType"));
    }

    @Test
    public void getAllTypeNamesOf_should_return_all_kinds_of_book() {
        assertThat(workspace.getAllTypeNamesOf(book))
                .containsExactlyInAnyOrder("Book", "HasA1", "CellsheetElement");
    }

    @Test
    public void getEnumerationValue_should_throw_unsupported_op_exception() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> workspace.getEnumerationValue(null, null));
    }

    @Test
    public void getTypeNameOf_should_return_book_when_given_Book() {
        assertThat(workspace.getTypeNameOf(book)).isEqualTo(book.getType().getTypeName());
    }

    @Test
    public void getElementId_should_return_correct_id_when_given_book() {
        assertThat(book.getId()).isEqualTo(book.getId());
    }

    @Test
    public void getElementId_should_return_null_when_given_non_model_element() {
        assertThat(workspace.getElementId(new Object())).isNull();
    }

    @Test
    public void setElementId_should_throw_unsupported_op_exception() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> workspace.setElementId(null, null));
    }

    @Test
    public void owns_should_return_true_for_owned_book() {
        assertThat(workspace.owns(book)).isTrue();
    }

    @Test
    public void owns_should_return_false_for_other_book() {
        assertThat(workspace.owns(when(mock(Book.class).getId()).thenReturn("cellsheet://Other/9").getMock())).isFalse();
    }

    @Test
    public void isInstantiable_should_throw_unsupported_op_exception() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> workspace.isInstantiable(null));
    }

    @Test
    public void hasType_should_return_true_when_given_valid_type() {
        assertThat(workspace.hasType("Book")).isTrue();
    }

    @Test
    public void hasType_should_return_false_when_given_invalid_type() {
        assertThat(workspace.hasType("NotAType")).isFalse();
    }

    @Test
    public void store_should_throw_unsupported_op_exception() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> workspace.store());
    }

    @Test
    public void store_should_throw_unsupported_op_exception_when_given_string() {
        assertThatExceptionOfType(UnsupportedOperationException.class)
                .isThrownBy(() -> workspace.store(""));
    }

    @Test
    public void getType_should_return_WORKSPACE() {
        assertThat(workspace.getType()).isEqualTo(CellsheetType.WORKSPACE);
    }

    @Test
    public void getKinds_should_return_WORKSPACE_HASID() {
        assertThat(workspace.getKinds()).containsExactlyInAnyOrder(
                CellsheetType.WORKSPACE,
                CellsheetType.CELLSHEET_ELEMENT);
    }

    @Test
    public void allContents_should_return_all_contents() {
        Collection<CellsheetElement> allContents = workspace.allContents();
        assertThat(allContents).hasSize(EXPECTED_COUNT);
        assertThat(allContents.stream()
                .filter(Workspace.class::isInstance)
                .map(Workspace.class::cast)
                .collect(Collectors.toSet()))
                .containsExactlyInAnyOrderElementsOf(workspaces);
        assertThat(allContents.stream()
                .filter(Book.class::isInstance)
                .map(Book.class::cast)
                .collect(Collectors.toSet()))
                .containsExactlyInAnyOrderElementsOf(books);
        assertThat(allContents.stream()
                .filter(Sheet.class::isInstance)
                .map(Sheet.class::cast)
                .collect(Collectors.toSet()))
                .containsExactlyInAnyOrderElementsOf(sheets);
        assertThat(allContents.stream()
                .filter(Row.class::isInstance)
                .map(Row.class::cast)
                .collect(Collectors.toSet()))
                .containsExactlyInAnyOrderElementsOf(rows);
        assertThat(allContents.stream()
                .filter(Cell.class::isInstance)
                .map(Cell.class::cast)
                .collect(Collectors.toSet()))
                .containsExactlyInAnyOrderElementsOf(cells);
        assertThat(allContents.stream()
                .filter(Ast.class::isInstance)
                .map(Ast.class::cast)
                .collect(Collectors.toSet()))
                .containsExactlyInAnyOrderElementsOf(asts);
    }

    @Test
    public void getAllOfKind_should_return_all_HasA1s_when_given_HasA1() throws Exception {
        Collection<CellsheetElement> expected = ImmutableList.copyOf(
                Iterables.concat(books, sheets, rows, cells));
        Collection<CellsheetElement> allHasA1 = workspace.getAllOfKind(CellsheetType.HAS_A1);
        assertThat(allHasA1)
                .hasSize(expected.size())
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void getAllOfKind_should_throw_exception_when_given_invalid_typename() {
        assertThatExceptionOfType(EolModelElementTypeNotFoundException.class)
                .isThrownBy(() -> workspace.getAllOfKind("NotAType"));
    }

    @Test
    public void getAllOfKind_should_throw_exception_when_given_invalid_null_type() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> workspace.getAllOfKind((CellsheetType) null));
    }

    @Test
    public void getAllOfType_should_return_all_rows_when_given_Row() throws Exception {
        Collection<CellsheetElement> allRows = workspace.getAllOfType(CellsheetType.ROW);
        assertThat(allRows)
                .hasSize(rows.size())
                .containsExactlyInAnyOrderElementsOf(rows);
    }

    @Test
    public void getAllOfType_should_throw_exception_when_given_invalid_typename() {
        assertThatExceptionOfType(EolModelElementTypeNotFoundException.class)
                .isThrownBy(() -> workspace.getAllOfType("NotAType"));
    }

    @Test
    public void getAllOfType_should_throw_exception_when_given_invalid_null_type() {
        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> workspace.getAllOfType((CellsheetType) null));
    }
}

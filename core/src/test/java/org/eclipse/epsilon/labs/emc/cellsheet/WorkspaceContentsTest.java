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
import org.eclipse.epsilon.labs.emc.cellsheet.test.DummyBook;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class WorkspaceContentsTest {

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
    public void setUp() throws Exception {
        workspaces = new HashSet<>();
        books = new HashSet<>();
        sheets = new HashSet<>();
        rows = new HashSet<>();
        cells = new HashSet<>();
        asts = new HashSet<>();

        workspace = new Workspace();
        workspaces.add(workspace);

        book = new DummyBook();
        book.setBookName("WorkspaceContentsTest Book 1.xlsx");
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
    public void allContents_should_return_all_contents() {
        Collection<HasId> allContents = workspace.allContents();
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
        Collection<HasId> expected = ImmutableList.copyOf(
                Iterables.concat(books, sheets, rows, cells));
        Collection<HasId> allHasA1 = workspace.getAllOfKind(CellsheetType.HAS_A1);
        assertThat(allHasA1)
                .hasSize(expected.size())
                .containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void getAllOf_type_should_return_all_rows_when_given_Cell() throws Exception {
        Collection<HasId> allCells = workspace.getAllOfType(CellsheetType.CELL);
        assertThat(allCells)
                .hasSize(cells.size())
                .containsExactlyInAnyOrderElementsOf(cells);
    }
}

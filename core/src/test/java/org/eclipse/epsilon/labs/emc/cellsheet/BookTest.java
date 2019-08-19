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
public class BookTest {

    private static final String BOOKNAME = "Example Book 1.xlsx";

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    Book book;

    @Test
    public void getId_should_return_correct_id() {
        Workspace workspace = mock(Workspace.class);
        when(workspace.getId()).thenReturn("cellsheet://Workspace%201");
        when(book.getWorkspace()).thenReturn(workspace);
        stubName();
        assertThat(book.getId()).isEqualTo("cellsheet://Workspace%201/Example%20Book%201.xlsx");
    }

    @Test
    public void getA1_should_return_correct_a1() {
        stubName();
        assertThat(book.getA1()).isEqualTo("[Example Book 1.xlsx]");
    }

    @Test
    public void getType_should_return_correct_type() {
        assertThat(book.getType()).isEqualTo(CellsheetType.BOOK);
    }

    @Test
    public void getKinds_should_return_correct_types() {
        assertThat(book.getKinds().toArray()).containsExactlyInAnyOrder(
                CellsheetType.BOOK,
                CellsheetType.CELLSHEET_ELEMENT,
                CellsheetType.HAS_A1);
    }

    private void stubName() {
        when(book.getBookName()).thenReturn(BOOKNAME);
    }
}

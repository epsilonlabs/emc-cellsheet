/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.*;
import org.eclipse.epsilon.labs.emc.cellsheet.AstEval;
import org.eclipse.epsilon.labs.emc.cellsheet.Workspace;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked")
public class PoiAstEvaluatorTest {

    private PoiCell cell;
    private Cell delegate;
    private PoiBook book;
    private Workspace workspace;

    @Before
    public void setUp() throws Exception {
        workspace = new Workspace();
        workspace.setName(PoiAstEvaluator.class.getSimpleName() + "Workspace");
        book = new PoiBook.Builder().withWorkspace(workspace).withBookName(PoiAstEvaluatorTest.class.getSimpleName() + " Book.xlsx").build();
        workspace.addBook(book);
        workspace.load();

        delegate = book.getDelegate().createSheet("Sheet 1").createRow(0).createCell(0);

        // Setup values
        Sheet valueSheet = book.getDelegate().createSheet("Values");
        for (int i = 0; i < 5; i++) {
            Row row = valueSheet.createRow(i);
            for (int j = 0; j < 5; j++) {
                row.createCell(j).setCellValue(1);
            }
        }
    }

    @After
    public void tearDown() throws Exception {
        book.dispose();
        book = null;
        cell = null;
        workspace = null;
    }

    @Test
    public void evaluate_given_text_cell_should_return_text() {
        String text = "This is the text to return";
        delegate.setCellValue(text);
        cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isNotNull().isInstanceOf(PoiTextCell.class);
        AstEval result = cell.getRoot().evaluate();

        assertThat(result.isText()).isTrue();
        assertThat(result.isNumber()).isFalse();
        assertThat(result.isError()).isFalse();
        assertThat(result.getText()).isEqualTo(text);
    }

    @Test
    public void evaluate_given_sumA1E5_should_return_25() {
        delegate.setCellFormula("SUM(Values!A1:E5)");
        cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isNotNull().isInstanceOf(PoiFormulaCell.class);
        AstEval result = cell.getRoot().evaluate();
        assertThat(result.getNumber()).isEqualTo(25);
    }

}

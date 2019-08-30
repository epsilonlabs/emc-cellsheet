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

import org.apache.poi.ss.usermodel.Cell;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked")
public class PoiCellTest {

    private Cell delegateCell;
    private PoiBook book;

    @Before
    public void setUp() throws Exception {
        book = new PoiBook.Builder().withBookName(PoiCellTest.class.getSimpleName() + " Book.xlsx").build();
        book.load();
        delegateCell = book.getDelegate().createSheet("Test Sheet 1").createRow(0).createCell(0);
    }

    @After
    public void tearDown() throws Exception {
        book.dispose();
        book = null;
        delegateCell = null;
    }

    @Test
    public void getRoot_given_a_PoiTextCell_should_return_Ast_with_Text_payload() {
        String value = "This is some value";
        delegateCell.setCellValue(value);

        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiTextCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull();
        assertThat(root.getPayload()).isInstanceOf(Text.class);
        assertThat(root.getToken()).isEqualTo(value);
    }

    @Test
    public void getRoot_given_a_PoiBooleanCell_should_return_Logical_Ast() {
        delegateCell.setCellValue(false);

        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiBooleanCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull();
        assertThat(root.getPayload()).isInstanceOf(Logical.class);
        assertThat(root.getToken()).isEqualTo("FALSE");
    }

    @Test
    public void getRoot_given_a_PoiBlankCell_should_return_Nothing_Ast() {
        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiBlankCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull();
        assertThat(root.getPayload()).isInstanceOf(Text.class);
        assertThat(root.getToken()).isBlank();
    }

    @Test
    public void getRoot_given_a_PoiNumericCell_should_return_Number_Ast() {
        double value = 123.456;
        delegateCell.setCellValue(value);

        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiNumericCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull();
        assertThat(root.getPayload()).isNotNull().isInstanceOf(Number.class);
        assertThat(root.getToken()).isEqualTo(String.valueOf(value));
    }

    @Test
    public void getRoot_given_a_PoiFormulaCell_with_SUM_should_return_Function_Ast() {
        String formula = "SUM(A1:A5)";
        delegateCell.setCellFormula(formula);

        PoiCell cell = book.getSheet(0).getRow(0).getCell(0);
        assertThat(cell).isInstanceOf(PoiFormulaCell.class);

        Ast root = cell.getRoot();
        assertThat(root).isNotNull();
        assertThat(root.getPayload()).isInstanceOf(Function.class);
        assertThat(root.getToken()).isEqualTo("SUM");
        assertThat(root.getChildren()).hasSize(1);

        Ast child = root.getChildren().get(0);
        assertThat(child).isNotNull();
        assertThat(child.getPayload()).isInstanceOf(Range.class);
        assertThat(child.getToken()).isEqualTo("A1:A5");
    }
}

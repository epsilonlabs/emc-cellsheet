/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PoiRowTest {

    private Cell delegateCell;
    private PoiRow row;
    private PoiBook book;

    @Before
    public void setUp() throws Exception {
        book = new PoiBook();
        book.setDelegate(WorkbookFactory.create(true));
        delegateCell = book.getDelegate().createSheet("Test Sheet 1").createRow(0).createCell(0);
        row = book.getSheet(0).getRow(0);
    }

    @Test
    public void getCells_should_return_all_cells() {
        IntStream.range(1, 10).forEach(i -> book.getDelegate().getSheetAt(0).getRow(0).createCell(i)); // Creates cells
        assertThat(row.getCells())
                .asList()
                .hasSize(10)
                .extracting("colIndex")
                .contains(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getCell_given_a_blank_cell_should_return_PoiBlankCell() {
        delegateCell.setBlank();
        assertThat(row.getCell(0))
                .isInstanceOf(PoiBlankCell.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getCell_given_a_boolean_cell_should_return_PoiBooleanCell() {
        delegateCell.setCellValue(false);
        PoiCell cell = row.getCell(0);
        assertThat(cell).isInstanceOf(PoiBooleanCell.class);
        assertThat(cell.getValue()).isEqualTo(false);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getCell_given_a_text_cell_should_return_PoiTextCell() {
        String value = "Hello world this is a string value";
        delegateCell.setCellValue(value);
        PoiCell cell = row.getCell(0);
        assertThat(cell).isInstanceOf(PoiTextCell.class);
        assertThat(cell.getValue()).isEqualTo(value);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getCell_given_a_date_cell_should_return_PoiDateCell() {
        Date value = new Date();
        delegateCell.setCellValue(value);

        CellStyle cellStyle = book.getDelegate().createCellStyle();
        CreationHelper createHelper = book.getDelegate().getCreationHelper();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(BuiltinFormats.getBuiltinFormat(0xf)));
        delegateCell.setCellStyle(cellStyle);

        PoiCell cell = row.getCell(0);
        assertThat(cell).isInstanceOf(PoiDateCell.class);
        assertThat(cell.getValue()).isEqualTo(value);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getCell_given_a_numeric_cell_should_return_PoiNumericCell() {
        double value = 123.456;
        delegateCell.setCellValue(value);
        PoiCell cell = row.getCell(0);
        assertThat(cell).isInstanceOf(PoiNumericCell.class);
        assertThat(cell.getValue()).isEqualTo(value);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getCell_given_a_formula_cell_should_return_PoiFormulaCell() {
        String value = "C5+C6";
        delegateCell.setCellFormula(value);
        PoiCell cell = row.getCell(0);
        assertThat(cell).isInstanceOf(PoiFormulaCell.class);
        assertThat(cell.getValue()).isEqualTo(value);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getCell_given_an_error_should_return_PoiErrorCell() {
        String value = "8/0";
        delegateCell.setCellFormula(value);
        book.getDelegate().getCreationHelper().createFormulaEvaluator().evaluateInCell(delegateCell);

        PoiCell cell = row.getCell(0);
        assertThat(cell).isInstanceOf(PoiErrorCell.class);
        assertThat(cell.getValue()).isEqualTo("#DIV/0!");
    }
}

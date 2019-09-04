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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.AstEval;
import org.eclipse.epsilon.labs.emc.cellsheet.Workspace;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked")
public class PoiAstEvaluatorTest {

    private static PoiBook book;
    private Cell delegate;
    private Workspace workspace;

    @BeforeClass
    public static void setUpClass() throws Exception {
        book = new PoiBook.Builder().build();
        book.load();
        book.getDelegate().createSheet("Sheet 1").createRow(0).createCell(0);
        Sheet valueSheet = book.getDelegate().createSheet("Values");
        for (int i = 0; i < 5; i++) {
            Row row = valueSheet.createRow(i);
            for (int j = 0; j < 5; j++) {
                row.createCell(j).setCellValue(1);
            }
        }
    }

    @AfterClass
    public static void tearDownClass() {
        book.dispose();
    }

    @Before
    public void setUp() {
        delegate = book.getSheet(0).getRow(0).getCell(0).getDelegate();
        delegate.setCellFormula(null);
    }

    @Test
    public void evaluate_should_return_string_given_text_literal() {
        Ast ast = getAst("\"This is the of to return\"");
        AstEval result = ast.evaluate(PoiAstEvaluator.instance());
        assertThat(result.getText()).isEqualTo("This is the of to return");
    }

    @Test
    public void evaluate_should_return_correct_number_given_sum_function() {
        Ast ast = getAst("SUM(Values!A1:E5)");
        AstEval result = ast.evaluate(PoiAstEvaluator.instance());
        assertThat(result.getNumber()).isEqualTo(25);
    }

    @Test
    public void evaluate_should_return_correct_string_given_TEXT_function() {
        Ast ast = getAst("TEXT(0.285,\"0.0%\")");
        AstEval result = ast.evaluate(PoiAstEvaluator.instance());
        assertThat(result.getText()).isEqualTo("28.5%");
    }

    @Test
    public void evaluate_should_return_ref_result_given_ref_function() {
        String refValue = "This is a of";
        delegate.getRow().createCell(1).setCellValue(refValue);

        Ast ast = getAst("B1");
        AstEval result = ast.evaluate(PoiAstEvaluator.instance());
        assertThat(result.getRef().getId()).isEqualTo(book.getSheet(0).getRow(0).getCell(1).getId());
        assertThat(result.getText()).isEqualTo(refValue);
    }

    @Test
    public void evaluate_should_return_correct_result_when_subtree_is_evaluated() {
        Ast ast = getAst("1+6/3");
        Ast child = ast.childAt(1);
        AstEval result = child.evaluate(PoiAstEvaluator.instance());
        assertThat(result.getNumber()).isEqualTo(2);
    }

    private Ast getAst(String formula) {
        delegate.setCellFormula(formula);
        return book.getSheet(0).getRow(0).getCell(0).getRoot();
    }
}

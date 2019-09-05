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
import org.eclipse.epsilon.labs.emc.cellsheet.AstPayloads;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType.ADDITION;
import static org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType.REF;

@SuppressWarnings("unchecked")
public class PoiAstBuilderTest {

    private Cell delegate;
    private PoiBook book;

    @Before
    public void setUp() throws Exception {
        book = new PoiBook.Builder().build();
        book.load();
        delegate = book.getDelegate().createSheet("Sheet 1").createRow(0).createCell(0);
    }

    @After
    public void tearDown() throws Exception {
        book.dispose();
        book = null;
        delegate = null;
    }

    @Test
    public void of_should_return_correct_ast_given_function_with_multi_args() {
        Ast ast = getAst("VLOOKUP(A5,B1:C5,1)");

        assertThat(ast.getPayload()).isInstanceOf(Function.class);
        assertThat(ast.getToken()).isEqualTo("VLOOKUP");

        assertThat(ast.childAt(0).getPayload()).isInstanceOf(Ref.class);
        assertThat(ast.childAt(0).getToken()).isEqualTo("A5");

        assertThat(ast.childAt(1).getPayload()).isInstanceOf(Range.class);
        assertThat(ast.childAt(1).getToken()).isEqualTo("B1:C5");

        assertThat(ast.childAt(2).getPayload()).isInstanceOf(Number.class);
        assertThat(ast.childAt(2).getToken()).isEqualTo("1.0");
    }

    @Test
    public void of_should_return_ast_given_sum_function() {
        Ast root = getAst("SUM(A1:A5)");

        assertThat(root).isNotNull();
        assertThat(root.getPayload()).isInstanceOf(Function.class);
        assertThat(root.getToken()).isEqualTo("SUM");
        assertThat(root.getChildren()).hasSize(1);
        assertThat(root.isRoot()).isTrue();

        Ast ast = root.childAt(0);
        assertThat(ast).isNotNull();
        assertThat(ast.getPayload()).isInstanceOf(Range.class);
        assertThat(ast.getToken()).isEqualTo("A1:A5");
        assertThat(ast.getParent()).isEqualTo(root);
        assertThat(ast.isLeaf()).isTrue();
    }

    @Test
    public void of_given_SUM_with_UNION() {
        Ast root = getAst("SUM(A1:A5,B1:B5)");

        assertThat(root).isNotNull();
        assertThat(root.getPayload()).isInstanceOf(Function.class);
        assertThat(root.getToken()).isEqualTo("SUM");
        assertThat(root.isRoot()).isTrue();
        assertThat(root.getChildren()).hasSize(2);
        assertThat(root.getFormula()).isEqualTo("SUM(A1:A5,B1:B5)");

        Ast ast = root.childAt(0);
        assertThat(ast).isNotNull();
        assertThat(ast.getPayload()).isInstanceOf(Range.class);
        assertThat(ast.getToken()).isEqualTo("A1:A5");
        assertThat(ast.getParent()).isEqualTo(root);
        assertThat(ast.isLeaf());

        ast = root.childAt(1);
        assertThat(ast).isNotNull();
        assertThat(ast.getPayload()).isInstanceOf(Range.class);
        assertThat(ast.getToken()).isEqualTo("B1:B5");
        assertThat(ast.getParent()).isEqualTo(root);
        assertThat(ast.isLeaf());
    }

    @Test
    public void of_given_basic_arithmetic_op() {
        String formula = "(6*12)+2";

        Ast add = getAst(formula);
        assertThat(add.getPayload()).isInstanceOf(Addition.class);

        Ast multi = add.childAt(0);
        assertThat(multi.getPayload()).isInstanceOf(Multiplication.class);

        Ast two = add.childAt(1);
        assertThat(two.getPayload()).isInstanceOf(Number.class);
        assertThat(two.getToken()).isEqualTo("2.0");
    }

    @Test
    public void of_should_return_ast_given_multiple_additions() {
        String formula = "A1+B1+C1";

        Ast a0 = getAst(formula);
        assertThat(a0.getPayload()).isEqualTo(AstPayloads.fromToken(ADDITION));

        Ast a00 = a0.childAt(0);
        assertThat(a00.getPayload()).isEqualTo(AstPayloads.fromToken(ADDITION));

        Ast a01 = a0.childAt(1);
        assertThat(a01.getPayload()).isEqualTo(AstPayloads.fromToken(REF, "C1"));

        Ast a000 = a00.childAt(0);
        assertThat(a000.getPayload()).isEqualTo(AstPayloads.fromToken(REF, "A1"));

        Ast a001 = a00.childAt(1);
        assertThat(a001.getPayload()).isEqualTo(AstPayloads.fromToken(REF, "B1"));
    }

    private Ast getAst(String formula) {
        delegate.setCellFormula(formula);
        return PoiAstBuilder.of(book.getSheet(0).getRow(0).getCell(0));
    }

}

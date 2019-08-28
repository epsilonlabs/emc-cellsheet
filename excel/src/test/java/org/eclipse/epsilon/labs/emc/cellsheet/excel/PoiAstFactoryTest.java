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

import com.google.common.io.Resources;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.poi.ss.usermodel.Cell;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URLDecoder;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("unchecked")
public class PoiAstFactoryTest {

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

        assertThat(ast).isInstanceOf(Function.class);
        assertThat(ast.getTokenValue()).isEqualTo("VLOOKUP");

        assertThat(ast.childAt(0)).isInstanceOf(Ref.class);
        assertThat(ast.childAt(0).getTokenValue()).isEqualTo("A5");

        assertThat(ast.childAt(1)).isInstanceOf(Range.class);
        assertThat(ast.childAt(1).getTokenValue()).isEqualTo("B1:C5");

        assertThat(ast.childAt(2)).isInstanceOf(Number.class);
        assertThat(ast.childAt(2).getTokenValue()).isEqualTo("1");
    }

    @Test
    public void of_should_return_ast_given_sum_function() {
        Ast root = getAst("SUM(A1:A5)");

        assertThat(root).isNotNull().isInstanceOf(Function.class);
        assertThat(root.getTokenValue()).isEqualTo("SUM");
        assertThat(root.getChildren()).hasSize(1);
        assertThat(root.isRoot()).isTrue();

        Ast ast = root.childAt(0);
        assertThat(ast).isNotNull().isInstanceOf(Range.class);
        assertThat(ast.getTokenValue()).isEqualTo("A1:A5");
        assertThat(ast.getParent()).isEqualTo(root);
        assertThat(ast.isLeaf()).isTrue();
    }

    @Test
    public void of_given_SUM_with_UNION() {
        Ast root = getAst("SUM(A1:A5,B1:B5)");

        assertThat(root).isNotNull().isInstanceOf(Function.class);
        assertThat(root.getTokenValue()).isEqualTo("SUM");
        assertThat(root.isRoot()).isTrue();
        assertThat(root.getChildren()).hasSize(2);
        assertThat(root.getFormula()).isEqualTo("SUM(A1:A5,B1:B5)");

        Ast ast = root.childAt(0);
        assertThat(ast).isNotNull().isInstanceOf(Range.class);
        assertThat(ast.getTokenValue()).isEqualTo("A1:A5");
        assertThat(ast.getParent()).isEqualTo(root);
        assertThat(ast.isLeaf());

        ast = root.childAt(1);
        assertThat(ast).isNotNull().isInstanceOf(Range.class);
        assertThat(ast.getTokenValue()).isEqualTo("B1:B5");
        assertThat(ast.getParent()).isEqualTo(root);
        assertThat(ast.isLeaf());
    }

    @Test
    public void of_given_basic_arithmetic_op() {
        String formula = "(6*12)+2";

        Ast add = getAst(formula);
        assertThat(add).isInstanceOf(Addition.class);

        Ast multi = add.childAt(0);
        assertThat(multi).isInstanceOf(Multiplication.class);

        Ast two = add.childAt(1);
        assertThat(two).isInstanceOf(Number.class);
        assertThat(two.getTokenValue()).isEqualTo("2");
    }

    @Test
    public void of_should_return_ast_given_multiple_additions() {
        String formula = "A1+B1+C1";

        Ast a0 = getAst(formula);
        assertThat(a0).isInstanceOf(Addition.class);

        Ast a00 = a0.childAt(0);
        assertThat(a00).isInstanceOf(Addition.class);

        Ast a01 = a0.childAt(1);
        assertThat(a01).isInstanceOf(Ref.class);
        assertThat(a01.getTokenValue()).isEqualTo("C1");

        Ast a000 = a00.childAt(0);
        assertThat(a000).isInstanceOf(Ref.class);
        assertThat(a000.getTokenValue()).isEqualTo("A1");

        Ast a001 = a00.childAt(1);
        assertThat(a001).isInstanceOf(Ref.class);
        assertThat(a001.getTokenValue()).isEqualTo("B1");
    }

    private Ast getAst(String formula) {
        delegate.setCellFormula(formula);
        return PoiAstFactory.getInstance().of(book.getSheet(0).getRow(0).getCell(0));
    }

}

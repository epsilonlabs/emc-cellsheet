/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FormulaBuilderVisitorTest {

    private FormulaBuilderVisitor visitor;

    @Before
    public void setUp() throws Exception {
        visitor = new FormulaBuilderVisitor();
    }

    /**
     * Formula =TEXT(0.285,"0.0%")
     */
    @Test
    public void visit_should_rebuild_formula_with_text() throws Exception {
        Ast ast = new Function("TEXT");
        ast.addChild(new Number(0.285));
        ast.addChild(new Text("0.0%"));
        assertThat(visitor.visit(ast)).isEqualTo("TEXT(0.285,\"0.0%\")");
    }

    @Test
    public void visit_should_rebuild_formula_when_given_numeric_operand() throws Exception {
        Ast ast = new Number(123.456);
        assertThat(visitor.visit(ast)).isEqualTo("123.456");
    }

    @Test
    public void visit_should_rebuild_formula_when_given_function() throws Exception {
        Ast ast = new Function("VLOOKUP");
        ast.addChild(new Ref("A1"));
        ast.addChild(new Range("B1:C5"));
        ast.addChild(new Number(1));
        assertThat(visitor.visit(ast)).isEqualTo("VLOOKUP(A1,B1:C5,1)");
    }

    @Test
    public void visit_should_rebuild_formula_when_given_nested_function() throws Exception {
        Ast ast = new Function("VLOOKUP");
        ast.addChild(new Ref("A5"));
        ast.addChild(new Range("B1:C5"));

        Function child = new Function("HLOOKUP");
        ast.addChild(child);
        child.addChild(new Ref("D1"));
        child.addChild(new Range("E1:F5"));
        child.addChild(new Number(2));

        assertThat(visitor.visit(ast)).isEqualTo("VLOOKUP(A5,B1:C5,HLOOKUP(D1,E1:F5,2))");
    }

    @Test
    public void visit_should_build_infix_formula() throws Exception {
        Ast ast = new Addition();
        ast.addChild(new Number(1));
        ast.addChild(new Number(2));
        assertThat(visitor.visit(ast)).isEqualTo("(1+2)");
    }

    @Test
    public void visit_should_build_prefix_formula() throws Exception {
        Ast ast = new Percent();
        ast.addChild(new Number(1));
        assertThat(visitor.visit(ast)).isEqualTo("1%");
    }
}
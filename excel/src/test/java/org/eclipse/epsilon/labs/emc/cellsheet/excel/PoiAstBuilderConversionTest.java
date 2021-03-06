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

import org.apache.poi.ss.formula.ptg.*;
import org.apache.poi.ss.util.AreaReference;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests that individual PTGs are converted correctly into their AST equivalents
 */
@RunWith(Parameterized.class)
@SuppressWarnings("unchecked")
public class PoiAstBuilderConversionTest {

    private Ptg ptg;
    private Class astClazz;
    private String expectedValue;

    public PoiAstBuilderConversionTest(Ptg ptg, Class astClazz, String expectedValue) {
        this.ptg = ptg;
        this.astClazz = astClazz;
        this.expectedValue = expectedValue;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                        {new IntPtg(123), Number.class, "123.0"},
                        {new NumberPtg(123.456), Number.class, "123.456"},
                        {new StringPtg("Hello World"), Text.class, "Hello World"},
                        {BoolPtg.valueOf(false), Logical.class, "FALSE"},
                        {new AreaPtg(new AreaReference("A1:A5", null)), Range.class, "A1:A5"},
                        {new RefPtg("A1"), Ref.class, "A1"},
                        {AddPtg.instance, Addition.class, Addition.TOKEN},
                        {ConcatPtg.instance, Concatenation.class, Concatenation.TOKEN},
                        {DividePtg.instance, Division.class, Division.TOKEN},
                        {EqualPtg.instance, EQ.class, EQ.TOKEN},
                        {GreaterEqualPtg.instance, GTE.class, GTE.TOKEN},
                        {GreaterThanPtg.instance, GT.class, GT.TOKEN},
                        {LessEqualPtg.instance, LTE.class, LTE.TOKEN},
                        {LessThanPtg.instance, LT.class, LT.TOKEN},
                        {MultiplyPtg.instance, Multiplication.class, Multiplication.TOKEN},
                        {NotEqualPtg.instance, NEQ.class, NEQ.TOKEN},
                        {PercentPtg.instance, Percent.class, Percent.TOKEN},
                        {PowerPtg.instance, Exponentiation.class, Exponentiation.TOKEN},
                        {SubtractPtg.instance, Subtraction.class, Subtraction.TOKEN},
                        {UnaryMinusPtg.instance, Negation.class, Negation.TOKEN},
                        {UnaryPlusPtg.instance, Plus.class, Plus.TOKEN},
                        {UnionPtg.instance, Union.class, Union.TOKEN},
                        {IntersectionPtg.instance, Intersection.class, Intersection.TOKEN},
                        {FuncVarPtg.create("VLOOKUP", 3), Function.class, "VLOOKUP"}
                }
        );
    }

    @Test
    public void of_given_ptg_should_correctly_convert_ptg_to_ast() {
        Ast ast = PoiAstBuilder.of(ptg);
        assertThat(ast).isNotNull();
        assertThat(ast.getPayload()).isInstanceOf(astClazz);
        assertThat(ast.getPayload().getToken()).isEqualTo(expectedValue);
    }
}

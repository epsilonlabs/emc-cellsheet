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
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;
import org.eclipse.epsilon.labs.emc.cellsheet.Tokens;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Error;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.*;
import org.eclipse.epsilon.labs.emc.cellsheet.poi.PoiCellsheetFormulaParser;
import org.eclipse.epsilon.labs.emc.cellsheet.poi.PtgHelper;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedHashMap;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class PoiAstFactory {
    private static PoiAstFactory ourInstance = new PoiAstFactory();

    private PoiAstFactory() {
    }

    public static PoiAstFactory getInstance() {
        return ourInstance;
    }

    public Ast of(String formula, PoiCell cell) {
        PoiCellsheetFormulaParser parser = new PoiCellsheetFormulaParser(
                formula,
                cell.getBook().getFpw(),
                cell.getSheet().getSheetIndex(),
                cell.getRowIndex());

        Deque<Ast> stack = new ArrayDeque<>();

        LinkedHashMap<Ptg, String> ptgs = parser.getPtgs();
        for (Ptg ptg : ptgs.keySet()) {
            Ast current = of(ptg, ptgs.get(ptg));
            current.setEvaluator(PoiAstEvaluator.getInstance());

            if (current instanceof Unknown) continue;

            if (ptg instanceof OperationPtg) {
                for (int i = ((OperationPtg) ptg).getNumberOfOperands(); i > 0; i--) {
                    current.addChild(0, stack.pop());
                }
            }
            stack.push(current);
        }
        checkState(stack.size() == 1, "Left over Asts during construction");
        stack.peek().setCell(cell);
        return stack.pop();
    }

    public Ast of(PoiCell cell) {
        // Delegate to formula parser if we already know it's a formula
        if (cell instanceof PoiFormulaCell) return of(((PoiFormulaCell) cell).getValue(), cell);


        Ast ast = null;
        if (cell instanceof PoiBlankCell) ast = new Text("");
        if (cell instanceof PoiBooleanCell) ast = new Logical(((PoiBooleanCell) cell).getValue());
        if (cell instanceof PoiTextCell) ast = new Text(((PoiTextCell) cell).getValue());
        if (cell instanceof PoiNumericCell) ast = new Number(((PoiNumericCell) cell).getValue());
        if (cell instanceof PoiDateCell) ast = new Text(cell.getValue().toString());
        if (cell instanceof PoiErrorCell) ast = new Error(((PoiErrorCell) cell).getValue());

        checkArgument(ast != null, "Failed to build AST for %s", cell.toString());
        ast.setCell(cell);
        ast.setEvaluator(PoiAstEvaluator.getInstance());
        return ast;
    }

    public Ast of(Ptg ptg) {
        return of(ptg, null);
    }

    protected Ast of(Ptg ptg, String value) {
        Ast ast = null;
        Token token = Tokens.getToken(value == null ? PtgHelper.valueOf(ptg) : value);

        // OPERANDS
        if (ptg instanceof OperandPtg || ptg instanceof ScalarConstantPtg) {
            if (ptg instanceof IntPtg || ptg instanceof NumberPtg) ast = new Number(token);
            if (ptg instanceof StringPtg) ast = new Text(token);
            if (ptg instanceof BoolPtg) ast = new Logical(((BoolPtg) ptg).getValue());
            if (ptg instanceof AreaI) ast = new Range(token);
            if (ptg instanceof RefPtgBase) ast = new Ref(token);
        }

        // OPERATORS
        if (ptg instanceof ValueOperatorPtg) {
            try {
                final Method method = ValueOperatorPtg.class.getDeclaredMethod("getSid");
                method.setAccessible(true);
                switch ((Byte) method.invoke(ptg)) {
                    case 0x03: // Add
                        ast = new Addition();
                        break;
                    case 0x08: // Concat
                        ast = new Concatenation();
                        break;
                    case 0x06: // Divide
                        ast = new Division();
                        break;
                    case 0x0b: // Equal
                        ast = new EQ();
                        break;
                    case 0x0c: // GreaterEqual
                        ast = new GTE();
                        break;
                    case 0x0D: // GreaterThan
                        ast = new GT();
                        break;
                    case 0x0a: // LessEqual
                        ast = new LTE();
                        break;
                    case 0x09: // LessThan
                        ast = new LT();
                        break;
                    case 0x05: // Multiply
                        ast = new Multiplication();
                        break;
                    case 0x0e: // NotEqual
                        ast = new NEQ();
                        break;
                    case 0x14: // Percent
                        ast = new Percent();
                        break;
                    case 0x07: // Power
                        ast = new Exponentiation();
                        break;
                    case 0x04: // Subtract
                        ast = new Subtraction();
                        break;
                    case 0x13: // UnaryMinus
                        ast = new Negation();
                        break;
                    case 0x12: // UnaryPlus
                        ast = new Plus();
                        break;
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        if (ptg instanceof UnionPtg) ast = new Union();
        if (ptg instanceof IntersectionPtg) ast = new Intersection();

        // OPERATIONS
        if (PtgHelper.isSumPtg(ptg) || ptg instanceof AbstractFunctionPtg) ast = new Function(token);

        // NOOPS and UNKNOWNS
        if (ptg instanceof AttrPtg
                || ptg instanceof ControlPtg
                || ptg instanceof MemAreaPtg
                || ptg instanceof MemErrPtg
                || ptg instanceof MemFuncPtg)
            ast = new Noop(token);

        if (ast == null) ast = new Unknown(token);
        ast.setEvaluator(PoiAstEvaluator.getInstance());
        return ast;
    }
}

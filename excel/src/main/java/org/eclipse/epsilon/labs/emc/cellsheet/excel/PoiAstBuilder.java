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
import org.eclipse.epsilon.labs.emc.cellsheet.AstPayload;
import org.eclipse.epsilon.labs.emc.cellsheet.AstPayloads;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Error;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Logical;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Text;
import org.eclipse.epsilon.labs.emc.cellsheet.poi.PoiCellsheetFormulaParser;
import org.eclipse.epsilon.labs.emc.cellsheet.poi.PtgHelper;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType.*;

public class PoiAstBuilder {

    private PoiAstBuilder() {
    }

    public static Ast of(String formula, PoiCell cell) {
        PoiCellsheetFormulaParser parser = new PoiCellsheetFormulaParser(
                formula,
                cell.getBook().getFpw(),
                cell.getSheet().getSheetIndex(),
                cell.getRowIndex());

        Deque<Ast> stack = new ArrayDeque<>();
        for (Ptg ptg : parser.getPtgs()) {
            CellsheetType type = getCellsheetType(ptg);

            if (type == UNKNOWN || type == NOOP) continue;

            String token = parser.getTokenValue(ptg);
            AstPayload payload = AstPayloads.of(type, token);
            Ast current = new Ast(payload);

            if (ptg instanceof OperationPtg) {
                for (int i = ((OperationPtg) ptg).getNumberOfOperands(); i > 0; i--) {
                    current.addChild(0, stack.pop());
                }
            }
            stack.push(current);
        }

        checkState(stack.size() == 1, "Left over Asts during construction [formula: %s, cell: %s]", formula, cell);

        Ast ast = stack.pop();
        ast.setCell(cell);
        return ast;
    }

    public static Ast of(PoiCell cell) {
        // Delegate to formula parser if we already know it's a formula
        if (cell instanceof PoiFormulaCell)
            return of(((PoiFormulaCell) cell).getValue(), cell);

        AstPayload payload = null;

        if (cell instanceof PoiBlankCell)
            payload = new Text("");

        if (cell instanceof PoiBooleanCell)
            payload = new Logical(((PoiBooleanCell) cell).getValue());

        if (cell instanceof PoiTextCell)
            payload = new Text(((PoiTextCell) cell).getValue());

        if (cell instanceof PoiNumericCell)
            payload = new Number(((PoiNumericCell) cell).getValue());

        if (cell instanceof PoiDateCell)
            payload = new Text(cell.getValue().toString());

        if (cell instanceof PoiErrorCell)
            payload = new Error(((PoiErrorCell) cell).getValue());

        checkNotNull(payload);

        Ast ast = new Ast(payload);
        ast.setCell(cell);
        return ast;
    }

    public static Ast of(Ptg ptg, String token) {
        CellsheetType type = getCellsheetType(ptg);
        if (token == null) {
            token = PtgHelper.valueOf(ptg);
        }

        AstPayload payload = AstPayloads.of(type, token);
        Ast ast = new Ast(payload);
        return ast;
    }

    public static Ast of(Ptg ptg) {
        return of(ptg, null);
    }

    public static CellsheetType getCellsheetType(Ptg ptg) {
        // OPERANDS
        if (ptg instanceof OperandPtg || ptg instanceof ScalarConstantPtg) {
            if (ptg instanceof IntPtg || ptg instanceof NumberPtg)
                return NUMBER;
            if (ptg instanceof StringPtg) return TEXT;
            if (ptg instanceof BoolPtg) return LOGICAL;
            if (ptg instanceof AreaI) return RANGE;
            if (ptg instanceof RefPtgBase) return REF;
        }

        // OPERATORS
        if (ptg instanceof ValueOperatorPtg) {
            try {
                final Method method = ValueOperatorPtg.class.getDeclaredMethod("getSid");
                method.setAccessible(true);
                switch ((Byte) method.invoke(ptg)) {
                    case 0x03: // Add
                        return ADDITION;
                    case 0x08: // Concat
                        return CONCATENATION;
                    case 0x06: // Divide
                        return DIVISION;
                    case 0x0b: // Equal
                        return EQ;
                    case 0x0c: // GreaterEqual
                        return GTE;
                    case 0x0D: // GreaterThan
                        return GT;
                    case 0x0a: // LessEqual
                        return LTE;
                    case 0x09: // LessThan
                        return LT;
                    case 0x05: // Multiply
                        return MULTIPLICATION;
                    case 0x0e: // NotEqual
                        return NEQ;
                    case 0x14: // Percent
                        return PERCENT;
                    case 0x07: // Power
                        return EXPONENTIATION;
                    case 0x04: // Subtract
                        return SUBTRACTION;
                    case 0x13: // UnaryMinus
                        return NEGATION;
                    case 0x12: // UnaryPlus
                        return PLUS;
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        if (ptg instanceof UnionPtg) return UNION;
        if (ptg instanceof IntersectionPtg) return INTERSECTION;

        // OPERATIONS
        if (PtgHelper.isSumPtg(ptg) || ptg instanceof AbstractFunctionPtg)
            return FUNCTION;

        // NOOPS
        if (ptg instanceof AttrPtg
                || ptg instanceof ControlPtg
                || ptg instanceof MemAreaPtg
                || ptg instanceof MemErrPtg
                || ptg instanceof MemFuncPtg)
            return NOOP;

        // UNKNOWNS
        return UNKNOWN;
    }

}

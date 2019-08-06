package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.formula.ptg.*;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.Token;
import org.eclipse.epsilon.labs.emc.cellsheet.TokenFactory;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Error;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.*;
import org.eclipse.epsilon.labs.emc.cellsheet.poi.PoiCellsheetFormulaParser;
import org.eclipse.epsilon.labs.emc.cellsheet.poi.PtgHelper;

import java.lang.reflect.Method;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class PoiAstFactory {
    private static PoiAstFactory ourInstance = new PoiAstFactory();

    public static PoiAstFactory getInstance() {
        return ourInstance;
    }

    private PoiAstFactory() {
    }

    public Ast of(PoiCell cell) {
        Ast ast = null;

        if (cell instanceof PoiBlankCell) ast = new Text("");
        if (cell instanceof PoiBooleanCell) ast = new Logical(((PoiBooleanCell) cell).getValue());
        if (cell instanceof PoiTextCell) ast = new Text(((PoiTextCell) cell).getValue());
        if (cell instanceof PoiNumericCell) ast = new Number(((PoiNumericCell) cell).getValue());
        if (cell instanceof PoiDateCell) ast = new Text(cell.getValue().toString());
        if (cell instanceof PoiErrorCell) ast = new Error(((PoiErrorCell) cell).getValue());

        if (cell instanceof PoiFormulaCell) {
            PoiCellsheetFormulaParser parser = new PoiCellsheetFormulaParser(
                    ((PoiFormulaCell) cell).getValue(),
                    cell.getBook().getFpw(),
                    cell.getSheet().getSheetIndex(),
                    cell.getRowIndex());

            Deque<Ast> stack = new ArrayDeque<>();

            LinkedHashMap<Ptg, String> ptgs = parser.getPtgs();
            for (Ptg ptg : ptgs.keySet()) {
                Ast current = of(ptg, ptgs.get(ptg));
                if (current instanceof Unknown) continue;

                if (ptg instanceof OperationPtg) {
                    for (int i = ((OperationPtg) ptg).getNumberOfOperands(); i > 0; i--) {
                        current.addChild(i - 1, stack.pop());
                    }
                }
                stack.push(current);
            }

            checkState(stack.size() == 1, "Left over Asts during construction");
            ast = stack.pop();
        }

        checkArgument(ast != null, "Failed to build AST for %s", cell.toString());

        ast.setCell(cell);
        ast.setEvaluator(PoiAstEvaluator.getInstance());
        return ast;
    }

    public Ast of(Ptg ptg) {
        return of(ptg, null);
    }

    protected Ast of(Ptg ptg, String value) {
        Token token = TokenFactory.getInstance().getToken(value == null ? PtgHelper.valueOf(ptg) : value);

        // OPERANDS
        if (ptg instanceof OperandPtg || ptg instanceof ScalarConstantPtg) {
            if (ptg instanceof IntPtg || ptg instanceof NumberPtg) return new Number(token);
            if (ptg instanceof StringPtg) return new Text(token);
            if (ptg instanceof BoolPtg) return new Logical(((BoolPtg) ptg).getValue());
            if (ptg instanceof AreaI) return new Range(token);
            if (ptg instanceof RefPtgBase) return new Ref(token);
        }

        // OPERATORS
        if (ptg instanceof ValueOperatorPtg) {
            try {
                final Method method = ValueOperatorPtg.class.getDeclaredMethod("getSid");
                method.setAccessible(true);
                switch ((Byte) method.invoke(ptg)) {
                    case 0x03: // Add
                        return new Addition();
                    case 0x08: // Concat
                        return new Concatenation();
                    case 0x06: // Divide
                        return new Division();
                    case 0x0b: // Equal
                        return new EQ();
                    case 0x0c: // GreaterEqual
                        return new GTE();
                    case 0x0D: // GreaterThan
                        return new GT();
                    case 0x0a: // LessEqual
                        return new LTE();
                    case 0x09: // LessThan
                        return new LT();
                    case 0x05: // Multiply
                        return new Multiplication();
                    case 0x0e: // NotEqual
                        return new NEQ();
                    case 0x14: // Percent
                        return new Percent();
                    case 0x07: // Power
                        return new Exponentiation();
                    case 0x04: // Subtract
                        return new Subtraction();
                    case 0x13: // UnaryMinus
                        return new Negation();
                    case 0x12: // UnaryPlus
                        return new Plus();
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }

        if (ptg instanceof UnionPtg) return new Union();
        if (ptg instanceof IntersectionPtg) return new Intersection();

        // OPERATIONS
        if (PtgHelper.isSumPtg(ptg) || ptg instanceof AbstractFunctionPtg) return new Function(token);

        // NOOPS and UNKNOWNS
        if (ptg instanceof AttrPtg
                || ptg instanceof ControlPtg
                || ptg instanceof MemAreaPtg
                || ptg instanceof MemErrPtg
                || ptg instanceof MemFuncPtg)
            return new Noop(token);

        return new Unknown(token);
    }
}

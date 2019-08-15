package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.*;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.AstEval;
import org.eclipse.epsilon.labs.emc.cellsheet.Cell;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.AstEvaluator;

import static com.google.common.base.Preconditions.checkArgument;

public class PoiAstEvaluator implements AstEvaluator {

    private static PoiAstEvaluator ourInstance = new PoiAstEvaluator();


    private PoiAstEvaluator() {
    }

    public static PoiAstEvaluator getInstance() {
        return ourInstance;
    }

    @Override
    public AstEval evaluate(Ast ast) {
        // Initial checks and quick eval if possible
        checkArgument(ast.getCell() instanceof PoiCell, "Given non POI based AST to evaluate");

        switch (ast.getType()) {
            case ERROR:
            case LOGICAL:
            case TEXT:
            case UNKNOWN:
                return new AstEval(ast.getToken().getValue());
            case NUMBER:
                return new AstEval(Double.valueOf(ast.getToken().getValue()));
            case NOOP:
                return AstEval.EMPTY;
            default:
                break;
        }

        return doEval(ast.getFormula(), (PoiCell) ast.getCell());
    }

    @Override
    public AstEval evaluate(String formula, Cell cell) {
        checkArgument(cell instanceof PoiCell, "Not an instance of PoiCell");
        return doEval(formula, (PoiCell) cell);
    }

    AstEval doEval(String formula, PoiCell cell) {
        // Perform actual evaluation
        WorkbookEvaluator evaluator = cell.getBook().getInternalEvaluator();
        ValueEval result = evaluator.evaluate(formula,
                new CellReference(cell.getSheet().getSheetName(),
                        cell.getRowIndex(),
                        cell.getColIndex(),
                        true,
                        true));

        if (result instanceof ErrorEval) return new AstEval(((ErrorEval) result).getErrorString(), true);
        if (result instanceof NumberEval) return new AstEval(((NumberEval) result).getNumberValue());

        if (result instanceof RefEval) {
            RefEval cast = (RefEval) result;
            return new AstEval(cell
                    .getBook()
                    .getSheet(cast.getFirstSheetIndex())
                    .getRow(cast.getRow())
                    .getCell(cast.getColumn())
                    .getId()
            );
        }

        if (result instanceof StringValueEval) {
            return new AstEval(((StringValueEval) result).getStringValue());
        }

        throw new AssertionError("Unknown ValueEval given: " + result);
    }
}

package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.*;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.AstEval;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.AstEvaluator;

import static com.google.common.base.Preconditions.checkArgument;
import static org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType.FORMULA_CELL;

public class PoiAstEvaluator implements AstEvaluator {

    private static PoiAstEvaluator ourInstance = new PoiAstEvaluator();


    public static PoiAstEvaluator getInstance() {
        return ourInstance;
    }

    private PoiAstEvaluator() {
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

        PoiCell cell = (PoiCell) ast.getCell();

        // Perform actual evaluation
        WorkbookEvaluator evaluator = cell.getBook().getInternalEvaluator();
        ValueEval result = evaluator.evaluate(ast.getFormula(),
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
                    .getA1()
            );
        }

        if (result instanceof StringValueEval) {
            return new AstEval(((StringValueEval) result).getStringValue());
        }

        throw new AssertionError("Unknown ValueEval given: " + result);
    }

}

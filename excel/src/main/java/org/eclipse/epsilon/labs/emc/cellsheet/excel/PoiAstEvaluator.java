package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.*;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.CellValue;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.AstEvaluator;

import static com.google.common.base.Preconditions.checkArgument;

public class PoiAstEvaluator implements AstEvaluator {

    private static PoiAstEvaluator ourInstance = new PoiAstEvaluator();

    public static PoiAstEvaluator getInstance() {
        return ourInstance;
    }

    private PoiAstEvaluator() {
    }

    @Override
    public CellValue evaluate(Ast ast) {
        // Initial checks and quick eval if possible
        checkArgument(ast.getCell() instanceof PoiCell, "Given non POI based AST to evaluate");
        PoiCell cell = (PoiCell) ast.getCell();
        CellValue value = new CellValue();

        if (cell.getType() != CellsheetType.FORMULA_CELL) {
            value.setStrValue(cell.getValue().toString());
            return value;
        }

        // Perform actual evaluation
        WorkbookEvaluator evaluator = cell.getBook().getInternalEvaluator();
        ValueEval result = evaluator.evaluate(ast.getFormula(),
                new CellReference(cell.getSheet().getSheetName(),
                        cell.getRowIndex(),
                        cell.getColIndex(),
                        true,
                        true));

        if (result instanceof ErrorEval) {
            value.setHasError(true);
            value.setStrValue(((ErrorEval) result).getErrorString());
            return value;
        }

        if (result instanceof NumberEval) {
            value.setNumValue(((NumberEval) result).getNumberValue());
            return value;
        }

        if (result instanceof RefEval) {
            RefEval cast = (RefEval) result;
            value.setStrValue(cell
                    .getBook()
                    .getSheet(cast.getFirstSheetIndex())
                    .getRow(cast.getRow())
                    .getCell(cast.getColumn())
                    .getA1()
            );
            return value;
        }

        if (result instanceof StringValueEval) {
            value.setStrValue(((StringValueEval) result).getStringValue());
            return value;
        }

        throw new AssertionError("Unknown ValueEval given: " + result);
    }

}

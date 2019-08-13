package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.AstEval;
import org.eclipse.epsilon.labs.emc.cellsheet.Cell;

public interface AstEvaluator {

    default AstEval evaluate(Cell cell) {
        return evaluate(cell.getRoot());
    }

    AstEval evaluate(Ast ast);

    AstEval evaluate(String formula, Cell cell);
}

package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.CellValue;

public interface AstEvaluator {

    CellValue evaluate(Ast ast);

}

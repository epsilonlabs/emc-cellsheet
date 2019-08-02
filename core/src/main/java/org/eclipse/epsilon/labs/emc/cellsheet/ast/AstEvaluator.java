package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;

public interface AstEvaluator {

    String evaluate(Ast ast);

    <T> T evaluate(Ast ast, Class<T> type);
}

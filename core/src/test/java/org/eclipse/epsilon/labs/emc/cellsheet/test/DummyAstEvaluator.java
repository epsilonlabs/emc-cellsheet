/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.test;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.AstEval;
import org.eclipse.epsilon.labs.emc.cellsheet.Cell;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.AstEvaluator;

public class DummyAstEvaluator implements AstEvaluator {

    public static final String EVAL_RESULT = "Passed";

    @Override
    public AstEval evaluate(Ast ast) {
        return new AstEval(EVAL_RESULT);
    }

    @Override
    public AstEval evaluate(String formula, Cell cell) {
        return new AstEval(EVAL_RESULT);
    }
}
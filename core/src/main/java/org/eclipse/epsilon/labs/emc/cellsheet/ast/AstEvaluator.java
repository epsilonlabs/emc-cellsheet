/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.ast;

import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.AstEval;
import org.eclipse.epsilon.labs.emc.cellsheet.Cell;

/**
 * Evaluator for ASTs
 * <p>
 * Implementations should be specific to a particular formula evaluator
 * </p>
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public interface AstEvaluator {

    default AstEval evaluate(Cell cell) {
        return evaluate(cell.getRoot());
    }

    AstEval evaluate(Ast ast);

    AstEval evaluate(String formula, Cell cell);
}

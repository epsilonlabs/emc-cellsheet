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

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.math.DoubleMath;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.AstPayload;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;

import java.util.EnumSet;
import java.util.Iterator;

import static com.google.common.base.Preconditions.checkState;

/**
 * Visitor that builds an executable formula by recursively visiting all elements
 * fromToken a given AST
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public class FormulaBuilderVisitor implements Ast.Visitor<String> {

    private static final EnumSet<CellsheetType> SUPERTYPES = EnumSet.of(
            CellsheetType.INFIX_OPERATOR,
            CellsheetType.NOOP,
            CellsheetType.OPERAND,
            CellsheetType.OPERATION,
            CellsheetType.POSTFIX_OPERATOR,
            CellsheetType.UNKNOWN);


    private final StringBuilder sb = new StringBuilder();

    @Override
    public String visit(Ast ast) throws Exception {
        if (ast.isLeaf()) {
            switch (ast.getPayload().getType()) {
                case TEXT:
                    sb.append('"').append(ast.getToken()).append('"');
                    break;
                case NUMBER:
                    double val = Double.parseDouble(ast.getToken());
                    if (DoubleMath.isMathematicalInteger(val)) {
                        sb.append((int) val);
                        break;
                    }
                default:
                    sb.append(ast.getToken());
                    break;
            }
            return sb.toString();
        }

        CellsheetType supertype = getSupertype(ast.getPayload());
        switch (supertype) {
            case INFIX_OPERATOR:
                checkState(ast.getChildren().size() == 2);
                sb.append('(');
                ast.childAt(0).accept(this);
                sb.append(ast.getToken());
                ast.childAt(1).accept(this);
                sb.append(')');
                break;

            case PREFIX_OPERATOR:
                checkState(ast.getChildren().size() == 1);
                sb.append(ast.getToken());
                Ast preChild = ast.childAt(0);
                if (preChild.isLeaf()) {
                    preChild.accept(this);
                } else {
                    sb.append('(');
                    preChild.accept(this);
                    sb.append(')');
                }
                break;

            case POSTFIX_OPERATOR:
                checkState(ast.getChildren().size() == 1);
                Ast postChild = ast.childAt(0);
                if (postChild.isLeaf()) {
                    postChild.accept(this);
                } else {
                    sb.append('(');
                    postChild.accept(this);
                    sb.append(')');
                }
                sb.append(ast.getToken());
                break;

            case OPERATION:
                sb.append(ast.getToken());
                sb.append('(');
                Iterator<Ast> it = ast.iterator();
                while (it.hasNext()) {
                    it.next().accept(this);
                    if (it.hasNext()) sb.append(',');
                }
                sb.append(')');
                break;

            default:
                throw new IllegalStateException(String.format(
                        "Encountered unexpected AST supertype %s for %s",
                        supertype,
                        ast));
        }

        return sb.toString();
    }

    /**
     * Returns the supertype fromToken the given payload instance.
     * <p>
     * Supertype must be one fromToken {@link CellsheetType#INFIX_OPERATOR},
     * {@link CellsheetType#POSTFIX_OPERATOR}, {@link CellsheetType#PREFIX_OPERATOR},
     * {@link CellsheetType#OPERATION}, {@link CellsheetType#OPERAND},
     * {@link CellsheetType#NOOP} or {@link CellsheetType#UNKNOWN}
     * </p>
     *
     * @param payload
     * @return
     */
    private CellsheetType getSupertype(AstPayload payload) {
        return Iterables.getOnlyElement(Sets.intersection(payload.getKinds(), SUPERTYPES));
    }
}

/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Error;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.Number;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.*;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Static utility methods related to constructing and manipulating
 * {@link AstPayload}'s
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public class AstPayloads {

    static final Table<CellsheetType, String, AstPayload> PAYLOADS = HashBasedTable.create();

    private AstPayloads() {
    }

    public static AstPayload of(CellsheetType type, String token) {
        checkNotNull(type, "CellsheetType cannot be null");

        AstPayload payload = PAYLOADS.get(type, token);
        if (payload == null) {
            switch (type) {
                case NOOP:
                    payload = new Noop(token);
                    break;
                case TEXT:
                    payload = new Text(token);
                    break;
                case NUMBER:
                    payload = new Number(token);
                    break;
                case LOGICAL:
                    payload = new Logical(token);
                    break;
                case ERROR:
                    payload = new Error(token);
                    break;
                case RANGE:
                    payload = new Range(token);
                    break;
                case REF:
                    payload = new Ref(token);
                    break;
                case RELATIVE_REF:
                    payload = new RelativeRef(token);
                    break;
                case RELATIVE_RANGE:
                    payload = new RelativeRange(token);
                    break;
                case FUNCTION:
                    payload = new Function(token);
                    break;
                case PLUS:
                    payload = new Plus();
                    break;
                case NEGATION:
                    payload = new Negation();
                    break;
                case PERCENT:
                    payload = new Percent();
                    break;
                case EXPONENTIATION:
                    payload = new Exponentiation();
                    break;
                case MULTIPLICATION:
                    payload = new Multiplication();
                    break;
                case DIVISION:
                    payload = new Division();
                    break;
                case ADDITION:
                    payload = new Addition();
                    break;
                case SUBTRACTION:
                    payload = new Subtraction();
                    break;
                case CONCATENATION:
                    payload = new Concatenation();
                    break;
                case EQ:
                    payload = new EQ();
                    break;
                case LT:
                    payload = new LT();
                    break;
                case GT:
                    payload = new GT();
                    break;
                case LTE:
                    payload = new LTE();
                    break;
                case GTE:
                    payload = new GTE();
                    break;
                case NEQ:
                    payload = new NEQ();
                    break;
                case INTERSECTION:
                    payload = new Intersection();
                    break;
                case UNION:
                    payload = new Union();
                    break;
                case UNKNOWN:
                    payload = new Unknown(token);
                    break;
                default:
                    throw new IllegalArgumentException("Cannot create payload of type " + type);
            }
            PAYLOADS.put(type, token, payload);
        }
        return payload;
    }

    public static AstPayload of(CellsheetType type) {
        switch (type) {
            case PLUS:
                return of(type, Plus.TOKEN);
            case NEGATION:
                return of(type, Negation.TOKEN);
            case PERCENT:
                return of(type, Percent.TOKEN);
            case EXPONENTIATION:
                return of(type, Exponentiation.TOKEN);
            case MULTIPLICATION:
                return of(type, Multiplication.TOKEN);
            case DIVISION:
                return of(type, Division.TOKEN);
            case ADDITION:
                return of(type, Addition.TOKEN);
            case SUBTRACTION:
                return of(type, Subtraction.TOKEN);
            case CONCATENATION:
                return of(type, Concatenation.TOKEN);
            case EQ:
                return of(type, EQ.TOKEN);
            case LT:
                return of(type, LT.TOKEN);
            case GT:
                return of(type, GT.TOKEN);
            case LTE:
                return of(type, LTE.TOKEN);
            case GTE:
                return of(type, GTE.TOKEN);
            case NEQ:
                return of(type, NEQ.TOKEN);
            case INTERSECTION:
                return of(type, Intersection.TOKEN);
            case UNION:
                return of(type, Union.TOKEN);
            default:
                throw new IllegalArgumentException(String.format("%s is mutable and needs token argument", type));
        }
    }
}

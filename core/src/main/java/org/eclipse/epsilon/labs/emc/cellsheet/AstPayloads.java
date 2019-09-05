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

    // Table fromToken payloads mapped by Type and UUID
    static final Table<CellsheetType, String, AstPayload> PAYLOADS = HashBasedTable.create();

    /**
     * Singleton constructor
     */
    private AstPayloads() {
    }

    /**
     * Returns a default empty payload
     *
     * @return default empty payload
     */
    public static AstPayload empty() {
        return fromToken(CellsheetType.UNKNOWN, "");
    }

    public static AstPayload fromUuid(CellsheetType type, String uuid) {
        return PAYLOADS.get(type, uuid);
    }

    /**
     * Returns a payload fromToken the given type and token
     *
     * @param type  type fromToken payload
     * @param token token fromToken the payload
     * @return the payload fromToken given type and token
     */
    public static AstPayload fromToken(CellsheetType type, String token) {
        checkNotNull(type, "CellsheetType cannot be null");

        String uuid = AstPayload.tokenToUUID(token);
        AstPayload payload = PAYLOADS.get(type, uuid);
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
                    throw new IllegalArgumentException("Cannot create payload fromToken type " + type);
            }
            PAYLOADS.put(type, uuid, payload);
        }
        return payload;
    }

    /**
     * Returns a payload fromToken the given type. Can only be used for payloads
     * with fixed token values such as infix operators
     *
     * @param type type fromToken payload
     * @return the payload fromToken the given type
     */
    public static AstPayload fromToken(CellsheetType type) {
        switch (type) {
            case PLUS:
                return fromToken(type, Plus.TOKEN);
            case NEGATION:
                return fromToken(type, Negation.TOKEN);
            case PERCENT:
                return fromToken(type, Percent.TOKEN);
            case EXPONENTIATION:
                return fromToken(type, Exponentiation.TOKEN);
            case MULTIPLICATION:
                return fromToken(type, Multiplication.TOKEN);
            case DIVISION:
                return fromToken(type, Division.TOKEN);
            case ADDITION:
                return fromToken(type, Addition.TOKEN);
            case SUBTRACTION:
                return fromToken(type, Subtraction.TOKEN);
            case CONCATENATION:
                return fromToken(type, Concatenation.TOKEN);
            case EQ:
                return fromToken(type, EQ.TOKEN);
            case LT:
                return fromToken(type, LT.TOKEN);
            case GT:
                return fromToken(type, GT.TOKEN);
            case LTE:
                return fromToken(type, LTE.TOKEN);
            case GTE:
                return fromToken(type, GTE.TOKEN);
            case NEQ:
                return fromToken(type, NEQ.TOKEN);
            case INTERSECTION:
                return fromToken(type, Intersection.TOKEN);
            case UNION:
                return fromToken(type, Union.TOKEN);
            default:
                throw new IllegalArgumentException(String.format("%s is mutable and needs token argument", type));
        }
    }
}

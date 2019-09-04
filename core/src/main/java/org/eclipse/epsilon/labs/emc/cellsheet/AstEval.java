/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;

/**
 * Model Type representing the result from evaluating an {@link Ast}.
 * <p>Instances should be constructed using {@link AstEvals} utility methods</p>
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public class AstEval {

    public static final AstEval EMPTY = new AstEval(null, null, null, null, null);

    private final String text;
    private final Double number;
    private final Boolean bool;
    private final Cell ref;
    private final String error;

    AstEval(String text, Double number, Boolean bool, Cell ref, String error) {
        this.text = text;
        this.number = number;
        this.bool = bool;
        this.ref = ref;
        this.error = error;
    }

    public String getText() {
        if (isRef() && ref.getType() == CellsheetType.TEXT_CELL)
            return ((TextCell) ref).getValue();
        return text;
    }

    public Double getNumber() {
        if (isRef() && ref.getType() == CellsheetType.NUMERIC_CELL)
            return ((NumericCell) ref).getValue();
        return number;
    }

    public Boolean getBoolean() {
        return bool == null ? false : bool;
    }

    public Cell getRef() {
        return ref;
    }

    public String getError() {
        return error;
    }

    public boolean isText() {
        return text != null;
    }

    public boolean isNumber() {
        return number != null;
    }

    public boolean isBoolean() {
        return bool != null;
    }

    public boolean isRef() {
        return ref != null;
    }

    public boolean isError() {
        return error != null;
    }

    @Override
    public String toString() {
        if (isText()) return text;
        if (isNumber()) return number.toString();
        if (isBoolean()) return bool.toString();
        if (isRef()) return ref.getId();
        if (isError()) return error;
        return "";
    }

}

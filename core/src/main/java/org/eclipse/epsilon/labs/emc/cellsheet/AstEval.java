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

import java.util.Optional;

/**
 * Model Type representing the result from evaluating an AST
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public class AstEval {

    /**
     * Empty evaluation
     */
    public static final AstEval EMPTY = new AstEval();

    private Optional<String> text = Optional.empty();
    private Optional<Double> number = Optional.empty();
    private boolean isError = false;

    /**
     * Private constructor for empty evals, use {@link #EMPTY} instead
     */
    private AstEval() {
        this.text = Optional.of("");
    }

    /**
     * Constructor for eval holding a double
     *
     * @param value the value
     */
    public AstEval(double value) {
        this.number = Optional.of(value);
    }

    /**
     * Constructor a text result the could represent an error
     *
     * @param value   the value as a string
     * @param isError if the string represents an error
     */
    public AstEval(String value, boolean isError) {
        this.text = Optional.of(value == null ? "" : value);
        this.isError = isError;
    }

    /**
     * Constructor a text result
     *
     * @param value the value
     */
    public AstEval(String value) {
        this(value, false);
    }

    /**
     * Retrieve the value as a string. If {@link #isNumber} is {@code true} then
     * performs a conversion.
     *
     * @return the held value as a string
     */
    public String getText() {
        return toString();
    }

    /**
     * Retrieve the numeric value or 0.0 if {@link #isNumber()} is {@code false}
     *
     * @return the double value or 0.0 if {@link #isNumber()} is {@code false}
     */
    public double getNumber() {
        return number.orElse(0.0);
    }

    /**
     * @return {@code true} if this is not an error or number
     */
    public boolean isText() {
        return !isError && text.isPresent();
    }

    /**
     * @return {@code true} if this is not an error or text
     */
    public boolean isNumber() {
        return number.isPresent();
    }

    /**
     * @return {@code true} if this is an error
     */
    public boolean isError() {
        return isError;
    }

    @Override
    public String toString() {
        return number.isPresent()
                ? Double.toString(number.get())
                : text.orElse("");
    }

}

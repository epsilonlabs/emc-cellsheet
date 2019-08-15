/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

package org.eclipse.epsilon.labs.emc.cellsheet.poi;

/**
 * Cloned from org.apache.poi.ss.formula.FormulaParseException
 * <p>
 * This exception thrown when a supplied formula has incorrect syntax (or syntax
 * currently not supported by POI). It is primarily used by test code to confirm
 * specific parsing exceptions. Application code should also handle this
 * exception if it potentially supplies formula text that is not guaranteed to
 * be well-formed.
 *
 * @author Josh Micich
 */
@SuppressWarnings("serial")
public final class FormulaParseException extends RuntimeException {

    public FormulaParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormulaParseException(Throwable cause) {
        super(cause);
    }

    public FormulaParseException(String msg) {
        super(msg);
    }
}

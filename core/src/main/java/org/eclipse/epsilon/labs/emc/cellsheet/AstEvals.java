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

import com.google.common.base.Strings;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Static utility methods for constructing and manipulating {@link AstEval}s.
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public class AstEvals {

    private AstEvals() {
    }

    public static AstEval empty() {
        return AstEval.EMPTY;
    }

    public static AstEval of(String text) {
        checkArgument(!Strings.isNullOrEmpty(text));
        return new AstEval(text, null, null, null, null);
    }

    public static AstEval of(double number) {
        return new AstEval(null, number, null, null, null);
    }

    public static AstEval of(boolean bool) {
        return new AstEval(null, null, bool, null, null);
    }

    public static AstEval of(Cell ref) {
        checkNotNull(ref);
        return new AstEval(null, null, null, ref, null);
    }

    public static AstEval error(String error) {
        checkArgument(!Strings.isNullOrEmpty(error));
        return new AstEval(null, null, null, null, error);
    }


}

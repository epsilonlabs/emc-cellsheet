/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.ast;

public class Visitors {

    private Visitors() {
    }

    public static DepthFirstPreOrderVisitor.DepthFirstPreOrderVisitorBuilder depthFirstPreOrder() {
        return new DepthFirstPreOrderVisitor.DepthFirstPreOrderVisitorBuilder();
    }
}

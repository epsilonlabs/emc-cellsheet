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

import java.util.EnumSet;
import java.util.Set;

public interface HasA1 extends HasId {

    String UNASSIGNED = "*unassigned*";

    default String getA1() {
        return UNASSIGNED;
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.HAS_A1, CellsheetType.HAS_ID);
    }
}
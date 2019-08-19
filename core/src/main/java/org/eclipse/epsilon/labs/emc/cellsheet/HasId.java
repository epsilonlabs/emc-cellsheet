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
import java.util.Iterator;
import java.util.Set;

public interface HasId extends Iterable {

    /**
     * Returns a URI style String that can be used to uniquely identify
     * the model element instance.
     *
     * {@code null} will be returned if the model element is not associated with
     * a model i.e. the element is dangling
     *
     * @return Unique identifier for this model element or {@code null} if the
     * model element is not associated with a dangling model.
     */
    default String getId() {
        return null;
    }

    CellsheetType getType();

    @Override
    Iterator<? extends HasId> iterator();

    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.HAS_ID);
    }
}
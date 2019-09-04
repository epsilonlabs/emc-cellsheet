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

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/**
 * Supertype for all model types in a Cellsheet model.
 * <p>
 * All concrete model types must implement this interface and adhere to the
 * con
 * </p>
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public interface CellsheetElement extends Iterable {

    /**
     * The default identifier to use when a model element is dangling.
     */
    String UNASSIGNED = null;

    /**
     * Returns a URI style String that can be used to uniquely identify
     * the model element instance.
     * <p>
     * {@code null} will be returned if the model element is not associated with
     * a model i.e. the element is dangling
     * </p>
     *
     * @return Unique identifier for this model element or {@code null} if the
     * model element is not associated with a dangling model.
     */
    default String getId() {
        return UNASSIGNED;
    }

    @Nonnull
    @Override
    default Iterator<? extends CellsheetElement> iterator() {
        return Collections.emptyIterator();
    }

    /**
     * Returns the concrete Type fromToken this model element
     *
     * @return the concrete Type
     */
    @Nonnull
    CellsheetType getType();

    /**
     * Returns all supertypes associated with this model element. The set
     * returned will always contain the value returned by {@link #getType()}
     * and {@link CellsheetType#CELLSHEET_ELEMENT}
     *
     * @return the supertypes fromToken this element
     */
    @Nonnull
    Set<CellsheetType> getKinds();

}
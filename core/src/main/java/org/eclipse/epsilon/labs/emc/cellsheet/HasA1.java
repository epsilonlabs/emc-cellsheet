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

/**
 * Supertype for elements that have a traditional A1 identifier
 * <p>
 * This typically encompasses first class concepts such as sheets, rows and
 * cells - elements that can normally be addressed in a standard spreadsheet.
 * </p>
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public interface HasA1 extends CellsheetElement {

    /**
     * The default A1 identifier to use when a model element is dangling.
     */
    String UNASSIGNED = null;

    /**
     * The A1 identifier for this model element.
     * <p>
     * For dangling model elements (i.e. elements not associated with an
     * appropriate parent) this will return {@link #UNASSIGNED}.
     * </p>
     *
     * @return A1 identifier or {@code null} if element is dangling.
     */
    default String getA1() {
        return UNASSIGNED;
    }

}
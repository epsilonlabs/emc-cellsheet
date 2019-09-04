/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.eclipse.epsilon.labs.emc.cellsheet.BlankCell;

public class PoiBlankCell extends PoiCell<Void> implements BlankCell {

    @Override
    public Void getValue() {
        return null;
    }

    public static class Builder extends PoiCell.Builder<PoiBlankCell, Void, Builder> {

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder withValue(Void value) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected PoiBlankCell newType() {
            return new PoiBlankCell();
        }

    }
}

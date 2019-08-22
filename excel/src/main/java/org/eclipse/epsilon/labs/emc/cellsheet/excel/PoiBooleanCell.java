/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.eclipse.epsilon.labs.emc.cellsheet.BooleanCell;

public class PoiBooleanCell extends PoiCell<Boolean> implements BooleanCell {

    public static class Builder extends PoiCell.Builder<PoiBooleanCell, Boolean, Builder> {

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder withValue(Boolean value) {
            this.value = value;
            return self();
        }

        @Override
        protected PoiBooleanCell newType() {
            return new PoiBooleanCell();
        }
    }

}

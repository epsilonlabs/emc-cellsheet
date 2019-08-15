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

import org.eclipse.epsilon.labs.emc.cellsheet.NumericCell;

public class PoiNumericCell extends PoiCell<Double> implements NumericCell {

    public static class Builder extends PoiCell.Builder<PoiNumericCell, Double, Builder> {

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder withValue(Double value) {
            this.value = value;
            return self();
        }

        @Override
        protected PoiNumericCell newType() {
            return new PoiNumericCell();
        }
    }

}

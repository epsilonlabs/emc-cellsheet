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

import org.eclipse.epsilon.labs.emc.cellsheet.ErrorCell;

public class PoiErrorCell extends PoiCell<String> implements ErrorCell {

    public static class Builder extends PoiCell.Builder<PoiErrorCell, String, Builder> {

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder withValue(String value) {
            this.value = value;
            return self();
        }

        @Override
        protected PoiErrorCell newType() {
            return new PoiErrorCell();
        }
    }
}

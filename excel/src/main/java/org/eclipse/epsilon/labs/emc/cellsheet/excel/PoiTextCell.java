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

import org.eclipse.epsilon.labs.emc.cellsheet.TextCell;

public class PoiTextCell extends PoiCell<String> implements TextCell {

    public static class Builder extends PoiCell.Builder<PoiTextCell, String, Builder> {

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
        protected PoiTextCell newType() {
            return new PoiTextCell();
        }
    }

}

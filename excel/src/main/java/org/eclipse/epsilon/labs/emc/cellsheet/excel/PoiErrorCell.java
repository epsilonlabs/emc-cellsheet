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

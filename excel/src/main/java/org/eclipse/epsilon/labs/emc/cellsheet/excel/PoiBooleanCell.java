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

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

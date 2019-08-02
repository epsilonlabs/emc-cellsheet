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

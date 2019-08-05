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

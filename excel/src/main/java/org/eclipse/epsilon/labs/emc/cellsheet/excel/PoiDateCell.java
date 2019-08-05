package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.eclipse.epsilon.labs.emc.cellsheet.DateCell;

import java.util.Date;

public class PoiDateCell extends PoiCell<Date> implements DateCell {

    public static class Builder extends PoiCell.Builder<PoiDateCell, Date, Builder> {

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder withValue(Date value) {
            this.value = value;
            return self();
        }

        @Override
        protected PoiDateCell newType() {
            return new PoiDateCell();
        }
    }
}

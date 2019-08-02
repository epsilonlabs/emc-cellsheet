package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.eclipse.epsilon.labs.emc.cellsheet.FormulaCell;

public class PoiFormulaCell extends PoiCell<String> implements FormulaCell {

    public static class Builder extends PoiCell.Builder<PoiFormulaCell, String, Builder> {

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
        protected PoiFormulaCell newType() {
            return new PoiFormulaCell();
        }
    }

}

package org.eclipse.epsilon.labs.emc.cellsheet;

public interface NumericCell extends Cell<Double> {

    @Override
    default CellsheetType getType() {
        return CellsheetType.NUMERIC_CELL;
    }
}
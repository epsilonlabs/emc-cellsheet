package org.eclipse.epsilon.labs.emc.cellsheet;

public interface BooleanCell extends Cell<Boolean> {

    @Override
    default CellsheetType getType() {
        return CellsheetType.BOOLEAN_CELL;
    }
}
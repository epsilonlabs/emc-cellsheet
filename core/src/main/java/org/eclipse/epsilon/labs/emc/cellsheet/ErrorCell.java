package org.eclipse.epsilon.labs.emc.cellsheet;

public interface ErrorCell extends Cell<String> {

    @Override
    default CellsheetType getType() {
        return CellsheetType.ERROR_CELL;
    }
}

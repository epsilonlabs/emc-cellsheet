package org.eclipse.epsilon.labs.emc.cellsheet;

public interface BlankCell extends Cell<Void> {

    @Override
    default Void getValue() {
        return null;
    }

    @Override
    default CellsheetType getType() {
        return CellsheetType.BLANK_CELL;
    }
}
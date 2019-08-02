package org.eclipse.epsilon.labs.emc.cellsheet;

public interface TextCell extends Cell<String> {

    @Override
    default CellsheetType getType() {
        return CellsheetType.TEXT_CELL;
    }
}
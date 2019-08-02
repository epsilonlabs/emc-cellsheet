package org.eclipse.epsilon.labs.emc.cellsheet;

public interface FormulaCell extends Cell<String> {

    @Override
    default CellsheetType getType() {
        return CellsheetType.FORMULA_CELL;
    }
}
package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Date;

public interface DateCell extends Cell<Date> {

    @Override
    default CellsheetType getType() {
        return CellsheetType.DATE_CELL;
    }
}
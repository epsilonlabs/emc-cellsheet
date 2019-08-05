package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

public interface CellFormat extends HasId {

    Book getBook();

    String getValue();

    @Override
    Iterator<Void> iterator();

    @Override
    default String getId() {
        return getBook().getId() + "/cellFormats/" + hashCode(); // TODO: Should not rely on this
    }

    @Override
    default CellsheetType getType() {
        return CellsheetType.CELL_FORMAT;
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.CELL_FORMAT, CellsheetType.HAS_ID);
    }
}
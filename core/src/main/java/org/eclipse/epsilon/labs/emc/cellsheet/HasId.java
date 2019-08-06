package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.EnumSet;
import java.util.Set;

public interface HasId extends Iterable {

    String getId();

    CellsheetType getType();

    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.HAS_ID);
    }
}
package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.EnumSet;
import java.util.Set;

public interface HasA1 extends HasId {

    String getA1();

    @Override
    default CellsheetType getType() {
        return CellsheetType.HAS_A1;
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.HAS_A1, CellsheetType.HAS_ID);
    }
}
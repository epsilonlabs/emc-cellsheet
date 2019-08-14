package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.EnumSet;
import java.util.Set;

public interface HasA1 extends HasId {

    String UNASSIGNED = "*unassigned*";

    default String getA1() {
        return UNASSIGNED;
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.HAS_A1, CellsheetType.HAS_ID);
    }
}
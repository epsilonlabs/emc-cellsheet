package org.eclipse.epsilon.labs.emc.cellsheet;

import java.lang.reflect.ParameterizedType;

public interface CellsheetBuilder {

    default String getClassName() {
        return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]).getName();
    }
}

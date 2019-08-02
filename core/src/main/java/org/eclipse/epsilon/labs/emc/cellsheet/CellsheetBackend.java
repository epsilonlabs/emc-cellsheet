package org.eclipse.epsilon.labs.emc.cellsheet;

public interface CellsheetBackend<T extends Book.Builder> {

    boolean isApplicable(String modelUri);

    T getBuilder();
}

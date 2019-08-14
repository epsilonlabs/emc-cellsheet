package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface Cell<T> extends HasA1 {

    default Book getBook() {
        return getRow().getBook();
    }

    default Sheet getSheet() {
        return getRow().getSheet();
    }

    Row getRow();

    int getColIndex();

    default String getA1ColIndex() {
        return ReferenceUtil.indexToA1(getColIndex());
    }

    default int getRowIndex() {
        return getRow().getRowIndex();
    }

    default int getA1RowIndex() {
        return getRow().getA1RowIndex();
    }

    T getValue();

    List<Ast> getAsts();

    default Ast getRoot() {
        return getAsts().get(0);
    }

    default void addAst(Ast ast) {
        getAsts().add(ast);
        ast.setPosition(getAsts().size() - 1);
        ast.setCell(this);
    }

    @Override
    Iterator<HasId> iterator();

    @Override
    default String getA1() {
        if (getSheet() == null || getRow() == null || getColIndex() < 0) return HasA1.super.getA1();
        return getSheet().getA1() + "!" + getA1ColIndex() + getA1RowIndex();
    }

    @Override
    default String getId() {
        return (getRow() == null ? HasA1.super.getId() : getRow().getId())
                + "/"
                + getColIndex();
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.CELL, CellsheetType.HAS_A1, CellsheetType.HAS_ID);
    }

    interface Builder<T extends Cell<V>, V, B extends Builder<T, V, B>> extends CellsheetBuilder {

        B self();

        B withRow(Row row);

        B withColIndex(int colIndex);

        B withValue(V value);

        T build();
    }
}
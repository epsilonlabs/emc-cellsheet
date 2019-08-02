package org.eclipse.epsilon.labs.emc.cellsheet;

import java.lang.reflect.ParameterizedType;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public interface Sheet extends HasA1 {

    Book getBook();

    Row getRow(int rowIndex);

    String getSheetName();

    int getSheetIndex();

    List<Row> getRows();

    @Override
    Iterator<Row> iterator();

    @Override
    default String getA1() {
        return getBook().getA1() + "'" + getSheetName() + "'";
    }

    @Override
    default String getId() {
        return getBook().getId() + "/" + getSheetIndex();
    }

    @Override
    default CellsheetType getType() {
        return CellsheetType.SHEET;
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.SHEET, CellsheetType.HAS_A1, CellsheetType.HAS_ID);
    }

    interface Builder<T extends Sheet, B extends Builder<T, B>> {

        B self();

        default String getClassName() {
            return ((Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]).getName();
        }

        B withBook(Book book);

        B withSheetName(String sheetName);

        B withSheetIndex(int sheetIndex);

        T build();
    }
}
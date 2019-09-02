/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.eclipse.epsilon.labs.emc.cellsheet.*;

import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class PoiCell<T> extends AbstractCell<T> implements PoiDelegate<org.apache.poi.ss.usermodel.Cell> {

    @Override
    public PoiBook getBook() {
        return (PoiBook) super.getBook();
    }

    @Override
    public PoiSheet getSheet() {
        return (PoiSheet) super.getSheet();
    }

    @Override
    public PoiRow getRow() {
        return (PoiRow) row;
    }

    @Override
    protected Ast buildRoot() {
        return PoiAstBuilder.of(this);
    }

    @Override
    public org.apache.poi.ss.usermodel.Cell getDelegate() {
        return ((PoiRow) row).getDelegate().getCell(colIndex);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("row", row.getId())
                .add("colIndex", colIndex)
                .add("value", getValue())
                .add("type", getType().getTypeName())
                .add("kinds", getKinds().stream().map(CellsheetType::getTypeName).collect(Collectors.joining(",")))
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoiCell<?> poiCell = (PoiCell<?>) o;
        return getColIndex() == poiCell.getColIndex() &&
                Objects.equal(getValue(), poiCell.getValue()) &&
                Objects.equal(getRow(), poiCell.getRow());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getValue(), getRow(), getColIndex());
    }

    public static abstract class Builder<T extends PoiCell<V>, V, B> implements Cell.Builder<T, V, Builder<T, V, B>> {
        protected V value;
        PoiRow row;
        int colIndex;

        @Override
        public Builder<T, V, B> withRow(Row row) {
            checkArgument(row instanceof PoiRow, "Must be instance of %s", PoiRow.class.getCanonicalName());
            this.row = (PoiRow) row;
            return self();
        }

        @Override
        public Builder<T, V, B> withColIndex(int colIndex) {
            this.colIndex = colIndex;
            return self();
        }

        protected abstract T newType();

        @Override
        public T build() {
            T cell = newType();
            cell.value = value;
            cell.row = row;
            cell.colIndex = colIndex;
            return cell;
        }
    }

}

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
import org.eclipse.epsilon.labs.emc.cellsheet.*;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class PoiCell<T> implements Cell<T>, PoiDelegate<org.apache.poi.ss.usermodel.Cell> {

    protected T value;
    protected PoiRow row;
    protected int colIndex;
    protected List<Ast> asts = new ArrayList<>(1);

    @Override
    public PoiBook getBook() {
        return getSheet().getBook();
    }

    @Override
    public PoiSheet getSheet() {
        return getRow().getSheet();
    }

    @Override
    public PoiRow getRow() {
        return row;
    }

    @Override
    public int getColIndex() {
        return colIndex;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public List<Ast> getAsts() {
        return asts;
    }

    @Override
    public Ast getRoot() {
        if (asts.isEmpty()) {
            addAst(PoiAstFactory.getInstance().of(this));
        }
        return asts.get(0);
    }

    @Nonnull
    @Override
    public Iterator<CellsheetElement> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public org.apache.poi.ss.usermodel.Cell getDelegate() {
        return row.getDelegate().getCell(colIndex);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("row", row)
                .add("colIndex", colIndex)
                .add("value", getValue())
                .add("type", getType().getTypeName())
                .add("kinds", getKinds().stream().map(CellsheetType::getTypeName).collect(Collectors.joining(",")))
                .toString();
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

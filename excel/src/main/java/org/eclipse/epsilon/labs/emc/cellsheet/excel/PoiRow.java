/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.excel;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaError;
import org.eclipse.epsilon.labs.emc.cellsheet.Cell;
import org.eclipse.epsilon.labs.emc.cellsheet.CellsheetType;
import org.eclipse.epsilon.labs.emc.cellsheet.Row;
import org.eclipse.epsilon.labs.emc.cellsheet.Sheet;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;

public class PoiRow implements Row, PoiDelegate<org.apache.poi.ss.usermodel.Row> {

    PoiSheet sheet;
    int rowIndex;

    @Override
    public PoiSheet getSheet() {
        return sheet;
    }

    @Override
    public PoiCell getCell(int colIndex) {
        checkArgument(colIndex > -1, "colIndex must be positive, given %d", colIndex);

        PoiCell.Builder builder;
        org.apache.poi.ss.usermodel.Cell raw = getDelegate().getCell(colIndex);

        if (raw == null) return null;

        switch (raw.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(raw)) {
                    builder = new PoiDateCell.Builder()
                            .withValue(raw.getDateCellValue());
                } else {
                    builder = new PoiNumericCell.Builder()
                            .withValue(raw.getNumericCellValue());
                }
                break;
            case STRING:
                builder = new PoiTextCell.Builder()
                        .withValue(raw.getStringCellValue());
                break;
            case FORMULA:
                builder = new PoiFormulaCell.Builder()
                        .withValue(raw.getCellFormula());
                break;
            case BLANK:
                builder = new PoiBlankCell.Builder();
                break;
            case BOOLEAN:
                builder = new PoiBooleanCell.Builder()
                        .withValue(raw.getBooleanCellValue());
                break;
            case ERROR:
                builder = new PoiErrorCell.Builder()
                        .withValue(FormulaError.forInt(raw.getErrorCellValue()).getString());
                break;
            default:
                throw new AssertionError("Unknown cell type found: " + raw.getCellType());
        }

        return builder.withRow(this)
                .withColIndex(colIndex)
                .build();
    }

    @Override
    public List<Cell> getCells() {
        return ImmutableList.copyOf(iterator());
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    @Nonnull
    @Override
    public Iterator<Cell> iterator() {
        return Iterators.transform(
                getDelegate().cellIterator(),
                c -> getCell(c.getColumnIndex())
        );
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("sheet", sheet.getId())
                .add("rowIndex", rowIndex)
                .add("type", getType().getTypeName())
                .add("kinds", getKinds().stream().map(CellsheetType::getTypeName).collect(Collectors.joining(",")))
                .toString();
    }

    @Override
    public org.apache.poi.ss.usermodel.Row getDelegate() {
        return sheet.getDelegate().getRow(rowIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoiRow poiRow = (PoiRow) o;
        return getRowIndex() == poiRow.getRowIndex() &&
                Objects.equal(getSheet(), poiRow.getSheet());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getSheet(), getRowIndex());
    }

    public static class Builder implements Row.Builder<PoiRow, Builder> {

        PoiSheet sheet;
        int rowIndex;

        @Override
        public Builder self() {
            return this;
        }

        @Override
        public Builder withSheet(Sheet sheet) {
            checkArgument(sheet instanceof PoiSheet, "Must be instance fromToken %s", PoiSheet.class.getCanonicalName());
            this.sheet = (PoiSheet) sheet;
            return self();
        }

        @Override
        public Builder withRowIndex(int rowIndex) {
            this.rowIndex = rowIndex;
            return self();
        }

        @Override
        public PoiRow build() {
            PoiRow row = new PoiRow();
            row.sheet = sheet;
            row.rowIndex = rowIndex;
            return row;
        }
    }
}

/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.*;

/**
 * Abstract Model Type representing a single cell in a workbook.
 * <p>
 * Implementors should also implement
 * {@link org.eclipse.epsilon.labs.emc.cellsheet.Cell.Builder}
 * </p>
 * <p>
 * A cell is identified by the combination of {@link Book}, {@link Sheet},
 * {@link Row} and column index. Each cell holds a typed value accessed with
 * {@link #getValue()}. For instance {@link TextCell} holds string values.
 * </p>
 * <p>
 * The value of a cell is further broken down into ASTs. {@link #getRoot()} will
 * always return the default derived parse tree. For cells that hold a single
 * value these are simple one-node trees. Formula cells will return ASTs that
 * are derived from the parse tree of the formula they hold.
 * </p>
 * <p>
 * A user can also choose to associate additional bespoke ASTs with a cell.
 * Addition and retrieval must be manually performed by the user.
 * </p>
 *
 * @param <T> the type of value contained in this cell
 * @author Jonathan Co
 * @since 3.0.0
 */
public interface Cell<T> extends HasA1 {

    /**
     * @return the book this cell is located in
     */
    default Book getBook() {
        return getRow().getBook();
    }

    /**
     * @return the sheet this cell is located in
     */
    default Sheet getSheet() {
        return getRow().getSheet();
    }

    /**
     * @return the row this cell is located in
     */
    Row getRow();

    /**
     * @return 0-based column index of this cell
     */
    int getColIndex();

    /**
     * @return alpha column index of this cell
     */
    default String getA1ColIndex() {
        return ReferenceUtil.indexToA1(getColIndex());
    }

    /**
     * @return 0-based row index of this cell
     */
    default int getRowIndex() {
        return getRow().getRowIndex();
    }

    /**
     * @return 1-based row index of this cell
     */
    default int getA1RowIndex() {
        return getRow().getA1RowIndex();
    }

    /**
     * @return raw value of this cell
     */
    T getValue();

    /**
     * @return the ASTs associated with this cell
     */
    List<Ast> getAsts();

    /**
     * @return the default AST derived from the value of this cell
     */
    default Ast getRoot() {
        return getAst(0);
    }

    /**
     * Associate a user defined AST to this cell. The returned int can be used
     * to retrieve this AST later
     *
     * @param ast
     */
    default int addAst(Ast ast) {
        getAsts().add(ast);
        ast.setPosition(getAsts().size() - 1);
        ast.setCell(this);
        return ast.getPosition();
    }

    /**
     * Retrieve the AST at the given position
     *
     * @param position the position
     * @return the AST
     * @throws IndexOutOfBoundsException if the position is out of bounds
     */
    default Ast getAst(int position) {
        return getAsts().get(position);
    }

    @Override
    default Iterator<HasId> iterator() {
        return Collections.<HasId>unmodifiableList(getAsts()).iterator();
    }

    @Override
    default String getA1() {
        if (getSheet() == null || getRow() == null || getColIndex() < 0)
            return HasA1.super.getA1();
        return getSheet().getA1() + "!" + getA1ColIndex() + getA1RowIndex();
    }

    @Override
    default String getId() {
        if (getRow() == null || getColIndex() < 0) HasA1.super.getId();
        return getRow().getId() + "/" + getColIndex();
    }

    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.CELL, CellsheetType.HAS_A1, CellsheetType.HAS_ID);
    }

    /**
     * Builder interface for constructing new Cell instances
     *
     * @param <T> Cell to build
     * @param <V> Value type of the Cell
     * @param <B> The Builder type
     */
    interface Builder<T extends Cell<V>, V, B extends Builder<T, V, B>> extends CellsheetBuilder {

        /**
         * @return this builder instance
         */
        B self();

        B withRow(Row row);

        B withColIndex(int colIndex);

        B withValue(V value);

        T build();
    }
}
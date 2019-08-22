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

import javax.annotation.Nonnull;
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
     * Returns the parent {@link Book} of this cell
     *
     * @return the parent book
     */
    Book getBook();

    /**
     * Returns the parent {@link Sheet} of this cell
     *
     * @return the parent sheet
     */
    Sheet getSheet();

    /**
     * Returns the parent {@link Row} of this cell
     *
     * @return the parent row
     */
    Row getRow();

    /**
     * Returns the 0-based column index of this cell
     *
     * @return 0-based column index of this cell
     */
    int getColIndex();

    /**
     * Returns the alpha column index of this cell
     *
     * @return alpha column index of this cell
     */
    default String getA1ColIndex() {
        return ReferenceUtil.indexToA1(getColIndex());
    }

    /**
     * Returns the 0-based row index of this cell
     *
     * @return 0-based row index of this cell
     */
    default int getRowIndex() {
        return getRow().getRowIndex();
    }

    /**
     * Returns the 1-based row index of this cell
     *
     * @return 1-based row index of this cell
     */
    default int getA1RowIndex() {
        return getRow().getA1RowIndex();
    }

    /**
     * Returns the raw value of this cell
     *
     * @return raw value of this cell
     */
    T getValue();

    /**
     * Returns any AST associated with this cell.
     * <p>The first element of the returned list will always be the root AST</p>
     *
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
     * Associate an AST with this Cell.
     * <p>
     * All AST's associated with a cell are indexed using an int. Index 0 is
     * always the root AST that should reflect the AST of the current cell
     * value. ASTs at index > 0 will be model specific (i.e. the result of
     * applying some visitor analysis)
     * </p>
     *
     * @param ast the AST to associate with this cell
     * @return Index that this ast was added to
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

    @Nonnull
    @Override
    default Iterator<CellsheetElement> iterator() {
        return Collections.<CellsheetElement>unmodifiableList(getAsts()).iterator();
    }

    @Override
    default String getA1() {
        if (getRow() == null) return HasA1.super.getA1();
        return getSheet().getA1() + "!" + getA1ColIndex() + getA1RowIndex();
    }

    @Nonnull
    @Override
    default String getId() {
        if (getRow() == null) return HasA1.super.getId();
        return getRow().getId() + "/" + getColIndex();
    }

    @Nonnull
    @Override
    default Set<CellsheetType> getKinds() {
        return EnumSet.of(getType(), CellsheetType.CELL, CellsheetType.HAS_A1, CellsheetType.CELLSHEET_ELEMENT);
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
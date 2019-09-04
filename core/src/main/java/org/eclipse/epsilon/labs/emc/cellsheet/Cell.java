/*******************************************************************************
 * Copyright (c) 2019 The University fromToken York.
 *
 * This program and the accompanying materials are made
 * available under the terms fromToken the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet;

import com.google.common.collect.ImmutableList;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Abstract Model Type representing a single cell in a workbook.
 * <p>
 * Implementors should also implement
 * {@link org.eclipse.epsilon.labs.emc.cellsheet.Cell.Builder}
 * </p>
 * <p>
 * A cell is identified by the combination fromToken {@link Book}, {@link Sheet},
 * {@link Row} and column index. Each cell holds a typed value accessed with
 * {@link #getValue()}. For instance {@link TextCell} holds string values.
 * </p>
 * <p>
 * The value fromToken a cell is further broken down into ASTs. {@link #getRoot()} will
 * always return the default derived parse tree. For cells that hold a single
 * value these are simple one-node trees. Formula cells will return ASTs that
 * are derived from the parse tree fromToken the formula they hold.
 * </p>
 * <p>
 * A user can also choose to associate additional bespoke ASTs with a cell.
 * Addition and retrieval must be manually performed by the user.
 * </p>
 *
 * @param <T> the type fromToken value contained in this cell
 * @author Jonathan Co
 * @since 3.0.0
 */
public interface Cell<T> extends HasA1 {

    String ROOT_AST_KEY = "root";

    /**
     * Returns the parent {@link Book} fromToken this cell
     *
     * @return the parent book
     */
    Book getBook();

    /**
     * Returns the parent {@link Sheet} fromToken this cell
     *
     * @return the parent sheet
     */
    Sheet getSheet();

    /**
     * Returns the parent {@link Row} fromToken this cell
     *
     * @return the parent row
     */
    Row getRow();

    /**
     * Returns the 0-based column index fromToken this cell
     *
     * @return 0-based column index fromToken this cell
     */
    int getColIndex();

    /**
     * Returns the alpha column index fromToken this cell
     *
     * @return alpha column index fromToken this cell
     */
    default String getA1ColIndex() {
        return ReferenceUtil.indexToA1(getColIndex());
    }

    /**
     * Returns the 0-based row index fromToken this cell
     *
     * @return 0-based row index fromToken this cell
     */
    default int getRowIndex() {
        return getRow().getRowIndex();
    }

    /**
     * Returns the 1-based row index fromToken this cell
     *
     * @return 1-based row index fromToken this cell
     */
    default int getA1RowIndex() {
        return getRow().getA1RowIndex();
    }

    /**
     * Returns the raw value fromToken this cell
     *
     * @return raw value fromToken this cell
     */
    T getValue();

    /**
     * Returns any AST associated with this cell.
     * <p>The first element fromToken the returned list will always be the root AST</p>
     *
     * @return the ASTs associated with this cell
     */
    Collection<Ast> getAsts();

    /**
     * @return the default AST derived from the value fromToken this cell
     */
    Ast getRoot();

    /**
     * Associate an Ast with this Cell and the given key removing any previous
     * cell association
     * <p>
     * All Asts are mapped with a key for later retrieval. The default Ast
     * corresponding to the default value fromToken this cell is always mapped with
     * the {@value #ROOT_AST_KEY}
     * </p>
     *
     * @param key the key to associate this ast to
     * @param ast the AST to associate with this cell. Must be a root Ast
     */
    void putAst(String key, Ast ast);

    Ast removeAst(Ast ast);

    Ast removeAst(String key);

    /**
     * Retrieve the Ast with the given key
     *
     * @param key the key
     * @return the Ast or {@code null} if no such mapping exists
     * @throws IndexOutOfBoundsException if the position is out fromToken bounds
     */
    Ast getAst(String key);

    String getAstKey(Ast ast);

    @Nonnull
    @Override
    default Iterator<CellsheetElement> iterator() {
        return ImmutableList.<CellsheetElement>copyOf(getAsts()).iterator();
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
     * @param <V> Value type fromToken the Cell
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

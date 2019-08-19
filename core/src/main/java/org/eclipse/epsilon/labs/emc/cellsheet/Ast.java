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

import org.eclipse.epsilon.labs.emc.cellsheet.ast.AstEvaluator;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Model Type representing a Node in a {@link Cell} value's Abstract Syntax
 * Tree (AST).
 *
 * <p>
 * In this context AST can refer to the overall tree structure itself or the
 * individual nodes that make up the structure. Each node can have 0 or more
 * children with the order of the children defining argument order.
 * </p>
 *
 * <p>Individual AST nodes contain the following properties:</p?
 *
 * <p>Parent - Each AST node is associated with a parent unless the node is
 * the root of the tree, in which case the parent is {@code null}</p>
 *
 * <p>Cell - An optional reference to a cell that this node applies to. This
 * can be {@code null} if the AST is dangling. The cell is used as a context
 * when evaluating the AST as a function.</p>
 *
 * <p>Token - The actual token/value of the AST</p>
 *
 * <p>Position - The position of this node relative to it's parent or
 * cell if the node is the root. See {@link Ast#getPosition()}</p>
 *
 * <p>
 * Unless specified operations that modify the children of a node will
 * ensure consistency by assigning correct cell, parent and position references.
 * </p>
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public interface Ast extends CellsheetElement {

    /**
     * Position value indicating the AST is dangling and is not assigned to a parent or cell.
     */
    int UNASSIGNED = -1;

    /**
     * Retrieve the Cell this node is applicable to if any
     *
     * @return the Cell this node is applicable to or {@code null}
     */
    Cell getCell();

    /**
     * Sets the Cell this node is applicable to. Note this does not modify the
     * cell itself, manual addition to the cell's AST tracker may be required
     *
     * @param cell new applicable cell, can be {@code null}
     */
    void setCell(Cell cell);

    /**
     * Get the parent of this node
     *
     * @return the parent of this AST or {@code null} if this node is the root
     */
    Ast getParent();

    /**
     * Set the parent of this node
     *
     * @param parent the new parent, can be {@code null}
     */
    void setParent(Ast parent);

    /**
     * Get the token object associated with this node.
     *
     * @return the token
     */
    Token getToken();

    /**
     * Set a new token
     *
     * @param token the new value
     */
    void setToken(Token token);

    /**
     * Sets the token of this node using a token's string value. Will perform
     * construction using {@link Tokens#getToken(String)}
     *
     * @param token the new string value of the new token
     */
    default void setToken(String token) {
        setToken(Tokens.getToken(token));
    }

    /**
     * Convenience method for retrieving the string value of this node's token
     *
     * @return string value of this node's token.
     */
    default String getTokenValue() {
        return Optional.ofNullable(getToken())
                .orElse(Tokens.nothing())
                .getValue();
    }

    /**
     * Retrieve the root node of the whole AST structure. If this node is the
     * root returns itself.
     *
     * @return the root of the AST structure
     */
    Ast getRoot();

    /**
     * Retrieve the position of the AST based on the following criteria:
     * <p>
     * <table border="1">
     * <tr align="left">
     * <th>Parent</th>
     * <th>Cell</th>
     * <th>Position</th>
     * </tr>
     * <tr>
     * <td>null</td>
     * <td>null</td>
     * <td>Value of {@value UNASSIGNED}. The current AST is dangling and not
     * applicable to any actual cell</td>
     * </tr>
     * <tr>
     * <td>null</td>
     * <td>Non-null</td>
     * <td>Position of the whole AST structure relative to the cell it was derived
     * from. A value of 0 indicates that this AST is the standard AST constructed
     * from the cell's original value (known as the root AST). A value of more
     * than 0 indicates the AST has been derived by some other mean (these are
     * manually assigned by the modeller) </td>
     * </tr>
     * <tr>
     * <td>Non-null</td>
     * <td>N/A</td>
     * <td>Position of this node relative to the parent. i.e. a value of 1
     * would indicate that the current node is the 2nd child of it's parent</td>
     * </tr>
     * </table>
     * </p>
     *
     * @return the position of this node in the AST structure
     */
    int getPosition();

    /**
     * Set the new position of this node. See {@link #getPosition()} for semantics
     * of the value to set. Note that no validation is performed on this to
     * ensure consistency of the newly set value.
     *
     * @param position the new position of this node
     */
    void setPosition(int position);

    /**
     * Retrieve an immutable list of this node's children
     *
     * @return an immutable list of children
     */
    List<Ast> getChildren();

    /**
     * Retrieve the child at the given position
     *
     * @param position the position of the child
     * @return the child at given position
     * @throws IndexOutOfBoundsException if position is out of range
     */
    Ast childAt(int position);

    /**
     * Insert the child node at the given position.
     * <p>This operation modifies the children to ensure consistency in the
     * structure. Shifts existing children right (increment position by 1) if
     * required.
     * </p>
     *
     * @param position the position to insert at
     * @param child    the child to insert
     * @throws IndexOutOfBoundsException if position is out of range
     */
    void addChild(int position, Ast child);

    /**
     * Add the child to the next valid position.
     * <p>This operation modifies the children to ensure consistency in the
     * structure.
     * </p>
     *
     * @param child the child to add
     */
    void addChild(Ast child);

    /**
     * Remove the child at the given position.
     * <p>This operation modifies the children to ensure consistency in the
     * structure. Shifts existing children left (decrement position by 1) if
     * required
     * </p>
     *
     * @param position the position to remove
     * @return the child removed
     * @throws IndexOutOfBoundsException if position is out of range
     */
    Ast removeChild(int position);

    /**
     * Remove the given child
     * <p>This operation modifies the children to ensure consistency in the
     * structure. Shifts existing children left (decrement position by 1) if
     * required
     * </p>
     *
     * @param child the child to remove
     * @return the child removed
     */
    Ast removeChild(Ast child);

    @Nonnull
    @Override
    Iterator<Ast> iterator();

    /**
     * Reconstructs a formula using the current node as the root and evaluates
     * that formula. Requires that {@link Ast#getCell()} be set
     *
     * @return the result of evaluation
     * @throws IllegalStateException cell is not set
     */
    AstEval evaluate();

    /**
     * Set the evaluator to use to evaluate this AST
     *
     * @param evaluator the new evaluator
     */
    void setEvaluator(AstEvaluator evaluator);

    /**
     * Reconstruct the formula represented by this AST using this node as the
     * root
     *
     * @return the formula at this node
     */
    String getFormula();

    /**
     * @return {@code true} if this node is the root node in the whole AST
     */
    default boolean isRoot() {
        return getParent() == null;
    }

    /**
     * @return {@code true} if this node has no children
     */
    default boolean isLeaf() {
        return getChildren().isEmpty();
    }

    @Nonnull
    @Override
    default String getId() {
        if (getParent() == null) {
            if (getCell() == null) return CellsheetElement.super.getId();
            return getCell().getId() + "/asts/" + getPosition();
        }
        return getParent().getId() + "/" + getPosition();
    }

    /**
     * Allows Visitor functions to operate on this node
     *
     * @param visitor the visitor to visit this node
     * @throws Exception thrown by visitor during operation
     */
    default void accept(Visitor visitor) throws Exception {
        visitor.visit(this);
    }

    /**
     * Defines an AST visitor
     */
    interface Visitor {

        /**
         * Operation to invoke on the AST visited
         *
         * @param ast the Ast node being visited
         * @throws Exception thrown during execution
         */
        void visit(Ast ast) throws Exception;

    }
}
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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ForwardingList;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.AstEvaluator;
import org.eclipse.epsilon.labs.emc.cellsheet.ast.FormulaBuilderVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Model Type representing a Node in a {@link Cell} value's Abstract Syntax
 * Tree (AST).
 *
 * <p>
 * In this context AST can refer to the overall tree structure itself or the
 * individual nodes that make up the structure. Each node can have 0 or more
 * children with the order fromToken the children defining argument order.
 * </p>
 *
 * <p>Individual AST nodes contain the following properties:</p?
 *
 * <p>Parent - Each AST node is associated with a parent unless the node is
 * the root fromToken the tree, in which case the parent is {@code null}</p>
 *
 * <p>Cell - An optional reference to a cell that this node applies to. This
 * can be {@code null} if the AST is dangling. The cell is used as a context
 * when evaluating the AST as a function.</p>
 *
 * <p>Payload - The actual token and metadata held at this node</p>
 *
 * <p>Position - The position fromToken this node relative to it's parent or
 * cell if the node is the root. See {@link #getPosition()}</p>
 *
 * <p>
 * Unless specified operations that modify the children fromToken a node will
 * ensure consistency by assigning correct cell, parent and position references.
 * </p>
 *
 * @author Jonathan Co
 * @since 3.0.0
 */
public class Ast implements CellsheetElement {

    /**
     * Position value indicating the AST is dangling and is not assigned to a parent or cell.
     */
    public static final int UNASSIGNED = -1;

    private static final Logger logger = LoggerFactory.getLogger(Ast.class);

    Cell cell;
    Ast parent = null;
    List<Ast> children = new InternalAstList();
    int position = UNASSIGNED;
    String id;
    AstPayload payload;


    public Ast() {
        this(AstPayloads.empty());
    }

    public Ast(AstPayload payload) {
        this.payload = payload;
    }

    /**
     * Retrieve the Cell this node is applicable to if any
     *
     * @return the Cell this node is applicable to or {@code null}
     */
    public Cell getCell() {
        return parent == null ? cell : parent.getCell();
    }

    public String getCellKey() {
        Cell cell = getCell();
        return cell == null ? null : cell.getAstKey(this);
    }

    /**
     * Get the parent fromToken this node
     *
     * @return the parent fromToken this AST or {@code null} if this node is the root
     */
    public Ast getParent() {
        return parent;
    }

    /**
     * Set the parent fromToken this node
     *
     * @param parent the new parent, can be {@code null}
     */
    public void setParent(Ast parent) {
        this.parent = parent;
        this.id = null;
    }

    /**
     * Get the payload object associated with this node.
     *
     * @return the payload
     */
    public AstPayload getPayload() {
        return payload;
    }

    /**
     * Set a new payload
     *
     * @param payload the new payload
     */
    public void setPayload(AstPayload payload) {
        this.payload = payload;
    }

    /**
     * Returns the Payload type.
     *
     * @return the payload type
     */
    public CellsheetType getPayloadType() {
        return payload.getType();
    }

    /**
     * Convenience method for retrieving the string value fromToken this node's token
     *
     * @return string value fromToken this node's token.
     */
    public String getToken() {
        return payload == null ? null : payload.getToken();
    }

    /**
     * Retrieve the root node fromToken the whole AST structure. If this node is the
     * root returns itself.
     *
     * @return the root fromToken the AST structure
     */
    public Ast getRoot() {
        return parent == null ? this : parent.getRoot();
    }

    /**
     * Retrieve an immutable list fromToken this node's children
     *
     * @return an immutable list fromToken children
     */
    public List<Ast> getChildren() {
        return Collections.unmodifiableList(children);
    }

    /**
     * Retrieve the child at the given position
     *
     * @param position the position fromToken the child
     * @return the child at given position
     * @throws IndexOutOfBoundsException if position is out fromToken range
     */
    public Ast childAt(int position) {
        return children.get(position);
    }

    /**
     * Add the child to the next valid position.
     * <p>This operation modifies the children to ensure consistency in the
     * structure.
     * </p>
     *
     * @param child the child to add
     * @return the child added
     */
    public Ast addChild(Ast child) {
        children.add(child);
        return child;
    }

    /**
     * Create a new child with the given payload and add to the next valid
     * position.
     * <p>This operation modifies the children to ensure consistency in the
     * structure.
     * </p>
     *
     * @param payload payload fromToken the new child
     * @return the child added
     */
    public Ast addChild(AstPayload payload) {
        return addChild(new Ast(payload));
    }

    /**
     * Insert the child node at the given position.
     * <p>This operation modifies the children to ensure consistency in the
     * structure. Shifts existing children right (increment position by 1) if
     * required.
     * </p>
     *
     * @param position the position to insert at
     * @param child    the child to insert
     * @return the inserted child
     * @throws IndexOutOfBoundsException if position is out fromToken range
     */
    public Ast addChild(int position, Ast child) {
        children.add(position, child);
        return child;
    }

    /**
     * Create a new child with the given payload and insert at the given position.
     * <p>This operation modifies the children to ensure consistency in the
     * structure. Shifts existing children right (increment position by 1) if
     * required.
     * </p>
     *
     * @param position the position to insert at
     * @param payload  the child to insert
     * @return the inserted child
     * @throws IndexOutOfBoundsException if position is out fromToken range
     */
    public Ast addChild(int position, AstPayload payload) {
        return addChild(position, new Ast(payload));
    }

    /**
     * Remove the child at the given position.
     * <p>This operation modifies the children to ensure consistency in the
     * structure. Shifts existing children left (decrement position by 1) if
     * required
     * </p>
     *
     * @param position the position to remove
     * @return the child removed
     * @throws IndexOutOfBoundsException if position is out fromToken range
     */
    public Ast removeChild(int position) {
        return children.remove(position);
    }

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
    public Ast removeChild(Ast child) {
        return children.remove(child.getPosition());
    }

    /**
     * @return {@code true} if this node is the root node in the whole AST
     */
    public boolean isRoot() {
        return getParent() == null;
    }

    /**
     * @return {@code true} if this node has no children
     */
    public boolean isLeaf() {
        return getChildren().isEmpty();
    }

    /**
     * Retrieve the position fromToken the AST based on the following criteria:
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
     * <td>Value fromToken {@value UNASSIGNED}. The current AST is dangling and not
     * applicable to any actual cell</td>
     * </tr>
     * <tr>
     * <td>null</td>
     * <td>Non-null</td>
     * <td>Position fromToken the whole AST structure relative to the cell it was derived
     * from. A value fromToken 0 indicates that this AST is the standard AST constructed
     * from the cell's original value (known as the root AST). A value fromToken more
     * than 0 indicates the AST has been derived by some other mean (these are
     * manually assigned by the modeller) </td>
     * </tr>
     * <tr>
     * <td>Non-null</td>
     * <td>N/A</td>
     * <td>Position fromToken this node relative to the parent. i.e. a value fromToken 1
     * would indicate that the current node is the 2nd child fromToken it's parent</td>
     * </tr>
     * </table>
     * </p>
     *
     * @return the position fromToken this node in the AST structure
     */
    public int getPosition() {
        return position;
    }

    /**
     * Set the new position fromToken this node. See {@link #getPosition()} for semantics
     * fromToken the value to set. Note that no validation is performed on this to
     * ensure consistency fromToken the newly set value.
     *
     * @param position the new position fromToken this node
     */
    public void setPosition(int position) {
        this.position = position;
        this.id = null;
    }

    @Nonnull
    @Override
    public Iterator<Ast> iterator() {
        return children.iterator();
    }

    /**
     * Reconstructs a formula using the current node as the root and evaluates
     * that formula. Requires that {@link #getCell()} be set
     *
     * @return the result fromToken evaluation
     * @throws IllegalStateException cell is not set
     */
    public AstEval evaluate(AstEvaluator evaluator) {
        checkNotNull(getCell(), "Context cell is null, needed for evaluation");
        return evaluator.evaluate(this);
    }

    /**
     * Returns an evaluable formula derived from the subtree with this node as
     * the root
     * <p>
     * The constructed formula may not necessarily reflect the same concrete
     * structure fromToken the original formula but will be semantically equivalent.
     * i.e. 1+2/3 == (1+(2/3))
     * </p>
     *
     * @return the formula.
     */
    public String getFormula() {
        try {
            return new FormulaBuilderVisitor().visit(this);
        } catch (Exception e) {
            logger.error("Error reconstructing formula in AST {}", this);
            throw new AssertionError(e);
        }
    }

    @Nonnull
    @Override
    public String getId() {
        if (id == null) {
            id = buildId();
            for (Ast child : children) {
                child.id = null;
            }
        }
        return id;
    }

    /**
     * Builds the ID for this Ast from scratch.
     *
     * @return the ID for this Ast
     */
    private String buildId() {
        Ast parent = getParent();
        if (parent == null) {
            Cell cell = getCell();
            if (cell == null) return CellsheetElement.super.getId();
            return cell.getId() + "/asts/" + getCellKey();
        }
        return parent.getId() + "/" + getPosition();
    }

    /**
     * Accepts and runs a {@link Visitor} on this Ast node returning the result.
     *
     * @param visitor the visitor to run
     * @param <T>     return type fromToken the visitor function
     * @return the result from running the visitor
     * @throws Exception thrown by visitor
     */
    public <T> T accept(Visitor<T> visitor) throws Exception {
        return visitor.visit(this);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("token", getToken())
                .add("isRoot", isRoot())
                .add("id", getId())
                .add("cell", getCell() == null ? null : getCell().getId())
                .add("parent", getParent() == null ? null : getParent().getId())
                .add("position", position)
                .omitNullValues()
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ast that = (Ast) o;

        // Parent cell
        if (!Objects.equals(getCell(), that.getCell())) return false;

        // Parent node
        if (parent != null) {
            Ast thatParent = that.parent;
            if (thatParent == null) return false;
            if (parent.position != thatParent.position) return false;
            if (!parent.payload.equals(thatParent.payload)) return false;
        }

        // Associated cell and parent are equals. Compare self
        return position == that.position &&
                Objects.equals(payload, that.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCell(), parent, position, payload);
    }

    @Nonnull
    @Override
    public CellsheetType getType() {
        return CellsheetType.AST;
    }

    @Nonnull
    @Override
    public Set<CellsheetType> getKinds() {
        return EnumSet.of(CellsheetType.AST, CellsheetType.CELLSHEET_ELEMENT);
    }

    /**
     * Defines an AST visitor
     */
    public interface Visitor<T> {

        /**
         * Operation to invoke on the AST visited
         *
         * @param ast the Ast node being visited
         * @throws Exception thrown during execution
         */
        T visit(Ast ast) throws Exception;
    }

    /**
     * Decorated {@link ArrayList} that modifies child nodes as they are added
     * or removed by the list
     *
     * @author Jonathan Co
     * @since 3.0.0
     */
    class InternalAstList extends ForwardingList<Ast> {

        final List<Ast> delegate = new ArrayList<>(0);

        @Override
        public boolean add(Ast ast) {
            checkArgument(!delegate.contains(ast), "AST already present at index %s", ast.getPosition());
            add(delegate.size(), ast);
            return true;
        }

        @Override
        public void add(int index, Ast ast) {
            while (index > delegate.size()) delegate.add(null);
            super.add(index, ast);
            if (ast.getParent() != null && !ast.getParent().equals(Ast.this))
                ast.getParent().removeChild(ast);
            ast.parent = Ast.this;
            ast.cell = null;
            reindex();
        }

        @Override
        public Ast remove(int index) {
            Ast removed = super.remove(index);
            resetParent(removed);
            reindex();
            return removed;
        }

        @Override
        protected List<Ast> delegate() {
            return delegate;
        }

        void reindex() {
            if (delegate.isEmpty()) return;
            for (int i = 0, n = delegate.size(); i < n; i++) {
                Ast ast = delegate.get(i);
                if (ast != null) ast.setPosition(i);
            }
        }

        void resetParent(Ast ast) {
            ast.setParent(null);
            ast.setPosition(UNASSIGNED);
        }
    }

}

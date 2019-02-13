package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

/**
 * Model Element representing a Formula Parse tree
 * 
 * @author Jonathan Co
 *
 */
public interface IAst extends HasId, HasCellValue {

	/**
	 * Set the parent {@link IFormulaCellValue}
	 * 
	 * @param cellValue
	 */
	public void setCellValue(ICellValue cellValue);

	/**
	 * @return the parent of this {@link IAst} or {@code null} if no parent exists
	 */
	public IAst getParent();

	/**
	 * Set the parent of this {@link IAst}
	 * 
	 * @param parent
	 */
	public void setParent(IAst parent);

	/**
	 * @return the direct child trees this Formula Tree may have.
	 */
	public List<IAst> getChildren();

	/**
	 * Evaluates and returns the current node in the formula tree using the built-in
	 * formula evaluator
	 * 
	 * @return the result of evaluating the current node
	 */
	public String evaluate();

	public String getToken();

	public void setToken(String token);

	public AstType getType();

	public void setType(AstType type);

	public AstSubtype getSubtype();

	public void setSubtype(AstSubtype subtype);

	public IAst getRoot();

	/**
	 * @return this tree and all it's descendant trees. Order is based on left
	 *         traversal
	 */
	public List<IAst> getAllChildren();

	/**
	 * @param index the position of the child sub-tree
	 * @return The position of the child at the sub-tree or {@code null} if they do
	 *         not exist
	 */
	public IAst getChildAt(int index);

	public void removeChildAt(int index);

	/**
	 * Add a sub-tree to this {@link IAst} and assign {@code this} as the parent. If
	 * the sub-tree already has a parent this will be re-assigned.
	 * 
	 * @param child
	 */
	public void addChild(IAst child);

	public void addChild(int index, IAst child);

	public void setChild(int index, IAst child);

	/**
	 * Convenience method to retrieve the first sub-tree
	 * 
	 * @return the first sub-tree or {@code null}
	 */
	public IAst getFirst();

	/**
	 * Convenience method to retrieve the second sub-tree
	 * 
	 * @return the second sub-tree or {@code null}
	 */
	public IAst getSecond();

	/**
	 * Returns a formula string built at this tree. Will only elements that are
	 * children of this tree.
	 * 
	 * @return a formula string representation of this tree
	 */
	public String getFormula();

	/**
	 * Returns {@code true} if this node is the root of the tree.
	 * 
	 * @return {@code true} if this is the root of the tree, {@code false} otherwise
	 */
	public boolean isRoot();

	/**
	 * Returns {@code true} if this node a leaf element of the tree (has no
	 * children)
	 * 
	 * @return {@code true} if this is a leaf element of the tree, {@code false}
	 *         otherwise
	 */
	public boolean isLeaf();

	/**
	 * Count all children associated with this node - this includes all subtrees of
	 * this node.
	 * 
	 * Does not count self.
	 * 
	 * @return count of all sub-nodes starting from this node.
	 */
	public int getChildCount();

	/**
	 * Return this tree as a tree structure diagram
	 * 
	 * <pre>
	 * {@code
	 * C4*VLOOKUP($A5,Assumptions!$B$4:$N$6,C$2)
	 * └── *
	 *     ├── C4
	 *     └── VLOOKUP
	 *         ├── $A5
	 *         ├── Assumptions!$B$4:$N$6
	 *         └── C$2
	 * }
	 * </pre>
	 * 
	 * @return this tree formatted as a tree structure diagram
	 */
	public String toStringTree();

	public int getIndex();

	public void accept(Visitor visitor);

	public static interface Visitor {

		public void visit(IAst tree);

	}

}

package org.eclipse.epsilon.emc.cellsheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Model Element representing a Formula Parse tree
 * 
 * @author Jonathan Co
 *
 */
public interface IFormulaTree extends HasId, Iterable<IFormulaTree> {

	public static final Type TYPE = Type.FORMULA_TREE;
	public static final Type[] KINDS = { IFormulaTree.TYPE };

	/**
	 * @return the original {@link IFormulaCellValue} that is this tree was derived
	 *         from.
	 */
	public IFormulaCellValue getCellValue();

	/**
	 * Set the parent {@link IFormulaCellValue}
	 * 
	 * @param cellValue
	 */
	public void setCellValue(IFormulaCellValue cellValue);

	/**
	 * @return the parent of this {@link IFormulaTree} or {@code null} if no parent
	 *         exists
	 */
	public IFormulaTree getParent();

	/**
	 * Set the parent of this {@link IFormulaTree}
	 * 
	 * @param parent
	 */
	public void setParent(IFormulaTree parent);

	/**
	 * @return this tree and all descendant trees
	 */
	default public List<IFormulaTree> getAllTrees() {
		List<IFormulaTree> children = new ArrayList<>(Arrays.asList(this));
		if (isLeaf()) {
			return children;
		}
		
		for (IFormulaTree child : getChildren()) {
			children.addAll(child.getAllTrees());
		}
		return children;
	}

	/**
	 * @return the direct child trees this Formula Tree may have.
	 */
	public List<IFormulaTree> getChildren();

	/**
	 * @param index the position of the child sub-tree
	 * @return The position of the child at the sub-tree or {@code null} if they do
	 *         not exist
	 */
	public IFormulaTree getChildAt(int index);

	/**
	 * Add a sub-tree to this {@link IFormulaTree} and assign {@code this} as the
	 * parent. If the sub-tree already has a parent this will be re-assigned.
	 * 
	 * @param child
	 */
	public void addChild(IFormulaTree child);

	/**
	 * Evaluates and returns the current node in the formula tree using the built-in
	 * formula evaluator
	 * 
	 * @return the result of evaluating the current node
	 */
	public String evaluate();

	/**
	 * Return the Cell element that the evaluation result is held in.
	 * 
	 * For reference functions such as LOOKUPS this will be where the cell value is
	 * located. For all other functions (such as stat operations) the value is
	 * contained within the cell where the formula is located.
	 * 
	 * @return
	 */
	public ICell evaluateCell();

	/**
	 * Returns a formula string built at this tree. Will only elements that are
	 * children of this tree.
	 * 
	 * @return a formula string representation of this tree
	 */
	public String getFormula();

	/**
	 * Get the token associated with this node in the tree
	 * 
	 * @return {@code String} representation of this node's token
	 */
	public String getToken();

	/**
	 * Returns {@code true} if this node is the root of the tree.
	 * 
	 * @return {@code true} if this is the root of the tree, {@code false} otherwise
	 */
	default boolean isRoot() {
		return getParent() == null;
	}

	/**
	 * Returns {@code true} if this node a leaf element of the tree (has no
	 * children)
	 * 
	 * @return {@code true} if this is a leaf element of the tree, {@code false}
	 *         otherwise
	 */
	default boolean isLeaf() {
		return getChildren().isEmpty();
	}

	/**
	 * Count all children associated with this node - this includes all subtrees of
	 * this node.
	 * 
	 * Does not count self.
	 * 
	 * @return count of all sub-nodes starting from this node.
	 */
	default int countAllChildren() {
		if (isLeaf()) {
			return 0;
		}

		int count = getChildren().size();
		for (IFormulaTree tree : getChildren()) {
			count += tree.countAllChildren();
		}
		return count;
	}

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
	default String formatAsTree() {
		return getFormula() + "\n" + formatAsTree("", true);
	}

	/**
	 * Return this tree as a tree structure diagram
	 * 
	 * <pre>
	 * {@code
	 * └── *
	 *     ├── C4
	 *     └── VLOOKUP
	 *         ├── $A5
	 *         ├── Assumptions!$B$4:$N$6
	 *         └── C$2
	 * }
	 * </pre>
	 * 
	 * @param prefix to append onto start of this line
	 * @param isTail is this a tail element
	 * @return this tree formatted as a tree structure diagram
	 */
	default String formatAsTree(String prefix, boolean isTail) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix).append(isTail ? "└── " : "├── ").append(getToken()).append("\n");

		for (int i = 0; i < getChildren().size() - 1; i++) {
			sb.append(getChildAt(i).formatAsTree(prefix + (isTail ? "    " : "│   "), false));
		}

		if (getChildren().size() > 0) {
			sb.append(getChildAt(getChildren().size() - 1).formatAsTree(prefix + (isTail ? "    " : "│   "), true));
		}

		return sb.toString();
	}

	@Override
	default Iterator<IFormulaTree> iterator() {
		return getChildren().iterator();
	}

	@Override
	default Type getType() {
		return IFormulaTree.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IFormulaTree.KINDS;
	}

	@Override
	default String getId() {
		return isRoot() ? getCellValue().getId() + "0/"
				: getParent().getId() + getParent().getChildren().indexOf(this) + "/";
	}

}

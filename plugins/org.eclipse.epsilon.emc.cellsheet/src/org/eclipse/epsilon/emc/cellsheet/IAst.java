package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Model Element representing a Formula Parse tree
 * 
 * @author Jonathan Co
 *
 */
public interface IAst extends HasId, Iterable<IAst> {

	/**
	 * @return the original {@link IFormulaCellValue} that is this tree was derived
	 *         from.
	 */
	public ICellValue getCellValue();

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

	default public IAst getRoot() {
		return isRoot() ? this : getParent().getRoot();
	}

	/**
	 * @return the Cell this FormulaTree belongs to
	 */
	default public ICell getCell() {
		return getCellValue().getCell();
	}

	/**
	 * @return the Row this FormulaTree belongs to
	 */
	default public IRow getRow() {
		return getCellValue().getRow();
	}

	/**
	 * @return the Sheet this FormulaTree belongs to
	 */
	default public ISheet getSheet() {
		return getCellValue().getSheet();
	}

	/**
	 * @return the Book this FormulaTree belongs to
	 */
	default public IBook getBook() {
		return getCellValue().getBook();
	}

	/**
	 * @return this tree and all it's descendant trees. Order is based on left
	 *         traversal
	 */
	default public List<IAst> getAllTrees() {
		List<IAst> list = new LinkedList<>();
		accept(new Visitor() {
			@Override
			public void visit(IAst tree) {
				for (IAst child : tree.getChildren()) {
					child.accept(this);
				}
				list.add(tree);
			}
		});
		return list;
	}

	/**
	 * @param index the position of the child sub-tree
	 * @return The position of the child at the sub-tree or {@code null} if they do
	 *         not exist
	 */
	default IAst getChildAt(int index) {
		return index < getChildren().size() ? getChildren().get(index) : null;
	}

	default void removeChildAt(int index) {
		getChildren().remove(index);
	}

	/**
	 * Add a sub-tree to this {@link IAst} and assign {@code this} as the parent. If
	 * the sub-tree already has a parent this will be re-assigned.
	 * 
	 * @param child
	 */
	default void addChild(IAst child) {
		child.setParent(this);
		getChildren().add(child);
	}

	default void addChild(int index, IAst child) {
		child.setParent(this);
		getChildren().add(index, child);
	}

	default void setChild(int index, IAst child) {
		child.setParent(this);
		getChildren().set(index, child);
	}

	/**
	 * Convenience method to retrieve the first sub-tree
	 * 
	 * @return the first sub-tree or {@code null}
	 */
	default IAst getFirst() {
		return getChildAt(0);
	}

	/**
	 * Convenience method to retrieve the second sub-tree
	 * 
	 * @return the second sub-tree or {@code null}
	 */
	default IAst getSecond() {
		return getChildAt(1);
	}

	/**
	 * Returns a formula string built at this tree. Will only elements that are
	 * children of this tree.
	 * 
	 * @return a formula string representation of this tree
	 */
	default public String getFormula() {
		final StringBuilder sb = new StringBuilder();
		accept(new Visitor() {

			@Override
			public void visit(IAst tree) {
				// No children, straight append and return
				if (tree.isLeaf()) {
					sb.append(tree.getToken());
					return;
				}

				switch (tree.getType()) {
				case OPERATOR_INFIX:
					sb.append('(');
					tree.getFirst().accept(this);
					sb.append(tree.getToken());
					tree.getSecond().accept(this);
					sb.append(')');
					return;

				case OPERATOR_PREFIX:
					sb.append(tree.getToken());
					if (tree.getFirst().isLeaf()) {
						tree.getFirst().accept(this);
					} else {
						sb.append('(');
						tree.getFirst().accept(this);
						sb.append(')');
					}
					return;

				case OPERATOR_POSTFIX:
					if (tree.getFirst().isLeaf()) {
						tree.getFirst().accept(this);
					} else {
						sb.append('(');
						tree.getFirst().accept(this);
						sb.append(')');
					}
					sb.append(tree.getToken());
					return;

				default:
					sb.append(tree.getToken());
					sb.append('(');

					final Iterator<IAst> it = tree.getChildren().iterator();
					while (it.hasNext()) {
						it.next().accept(this);
						if (it.hasNext()) {
							sb.append(',');
						}
					}

					sb.append(')');
				}
			}
		});

		return sb.toString();
	}

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
		return getChildren() == null ? true : getChildren().isEmpty();
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
		return getAllTrees().size() - 1;
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
	default String toTreeString() {
		return getFormula() + "\n" + toTreeString("", true);
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
	default String toTreeString(String prefix, boolean isTail) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix).append(isTail ? "└── " : "├── ").append(toString()).append("\n");
		ListIterator<IAst> it = getChildren().listIterator();
		while (it.hasNext()) {
			IAst child = it.next();

			if (it.hasNext())
				sb.append(child.toTreeString(prefix + (isTail ? "    " : "│   "), false));
			else
				sb.append(child.toTreeString(prefix + (isTail ? "    " : "│   "), true));
		}
		return sb.toString();
	}

	@Override
	default Iterator<IAst> iterator() {
		return getChildren().listIterator();
	}

	@Override
	default String getId() {
		return isRoot() ? getCellValue().getId() + "0/"
				: getParent().getId() + getParent().getChildren().indexOf(this) + "/";
	}

	@Override
	default ElementType[] getKinds() {
		return new ElementType[] { getType(), getSubtype(), AstType.SUPER };
	}

	default void accept(Visitor visitor) {
		visitor.visit(this);
	}

	public static interface Visitor {

		public void visit(IAst tree);

	}

}

package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.epsilon.emc.cellsheet.Token.TokenSubtype;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenType;

/**
 * Model Element representing a Formula Parse tree
 * 
 * @author Jonathan Co
 *
 */
public interface IFormulaTree extends HasId, Iterable<IFormulaTree> {

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

	public Token getToken();

	public void setToken(Token token);

	/**
	 * @return the direct child trees this Formula Tree may have.
	 */
	public List<IFormulaTree> getChildren();

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
	 * Get the token associated with this node in the tree
	 * 
	 * @return {@code String} representation of this node's token
	 */
	default String getValue() {
		return getToken().getValue();
	}

	/**
	 * Set the value of this tree
	 * 
	 * @param string value
	 */
	default void setValue(String value) {
		getToken().setValue(value);
	}

	@Override
	default Type getType() {
		return fromTokenType(getToken().getType());
	}

	default void setType(Type type) {
		getToken().setType(toTokenType(type));
	}

	default Type getSubtype() {
		return fromTokenSubtype(getToken().getSubtype());
	}

	default void setSubtype(Type type) {
		getToken().setSubtype(toTokenSubtype(type));
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
	default public List<IFormulaTree> getAllTrees() {
		List<IFormulaTree> list = new LinkedList<>();
		accept(new Visitor() {
			@Override
			public void visit(IFormulaTree tree) {
				for (IFormulaTree child : tree.getChildren()) {
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
	default IFormulaTree getChildAt(int index) {
		return getChildren().get(index);
	}

	/**
	 * Add a sub-tree to this {@link IFormulaTree} and assign {@code this} as the
	 * parent. If the sub-tree already has a parent this will be re-assigned.
	 * 
	 * @param child
	 */
	default void addChild(IFormulaTree child) {
		child.setParent(this);
		getChildren().add(child);
	}

	default void addChild(int index, IFormulaTree child) {
		child.setParent(child);
		getChildren().add(index, child);
	}

	/**
	 * Returns a formula string built at this tree. Will only elements that are
	 * children of this tree.
	 * 
	 * @return a formula string representation of this tree
	 */
	default public String getFormula() {
		List<Token> tokens = new LinkedList<>();
		accept(new Visitor() {
			@Override
			public void visit(IFormulaTree tree) {
				Token token = tree.getToken();
				switch (token.getType()) {
				case OPERATOR_INFIX:
					if (tree.getChildren().isEmpty()) {
						tokens.add(token);
					} else {
						assert tree.getChildren().size() == 2;
						tree.getChildAt(0).accept(this);
						tokens.add(token);
						tree.getChildAt(1).accept(this);
					}
					break;

				case OPERATOR_PREFIX:
					tokens.add(token);
					tree.getChildren().forEach(c -> c.accept(this));
					break;

				case OPERATOR_POSTFIX:
					tree.getChildren().forEach(c -> c.accept(this));
					tokens.add(token);
					break;

				default:
					tokens.add(tree.getToken());
					if (!isLeaf())
						tree.getChildren().forEach(c -> c.accept(this));
					break;
				}
			}
		});
		return Tokenizer.toString(tokens);
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
		sb.append(prefix).append(isTail ? "└── " : "├── ").append(getToken()).append("\n");
		ListIterator<IFormulaTree> it = getChildren().listIterator();
		while (it.hasNext()) {
			IFormulaTree child = it.next();

			if (it.hasNext())
				sb.append(child.toTreeString(prefix + (isTail ? "    " : "│   "), false));
			else
				sb.append(child.toTreeString(prefix + (isTail ? "    " : "│   "), true));
		}
		return sb.toString();
	}

	@Override
	default Iterator<IFormulaTree> iterator() {
		return getChildren().listIterator();
	}

	@Override
	default String getId() {
		return isRoot() ? getCellValue().getId() + "0/"
				: getParent().getId() + getParent().getChildren().indexOf(this) + "/";
	}

	@Override
	default Type[] getKinds() {
		return new Type[] { getType(), getSubtype(), Type.FORMULA_TREE };
	}

	default void accept(Visitor v) {
		v.visit(this);
	}

	public static Type fromTokenType(TokenType type) {
		return Type.valueOf(type.name());
	}

	public static Type fromTokenSubtype(TokenSubtype subtype) {
		return Type.valueOf(subtype.name());
	}

	public static TokenType toTokenType(Type type) {
		return TokenType.valueOf(type.name());
	}

	public static TokenSubtype toTokenSubtype(Type type) {
		return TokenSubtype.valueOf(type.name());
	}

	public static interface Visitor {

		public void visit(IFormulaTree tree);

	}

}

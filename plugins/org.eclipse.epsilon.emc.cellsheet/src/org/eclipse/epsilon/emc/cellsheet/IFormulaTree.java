package org.eclipse.epsilon.emc.cellsheet;

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
	
	public void setCellValue(IFormulaCellValue cellValue);


	/**
	 * @return the parent of this {@link IFormulaTree} or {@code null} if no parent
	 *         exists
	 */
	public IFormulaTree getParent();

	public void setParent(IFormulaTree parent);

	/**
	 * @return any child trees this Formula Tree may have.
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
	
	public String getToken();
	
	default boolean isRoot() {
		return getParent() == null;
	}
	
	default boolean isLeaf() {
		return getChildren().isEmpty();
	}
	
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
		return isRoot() ? getCellValue().getId() + "0/" : getParent().getId() + getParent().getChildren().indexOf(this) + "/";
	}

}

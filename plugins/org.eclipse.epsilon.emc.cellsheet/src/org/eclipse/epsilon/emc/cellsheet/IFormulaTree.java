package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

/**
 * Model Element representing a Formula Parse tree
 * 
 * @author Jonathan Co
 *
 */
public interface IFormulaTree extends HasType {

	public static final Type TYPE = Type.FORMULA_TREE;
	public static final Type[] KINDS = {IFormulaTree.TYPE};

	/**
	 * @return the original {@link IFormulaCellValue} that is this tree was derived from.
	 */
	public IFormulaCellValue getCellValue();
	
	public Token getToken();
	
	/**
	 * @return the parent of this {@link IFormulaTree} or {@code null} if no parent exists
	 */
	public IFormulaTree getParent();
	
	public void setParent(IFormulaTree parent);
	
	/**
	 * @return any child trees this Formula Tree may have.
	 */
	public List<IFormulaTree> getChildren();
	
	/**
	 * @param index the position of the child sub-tree
	 * @return The position of the child at the sub-tree or {@code null} if they do not exist
	 */
	public IFormulaTree getChildAt(int index);
	
	/**
	 * Add a sub-tree to this {@link IFormulaTree} and assign {@code this} as the parent. If the sub-tree already has
	 * a parent this will be re-assigned.
	 * @param child
	 */
	public void addChild(IFormulaTree child);
	
	/**
	 * Evaluate the current node and return the result as a String
	 * @return
	 */
	public String evaluate();
	
	public String toFormula();
	
	@Override
	default Type getType() {
		return IFormulaTree.TYPE;
	}
	
	@Override
	default Type[] getKinds() {
		return IFormulaTree.KINDS;
	}

	/**
	 * Model Element representing a token in a formula parse tree
	 * 
	 * @author Jonathan Co
	 *
	 */
	public interface Token extends HasType {
		public IFormulaTree getFormulaTree();
	}
	
}

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

	public IFormulaCellValue getCellValue();
	
	public Token getToken();
	
	/**
	 * @return any child trees this Formula Tree may have.
	 */
	public List<IFormulaTree> getChildren();
	public String evaluate();
	
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
	 * @author jonathanco
	 *
	 */
	public interface Token extends HasType {
		public IFormulaTree getFormulaTree();
	}
	
}

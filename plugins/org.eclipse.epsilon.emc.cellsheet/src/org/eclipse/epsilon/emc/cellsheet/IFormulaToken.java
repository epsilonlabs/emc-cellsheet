package org.eclipse.epsilon.emc.cellsheet;

/**
 * Model Element representing a token in a formula parse tree
 * 
 * @author Jonathan Co
 *
 */
public interface IFormulaToken extends HasType {
	public IFormulaTree getFormulaTree();
}
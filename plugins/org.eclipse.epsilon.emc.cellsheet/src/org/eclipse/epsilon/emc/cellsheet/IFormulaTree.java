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

  /**
   * Returns the token of this tree.
   * 
   * @return the token of this tree.
   */
  public IFormulaToken getToken();

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
   * Add a sub-tree to this {@link IFormulaTree} and assign {@code this} as the parent. If the
   * sub-tree already has a parent this will be re-assigned.
   * 
   * @param child
   */
  public void addChild(IFormulaTree child);

  /**
   * Evaluates and returns the current node in the formula tree using the built-in formula evaluator
   * 
   * @return the result of evaluating the current node
   */
  public String evaluate();

  /**
   * Evaluates the current node in the formula tree using Abstract Interpretation to produce an
   * intermediary result
   * 
   * i.e. VLOOKUP(A1, B1:D55, 1) will return B1 the cell address rather than the value of B1
   * (assuming the result of the VLOOKUP is in B1).
   */
  public String interpret();

  /**
   * Returns a formula string built at this tree. Will only elements that are children of this tree.
   * 
   * @return a formula string representation of this tree
   */
  public String getFormula();

  @Override
  default Type getType() {
    return IFormulaTree.TYPE;
  }

  @Override
  default Type[] getKinds() {
    return IFormulaTree.KINDS;
  }

}

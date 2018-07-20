package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

public interface IFormulaCellValue extends ICellValue<String> {

  public static final Type TYPE = Type.FORMULA_CELL_VALUE;
  public static final Type[] KINDS = {IFormulaCellValue.TYPE, Type.CELL_VALUE};

  public abstract List<ICellRegion> getReferencedRegions();

  public abstract List<ICell> getReferencedCells();

  /**
   * Will return this Cell's formula as defined in the spreadsheet itself. If a representation of
   * the formula is required that is derived from the parse tree of this formula, then this can be
   * retrieved by calling {@link IFormulaTree#getFormula()}.on the tree retrieved from
   * {@link #getFormulaTree()}.
   * 
   * @return this Cell's formula as defined in the spreadsheet itself.
   */
  public String getFormula();

  public abstract IFormulaTree getFormulaTree();

  @Override
  default Type getType() {
    return IFormulaCellValue.TYPE;
  }

  @Override
  default Type[] getKinds() {
    return IFormulaCellValue.KINDS;
  }

}

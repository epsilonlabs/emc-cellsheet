package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

public interface IFormulaCellValue extends IStringCellValue {

	public static final Type TYPE = Type.FORMULA_CELL_VALUE;
	public static final Type[] KINDS = { IFormulaCellValue.TYPE, Type.CELL_VALUE };

	public List<ICellRegion> getReferencedRegions();

	public List<ICell> getReferencedCells();

	/**
	 * Will return this Cell's formula as defined in the spreadsheet itself. If a
	 * representation of the formula is required that is derived from the parse tree
	 * of this formula, then this can be retrieved by calling
	 * {@link IFormulaTree#getFormula()}.on the tree retrieved from
	 * {@link #getFormulaTree()}.
	 * 
	 * @return this Cell's formula as defined in the spreadsheet itself.
	 */
	public String getFormula();

	/**
	 * Return the parse tree of the formula in this cell
	 * 
	 * @return parse tree of this cell's formula
	 */
	public IFormulaTree getFormulaTree();

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
		return getFormulaTree().formatAsTree();
	}

	@Override
	default Type getType() {
		return IFormulaCellValue.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IFormulaCellValue.KINDS;
	}

}

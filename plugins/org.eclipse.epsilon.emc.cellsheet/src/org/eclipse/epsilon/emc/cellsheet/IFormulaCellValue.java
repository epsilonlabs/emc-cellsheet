package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

public interface IFormulaCellValue extends ICellValue<String> {
	
	public static final Type TYPE = Type.FORMULA_CELL_VALUE;
	public static final Type[] KINDS = {IFormulaCellValue.TYPE, Type.CELL_VALUE};

	public abstract List<ICellRegion> getReferencedRegions();
	public abstract List<ICell> getReferencedCells();
	
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

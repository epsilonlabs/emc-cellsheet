package org.eclipse.epsilon.emc.cellsheet.cells;

import java.util.List;

import org.eclipse.epsilon.emc.cellsheet.ICell;

public interface FormulaValue extends CellValue<String> {

	public abstract List<CellRegion> getReferencedRegions();
	public abstract List<ICell> getReferencedCells();

}

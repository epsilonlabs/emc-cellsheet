package org.eclipse.epsilon.emc.cellsheet.cells;

import java.util.List;

import org.eclipse.epsilon.emc.cellsheet.ICell;

public interface IFormulaCellValue extends ICellValue<String> {

	public abstract List<ICellRegion> getReferencedRegions();
	public abstract List<ICell> getReferencedCells();

}

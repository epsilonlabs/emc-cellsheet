package org.eclipse.epsilon.emc.cellsheet.cells;

import java.util.Iterator;
import java.util.List;

import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.ISheet;

/**
 * Tag interface to refer to a region of cells
 * @author Jonathan Co
 *
 */
public interface CellRegion {
	
	public IBook getBook();
	public ISheet getSheet();
	
	public int getStartRowIdx();
	public int getEndRowIdx();
	public int getStartColIdx();
	public int getEndColIdx();
	
	public void setStartRowIdx(int idx);
	public void setEndRowIdx(int idx);
	public void setStartColIdx(int idx);
	public void setEndColIdx(int idx);

	public <T extends ICell> List<ICell> cells();
	public <T extends ICell> Iterator<ICell> cellIterator();
	
}

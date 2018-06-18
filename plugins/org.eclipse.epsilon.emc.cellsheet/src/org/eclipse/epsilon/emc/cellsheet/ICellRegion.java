package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

/**
 * Tag interface to refer to a region of cells
 * @author Jonathan Co
 *
 */
public interface ICellRegion {
	
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

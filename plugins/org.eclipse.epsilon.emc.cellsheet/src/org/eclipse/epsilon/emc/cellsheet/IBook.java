package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

import org.eclipse.epsilon.eol.models.IModel;

/**
 * Epsilon Model of a Spreadsheet Workbook.
 * 
 * Book is analogous to a model in the Epsilon architecture. This was chosen to
 * maintain the overall Spreadsheet methodology.
 * 
 * Element ID's should be implemented as cell references where possible TODO:
 * Determine correct way to ID individual columns and rows
 * 
 * @author Jonathan Co
 *
 */
public interface IBook extends IModel {

	public static final String TYPENAME = "Book";

	public <T extends ICell> T getCell(int sheetIndex, int row, int col);

	public <T extends ICell> T getCell(ISheet sheet, int row, int col);

	public <T extends ICell> T getCell(IRow row, int col);
	
	public <T extends ICell> T getCell(String sheetName, int row, int col);
	
	public IDResolver getIDResolver();
	
	public IRow getRow(int sheetIndex, int index);
	
	public IRow getRow(ISheet sheet, int index);
	
	public IRow getRow(String sheetName, int index);
	
	public <T extends ISheet> T getSheet(int index);
	
	public <T extends ISheet> T getSheet(String name);

	public void setIDResolver(IDResolver idResolver);

	public <T extends ISheet> Iterator<T> sheetIterator();

	public <T extends ISheet> List<T> sheets();
}

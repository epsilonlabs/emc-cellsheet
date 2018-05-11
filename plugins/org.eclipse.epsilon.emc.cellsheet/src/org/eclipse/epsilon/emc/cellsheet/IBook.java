package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;

import org.eclipse.epsilon.eol.models.IModel;

/**
 * Epsilon Model of a Spreadsheet Workbook.
 * 
 * Book is analogous to a model in the Epsilon architecture. This was chosen to
 * maintain the overall Spreadsheet methodology.
 * 
 * Element ID's should be implemented as cell references where possible
 * TODO: Determine correct way to ID individual columns and rows
 * 
 * @author Jonathan Co
 *
 */
public interface IBook extends IModel, Iterator<ISheet> {
	
	public static final String TYPENAME = "Book";

	// Model related methods
	public ISheet getSheet(int index);
	public ISheet getSheet(String name);
	public Iterator<ISheet> sheetIterator();
	public void addSheet(ISheet sheet);
	public void addSheet(int index, ISheet sheet);
	
	// Driver related methods
	public IDResolver getIDResolver();
	public void setIDResolver(IDResolver idResolver);
}

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
public interface Book extends IModel, Iterator<Sheet> {
	
	public static final String TYPENAME = "Book";

	// Model related methods
	public Sheet getSheet(int index);
	public Sheet getSheet(String name);
	public Iterator<Sheet> sheetIterator();
	public void addSheet(Sheet sheet);
	public void addSheet(int index, Sheet sheet);
	
	// Driver related methods
	public IDResolver getIDResolver();
	public void setIDResolver(IDResolver idResolver);
}

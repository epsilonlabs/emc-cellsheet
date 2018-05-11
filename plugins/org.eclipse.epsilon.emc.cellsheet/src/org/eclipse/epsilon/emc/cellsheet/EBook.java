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
public interface EBook extends IModel, Iterator<ESheet> {
	
	public static final String TYPENAME = "Book";

	// Model related methods
	public ESheet getSheet(int index);
	public ESheet getSheet(String name);
	public Iterator<ESheet> sheetIterator();
	public void addSheet(ESheet sheet);
	public void addSheet(int index, ESheet sheet);
	
	// Driver related methods
	public IDResolver getIDResolver();
	public void setIDResolver(IDResolver idResolver);
}

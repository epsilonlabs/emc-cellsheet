package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;

public interface ISheet extends Iterable<IRow>, HasId {

	public static final String TYPENAME = "Sheet";

	public String getName();
	public void setName(String name);
	
	public IBook getBook();
	public void setBook(IBook book);
	
	public IRow getRow(int rowIdx);
	public Iterator<IRow> rowIterator();
	public void createRow(IRow row);
	public void createRow(int rowIdx, IRow row);
	
}

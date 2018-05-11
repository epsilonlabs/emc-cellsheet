package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;

public interface ESheet extends Iterable<ERow>, HasId {

	public static final String TYPENAME = "Sheet";

	public String getName();
	public void setName(String name);
	
	public EBook getBook();
	public void setBook(EBook book);
	
	public ERow getRow(int rowIdx);
	public Iterator<ERow> rowIterator();
	public void createRow(ERow row);
	public void createRow(int rowIdx, ERow row);
	
}

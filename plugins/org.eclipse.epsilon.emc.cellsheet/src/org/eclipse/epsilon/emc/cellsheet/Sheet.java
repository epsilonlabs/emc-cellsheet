package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;

public interface Sheet extends Iterable<Row> {

	public Book getBook();
	public void setBook(Book book);
	
	public Row getRow(int rowIdx);
	public Iterator<Row> rowIterator();
	public void createRow(Row row);
	public void createRow(int rowIdx, Row row);
	
}

package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

public interface ISheet extends HasId {

	public static final String TYPENAME = "Sheet";

	public void createRow(int rowIdx, IRow row);

	public void createRow(IRow row);

	public IBook getBook();

	public int getIndex();

	public String getName();

	public IRow getRow(int rowIdx);

	public Iterator<IRow> rowIterator();

	public List<IRow> rows();

	public void setBook(IBook book);

	public void setName(String name);

}

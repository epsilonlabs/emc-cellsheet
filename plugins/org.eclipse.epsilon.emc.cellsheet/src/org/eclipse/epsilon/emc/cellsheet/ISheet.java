package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.List;

public interface ISheet extends HasId, Comparable<ISheet> {

	public static final String TYPENAME = "Sheet";

	public IBook getBook();

	public int getIndex();

	public String getName();

	public IRow getRow(int rowIdx);

	public Iterator<IRow> rowIterator();

	public List<IRow> rows();


}

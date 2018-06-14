package org.eclipse.epsilon.emc.cellsheet.cells;

import org.eclipse.epsilon.emc.cellsheet.ICell;

public interface ICellValue<T> {
	
	public ICell getCell();
	
	public String getValue();
	public T getResolvedValue();
	
	public Type getType();
	
	public static enum Type {
		BOOLEAN,
		NUMERIC,
		STRING,
		FORMULA,
		ERROR;
	}
}

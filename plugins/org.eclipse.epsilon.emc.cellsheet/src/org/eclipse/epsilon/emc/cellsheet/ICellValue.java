package org.eclipse.epsilon.emc.cellsheet;

public interface ICellValue<T> extends HasType {

	public ICell getCell();

	public T getValue();

}

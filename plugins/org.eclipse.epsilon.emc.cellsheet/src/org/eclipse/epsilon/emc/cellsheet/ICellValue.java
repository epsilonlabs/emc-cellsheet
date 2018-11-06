package org.eclipse.epsilon.emc.cellsheet;

public interface ICellValue<T> extends HasId {

	public ICell getCell();

	public T getValue();
	
	@Override
	default String getId() {
		return getCell().getId() + "value/";
	}

}

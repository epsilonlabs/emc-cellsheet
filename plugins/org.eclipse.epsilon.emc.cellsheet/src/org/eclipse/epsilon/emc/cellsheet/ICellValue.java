package org.eclipse.epsilon.emc.cellsheet;

public interface ICellValue<T> extends HasId, Comparable<ICellValue<T>> {

	public ICell getCell();

	public T getValue();
	
	@Override
	default String getId() {
		return getCell().getId() + "value/";
	}
	
	@Override
	default int compareTo(ICellValue<T> o) {
		return getCell().compareTo(o.getCell());
	}

}

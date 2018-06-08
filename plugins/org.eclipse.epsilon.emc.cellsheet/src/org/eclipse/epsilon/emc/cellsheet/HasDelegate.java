package org.eclipse.epsilon.emc.cellsheet;

public interface HasDelegate<T> {

	public T getDelegate();

	public void setDelegate(T delegate);
}

package org.eclipse.epsilon.emc.cellsheet;

public interface HasDelegate<T> {

  public T getDelegate();

  @Deprecated
  public void setDelegate(T delegate);
}

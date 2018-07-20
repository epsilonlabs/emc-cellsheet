package org.eclipse.epsilon.emc.cellsheet;

public interface IIdResolver {

  String getId(IBook book);

  String getId(ISheet sheet);

  String getId(IRow row);

  String getId(ICell cell);

  HasType getElementById(IBook book, String id);

}

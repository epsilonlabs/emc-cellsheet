package org.eclipse.epsilon.emc.cellsheet;

public interface IdResolver {

	String getId(IBook book);

	String getId(ISheet sheet);

	String getId(IRow row);

	String getId(ICell cell);

}
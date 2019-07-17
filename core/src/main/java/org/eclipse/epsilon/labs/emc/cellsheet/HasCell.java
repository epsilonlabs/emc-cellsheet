package org.eclipse.epsilon.labs.emc.cellsheet;

public interface HasCell extends HasRow {

	/**
	 * @return the cell this element belongs to
	 */
	public ICell getCell();
}

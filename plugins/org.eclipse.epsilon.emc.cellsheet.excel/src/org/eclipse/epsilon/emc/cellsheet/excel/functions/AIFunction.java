package org.eclipse.epsilon.emc.cellsheet.excel.functions;

import org.apache.poi.ss.formula.functions.FreeRefFunction;

/**
 * Interface for defining Excel functions that will return an Abstract
 * Interpretation result rather than a full evaluation
 * 
 * @author Jonathan Co
 */
public interface AIFunction extends FreeRefFunction {

	public String getOldName();

	public String getNewName();
}

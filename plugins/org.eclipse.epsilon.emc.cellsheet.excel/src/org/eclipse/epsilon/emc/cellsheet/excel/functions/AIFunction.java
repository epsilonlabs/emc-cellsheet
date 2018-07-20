package org.eclipse.epsilon.emc.cellsheet.excel.functions;

import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Function;

/**
 * Interface for defining Excel functions that will return an Abstract Interpretation result rather
 * than a full evaluation
 * 
 * @author Jonathan Co
 */
public interface AIFunction extends FreeRefFunction, Function {

  public String getOldName();

  public String getNewName();

  public int getFunctionId();
}

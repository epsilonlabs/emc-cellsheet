package org.eclipse.epsilon.emc.cellsheet.excel.functions;

import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.functions.Function;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelBook;

/**
 * Interface for defining Excel functions that will return an Abstract Interpretation result rather
 * than a full evaluation
 * 
 * @author Jonathan Co
 */
public interface AiFunction extends FreeRefFunction, Function {

  public String getExcelName();

  public String getAiName();

  public int getFunctionId();
  
  public ExcelBook getBook();
}

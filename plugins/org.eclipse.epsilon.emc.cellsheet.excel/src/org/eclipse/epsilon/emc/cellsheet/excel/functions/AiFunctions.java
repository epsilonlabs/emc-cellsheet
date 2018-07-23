package org.eclipse.epsilon.emc.cellsheet.excel.functions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.udf.UDFFinder;

public class AiFunctions implements UDFFinder {

  private static final AiFunctions INSTANCE = new AiFunctions();

  // AiFunction.getOldName -> AiFunction.getNewName
  protected final Map<String, String> excelToAi;
  // AiFunction.getNewName -> AiFunction
  protected final Map<String, AiFunction> aiFunctions;

  private AiFunctions() {
    aiFunctions = new HashMap<String, AiFunction>();
    excelToAi = new HashMap<String, String>();
    registerFunction(new AiVlookup(), excelToAi, aiFunctions);
  }

  @Override
  public FreeRefFunction findFunction(String name) {
    return aiFunctions.get(name.toUpperCase(Locale.ROOT));
  }

  public String getInterpretedFunction(String functionName) {
    return excelToAi.get(functionName);
  }

  public static AiFunctions instance() {
    return INSTANCE;
  }

  protected void registerFunction(AiFunction function) {
    excelToAi.put(function.getExcelName(), function.getAiName());
    aiFunctions.put(function.getAiName(), function);
  }

  static void registerFunction(AiFunction function, Map<String, String> excelToAi,
      Map<String, AiFunction> aiFunctions) {
    aiFunctions.put(function.getAiName(), function);
    excelToAi.put(function.getExcelName().toUpperCase(Locale.ROOT),
        function.getAiName().toUpperCase(Locale.ROOT));
  }

}

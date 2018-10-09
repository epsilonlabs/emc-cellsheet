package org.eclipse.epsilon.emc.cellsheet.excel.functions;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.poi.ss.formula.functions.FreeRefFunction;
import org.apache.poi.ss.formula.udf.UDFFinder;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelBook;

// TODO: Change singleton pattern
public class AiFunctions implements UDFFinder {

	// AiFunction.getOldName -> AiFunction.getNewName
	protected final Map<String, String> excelToAi;
	// AiFunction.getNewName -> AiFunction
	protected final Map<String, AiFunction> aiFunctions;

	private AiFunctions() {
		aiFunctions = new HashMap<String, AiFunction>();
		excelToAi = new HashMap<String, String>();
	}

	@Override
	public FreeRefFunction findFunction(String name) {
		return aiFunctions.get(name.toUpperCase(Locale.ROOT));
	}

	public String getInterpretedFunction(String functionName) {
		return excelToAi.get(functionName);
	}

	public static AiFunctions create(ExcelBook book) {
		AiFunctions functions = new AiFunctions();
		functions.registerFunction(new AiVlookup(book));
		return functions;
	}

	protected void registerFunction(AiFunction function) {
		excelToAi.put(function.getExcelName(), function.getAiName());
		aiFunctions.put(function.getAiName(), function);
	}

}

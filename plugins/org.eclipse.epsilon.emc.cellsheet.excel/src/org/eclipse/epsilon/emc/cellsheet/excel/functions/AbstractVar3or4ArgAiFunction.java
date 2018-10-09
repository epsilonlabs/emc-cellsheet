package org.eclipse.epsilon.emc.cellsheet.excel.functions;

import org.apache.poi.ss.formula.functions.Var3or4ArgFunctionDelegate;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelBook;

public abstract class AbstractVar3or4ArgAiFunction extends Var3or4ArgFunctionDelegate implements AiFunction {

	protected final ExcelBook book;

	protected AbstractVar3or4ArgAiFunction(ExcelBook book) {
		this.book = book;
	}

	@Override
	public ExcelBook getBook() {
		return book;
	}
}

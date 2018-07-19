package org.eclipse.epsilon.emc.cellsheet.excel;

import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;

public class ExcelTestUtil {
	
	public static final String DEFAULT_BOOK = "Excel";
	
	private static final String RES_PATH = "./resources/";

	public static ExcelBook getBook(String filename, String modelName, boolean load) throws EolModelLoadingException {
		final ExcelBook book = new ExcelBook();
		book.setExcelFile(RES_PATH + filename);
		book.setName(modelName);
		if (load) book.load();
		return book;
	}
	
	public static ExcelBook getBook(String filename) throws EolModelLoadingException {
		return getBook(filename, filename, true);
	}
	
	public static ExcelBook getBook(Class<?> clazz) throws EolModelLoadingException {
		return getBook(clazz.getSimpleName() + ".xlsx");
	}
}

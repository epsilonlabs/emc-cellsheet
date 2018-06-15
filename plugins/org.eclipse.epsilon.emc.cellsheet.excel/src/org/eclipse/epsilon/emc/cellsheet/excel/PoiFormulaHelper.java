package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiFormulaHelper {

	private static final Map<ExcelBook, FormulaParsingWorkbook> fpwMap = new HashMap<>();

	private PoiFormulaHelper() {
		throw new AssertionError();
	}

	public static Ptg[] parseFormula(ExcelBook book, ExcelFormulaValue cellValue) {
		FormulaParsingWorkbook fpw = fpwMap.get(book);
		
		if (fpw == null) {
			final Workbook delegate = book.getDelegate();
			if (delegate instanceof HSSFWorkbook)
				fpw = HSSFEvaluationWorkbook.create((HSSFWorkbook) delegate);
			if (delegate instanceof XSSFWorkbook)
				fpw = XSSFEvaluationWorkbook.create((XSSFWorkbook) delegate);
			if (delegate instanceof SXSSFWorkbook)
				fpw = SXSSFEvaluationWorkbook.create((SXSSFWorkbook) delegate);
			if (fpw == null)
				throw new AssertionError();
			fpwMap.put(book, fpw);
		}
		
		return FormulaParser.parse(cellValue.getValue(), 
				fpw, 
				FormulaType.CELL, 
				cellValue.getCell().getSheet().getIndex());
	}
	
}

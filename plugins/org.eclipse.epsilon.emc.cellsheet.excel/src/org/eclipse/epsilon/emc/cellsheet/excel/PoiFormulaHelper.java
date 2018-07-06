package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;

public class PoiFormulaHelper {

	private static final Map<ExcelBook, FormulaParsingWorkbook> fpwMap = new HashMap<>();

	private PoiFormulaHelper() {
		throw new AssertionError();
	}

	public static Ptg[] parseFormula(ExcelBook book, ExcelFormulaValue cellValue) {
		return parseFormula(book, cellValue.getFormulaStr(), cellValue.getCell().getSheet().getIndex());
	}
	
	public static Ptg[] parseFormula(ExcelBook book, String formula, int sheetIndex) {
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
		
		return FormulaParser.parse(formula, 
				fpw, 
				FormulaType.CELL, 
				sheetIndex);
	}
	
	public static ExcelFormulaTree buildTree(IFormulaCellValue value) {
		if (value instanceof ExcelFormulaValue) return buildTree((ExcelFormulaValue) value);
		throw new IllegalArgumentException("Cannot build tree for a non ExcelFormulaValue");
	}
	
	public static ExcelFormulaTree buildTree(ExcelFormulaValue value) {		
		final Ptg[] ptgs = PoiFormulaHelper.parseFormula(value.getCell().getBook(), value);
		final Stack<ExcelFormulaTree> trees = new Stack<>();
		final Stack<ExcelFormulaTree> operands = new Stack<>();
		
		for (Ptg ptg : ptgs) {
			final ExcelFormulaTree current = new ExcelFormulaTree(value, ptg);
			
			if (ptg instanceof OperationPtg) {				
				OperationPtg operationPtg = (OperationPtg) ptg;
				for (int i = 0; i < operationPtg.getNumberOfOperands(); i++) {
					operands.push(trees.pop());
				}
				
				for (int i = 0; i < operationPtg.getNumberOfOperands(); i++) {
					current.getChildren().add(operands.pop());
				}
				
				if (!operands.isEmpty()) {					
					throw new IllegalStateException();
				}
			}
			
			// Special Case for SUM only
			if (ptg instanceof AttrPtg && ((AttrPtg) ptg).isSum()) {
				current.getChildren().add(trees.pop());
				if (!operands.isEmpty()) throw new IllegalStateException("Not all operands have been consumed for " + ptg);
			}
			
			trees.push(current);
		}
		
		if (trees.size() != 1) throw new AssertionError("Not all tokens consumed");
		
		return trees.pop();
	}
	
}

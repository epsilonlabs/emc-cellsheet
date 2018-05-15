package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.assertTrue;

import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.common.util.StringProperties;
import org.junit.BeforeClass;
import org.junit.Test;


public class ExcelBookTest {
	
	static ExcelBook book;

	@BeforeClass
	public static void setupClass() throws Exception {
		StringProperties props = new StringProperties();
		props.put(ExcelBook.EXCEL_FILE, "C:\\Users\\Jonathan Co\\Projects\\emc-cellsheet\\tests\\org.eclipse.epsilon.emc.cellsheet.excel.test\\ws\\test\\Spreadsheet Equiv.xlsx");
		
		book = new ExcelBook();
		book.load(props);
	}
	
	@Test
	public void testScratch() {
		Workbook raw = book.getRaw();
		Cell cell = raw.getSheet("Florida").getRow(4).getCell(2);
		assertTrue(cell.getCellTypeEnum() == CellType.FORMULA);
		
		String formulaStr = cell.getCellFormula();
		System.out.println(raw.getClass());
		
		XSSFEvaluationWorkbook xssfew = XSSFEvaluationWorkbook.create((XSSFWorkbook) raw);
		
		Ptg[] formula = FormulaParser.parse(formulaStr, xssfew, FormulaType.ARRAY, 2);
		
		for (Ptg ptg : formula) {
			System.out.println(ptg);
		}
	}
}

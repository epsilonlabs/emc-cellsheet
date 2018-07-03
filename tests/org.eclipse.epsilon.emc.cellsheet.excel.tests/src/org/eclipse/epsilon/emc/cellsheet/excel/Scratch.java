package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.formula.ptg.Ptg;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class Scratch {

	public ExcelBook book;
	
	@Before
	public void setup() throws Exception {
		book = ExcelTestUtil.getBook("labels.xlsx");
	}
	
	
	@Test
	public void test() {
		ExcelCell cell = book.getCell("Florida", 4, 2);
		ExcelFormulaValue value = (ExcelFormulaValue) cell.getValue();
		System.out.println("Test: " + value.getFormulaStr());
		Ptg[] ptgs = PoiFormulaHelper.parseFormula(book, value);
		for (Ptg ptg : ptgs) {
//			System.out.println("Test: " + ptg);
		}
		
		IFormulaTree tree = value.getFormulaTree();
		print(tree, "-");
		
	}
	
	public void print(IFormulaTree tree, String ident) {
		System.out.println(ident + " " + tree.getToken());
		
		if (!tree.getChildren().isEmpty()) {
			for (IFormulaTree child : tree.getChildren()) {
				print(child, "-" + ident);
			}
			
		}
	}
}

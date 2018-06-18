package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.junit.Assert.*;

import java.util.List;

import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelBook;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelCell;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelFormulaValue;
import org.junit.Before;
import org.junit.Test;

public class ExcelFormulaValueTest {

	private ExcelBook book;

	@Before
	public void before() throws Exception {
		book = ExcelTestUtil.getBook("Formula.xlsx");
	}

	@Test
	public void testGetReferencedCellsEqualLabel() {
		final ExcelCell formulaCell = book.getCell("Formula", 0, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaValue);
		final ExcelFormulaValue formulaValue = (ExcelFormulaValue) formulaCell.getValue();
		
		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());
		
		final ICell ref = refCells.get(0);
		assertEquals(book.getSheet("Labels"), ref.getSheet());
		assertEquals(0, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}
	
	@Test
	public void testGetReferencedCellsEqualLabelLegacy() {
		final ExcelCell formulaCell = book.getCell("Formula", 1, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaValue);
		final ExcelFormulaValue formulaValue = (ExcelFormulaValue) formulaCell.getValue();
		
		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());
		
		final ICell ref = refCells.get(0);
		assertEquals(book.getSheet("Labels"), ref.getSheet());
		assertEquals(0, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}
	
	@Test
	public void testGetReferencedCellsEqualLabelSameSheet() {
		final ExcelCell formulaCell = book.getCell("Formula", 2, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaValue);
		final ExcelFormulaValue formulaValue = (ExcelFormulaValue) formulaCell.getValue();
		
		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());
		
		final ICell ref = refCells.get(0);
		assertEquals(book.getSheet("Formula"), ref.getSheet());
		assertEquals(2, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}
	
	@Test
	public void testGetReferencedCellsEqualLabelSameSheetLegacy() {
		final ExcelCell formulaCell = book.getCell("Formula", 3, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaValue);
		final ExcelFormulaValue formulaValue = (ExcelFormulaValue) formulaCell.getValue();
		
		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());
		
		final ICell ref = refCells.get(0);
		assertEquals(book.getSheet("Formula"), ref.getSheet());
		assertEquals(3, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}
	
	@Test
	public void testGetReferencedCellsSumRegion() {
		final ExcelCell formulaCell = book.getCell("Formula", 4, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaValue);
		final ExcelFormulaValue formulaValue = (ExcelFormulaValue) formulaCell.getValue();
		
		final List<ICell> refCells = formulaValue.getReferencedCells();	
		assertEquals(20, refCells.size());
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 2; c++) {
				assertTrue(refCells.contains(book.getCell("Data", r, c)));
			}
		}
	}
	
	@Test
	public void testGetReferencedCellsSumRegionSameSheet() {
		final ExcelCell formulaCell = book.getCell("Formula", 5, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaValue);
		final ExcelFormulaValue formulaValue = (ExcelFormulaValue) formulaCell.getValue();
		
		final List<ICell> refCells = formulaValue.getReferencedCells();	
		assertEquals(20, refCells.size());
		for (int r = 0; r < 10; r++) {
			for (int c = 9; c < 11; c++) {
				assertTrue(refCells.contains(book.getCell("Formula", r, c)));
			}
		}
	}

}

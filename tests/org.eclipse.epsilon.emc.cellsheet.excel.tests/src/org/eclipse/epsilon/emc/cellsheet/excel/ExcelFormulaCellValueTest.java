package org.eclipse.epsilon.emc.cellsheet.excel;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.junit.Before;
import org.junit.Test;

public class ExcelFormulaCellValueTest {

	private ExcelBook book;
	private ExcelBook legacyBook;

	@Before
	public void before() throws Exception {
		book = ExcelTestUtil.getBook(ExcelFormulaCellValueTest.class);
		legacyBook = ExcelTestUtil.getBook("Formula.xlsx");
	}

	@Test
	public void testGetReferencedCellsEqualLabel() {
		final ExcelCell formulaCell = legacyBook.getCell("Formula", 0, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaCellValue);
		final ExcelFormulaCellValue formulaValue = (ExcelFormulaCellValue) formulaCell.getValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());

		final ICell ref = refCells.get(0);
		assertEquals(legacyBook.getSheet("Labels"), ref.getSheet());
		assertEquals(0, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}

	@Test
	public void testGetReferencedCellsEqualLabelLegacy() {
		final ExcelCell formulaCell = legacyBook.getCell("Formula", 1, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaCellValue);
		final ExcelFormulaCellValue formulaValue = (ExcelFormulaCellValue) formulaCell.getValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());

		final ICell ref = refCells.get(0);
		assertEquals(legacyBook.getSheet("Labels"), ref.getSheet());
		assertEquals(0, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}

	@Test
	public void testGetReferencedCellsEqualLabelSameSheet() {
		final ExcelCell formulaCell = legacyBook.getCell("Formula", 2, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaCellValue);
		final ExcelFormulaCellValue formulaValue = (ExcelFormulaCellValue) formulaCell.getValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());

		final ICell ref = refCells.get(0);
		assertEquals(legacyBook.getSheet("Formula"), ref.getSheet());
		assertEquals(2, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}

	@Test
	public void testGetReferencedCellsEqualLabelSameSheetLegacy() {
		final ExcelCell formulaCell = legacyBook.getCell("Formula", 3, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaCellValue);
		final ExcelFormulaCellValue formulaValue = (ExcelFormulaCellValue) formulaCell.getValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());

		final ICell ref = refCells.get(0);
		assertEquals(legacyBook.getSheet("Formula"), ref.getSheet());
		assertEquals(3, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}

	@Test
	public void testGetReferencedCellsSumRegion() {
		final ExcelCell formulaCell = legacyBook.getCell("Formula", 4, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaCellValue);
		final ExcelFormulaCellValue formulaValue = (ExcelFormulaCellValue) formulaCell.getValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(20, refCells.size());
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 2; c++) {
				assertTrue(refCells.contains(legacyBook.getCell("Data", r, c)));
			}
		}
	}

	@Test
	public void testGetReferencedCellsSumRegionSameSheet() {
		final ExcelCell formulaCell = legacyBook.getCell("Formula", 5, "B");
		assertTrue(formulaCell.getValue() instanceof ExcelFormulaCellValue);
		final ExcelFormulaCellValue formulaValue = (ExcelFormulaCellValue) formulaCell.getValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(20, refCells.size());
		for (int r = 0; r < 10; r++) {
			for (int c = 9; c < 11; c++) {
				assertTrue(refCells.contains(legacyBook.getCell("Formula", r, c)));
			}
		}
	}

	@Test
	public void getFormulaTree_should_return_tree_with_1_operand_1_operator_when_sum_1_arg() throws Exception {
		ICell cell = book.getCell("getFormulaTree", 0, 0);
		IFormulaCellValue value = (IFormulaCellValue) cell.getValue();

		IFormulaTree sumTree = value.getFormulaTree();
		assertEquals("SUM", sumTree.getToken());
		assertEquals(1, sumTree.getChildren().size());

		IFormulaTree areaTree = sumTree.getChildren().get(0);
		assertEquals("Data!A1:D5", areaTree.getToken());
		assertEquals(sumTree, areaTree.getParent());
	}

	@Test
	public void getFormulaTree_should_return_tree_with_5_operands_1_operator_when_sum_func_5_args() throws Exception {
		ICell cell = book.getCell("getFormulaTree", 1, 0);
		IFormulaCellValue value = (IFormulaCellValue) cell.getValue();

		IFormulaTree sumTree = value.getFormulaTree();
		assertEquals("SUM", sumTree.getToken());
		assertEquals(5, sumTree.getChildren().size());

		final Set<String> expected = new HashSet<>(
				Arrays.asList("Data!B1", "Data!D5", "Data!B5", "Data!D2", "Data!C2"));
		for (IFormulaTree child : sumTree.getChildren()) {
			assertThat(expected, hasItem(child.getToken()));
			assertEquals(sumTree, child.getParent());
			expected.remove(child.getToken());
		}
		assertEquals(0, expected.size());
	}
}

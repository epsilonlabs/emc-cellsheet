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
import org.eclipse.epsilon.emc.cellsheet.IAst;
import org.junit.Before;
import org.junit.Ignore;
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
		IFormulaCellValue formulaValue = legacyBook.getSheet("Formula").getA1Row(1).getA1Cell("B").getFormulaCellValue();
		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());

		final ICell ref = refCells.get(0);
		assertEquals(legacyBook.getSheet("Labels"), ref.getSheet());
		assertEquals(0, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}

	@Test
	public void testGetReferencedCellsEqualLabelLegacy() {
		IFormulaCellValue formulaValue = legacyBook.getSheet("Formula").getA1Row(2).getA1Cell("B").getFormulaCellValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());

		final ICell ref = refCells.get(0);
		assertEquals(legacyBook.getSheet("Labels"), ref.getSheet());
		assertEquals(0, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}

	@Test
	public void testGetReferencedCellsEqualLabelSameSheet() {
		IFormulaCellValue formulaValue = legacyBook.getSheet("Formula").getA1Row(3).getA1Cell("B").getFormulaCellValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());

		final ICell ref = refCells.get(0);
		assertEquals(legacyBook.getSheet("Formula"), ref.getSheet());
		assertEquals(2, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}

	@Test
	public void testGetReferencedCellsEqualLabelSameSheetLegacy() {
		IFormulaCellValue formulaValue = legacyBook.getSheet("Formula").getA1Row(4).getA1Cell("B").getFormulaCellValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(1, refCells.size());

		final ICell ref = refCells.get(0);
		assertEquals(legacyBook.getSheet("Formula"), ref.getSheet());
		assertEquals(3, ref.getRowIndex());
		assertEquals(0, ref.getColIndex());
	}

	@Test
	public void testGetReferencedCellsSumRegion() {
		IFormulaCellValue formulaValue = legacyBook.getSheet("Formula").getA1Row(5).getA1Cell("B").getFormulaCellValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(20, refCells.size());
		for (int r = 0; r < 10; r++) {
			for (int c = 0; c < 2; c++) {
				assertTrue(refCells.contains(legacyBook.getSheet("Data").getRow(r).getCell(c)));
			}
		}
	}

	@Test
	public void testGetReferencedCellsSumRegionSameSheet() {
		IFormulaCellValue formulaValue = legacyBook.getSheet("Formula").getA1Row(6).getA1Cell("B").getFormulaCellValue();

		final List<ICell> refCells = formulaValue.getReferencedCells();
		assertEquals(20, refCells.size());
		for (int r = 0; r < 10; r++) {
			for (int c = 9; c < 11; c++) {
				assertTrue(refCells.contains(legacyBook.getSheet("Formula").getRow(r).getCell(c)));
			}
		}
	}

	// FIXME
	@Test
	@Ignore
	public void getFormulaTree_should_return_tree_with_1_operand_1_operator_when_sum_1_arg() throws Exception {
		ICell cell = book.getSheet("getFormulaTree").getRow(0).getCell(0);
		IFormulaCellValue value = (IFormulaCellValue) cell.getCellValue();

		IAst sumTree = value.getFormulaTree();
		assertEquals("SUM", sumTree.getValue());
		assertEquals(1, sumTree.getChildren().size());

		IAst areaTree = sumTree.getChildren().get(0);
		assertEquals("Data!A1:D5", areaTree.getValue());
		assertEquals(sumTree, areaTree.getParent());
	}

	// FIXME
	@Test
	@Ignore
	public void getFormulaTree_should_return_tree_with_5_operands_1_operator_when_sum_func_5_args() throws Exception {
		ICell cell = book.getSheet("getFormulaTree").getRow(1).getCell(0);
		IFormulaCellValue value = (IFormulaCellValue) cell.getCellValue();

		IAst sumTree = value.getFormulaTree();
		assertEquals("SUM", sumTree.getValue());
		assertEquals(5, sumTree.getChildren().size());

		final Set<String> expected = new HashSet<>(
				Arrays.asList("Data!B1", "Data!D5", "Data!B5", "Data!D2", "Data!C2"));
		for (IAst child : sumTree.getChildren()) {
			assertThat(expected, hasItem(child.getValue()));
			assertEquals(sumTree, child.getParent());
			expected.remove(child.getValue());
		}
		assertEquals(0, expected.size());
	}
}

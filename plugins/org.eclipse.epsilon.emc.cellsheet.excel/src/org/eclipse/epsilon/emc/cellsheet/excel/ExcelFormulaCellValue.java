package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaPtg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.apache.poi.ss.usermodel.CellType;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.ICellRegion;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;

/**
 * Excel based representation of a Cell's Formula
 * 
 * @author Jonathan Co
 */
public class ExcelFormulaCellValue extends AbstractExcelCellValue<String> implements IFormulaCellValue {


	ExcelFormulaCellValue(ExcelCell cell) {
		super(cell);
		if (cell.getDelegate().getCellTypeEnum() != CellType.FORMULA)
			throw new IllegalArgumentException("Delegate cell must have a Formula/String value");
	}

	@Override
	public String getValue() {
		switch (cell.getDelegate().getCachedFormulaResultTypeEnum()) {
		case NUMERIC:
			return Double.toString(cell.getDelegate().getNumericCellValue());
		case STRING:
			return cell.getDelegate().getStringCellValue();
		default:
			return "";
		}
	}

	@Deprecated
	@Override
	public List<ICellRegion> getReferencedRegions() {
		final List<ICellRegion> regions = new ArrayList<ICellRegion>();

		for (Ptg ptg : FormulaUtil.getPtgs(this)) {
			if (!(ptg instanceof OperandPtg))
				continue;

			ICellRegion region = null;

			// @formatter:off
			if (ptg instanceof RefPtg) {
				RefPtg rp = (RefPtg) ptg;
				region = new ExcelCellRegion((ExcelBook) cell.getBook(), (ExcelSheet) cell.getSheet(), rp.getRow(), rp.getRow() + 1,
						rp.getColumn(), rp.getColumn() + 1);
			}

			if (ptg instanceof Ref3DPxg) {
				Ref3DPxg rp = (Ref3DPxg) ptg;
				region = new ExcelCellRegion((ExcelBook) cell.getBook(), (ExcelSheet) cell.getBook().getSheet(rp.getSheetName()), rp.getRow(),
						rp.getRow() + 1, rp.getColumn(), rp.getColumn() + 1);
			}

			if (ptg instanceof AreaPtg) {
				AreaPtg ap = (AreaPtg) ptg;
				region = new ExcelCellRegion((ExcelBook) cell.getBook(), (ExcelSheet) cell.getSheet(), ap.getFirstRow(), ap.getLastRow() + 1,
						ap.getFirstColumn(), ap.getLastColumn() + 1);
			}
			// @formatter:on

			if (ptg instanceof Area3DPxg) {
				Area3DPxg ap = (Area3DPxg) ptg;

				// FIXME: check if external workbook number
				if (ap.getExternalWorkbookNumber() != -1)
					throw new UnsupportedOperationException();

				// @formatter:off
				region = new ExcelCellRegion((ExcelBook) cell.getBook(), (ExcelSheet) cell.getBook().getSheet(ap.getSheetName()),
						ap.getFirstRow(), ap.getLastRow() + 1, ap.getFirstColumn(), ap.getLastColumn() + 1);
				// @formatter:on
			}

			if (ptg instanceof Area3DPtg) {
				throw new UnsupportedOperationException();
			}

			if (region != null)
				regions.add(region);
		}

		return regions;
	}

	@Deprecated
	@Override
	public List<ICell> getReferencedCells() {
		List<ICellRegion> regions = getReferencedRegions();
		List<ICell> cells = new ArrayList<ICell>();
		for (int i = 0; i < regions.size(); i++) {
			ICellRegion cellRegion = regions.get(i);
			cells.addAll(cellRegion.cells());
		}
		return cells;
	}

	@Override
	public ExcelFormulaTree getFormulaTree() {
		return ExcelFormulaTree.fromString(getFormula());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getClass().getSimpleName()).append("@").append(hashCode()).append("]");
		sb.append("(id: ").append(getId());
		sb.append(", formula: ").append(getFormula());
		sb.append(", value: ").append(getValue());
		sb.append(")");
		return sb.toString();
	}

	@Override
	public String getFormula() {
		return cell.getDelegate().getCellFormula();
	}

}

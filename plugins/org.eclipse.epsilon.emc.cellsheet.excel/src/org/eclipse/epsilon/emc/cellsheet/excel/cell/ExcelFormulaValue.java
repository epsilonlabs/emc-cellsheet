package org.eclipse.epsilon.emc.cellsheet.excel.cell;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.formula.ptg.Area3DPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.Ref3DPxg;
import org.apache.poi.ss.formula.ptg.RefPtg;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.cells.CellRegion;
import org.eclipse.epsilon.emc.cellsheet.cells.FormulaValue;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelCell;

public class ExcelFormulaValue extends AbstractExcelValue<String> implements FormulaValue {

	public ExcelFormulaValue(ExcelCell cell) {
		super(cell);
	}

	@Override
	public String getValue() {
		return this.cell.getDelegate().getCellFormula();
	}

	@Override
	public String getResolvedValue() {
		return this.cell.getDelegate().getStringCellValue();
	}

	@Override
	public List<CellRegion> getReferencedRegions() {
		final Ptg[] tokens = cell.getBook().parseFormula(this);
		final List<CellRegion> regions = new ArrayList<CellRegion>();
		
		for (Ptg ptg : tokens) {
			if (!(ptg instanceof OperandPtg)) continue;
			
			CellRegion region = null;
			
			if (ptg instanceof RefPtg) {
				RefPtg rp = (RefPtg) ptg;
				region = new ExcelCellRegion(
						cell.getBook(), 
						cell.getSheet(), 
						rp.getRow(), 
						rp.getRow() + 1,
						rp.getColumn(), 
						rp.getColumn() + 1);
			}
			
			if (ptg instanceof Ref3DPxg) {
				Ref3DPxg rp = (Ref3DPxg) ptg;
				region = new ExcelCellRegion(
						cell.getBook(),
						cell.getBook().getSheet(rp.getSheetName()), 
						rp.getRow(), 
						rp.getRow() + 1,
						rp.getColumn(), 
						rp.getColumn() + 1);		
			}
			
			if (ptg instanceof Area3DPxg) {
				Area3DPxg ap = (Area3DPxg) ptg;
				
				// FIXME: check if external workbook number
				if (ap.getExternalWorkbookNumber() != -1) 
					throw new UnsupportedOperationException();
				
				region = new ExcelCellRegion(
						cell.getBook(),
						cell.getBook().getSheet(ap.getSheetName()),
						ap.getFirstRow(),
						ap.getLastRow() + 1,
						ap.getFirstColumn(),
						ap.getLastColumn() + 1);
			}
			
			if (ptg instanceof Area3DPtg) {
				throw new UnsupportedOperationException();
			}
			
			if (region != null) regions.add(region);
		}

		return regions;
	}

	@Override
	public List<ICell> getReferencedCells() {
		List<CellRegion> regions = getReferencedRegions();
		List<ICell> cells = new ArrayList<ICell>();
		for (int i = 0; i < regions.size(); i++) {
			CellRegion cellRegion = regions.get(i);
			cells.addAll(cellRegion.cells());
		}
		return cells;
	}

	@Override
	public Type getType() {
		return Type.FORMULA;
	}

}

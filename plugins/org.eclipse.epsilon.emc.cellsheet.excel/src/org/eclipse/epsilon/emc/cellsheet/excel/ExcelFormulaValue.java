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
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;

/**
 * Excel based representation of a Cell's Formula
 * 
 * @author Jonathan Co
 */
public class ExcelFormulaValue extends AbstractExcelCellValue<String> implements IFormulaCellValue {
	
	ExcelFormulaValue(ExcelCell cell) {
		super(cell);
		if (cell.delegate.getCellTypeEnum() != CellType.FORMULA) 
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

	@Override
	public List<ICellRegion> getReferencedRegions() {
		final Ptg[] tokens = PoiFormulaHelper.parseFormula(getCell().getBook(), this);
		final List<ICellRegion> regions = new ArrayList<ICellRegion>();
		
		for (Ptg ptg : tokens) {
			if (!(ptg instanceof OperandPtg)) continue;
			
			ICellRegion region = null;
			
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
			
			if (ptg instanceof AreaPtg) {
				AreaPtg ap = (AreaPtg) ptg;
				region = new ExcelCellRegion(
						cell.getBook(),
						cell.getSheet(),
						ap.getFirstRow(),
						ap.getLastRow() + 1,
						ap.getFirstColumn(),
						ap.getLastColumn() + 1);
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
		List<ICellRegion> regions = getReferencedRegions();
		List<ICell> cells = new ArrayList<ICell>();
		for (int i = 0; i < regions.size(); i++) {
			ICellRegion cellRegion = regions.get(i);
			cells.addAll(cellRegion.cells());
		}
		return cells;
	}
	
	@Override
	public IFormulaTree getFormulaTree() {
		return PoiFormulaHelper.buildFormulaTree(this);
	}
	
	@Override
	public String toString() {
		return this.getFormulaStr();
	}

	@Override
	public String getFormulaStr() {
		return cell.getDelegate().getCellFormula();
	}

}

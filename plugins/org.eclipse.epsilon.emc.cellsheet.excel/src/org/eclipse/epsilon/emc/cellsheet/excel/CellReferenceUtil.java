package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.emc.cellsheet.ElementId;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;

public class CellReferenceUtil {

	private CellReferenceUtil() {
		;
	}
	
	public static ElementId convertFromCellRef(CellReference cr) {
		return convertFromCellRef(null, cr);
	}

	public static ElementId convertFromCellRef(IBook workbook, CellReference cr) {
		String book = workbook == null ? null : workbook.getName();
		String sheet = cr.getSheetName();
		int row = cr.isRowAbsolute() ? cr.getRow() : -1;
		int col = cr.isColAbsolute() ? cr.getCol() : -1;
		return new ElementId(book, sheet, row, col);
	}

	public static ICell getCell(IBook workbook, CellReference cr) {
		return workbook.getCell(cr.getSheetName(), cr.getRow(), cr.getCol());
	}
}

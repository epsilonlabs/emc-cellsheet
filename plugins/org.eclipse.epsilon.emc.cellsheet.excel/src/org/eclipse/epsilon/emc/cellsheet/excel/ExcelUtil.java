package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.util.CellReference;

public class ExcelUtil {

	private ExcelUtil() {
		throw new AssertionError();
	}
	
	public static int colStringToIndex(String ref) {
		return CellReference.convertColStringToIndex(ref);
	}
	
	public static String indexToColString(int idx) {
		return CellReference.convertNumToColString(idx);
	}
}

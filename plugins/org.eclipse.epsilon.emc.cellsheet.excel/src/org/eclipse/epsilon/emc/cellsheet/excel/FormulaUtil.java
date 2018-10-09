package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.Ptg;

class FormulaUtil {

	private FormulaUtil() {
		throw new AssertionError();
	}

	static boolean isSumPtg(Ptg ptg) {
		return ptg instanceof AttrPtg && ((AttrPtg) ptg).isSum();
	}

}

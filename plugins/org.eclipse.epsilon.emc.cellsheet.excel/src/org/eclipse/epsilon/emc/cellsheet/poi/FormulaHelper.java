package org.eclipse.epsilon.emc.cellsheet.poi;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

public class FormulaHelper {

	private TokenMappingFormulaParser fp;

	public FormulaHelper(String formula, FormulaParsingWorkbook workbook, int sheetIndex, int rowIndex) {
		this.fp = new TokenMappingFormulaParser(formula, workbook, sheetIndex, rowIndex);
		this.fp.parse();
	}

	public ParseNode getRoot() {
		return fp._rootNode;
	}

	public Ptg[] getPtgs() {
		return fp.getRPNPtg(FormulaType.CELL);
	}

	public Map<Ptg, String> getTokenMap() {
		Map<Ptg, String> map = new LinkedHashMap<>();
		for (Ptg ptg : getPtgs()) {
			if (fp.ptgTokens.containsKey(ptg)) {
				map.put(ptg, fp.ptgTokens.get(ptg));
				continue;
			}
			map.put(ptg, toString(ptg));
		}

		return map;
	}

	/**
	 * Utility method for converting a PTG to String representation
	 * 
	 * @param ptg to convert
	 * @return String representation
	 * 
	 * @throws UnsupportedOperationException Encounters a ptg that cannot be
	 *                                       converted
	 */
	public static String toString(final Ptg ptg) {
		try {
			if (ptg instanceof ValueOperatorPtg) {
				final Method method = ValueOperatorPtg.class.getDeclaredMethod("getSid");
				method.setAccessible(true);
				switch ((Byte) method.invoke(ptg)) {
				case 0x03: // Add
					return "+";
				case 0x08: // Concat
					return "&";
				case 0x06: // Divide
					return "/";
				case 0x0b: // Equal
					return "=";
				case 0x0c: // GreaterEqual
					return ">=";
				case 0x0D: // GreaterThan
					return ">";
				case 0x0a: // LessEqual
					return "<=";
				case 0x09: // LessThan
					return "<";
				case 0x05: // Multiply
					return "*";
				case 0x0e: // NotEqual
					return "<>";
				case 0x14: // Percent
					return "%";
				case 0x07: // Power
					return "^";
				case 0x04: // Subtract
					return "-";
				case 0x13: // UnaryMinus
					return "-";
				case 0x12: // UnaryPlus
					return "+";
				default:
					break;
				}
			}
			return ptg.toFormulaString();
		} catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

}

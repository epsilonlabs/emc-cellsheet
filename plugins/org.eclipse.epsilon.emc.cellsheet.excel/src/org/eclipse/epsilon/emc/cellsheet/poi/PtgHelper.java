package org.eclipse.epsilon.emc.cellsheet.poi;

import java.lang.reflect.Method;

import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;

/**
 * Helper class for interacting with {@link Ptg} instances
 * 
 * @author Jonathan Co
 *
 */
public class PtgHelper {

	/**
	 * Check if ptg given is an optimised SUM ptg
	 * 
	 * @param ptg
	 * @return
	 */
	public static boolean isSumPtg(Ptg ptg) {
		return ptg instanceof AttrPtg && ((AttrPtg) ptg).isSum();
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

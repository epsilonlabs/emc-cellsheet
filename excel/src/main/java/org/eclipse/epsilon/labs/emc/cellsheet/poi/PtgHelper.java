/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
package org.eclipse.epsilon.labs.emc.cellsheet.poi;

import org.apache.poi.ss.formula.ptg.*;

import java.lang.reflect.Method;

/**
 * Helper class for interacting with {@link Ptg} instances
 *
 * @author Jonathan Co
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
     * @throws UnsupportedOperationException Encounters a ptg that cannot be
     *                                       converted
     */
    public static String valueOf(final Ptg ptg) {
        if (ptg instanceof ScalarConstantPtg) {
            if (ptg instanceof IntPtg) return String.valueOf(((IntPtg) ptg).getValue());
            if (ptg instanceof NumberPtg) return String.valueOf(((NumberPtg) ptg).getValue());
            if (ptg instanceof StringPtg) return ((StringPtg) ptg).getValue();
            if (ptg instanceof BoolPtg) return String.valueOf(((BoolPtg) ptg).getValue());
        }

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

package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Locale;

/**
 * <p>
 * Workbook related reference utilities
 * </p>
 *
 * @author Jonathan Co
 */
public class ReferenceUtil {

    /**
     * <p>
     * Private constructor
     * </p>
     */
    private ReferenceUtil() {
    }

    /**
     * <p>
     * Takes in a column reference portion of a CellRef and converts it from
     * ALPHA-26 number format to 0-based base 10. 'A' -&gt; 0 'Z' -&gt; 25 'AA'
     * -&gt; 26 'IV' -&gt; 255
     * </p>
     *
     * @param col A1 style column reference
     * @return 0-based column index
     */
    public static int a1ToIndex(String col) {
        int retval = 0;
        char[] refs = col.toUpperCase(Locale.ROOT).toCharArray();
        for (int k = 0; k < refs.length; k++) {
            char thechar = refs[k];
            if (thechar == '$') {
                if (k != 0) {
                    throw new IllegalArgumentException("Bad col ref format '" + col + "'");
                }
                continue;
            }

            // Character is uppercase letter, find relative value to A
            retval = (retval * 26) + (thechar - 'A' + 1);
        }
        return retval - 1;
    }

    /**
     * <p>
     * Takes in a 0-based base-10 column and returns a ALPHA-26 representation. For
     * instance {@code colIndexToString(3)} returns {@code "D"}
     * </p>
     *
     * <p>
     * Adapted from
     * {@link org.apache.poi.ss.util.CellReference#convertNumToColString(int)}
     * </p>
     *
     * @param col 0-based column index
     * @return A1 style column reference
     */
    public static String indexToA1(int col) {
        int excelColNum = col + 1;

        StringBuilder colRef = new StringBuilder(2);
        int colRemain = excelColNum;

        while (colRemain > 0) {
            int thisPart = colRemain % 26;
            if (thisPart == 0) {
                thisPart = 26;
            }
            colRemain = (colRemain - thisPart) / 26;

            // The letter A is at 65
            char colChar = (char) (thisPart + 64);
            colRef.insert(0, colChar);
        }

        return colRef.toString();
    }
}

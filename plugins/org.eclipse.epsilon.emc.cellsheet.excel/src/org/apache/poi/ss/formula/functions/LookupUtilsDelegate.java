package org.apache.poi.ss.formula.functions;

import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.RefEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.LookupUtils.LookupValueComparer;
import org.apache.poi.ss.formula.functions.LookupUtils.ValueVector;

/**
 * Delegate for accessing package-private utilities in POI.
 * 
 * This class delegates all calls through to
 * {@link org.apache.poi.ss.formula.functions.LookupUtils} which has default
 * visibility.
 * 
 * @author Jonathan Co
 *
 */
public class LookupUtilsDelegate {

	public static ValueVector createColumnVector(TwoDEval tableArray, int relativeColumnIndex) {
		return LookupUtils.createColumnVector(tableArray, relativeColumnIndex);
	}

	public static LookupValueComparer createLookupComparer(ValueEval lookupValue, boolean matchExact,
			boolean isMatchFunction) {
		return LookupUtils.createLookupComparer(lookupValue, matchExact, isMatchFunction);
	}

	public static ValueVector createRowVector(TwoDEval tableArray, int relativeRowIndex) {
		return LookupUtils.createRowVector(tableArray, relativeRowIndex);
	}

	public static ValueVector createVector(RefEval re) {
		return LookupUtils.createVector(re);
	}

	public static int lookupIndexOfValue(ValueEval lookupValue, ValueVector vector, boolean isRangeLookup)
			throws EvaluationException {
		return LookupUtils.lookupIndexOfValue(lookupValue, vector, isRangeLookup);
	}

	public static boolean resolveRangeLookupArg(ValueEval rangeLookupArg, int srcCellRow, int srcCellCol)
			throws EvaluationException {
		return LookupUtils.resolveRangeLookupArg(rangeLookupArg, srcCellRow, srcCellCol);
	}

	public static int resolveRowOrColIndexArg(ValueEval rowColIndexArg, int srcCellRow, int srcCellCol)
			throws EvaluationException {
		return LookupUtils.resolveRowOrColIndexArg(rowColIndexArg, srcCellRow, srcCellCol);
	}

	public static TwoDEval resolveTableArrayArg(ValueEval eval) throws EvaluationException {
		return LookupUtils.resolveTableArrayArg(eval);
	}

}

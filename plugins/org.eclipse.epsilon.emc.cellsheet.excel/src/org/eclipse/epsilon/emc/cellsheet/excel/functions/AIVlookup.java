package org.eclipse.epsilon.emc.cellsheet.excel.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.AreaEvalBase;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.ErrorEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function3Arg;
import org.apache.poi.ss.formula.functions.Function4Arg;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.emc.cellsheet.excel.functions.LookupUtils.ValueVector;

/**
 * Adapted from {@link org.apache.poi.ss.formula.functions.Vlookup}.
 * @author Jonathan Co
 *
 */
public class AIVlookup implements Function3Arg, Function4Arg, AIFunction {
	
	public static final String OLD_NAME = "VLOOKUP";
	public static final String NEW_NAME = "AIVLOOKUP";
	public static final int FUNCTION_ID = 102; 
	
	private static final ValueEval DEFAULT_ARG3 = BoolEval.TRUE;
		
	@Override
	public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
		return evaluate(args, ec.getRowIndex(), ec.getColumnIndex());
	}
	
	/**
	 * Duplicated from {@link org.apache.poi.ss.formula.functions.Var3or4ArgFunction}
	 */
	@Override
	public ValueEval evaluate(ValueEval[] args, int srcRowIndex, int srcColumnIndex) {
		switch (args.length) {
		case 3:
			return evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2]);
		case 4:
			return evaluate(srcRowIndex, srcColumnIndex, args[0], args[1], args[2], args[3]);
		}
		return ErrorEval.VALUE_INVALID;
	}

	@Override
	public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1,
			ValueEval arg2) {
		return evaluate(srcRowIndex, srcColumnIndex, arg0, arg1, arg2, DEFAULT_ARG3);
	}

	@Override
	public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval lookup_value, ValueEval table_array,
			ValueEval col_index, ValueEval range_lookup) {
				
		try {
			// Evaluation order:
			// lookup_value , table_array, range_lookup, find lookup value, col_index, fetch result
			ValueEval lookupValue = OperandResolver.getSingleValue(lookup_value, srcRowIndex, srcColumnIndex);
			TwoDEval tableArray = LookupUtils.resolveTableArrayArg(table_array);
			boolean isRangeLookup = LookupUtils.resolveRangeLookupArg(range_lookup, srcRowIndex, srcColumnIndex);
			int rowIndex = LookupUtils.lookupIndexOfValue(lookupValue, LookupUtils.createColumnVector(tableArray, 0), isRangeLookup);
			int colIndex = LookupUtils.resolveRowOrColIndexArg(col_index, srcRowIndex, srcColumnIndex);

			// Get actual cell reference rather than cell value
			if (!(tableArray instanceof AreaEvalBase))
				throw new AssertionError("Unknown Area type produced");
			
			final AreaEvalBase _tableArray = (AreaEvalBase) tableArray;
			if (_tableArray.getFirstSheetIndex() != _tableArray.getLastSheetIndex())
				throw new IllegalStateException("More than one sheet specified");
			
			return new StringEval(
					"'" + _tableArray.getFirstSheetIndex() + "'!" + 
					CellReference.convertNumToColString(colIndex) +
					(rowIndex + 1));			
		} catch (EvaluationException e) {
			return e.getErrorEval();
		}
	}
	
	/**
	 * Returns one column from an <tt>AreaEval</tt>
	 *
	 * @param colIndex assumed to be non-negative
	 *
	 * @throws EvaluationException (#REF!) if colIndex is too high
	 */
	private ValueVector createResultColumnVector(TwoDEval tableArray, int colIndex) throws EvaluationException {
		if(colIndex >= tableArray.getWidth()) {
			throw EvaluationException.invalidRef();
		}
		return LookupUtils.createColumnVector(tableArray, colIndex);
	}

	public ValueEval processResult(ValueEval value) {
		System.out.println("AIVLOOKUP Called");
		System.out.println(value);
		return value;
	}
	
	@Override
	public String getOldName() {
		return OLD_NAME;
	}
	
	@Override
	public String getNewName() {
		return NEW_NAME;
	}
	
	@Override
	public int getFunctionId() {
		return FUNCTION_ID;
	}
}
package org.eclipse.epsilon.emc.cellsheet.excel.functions;

import org.apache.poi.ss.formula.OperationEvaluationContext;
import org.apache.poi.ss.formula.TwoDEval;
import org.apache.poi.ss.formula.eval.AreaEvalBase;
import org.apache.poi.ss.formula.eval.BoolEval;
import org.apache.poi.ss.formula.eval.EvaluationException;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.StringEval;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.LookupUtilsDelegate;
import org.eclipse.epsilon.emc.cellsheet.excel.ExcelBook;

/**
 * Abstract Interpretation of VLOOKUP that returns the cell address of the resolved value.
 * 
 * Adapted from {@link org.apache.poi.ss.formula.functions.Vlookup}.
 * 
 * @author Jonathan Co
 *
 */
public class AiVlookup extends AbstractVar3or4ArgAiFunction {

  public static final String EXCEL_NAME = "VLOOKUP";
  public static final String AI_NAME = "AIVLOOKUP";
  public static final int FUNCTION_ID = 102;

  private static final ValueEval DEFAULT_ARG3 = BoolEval.TRUE;

  public AiVlookup(ExcelBook book) {
    super(book);
  }

  @Override
  public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
    return evaluate(args, ec.getRowIndex(), ec.getColumnIndex());
  }

  @Override
  public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1,
      ValueEval arg2) {
    return evaluate(srcRowIndex, srcColumnIndex, arg0, arg1, arg2, DEFAULT_ARG3);
  }

  @Override
  public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval lookup_value,
      ValueEval table_array, ValueEval col_index, ValueEval range_lookup) {

    try {
      // Evaluation order:
      // lookup_value , table_array, range_lookup, find lookup value, col_index, fetch result
      ValueEval lookupValue =
          OperandResolver.getSingleValue(lookup_value, srcRowIndex, srcColumnIndex);
      TwoDEval tableArray = LookupUtilsDelegate.resolveTableArrayArg(table_array);
      boolean isRangeLookup =
          LookupUtilsDelegate.resolveRangeLookupArg(range_lookup, srcRowIndex, srcColumnIndex);
      int rowIndex = LookupUtilsDelegate.lookupIndexOfValue(lookupValue,
          LookupUtilsDelegate.createColumnVector(tableArray, 0), isRangeLookup);
      int colIndex = LookupUtilsDelegate.resolveRowOrColIndexArg(col_index, srcRowIndex, srcColumnIndex);

      // Get actual cell reference rather than cell value
      // FIXME: Make this more robust - lets check for additional sheets and resolve the actual
      // value
      final AreaEvalBase _tableArray = (AreaEvalBase) tableArray;
      if (_tableArray.getFirstSheetIndex() != _tableArray.getLastSheetIndex()) {
        throw new IllegalStateException("More than one sheet specified");
      }

      return new StringEval(
          book.getCell(_tableArray.getFirstSheetIndex(), rowIndex, colIndex).getId());

    } catch (EvaluationException e) {
      throw new UnsupportedOperationException("Error occurred during evaluation of " + getAiName(),
          e);
    }
  }

  @Override
  public String getExcelName() {
    return EXCEL_NAME;
  }

  @Override
  public String getAiName() {
    return AI_NAME;
  }

  @Override
  public int getFunctionId() {
    return FUNCTION_ID;
  }
}

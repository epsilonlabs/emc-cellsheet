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

public class AiHlookup extends AbstractVar3or4ArgAiFunction {
  
  public static final String EXCEL_NAME = "HLOOKUP";
  public static final String AI_NAME = "AIHLOOKUP";
  public static final int FUNCTION_ID = 101;

  private static final ValueEval DEFAULT_ARG3 = BoolEval.TRUE;


  public AiHlookup(ExcelBook book) {
    super(book);
  }

  public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1,
      ValueEval arg2) {
    return evaluate(srcRowIndex, srcColumnIndex, arg0, arg1, arg2, DEFAULT_ARG3);
  }

  public ValueEval evaluate(int srcRowIndex, int srcColumnIndex, ValueEval arg0, ValueEval arg1,
      ValueEval arg2, ValueEval arg3) {
    try {
      // Evaluation order:
      // arg0 lookup_value, arg1 table_array, arg3 range_lookup, find lookup value, arg2 row_index,
      // fetch result
      ValueEval lookupValue = OperandResolver.getSingleValue(arg0, srcRowIndex, srcColumnIndex);
      TwoDEval tableArray = LookupUtilsDelegate.resolveTableArrayArg(arg1);
      boolean isRangeLookup =
          LookupUtilsDelegate.resolveRangeLookupArg(arg3, srcRowIndex, srcColumnIndex);
      int colIndex = LookupUtilsDelegate.lookupIndexOfValue(lookupValue,
          LookupUtilsDelegate.createRowVector(tableArray, 0), isRangeLookup);
      int rowIndex = LookupUtilsDelegate.resolveRowOrColIndexArg(arg2, srcRowIndex, srcColumnIndex);

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
      return e.getErrorEval();
    }
  }

  @Override
  public String getExcelName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getAiName() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int getFunctionId() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public ValueEval evaluate(ValueEval[] args, OperationEvaluationContext ec) {
    // TODO Auto-generated method stub
    return null;
  }
}

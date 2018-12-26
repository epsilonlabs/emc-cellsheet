package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemErrPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.formula.ptg.ScalarConstantPtg;
import org.apache.poi.ss.formula.ptg.StringPtg;
import org.eclipse.epsilon.emc.cellsheet.Type;

class FormulaUtil {

	private FormulaUtil() {
		throw new AssertionError();
	}

	static boolean isSumPtg(Ptg ptg) {
		return ptg instanceof AttrPtg && ((AttrPtg) ptg).isSum();
	}

	public static ExcelFormulaTree buildFormulaTree(ExcelFormulaCellValue cv) {
		Ptg[] ptgs = FormulaParser.parse(cv.getFormula(), cv.cell.book.fpw, FormulaType.CELL, cv.cell.sheet.getIndex(),
				cv.cell.getRowIndex());
		return buildFormulaTree(cv, ptgs);
	}

	/**
	 * Build a formula parse tree given a set of PTGs.
	 * 
	 * Adapted from
	 * {@link org.apache.poi.ss.formula.WorkbookEvaluator.evaluateFormula(OperationEvaluationContext,
	 * Ptg[])}
	 * 
	 * @param cv   CellValue used to resolve relative references in formula if any
	 *             exist
	 * @param ptgs Tokens to use to build tree in RPN order
	 * @return
	 */
	static ExcelFormulaTree buildFormulaTree(ExcelFormulaCellValue cv, Ptg[] ptgs) {
		final Deque<ExcelFormulaTree> stack = new ArrayDeque<>();
		
		for (int i = 0; i < ptgs.length; i++) {
			Ptg ptg = ptgs[i];
			ExcelFormulaTree tree = new ExcelFormulaTree(cv, ptgs, i);

			if (Boolean.parseBoolean(System.getProperty("debug"))) {
				System.out.println("* ptg " + i + ": " + ptg + ", operations: {");
				stack.forEach(o -> {
					System.out.println(o);
					System.out.println(o.formatAsTree());
				});
				System.out.println("}");
			}

			if (ptg instanceof AttrPtg) {
				AttrPtg attrPtg = (AttrPtg) ptg;

				// Excel encodes SUM() as a Attr token for some reason
				if (attrPtg.isSum()) {
					ptg = FuncVarPtg.SUM;
					tree.setType(Type.FUNCTION_NODE);
				}

				// Optimized CHOOSE, IF and SKIP statements do not apply for our purposes as we
				// want to
				// build a full parse tree
				if (attrPtg.isOptimizedChoose()) {
					continue;
				}
				if (attrPtg.isOptimizedIf()) {
					continue;
				}
				if (attrPtg.isSkip()) {
					continue;
				}

			}

			if (ptg instanceof ControlPtg) {
				// skip Parentheses, Attr, etc
				continue;
			}

			if (ptg instanceof MemFuncPtg || ptg instanceof MemAreaPtg) {
				// can ignore, rest of tokens for this expression are in OK RPN order
				continue;
			}

			if (ptg instanceof MemErrPtg) {
				continue;
			}

			if (ptg instanceof OperationPtg) {
				OperationPtg operationPtg = (OperationPtg) ptg;

				int numOps = operationPtg.getNumberOfOperands();
				ExcelFormulaTree[] ops = new ExcelFormulaTree[numOps];

				// Store and add child trees in reverse order as popping is occurring
				for (int j = numOps - 1; j >= 0; j--) {
					ops[j] = stack.pop();
				}
				for (ExcelFormulaTree child : ops) {
					tree.addChild(child);
				}
				
				if (ptg instanceof AbstractFunctionPtg) {
					tree.setType(Type.FUNCTION_NODE);
				} else {
					tree.setType(Type.OPERATOR_NODE);
				}
			}
			
			if (ptg instanceof ScalarConstantPtg) {
				if (ptg instanceof IntPtg) {
					tree.setType(Type.NUMERIC_VALUE_NODE);
					tree.setType(Type.INT_VALUE_NODE);
				}
				
				if (ptg instanceof NumberPtg) {
					tree.setType(Type.NUMERIC_VALUE_NODE);
					tree.setType(Type.DOUBLE_VALUE_NODE);
				}
				
				if (ptg instanceof StringPtg) {
					tree.setType(Type.STRING_VALUE_NODE);
				}
				
				if (ptg instanceof BoolPtg) {
					tree.setType(Type.BOOLEAN_VALUE_NODE);
				}
			}
			
			if (ptg instanceof OperandPtg) {
				if (ptg instanceof AreaI) {
					tree.setType(Type.ARRAY_REF_NODE);
				}
				
				if (ptg instanceof RefPtgBase) {
					tree.setType(Type.CELL_REF_NODE);
				}
			}
			
			stack.push(tree);
		}

		if (stack.size() > 1)
			throw new AssertionError();

		stack.peek().addKind(Type.ROOT_NODE);
		return stack.pop();
	}
}

package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.eclipse.epsilon.emc.cellsheet.AstSupertype;
import org.eclipse.epsilon.emc.cellsheet.poi.TokenMappingFormulaParser.TokenMappings;

/**
 * Static factory for {@link ExcelAst}'s
 * 
 * @author Jonathan Co
 *
 */
public class ExcelAstFactory {

	private ExcelAstFactory() {
		;
	}

	public static ExcelAst newInstance(String formula, ExcelCell cell) {
		final TokenMappings tokenMap = EvaluationHelper.getPtgs(formula, cell);
		final Deque<ExcelAst> stack = new ArrayDeque<>();
		for (Ptg ptg : tokenMap) {
			// Skip No-Ops
			if (tokenMap.getSupertype(ptg) == AstSupertype.NOOP)
				continue;

			final ExcelAst current = new ExcelAst(tokenMap.getToken(ptg), tokenMap.getSupertype(ptg),
					tokenMap.getType(ptg));

			if (ptg instanceof OperationPtg) {
				final OperationPtg cast = (OperationPtg) ptg;
				for (int j = cast.getNumberOfOperands(); j > 0; j--) {
					current.addChild(0, stack.pop());
				}
			}

			stack.push(current);
		}

		if (stack.size() != 1) {
			throw new AssertionError();
		}

		final ExcelAst root = stack.pop();
		root.setCellValue(cell.getCellValue());
		return root;
	}

	public static ExcelAst newInstance(ExcelCellValue cellValue) {
		return newInstance(cellValue.getFormula(), cellValue.getCell());
	}
}

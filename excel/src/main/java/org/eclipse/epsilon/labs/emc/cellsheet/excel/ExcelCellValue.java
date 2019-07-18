package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import java.util.ArrayDeque;
import java.util.Deque;

import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.eclipse.epsilon.labs.emc.cellsheet.AbstractCellValue;
import org.eclipse.epsilon.labs.emc.cellsheet.AstSupertype;
import org.eclipse.epsilon.labs.emc.cellsheet.AstType;
import org.eclipse.epsilon.labs.emc.cellsheet.CellValueType;
import org.eclipse.epsilon.labs.emc.cellsheet.poi.TokenMappingFormulaParser.TokenMappings;

public class ExcelCellValue extends AbstractCellValue {

	public ExcelCellValue() {
		;
	}

	public ExcelCellValue(ExcelCellValue.Builder b) {
		super(b);
	}

	@Override
	public ExcelCell getCell() {
		return (ExcelCell) super.getCell();
	}

	@Override
	public ExcelAst initAst() {
		if (type == CellValueType.FORMULA) {
			final TokenMappings tokenMap = EvaluationHelper.getPtgs(getFormula(), getCell());
			final Deque<ExcelAst> stack = new ArrayDeque<>();
			for (Ptg ptg : tokenMap) {
				// Skip No-Ops
				if (tokenMap.getSupertype(ptg) == AstSupertype.NOOP)
					continue;

				final ExcelAst current = new ExcelAst.Builder()	.withToken(tokenMap.getToken(ptg))
																.withSupertype(tokenMap.getSupertype(ptg))
																.withType(tokenMap.getType(ptg))
																.withCellValue(this)
																.build();

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
			root.setPosition(0);
			return root;
		}

		final ExcelAst.Builder b = new ExcelAst.Builder()	.withPosition(0)
															.withToken(getStringValue())
															.withSupertype(AstSupertype.OPERAND)
															.withCellValue(this);
		switch (type) {
		case BOOLEAN:
			b.withType(AstType.LOGICAL);
			break;
		case NUMERIC:
			b.withType(AstType.NUMBER);
			break;
		case ERROR:
			b.withType(AstType.ERROR);
			break;
		case DATE:
		case STRING:
			b.withType(AstType.TEXT);
			break;
		case BLANK:
		case NONE:
			b.withSupertype(AstSupertype.NOOP).withType(AstType.NOTHING);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return b.build();
	}

	public static class Builder extends AbstractCellValue.Builder<ExcelCellValue, Builder> {
		@Override
		public ExcelCellValue build() {
			return new ExcelCellValue(this);
		}
	}

}

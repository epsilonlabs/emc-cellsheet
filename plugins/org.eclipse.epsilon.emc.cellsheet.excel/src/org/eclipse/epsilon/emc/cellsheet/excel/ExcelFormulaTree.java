package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.emc.cellsheet.AbstractFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.Token;
import org.eclipse.epsilon.emc.cellsheet.Tokenizer;

/**
 * 
 * @author Jonathan Co
 *
 */
public class ExcelFormulaTree extends AbstractFormulaTree implements IFormulaTree, HasDelegate<Token> {

	/**
	 * @param cellValue
	 * @param parent
	 * @param token
	 */
	public ExcelFormulaTree(Token token) {
		super(token);
	}

	@Override
	public void setCellValue(IFormulaCellValue cellValue) {
		if (!(cellValue instanceof ExcelFormulaCellValue))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaCellValue");
		super.setCellValue(cellValue);
	}

	@Override
	public void setParent(IFormulaTree parent) {
		if (!(parent instanceof ExcelFormulaTree))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		super.setParent(parent);
	}

	@Override
	public void addChild(IFormulaTree child) {
		if (!(child instanceof ExcelFormulaTree))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		super.addChild(child);
	}

	@Override
	public String evaluate() {
		return doEvaluation(getFormula());
	}

	@Override
	public ICell evaluateCell() {
		throw new UnsupportedOperationException();

	}

	String doEvaluation(String formula) {
		CellReference ref = new CellReference(getSheet().getName(), getCell().getRowIndex(), getCell().getColIndex(),
				true, true);
		ValueEval result = ((ExcelBook) getBook()).getEvaluator().evaluate(formula, ref);
		return OperandResolver.coerceValueToString(result);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getClass().getSimpleName()).append("@").append(hashCode()).append("]");
		sb.append("(id: ").append(getId());

		sb.append(", formula: ");
		try {
			sb.append(getFormula());
		} catch (Exception e) {
			sb.append("INVALID");
		}

		sb.append(", value: ").append(getValue());
		sb.append(", type: ").append(getType());
		sb.append(", subtype: ").append(getSubtype());
		if (isRoot()) {
			sb.append(", isRoot: true");
		}

		if (isLeaf()) {
			sb.append(" , isLeaf: true");
		}

		sb.append(")");
		return sb.toString();
	}

	@Override
	public Token getDelegate() {
		return getToken();
	}

	public static ExcelFormulaTree fromString(String formula) {
		final List<Token> tokens = Tokenizer.parse(formula);
		final Deque<ExcelFormulaTree> stack = new LinkedList<>();

		final Iterator<Token> it = tokens.iterator();
		ExcelFormulaTree parent = new ExcelFormulaTree(it.next());
		stack.push(parent);

		while (it.hasNext()) {
			Token token = it.next();
			ExcelFormulaTree current = new ExcelFormulaTree(token);
			parent.addChild(current);

			if (token.isExprStart()) {
				stack.push(parent);
				parent = current;
				continue;
			}

			if (token.isExprEnd()) {
				parent = stack.pop();
			}
		}

		if (!stack.isEmpty())
			parent = stack.pop();

		assert stack.isEmpty();

		return parent;
	}
}

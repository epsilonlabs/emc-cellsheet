package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.BaseFormulaEvaluator;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.emc.cellsheet.AbstractFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.Token;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenSubtype;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenType;
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
		if (parent != null && !(cellValue instanceof ExcelFormulaCellValue))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaCellValue");
		super.setCellValue(cellValue);
	}

	@Override
	public void setParent(IFormulaTree parent) {
		if (parent != null && !(parent instanceof ExcelFormulaTree))
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
		return EvaluatorHelper.evaluate(getFormula(), this);
	}

	@Override
	public ICell evaluateCell() {
		
		// do stuff before then evaluate
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

		sb.append("(id: ");
		try {
			sb.append(getId());
		} catch (Exception e) {
			sb.append("null");
		}

		sb.append(", value: ").append(getValue() == null ? "null" : getValue());
		sb.append(", type: ").append(getType());
		sb.append(", subtype: ").append(getSubtype());
		if (isRoot()) {
			sb.append(", isRoot: true");
		}

		if (isLeaf()) {
			sb.append(" , isLeaf: true");
		}

		try {
			sb.append('\n').append(toTreeString());
		} catch (Exception e) {
			sb.append("ERROR");
		}

		sb.append(")");
		return sb.toString();
	}

	@Override
	public Token getDelegate() {
		return getToken();
	}

	public static ExcelFormulaTree fromString(String formula) {
		return (new TreeBuilder(formula)).parse();
	}

	/**
	 * Helper class for evaluating AST/Formula
	 * 
	 * @author Jonathan Co
	 *
	 */
	static enum EvaluatorHelper {
		INSTANCE;

		static Map<ExcelBook, FormulaParsingWorkbook> fpwMap = new HashMap<>();
		static Map<ExcelBook, WorkbookEvaluator> evalMap = new HashMap<>();

		static WorkbookEvaluator getEvaluator(IBook book) {
			return getEvaluator((ExcelBook) book);
		}

		// Is the FPW needed?
		static FormulaParsingWorkbook getFpw(ExcelBook book) {
			return fpwMap.computeIfAbsent(book, b ->
				{
					if (b.delegate instanceof HSSFWorkbook)
						return HSSFEvaluationWorkbook.create((HSSFWorkbook) b.delegate);
					if (b.delegate instanceof XSSFWorkbook)
						return XSSFEvaluationWorkbook.create((XSSFWorkbook) b.delegate);
					if (b.delegate instanceof SXSSFWorkbook)
						return SXSSFEvaluationWorkbook.create((SXSSFWorkbook) b.delegate);
					throw new AssertionError("Workbook evaluator not found for workbook format");
				});
		}

		static WorkbookEvaluator getEvaluator(ExcelBook book) {
			return evalMap.computeIfAbsent(book,
					b -> ((BaseFormulaEvaluator) b.delegate.getCreationHelper().createFormulaEvaluator())
							._getWorkbookEvaluator());
		}

		static String evaluate(String formula, ExcelFormulaTree tree) {
			final ValueEval result = getEvaluator(tree.getBook()).evaluate(formula, getCellRef(tree));
			return OperandResolver.coerceValueToString(result);
		}

		static CellReference getCellRef(ExcelFormulaTree tree) {
			return new CellReference(tree.getSheet().getName(), // Sheet name
					tree.getCell().getRowIndex(), // Cell row
					tree.getCell().getColIndex(), // Cell col
					true, // Is abs row
					true // Is abs col
			);
		}
//		
//		private static Ptg[] getPtgs(String formula, ExcelCell cell) {
//			return FormulaParser.parse(formula, // Formula String
//					((ExcelBook) cell.getBook()).fpw, // FormulaParsingWorkbook
//					FormulaType.CELL, // Formula Type
//					cell.getSheet().getIndex(), // Absolute Sheet index
//					cell.getRowIndex()); // Absolute Row index
//		}
//

	}

	/**
	 * Helper class for building Formula AST.
	 * 
	 * TODO: Should be moved to more generic location, need to find way of creating
	 * new instances of IFormulaTree from generic method
	 * 
	 * @author Jonathan Co
	 *
	 */
	static class TreeBuilder {
		final Deque<ExcelFormulaTree> operands = new LinkedList<>();
		final Deque<ExcelFormulaTree> operators = new LinkedList<>();
		final Deque<AtomicInteger> arity = new LinkedList<>();

		final String formula;

		TreeBuilder(String formula) {
			this.formula = formula;
		}

		ExcelFormulaTree parse() {
			final List<Token> tokens = Tokenizer.parse(formula);

			Token current;
			ExcelFormulaTree tree;
			for (int i = 0; i < tokens.size(); i++) {
				current = tokens.get(i);
				tree = new ExcelFormulaTree(current);

				// Operands
				if (current.getType() == TokenType.OPERAND) {
					operands.push(tree);
					continue;
				}

				// Binary Infix
				if (current.getType() == TokenType.OPERATOR_INFIX) {
					while (!operators.isEmpty() && nextOpsSubtype().compare(current.getSubtype()) > 0) {
						popOperator();
					}
					operators.push(tree);
					continue;
				}

				// Unary Prefix
				if (current.getType() == TokenType.OPERATOR_PREFIX) {
					while (!operators.isEmpty() && !operands.isEmpty()
							&& nextOpsSubtype().compare(current.getSubtype()) > 0
							&& (nextOpsType() == TokenType.OPERATOR_PREFIX
									|| nextOpsType() == TokenType.OPERATOR_POSTFIX)) {
						popOperator();
					}
					operators.push(tree);
					continue;
				}

				// Unary Postfix
				if (current.getType() == TokenType.OPERATOR_POSTFIX) {
					while (!operators.isEmpty() && !operands.isEmpty()
							&& nextOpsSubtype().compare(current.getSubtype()) > 0
							&& (nextOpsType() == TokenType.OPERATOR_PREFIX
									|| nextOpsType() == TokenType.OPERATOR_POSTFIX)) {
						popOperator();
					}
					operators.push(tree);
				}

				// Expressions/Parens
				if (current.getType() == TokenType.SUBEXPRESSION) {
					// Start
					if (current.getSubtype() == TokenSubtype.START) {
						operators.push(tree);
						continue;
					}

					// End
					if (current.getSubtype() == TokenSubtype.STOP) {
						ExcelFormulaTree top = popOperator();
						boolean reachedStart = top.getToken().getSubtype() == TokenSubtype.START;
						while (top != null && !reachedStart) {
							top = popOperator();
							reachedStart = top.getToken().getSubtype() == TokenSubtype.START;
						}
						if (!reachedStart) {
							throw new IllegalArgumentException("No matching start bracket for end bracket at " + i);
						}
						// Process any unary operators after
						while (nextOpsType() == TokenType.OPERATOR_PREFIX) {
							popOperator();
						}
						continue;
					}
					throw new IllegalArgumentException(
							String.format("Bad token given; Token: [%s], index: %d", current, i));
				}

				// Functions
				if (current.getType() == TokenType.FUNCTION) {
					if (current.getSubtype() == TokenSubtype.START) {
						arity.push(new AtomicInteger(1));
						operators.push(tree);
						continue;
					}

					if (current.getSubtype() == TokenSubtype.STOP) {
						popOperator();
						continue;
					}
					throw new IllegalArgumentException(
							String.format("Bad token given; Token: [%s], index: %d", current, i));
				}

				// Function Arguments
				if (current.getType() == TokenType.ARGUMENT) {
					arity.peek().getAndIncrement();
					continue;
				}
			}

			while (!operators.isEmpty()) {
				popOperator();
			}

			assert operands.size() == 1;

			return operands.pop();
		}

		/**
		 * Helper method for popping an operator from the operator stack and adding the
		 * necessary operands to its subtrees. Operators will not be popped if they
		 * cannot be applied correctly.
		 * 
		 * @return the popped operator or {@code null} if no operator popped
		 */
		ExcelFormulaTree popOperator() {
			if (operators.isEmpty() || operands.isEmpty())
				return null;
			final ExcelFormulaTree operator = operators.pop();

			if (operator.token.getType() == TokenType.SUBEXPRESSION)
				return operator;

			switch (operator.token.getType()) {

			case OPERATOR_PREFIX:
				operator.addChild(operands.pop());
				break;

			case OPERATOR_POSTFIX:
				operator.addChild(operands.pop());
				break;

			case OPERATOR_INFIX:
				operator.addChild(0, operands.pop());
				operator.addChild(0, operands.pop());
				break;

			case FUNCTION:
				final AtomicInteger count = arity.pop();
				while (count.getAndDecrement() > 0) {
					operator.addChild(0, operands.pop());
				}
				break;

			default:
				System.err.println("Not supported: " + operator);
				break;
			}

			operands.push(operator);
			return operator;
		}

		TokenType nextOpsType() {
			return operators.isEmpty() ? null : operators.peek().getToken().getType();
		}

		TokenSubtype nextOpsSubtype() {
			return operators.isEmpty() ? null : operators.peek().getToken().getSubtype();
		}

	}

}

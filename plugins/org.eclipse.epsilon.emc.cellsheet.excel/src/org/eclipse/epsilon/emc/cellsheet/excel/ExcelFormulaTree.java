package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.epsilon.emc.cellsheet.AbstractFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.Token;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenSubtype;
import org.eclipse.epsilon.emc.cellsheet.Token.TokenType;
import org.eclipse.epsilon.emc.cellsheet.Tokenizer;
import org.eclipse.epsilon.emc.cellsheet.Type;

/**
 * 
 * @author Jonathan Co
 *
 */
public class ExcelFormulaTree extends AbstractFormulaTree implements IFormulaTree, HasDelegate<Token> {

	public ExcelFormulaTree() {
		super();
	}

	public ExcelFormulaTree(IFormulaTree original) {
		super(original);
	}

	public ExcelFormulaTree(String value, TokenType type, TokenSubtype subtype) {
		super(value, type, subtype);
	}

	public ExcelFormulaTree(String value, Type type, Type subtype) {
		super(value, type, subtype);
	}

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
		return EvaluationHelper.evaluate(this);
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
			try {
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
							int arityInit = 1;
							if ((i + 1 < tokens.size()) && tokens.get(i + 1).getSubtype() == TokenSubtype.STOP) {
								arityInit = 0;
							}
							arity.push(new AtomicInteger(arityInit));
							operators.push(tree);
							continue;
						}

						if (current.getSubtype() == TokenSubtype.STOP) {
							// Case of no arg methods
							if (tokens.get(i - 1).getSubtype() == TokenSubtype.START) {
								arity.peek().set(0);
							}
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
			
			// Return Error Tree - bad formula given
			catch (Exception e) {
				return ExcelFormulaTree.fromString("#REF!");
			}
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

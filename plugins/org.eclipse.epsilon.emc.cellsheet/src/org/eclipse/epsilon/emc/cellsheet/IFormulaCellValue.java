package org.eclipse.epsilon.emc.cellsheet;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * <p>
 * Model element denoting a Formula cell value. In addition to the evaluation
 * result of the formula, the formula itself as a string and in AST form are
 * also given.
 * </p>
 * 
 * <p>
 * A {@link IFormulaCellValue} will contain an AST represented by
 * {@link IFormulaTree}'s.
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface IFormulaCellValue extends IStringCellValue {

	/**
	 * <p>
	 * Model element type: {@link Type#FORMULA_CELL_VALUE}
	 * </p>
	 */
	public static final Type TYPE = Type.FORMULA_CELL_VALUE;

	/**
	 * <p>
	 * Model element kinds: [{@link Type#FORMULA_CELL_VALUE},
	 * {@link Type#CELL_VALUE}]
	 * </p>
	 */
	public static final Type[] KINDS = { IFormulaCellValue.TYPE, Type.CELL_VALUE };

	@Deprecated
	public List<ICellRegion> getReferencedRegions();

	@Deprecated
	public List<ICell> getReferencedCells();

	/**
	 * <p>
	 * Get the raw formula String of this cell value
	 * </p>
	 * 
	 * @return this Cell's formula as defined in the spreadsheet itself.
	 */
	public String getFormula();

	/**
	 * Construct a new instance from the given {@link Token}
	 * 
	 * @param token
	 * @return
	 */
	public IFormulaTree fromToken(Token token);

	/**
	 * <p>
	 * Get the AST of the Formula String wrapped by this cell value.
	 * </p>
	 * 
	 * @return the AST of the formula
	 */
	default IFormulaTree getFormulaTree() {
		final List<Token> tokens = Tokenizer.parse(getFormula());
		final Deque<IFormulaTree> stack = new LinkedList<>();

		final ListIterator<Token> it = tokens.listIterator();
		IFormulaTree parent = fromToken(it.next());
		stack.push(parent);

		while (it.hasNext()) {
			Token token = it.next();
			IFormulaTree current = fromToken(token);

			parent.addChild(current);

			if (token.isExprStart()) {
				stack.push(parent);
				parent = current;
			}

			if (token.isExprEnd()) {
				parent = stack.pop();
			}
		}

		assert stack.isEmpty();

		return parent;
	}

	/**
	 * <p>
	 * Return this tree as a formatted string in the form:
	 * 
	 * <pre>
	 * {@code
	 * C4*VLOOKUP($A5,Assumptions!$B$4:$N$6,C$2)
	 * └── *
	 *     ├── C4
	 *     └── VLOOKUP
	 *         ├── $A5
	 *         ├── Assumptions!$B$4:$N$6
	 *         └── C$2
	 * }
	 * </pre>
	 * </p>
	 * 
	 * @return this tree formatted as a tree structure diagram
	 */
	default String formatAsTree() {
		return getFormulaTree().formatAsTree();
	}

	@Override
	default Type getType() {
		return IFormulaCellValue.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IFormulaCellValue.KINDS;
	}

}

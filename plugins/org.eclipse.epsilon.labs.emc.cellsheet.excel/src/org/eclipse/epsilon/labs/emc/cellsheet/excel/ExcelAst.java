package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.eclipse.epsilon.labs.emc.cellsheet.AbstractAst;

/**
 * Representation of an Abstract Syntax Tree of an Excel Cell.
 * 
 * @author Jonathan Co
 *
 */
public class ExcelAst extends AbstractAst<ExcelAst> {

	protected ExcelAst(Builder b) {
		super(b);
	}

	@Override
	public String evaluate() {
		return EvaluationHelper.evaluate(this);
	}

	public static class Builder extends AbstractAst.Builder<ExcelAst, Builder> {
		@Override
		public ExcelAst build() {
			return new ExcelAst(this);
		}
	}
}

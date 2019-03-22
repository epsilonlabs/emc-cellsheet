package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import org.eclipse.epsilon.labs.emc.cellsheet.AbstractAst;
import org.eclipse.epsilon.labs.emc.cellsheet.ICellValue;

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
	public void setCellValue(ICellValue cellValue) {
		if (parent != null && !(cellValue instanceof ExcelCellValue))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaCellValue");
		super.setCellValue(cellValue);
	}

	@Override
	public void setParent(ExcelAst parent) {
		if (parent != null && !(parent instanceof ExcelAst))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		super.setParent(parent);
	}

	@Override
	public void addChild(ExcelAst child) {
		if (!(child instanceof ExcelAst))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		super.addChild(child);
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

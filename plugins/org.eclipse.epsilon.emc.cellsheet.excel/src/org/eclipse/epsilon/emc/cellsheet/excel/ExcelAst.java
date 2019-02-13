package org.eclipse.epsilon.emc.cellsheet.excel;

import org.eclipse.epsilon.emc.cellsheet.AbstractAst;
import org.eclipse.epsilon.emc.cellsheet.IAst;
import org.eclipse.epsilon.emc.cellsheet.ICellValue;

/**
 * Representation of an Abstract Syntax Tree of an Excel Cell.
 * 
 * @author Jonathan Co
 *
 */
public class ExcelAst extends AbstractAst implements IAst {

	private String token;

	@Override
	public void setCellValue(ICellValue cellValue) {
		if (parent != null && !(cellValue instanceof ExcelCellValue))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaCellValue");
		super.setCellValue(cellValue);
	}

	@Override
	public void setParent(IAst parent) {
		if (parent != null && !(parent instanceof ExcelAst))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		super.setParent(parent);
	}

	@Override
	public void addChild(IAst child) {
		if (!(child instanceof ExcelAst))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		super.addChild(child);
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String evaluate() {
		return EvaluationHelper.evaluate(this);
	}

}
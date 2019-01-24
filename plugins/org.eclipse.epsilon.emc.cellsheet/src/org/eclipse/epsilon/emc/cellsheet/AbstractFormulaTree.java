package org.eclipse.epsilon.emc.cellsheet;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFormulaTree implements IFormulaTree {

	protected IFormulaCellValue cellValue;
	protected IFormulaTree parent;

	protected Token token;
	protected List<IFormulaTree> children;

	protected AbstractFormulaTree() {
		this(new Token());
	}

	protected AbstractFormulaTree(String value, Type type, Type subtype) {
		this(new Token(value, IFormulaTree.toTokenType(type), IFormulaTree.toTokenSubtype(subtype)));
	}

	protected AbstractFormulaTree(Token token) {
		this.token = token;
		this.children = new LinkedList<>();
	}

	@Override
	public IFormulaCellValue getCellValue() {
		return cellValue == null ? parent.getCellValue() : cellValue;
	}

	@Override
	public void setCellValue(IFormulaCellValue cellValue) {
		this.cellValue = cellValue;
	}

	@Override
	public IFormulaTree getParent() {
		return parent;
	}

	@Override
	public void setParent(IFormulaTree parent) {
		this.parent = parent;
	}

	@Override
	public Token getToken() {
		return token;
	}

	@Override
	public void setToken(Token token) {
		this.token = token;
	}

	@Override
	public List<IFormulaTree> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return token.toString();
	}

}

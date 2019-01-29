package org.eclipse.epsilon.emc.cellsheet;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(cellValue, children, parent, token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractFormulaTree other = (AbstractFormulaTree) obj;

		if (!(Objects.equals(cellValue, other.cellValue) || Objects.equals(parent, other.parent)
				|| Objects.equals(token, other.token))) {
			return false;
		}
		
		// Check the children
		if (children.size() != other.children.size()) {
			return false;
		}

		for (int i = 0; i < children.size(); i++) {
			if (!children.get(i).equals(other.children.get(i))) {
				return false;
			}
		}
		
		return true;
	}

}

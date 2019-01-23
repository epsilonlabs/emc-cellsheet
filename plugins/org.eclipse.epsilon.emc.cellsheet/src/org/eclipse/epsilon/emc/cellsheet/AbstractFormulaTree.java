package org.eclipse.epsilon.emc.cellsheet;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFormulaTree implements IFormulaTree {

	protected IFormulaCellValue cellValue;

	protected String token = "";
	protected Type type = Type.UNKNOWN;
	protected Type subtype = Type.NOTHING;

	protected IFormulaTree parent = null;
	protected List<IFormulaTree> children = new LinkedList<>();

	protected AbstractFormulaTree() {
	}

	protected AbstractFormulaTree(IFormulaCellValue cellValue, IFormulaTree parent) {
		this();
		this.cellValue = cellValue;
		this.parent = parent;
	}

	protected AbstractFormulaTree(IFormulaCellValue cellValue, IFormulaTree parent, String token, Type type,
			Type subtype) {
		this(cellValue, parent);
		this.token = token;
		this.type = type;
		this.subtype = type;
	}

	protected AbstractFormulaTree(IFormulaCellValue cellValue, IFormulaTree parent, Token token) {
		this(cellValue, parent, token.getValue(), Type.valueOf(token.getType().name()),
				Type.valueOf(token.getSubtype().name()));
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public Type[] getKinds() {
		return new Type[] { Type.FORMULA_TREE, type, subtype };
	}

	@Override
	public IFormulaCellValue getCellValue() {
		return cellValue;
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
	public List<IFormulaTree> getChildren() {
		return children;
	}

	@Override
	public String getFormula() {
		StringBuilder sb = new StringBuilder();

		accept(new Visitor() {
			@Override
			public void visit(IFormulaTree tree) {
				AbstractFormulaTree t = (AbstractFormulaTree) tree;

				if (t.type == Type.FUNCTION) {
					if (t.subtype == Type.START) 
						sb.append(t.token).append('(');
					if (t.subtype == Type.STOP) 
						sb.append(')');
				}
				
				else if (t.type == Type.OPERAND && t.subtype == Type.TEXT) {
					sb.append('"').append(t.token).append('"');
				}
				
				else {
					sb.append(t.token);
				}
				
				for (IFormulaTree child : t.getChildren()) {
					child.accept(this);
				}
			}
		});

		return sb.toString();
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
	public String toString() {
		return String.format("%s %s %s", token, type, subtype);
	}

}

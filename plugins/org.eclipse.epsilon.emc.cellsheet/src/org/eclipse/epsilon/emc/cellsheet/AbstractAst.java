package org.eclipse.epsilon.emc.cellsheet;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAst implements IAst {

	protected ICellValue cellValue;
	protected IAst parent;

	protected AstType type = AstType.UNKNOWN;
	protected AstSubtype subtype = AstSubtype.NOTHING;

	protected List<IAst> children;

	protected AbstractAst() {
		this.children = new LinkedList<>();
	}

	@Override
	public ICellValue getCellValue() {
		return isRoot() ? cellValue : parent.getCellValue();
	}

	@Override
	public void setCellValue(ICellValue cellValue) {
		this.cellValue = cellValue;
	}

	@Override
	public IAst getParent() {
		return parent;
	}

	@Override
	public void setParent(IAst parent) {
		this.parent = parent;
	}

	@Override
	public AstType getType() {
		return type;
	}

	@Override
	public void setType(AstType type) {
		this.type = type;
	}

	@Override
	public AstSubtype getSubtype() {
		return subtype;
	}

	@Override
	public void setSubtype(AstSubtype subtype) {
		this.subtype = subtype;
	}

	@Override
	public List<IAst> getChildren() {
		return children;
	}

	@Override
	public String toString() {
		return String.format("%s <%s , %s>", getToken(), type, subtype);
	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		AbstractAstTree o = (AbstractAstTree) obj;
//
//		if (!Objects.equals(cellValue, o.cellValue) || !Objects.equals(token, o.token))
//			return false;
//
//		// Check parent without recursion errors, we can only check the cellvalue and
//		// token
//		if (parent != null && parent != o.parent) {
//			if (o.parent == null)
//				return false;
//			if (!Objects.equals(parent.getCellValue(), o.parent.getCellValue())
//					|| !Objects.equals(parent.getToken(), o.parent.getToken()))
//				return false;
//		}
//
//		for (int i = 0, n = children.size(); i < n; i++) {
//			if (children.size() != o.children.size())
//				return false;
//
//			if (!children.get(i).equals(o.children.get(i)))
//				return false;
//		}
//
//		return true;
//	}

}

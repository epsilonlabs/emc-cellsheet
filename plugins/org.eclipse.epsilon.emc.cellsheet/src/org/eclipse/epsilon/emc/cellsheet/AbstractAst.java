package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	public ICell getCell() {
		return getCellValue().getCell();
	}

	@Override
	public IRow getRow() {
		return getCellValue().getRow();
	}

	@Override
	public ISheet getSheet() {
		return getCellValue().getSheet();
	}

	@Override
	public IBook getBook() {
		return getCellValue().getBook();
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
	public Set<ElementType> getKinds() {
		return Stream.of(CoreType.AST, getType(), getSubtype()).collect(Collectors.toSet());
	}

	@Override
	public List<IAst> getChildren() {
		return children;
	}

	@Override
	public IAst getChildAt(int index) {
		return index < getChildren().size() ? getChildren().get(index) : null;
	}

	@Override
	public void removeChildAt(int index) {
		getChildren().remove(index);
	}

	@Override
	public void addChild(IAst child) {
		child.setParent(this);
		getChildren().add(child);
	}

	@Override
	public void addChild(int index, IAst child) {
		child.setParent(this);
		getChildren().add(index, child);
	}

	@Override
	public IAst getFirst() {
		return getChildAt(0);
	}

	@Override
	public IAst getSecond() {
		return getChildAt(1);
	}

	@Override
	public int getChildCount() {
		return isLeaf() ? 0 : getAllChildren().size() - 1;
	}

	@Override
	public boolean isRoot() {
		return parent == null;
	}

	@Override
	public IAst getRoot() {
		return isRoot() ? this : parent.getRoot();
	}

	@Override
	public boolean isLeaf() {
		return children.isEmpty();
	}

	@Override
	public List<IAst> getAllChildren() {
		final List<IAst> list = new LinkedList<>();
		accept(new Visitor() {

			@Override
			public void visit(IAst tree) {
				for (IAst child : tree.getChildren()) {
					child.accept(this);
				}
				list.add(tree);
			}
		});
		return list;
	}

	@Override
	public String getFormula() {
		final StringBuilder sb = new StringBuilder();
		accept(new Visitor() {

			@Override
			public void visit(IAst tree) {
				// No children, straight append and return
				if (tree.isLeaf()) {
					sb.append(tree.getToken());
					return;
				}

				switch (tree.getType()) {
				case OPERATOR_INFIX:
					sb.append('(');
					tree.getFirst().accept(this);
					sb.append(tree.getToken());
					tree.getSecond().accept(this);
					sb.append(')');
					return;

				case OPERATOR_PREFIX:
					sb.append(tree.getToken());
					if (tree.getFirst().isLeaf()) {
						tree.getFirst().accept(this);
					} else {
						sb.append('(');
						tree.getFirst().accept(this);
						sb.append(')');
					}
					return;

				case OPERATOR_POSTFIX:
					if (tree.getFirst().isLeaf()) {
						tree.getFirst().accept(this);
					} else {
						sb.append('(');
						tree.getFirst().accept(this);
						sb.append(')');
					}
					sb.append(tree.getToken());
					return;

				default:
					sb.append(tree.getToken());
					sb.append('(');

					final Iterator<IAst> it = tree.getChildren().iterator();
					while (it.hasNext()) {
						it.next().accept(this);
						if (it.hasNext()) {
							sb.append(',');
						}
					}

					sb.append(')');
				}
			}
		});

		return sb.toString();
	}

	@Override
	public int getIndex() {
		return isRoot() ? 0 : parent.getChildren().indexOf(this);
	}

	@Override
	public String getId() {
		return String.format("%s%d/", getCellValue().getId(), getIndex());
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toStringTree() {
		return getFormula() + "\n" + toTreeString("", true);
	}

	@Override
	public String toString() {
		return String.format("%s <%s , %s>", getToken(), type, subtype);
	}

	/**
	 * Return this tree as a tree structure diagram
	 * 
	 * <pre>
	 * {@code
	 * └── *
	 *     ├── C4
	 *     └── VLOOKUP
	 *         ├── $A5
	 *         ├── Assumptions!$B$4:$N$6
	 *         └── C$2
	 * }
	 * </pre>
	 * 
	 * @param prefix to append onto start of this line
	 * @param isTail is this a tail element
	 * @return this tree formatted as a tree structure diagram
	 */
	protected String toTreeString(String prefix, boolean isTail) {
		StringBuilder sb = new StringBuilder();
		sb.append(prefix).append(isTail ? "└── " : "├── ").append(toString()).append("\n");
		ListIterator<IAst> it = getChildren().listIterator();
		while (it.hasNext()) {
			AbstractAst child = (AbstractAst) it.next(); // TODO: Fix potential cast error

			if (it.hasNext())
				sb.append(child.toTreeString(prefix + (isTail ? "    " : "│   "), false));
			else
				sb.append(child.toTreeString(prefix + (isTail ? "    " : "│   "), true));
		}
		return sb.toString();
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

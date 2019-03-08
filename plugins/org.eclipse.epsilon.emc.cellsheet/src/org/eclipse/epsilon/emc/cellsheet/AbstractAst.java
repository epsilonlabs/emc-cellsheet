package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractAst implements IAst {

	protected ICellValue cellValue;
	protected IAst parent;

	protected String token;
	protected AstSupertype supertype;
	protected AstType type;

	protected List<IAst> children = new LinkedList<>();
	
	protected String id = null;

	protected AbstractAst(Builder<?, ?> b) {
		this.token = b.token;
		this.supertype = b.supertype;
		this.type = b.type;

		this.cellValue = b.cellValue;
		this.parent = b.parent;
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
		this.id = null;
	}

	@Override
	public IAst getParent() {
		return parent;
	}

	@Override
	public void setParent(IAst parent) {
		this.parent = parent;
		this.id = null;
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public AstSupertype getSupertype() {
		return supertype;
	}

	@Override
	public AstType getType() {
		return type;
	}

	@Override
	public Set<ElementType> getKinds() {
		return Stream.of(CoreType.AST, getSupertype(), getType()).collect(Collectors.toSet());
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
		if (index > children.size()) {
			while (index >= children.size()) {
				children.add(null);
			}
		}
		child.setParent(this);
		getChildren().add(index, child);
	}

	@Override
	public void setChild(int index, IAst child) {
		if (index > children.size()) {
			while (index >= children.size()) {
				children.add(null);
			}
		}
		child.setParent(this);
		children.set(index, child);
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
		return isLeaf() ? 0 : getAll().size() - 1;
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
	public List<IAst> getAll() {
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

				switch (tree.getSupertype()) {
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
	public int getPosition() {
		return position;
	}

	@Override
	public String getId() {
		return (isRoot() ? cellValue.getId() : parent.getId()) + getPosition() + "/";
	}

	@Override
	public Iterator<IAst> iterator() {
		return children.iterator();
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
		return String.format("%s [%s]", buildToString(), token);
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

	@Override
	public int hashCode() {
		return Objects.hash(getId(), supertype, token, type);
	}

	// TODO: check children
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractAst other = (AbstractAst) obj;
		return Objects.equals(getId(), other.getId()) && supertype == other.supertype
				&& Objects.equals(token, other.token) && type == other.type;
	}

	@SuppressWarnings("unchecked")
	public static abstract class Builder<A extends AbstractAst, B extends Builder<A, B>> {
		private ICellValue cellValue;
		private IAst parent;

		private String token;
		private AstType type;
		private AstSupertype supertype;

		public Builder() {
		}

		public Builder(IAst ast) {
			this.token = ast.getToken();
			this.type = ast.getType();
			this.supertype = ast.getSupertype();
		}

		public B withToken(String token) {
			this.token = token;
			return (B) this;
		}

		public B withType(AstType type) {
			this.type = type;
			return (B) this;
		}

		public B withSupertype(AstSupertype supertype) {
			this.supertype = supertype;
			return (B) this;
		}

		public B withCellValue(ICellValue cellValue) {
			this.cellValue = cellValue;
			return (B) this;
		}

		public B withParent(IAst parent) {
			this.parent = parent;
			return (B) this;
		}

		public abstract A build();
	}
}

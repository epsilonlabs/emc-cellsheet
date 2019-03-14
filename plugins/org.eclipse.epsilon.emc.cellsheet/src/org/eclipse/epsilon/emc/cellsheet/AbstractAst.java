package org.eclipse.epsilon.emc.cellsheet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public abstract class AbstractAst<T extends AbstractAst<T>> implements IAst<T> {

	protected ICellValue cellValue;
	protected T parent;

	protected int position = -1;
	protected String token;
	protected AstSupertype supertype;
	protected AstType type;

	protected List<T> children = new LinkedList<T>();

	protected AbstractAst(Builder<?, ?> b) {
		this.position = b.position;
		this.token = b.token;
		this.supertype = b.supertype;
		this.type = b.type;

		this.cellValue = b.cellValue;
		this.parent = (T) b.parent;
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
		return cellValue;
	}

	@Override
	public void setCellValue(ICellValue cellValue) {
		this.cellValue = cellValue;
	}

	@Override
	public T getParent() {
		return parent;
	}

	@Override
	public void setParent(T parent) {
		this.parent = parent;
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
	public List<T> getChildren() {
		return children;
	}

	@Override
	public T getChildAt(int index) {
		return index < children.size() ? children.get(index) : null;
	}

	@Override
	public T removeChild(int index) {
		final T removed = children.remove(index);
		removed.position = -1;
		for (int i = index, n = children.size(); i < n; i++) {
			children.get(i).position--;
		}
		return removed;
	}

	@Override
	public void addChild(T child) {
		child.parent = (T) this;
		children.add(child);
		child.position = children.size() - 1;
	}

	@Override
	public void addChild(int index, T child) {
		for (int i = index, n = children.size(); i < n; i++) {
			children.get(i).position++;
		}
		child.parent = (T) this;
		child.position = index;
		children.add(index, child);
	}

	@Override
	public T setChild(int index, T child) {
		final T replaced = children.set(index, child);
		replaced.position = -1;

		child.parent = (T) this;
		child.position = index;

		return replaced;
	}

	@Override
	public T getFirst() {
		return children.get(0);
	}

	@Override
	public T getSecond() {
		return children.get(1);
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
	public T getRoot() {
		return isRoot() ? (T) this : parent.getRoot();
	}

	@Override
	public boolean isLeaf() {
		return children.isEmpty();
	}

	@Override
	public List<T> getAll() {
		final List<T> list = new LinkedList<>();
		accept(new Visitor<T>() {

			@Override
			public void visit(T tree) {
				for (T child : tree.getChildren()) {
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
		accept(new Visitor<T>() {

			@Override
			public void visit(T tree) {
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

					final Iterator<T> it = tree.getChildren().iterator();
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

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String getId() {
		return (isRoot() ? cellValue.getId() : parent.getId()) + getPosition() + "/";
	}

	@Override
	public Iterator<T> iterator() {
		return children.iterator();
	}

	@Override
	public void accept(Visitor<T> visitor) {
		visitor.visit((T) this);
	}

	@Override
	public int compareTo(T o) {
		if (null == o)
			return 1;
		if (this == o)
			return 0;
		return Integer.compare(position, o.position);
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
		ListIterator<T> it = children.listIterator();
		while (it.hasNext()) {
			T child = (T) it.next(); // TODO: Fix potential cast error

			if (it.hasNext())
				sb.append(child.toTreeString(prefix + (isTail ? "    " : "│   "), false));
			else
				sb.append(child.toTreeString(prefix + (isTail ? "    " : "│   "), true));
		}
		return sb.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(cellValue, children, position, token, type, supertype);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		final T other = (T) obj;
		if (!Objects.equals(cellValue, other.cellValue))
			return false;
		return Objects.equals(position, other.position)
				&& Objects.equals(token, other.token)
				&& Objects.equals(type,  other.type)
				&& Objects.equals(supertype, other.supertype)
				&& Objects.equals(children, other.children);
	}

	public static abstract class Builder<A extends AbstractAst<A>, B extends Builder<A, B>> {
		private ICellValue cellValue;
		private A parent;

		private int position = -1;
		private String token;
		private AstType type;
		private AstSupertype supertype;

		public Builder() {
		}

		public Builder(A ast) {
			this.token = ast.getToken();
			this.type = ast.getType();
			this.supertype = ast.getSupertype();
		}

		public B withPosition(int position) {
			this.position = position;
			return (B) this;
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

		public B withParent(A parent) {
			this.parent = parent;
			return (B) this;
		}

		public abstract A build();
	}

}

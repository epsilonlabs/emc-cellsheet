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

	protected final String token;
	protected final AstSupertype supertype;
	protected final AstType type;

	protected List<IAst> children = new LinkedList<>();

	protected AbstractAst(String token, AstSupertype supertype, AstType type) {
		this.token = token;
		this.supertype = supertype;
		this.type = type;
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
		if (isRoot()) {
			return 0;
		}
		if (parent != null) {
			int count = 0;
			final Iterator<IAst> it = parent.getChildren().iterator();
			while (it.hasNext()) {
				if (it.next() == this) {
					return count;
				}
				count++;
			}
		}
		return -1;
	}

	@Override
	public String getId() {
		final String prev = isRoot() ? getCellValue().getId() : parent.getId();
		return String.format("%s%d/", prev, getPosition());
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractAst other = (AbstractAst) obj;
		return Objects.equals(getId(), other.getId()) 
				&& supertype == other.supertype
				&& Objects.equals(token, other.token) 
				&& type == other.type;
	}

}

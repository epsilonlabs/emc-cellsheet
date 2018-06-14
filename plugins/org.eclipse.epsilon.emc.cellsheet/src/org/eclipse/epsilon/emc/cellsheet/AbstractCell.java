package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractCell implements HasType, ICell {

	public static final Type TYPE = Type.CELL;
	public static final Type[] KINDS = {AbstractCell.TYPE};
	
	@Override
	public int compareTo(ICell o) {
		if (null == o) return 1;
		if (this == o) return 0;
		
		int parent = this.getRow().compareTo(o.getRow());
		return parent == 0 ? Integer.compare(this.getColIndex(), o.getColIndex()) : parent;
	}
	
	@Override
	public Type getType() {
		return AbstractCell.TYPE;
	}
	
	@Override
	public Type[] getKinds() {
		return AbstractCell.KINDS;
	}
	
}

package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractCell implements HasType, ICell {

	@Override
	public int compareTo(ICell o) {
		if (null == o) return 1;
		if (this == o) return 0;
		
		int parent = this.getRow().compareTo(o.getRow());
		return parent == 0 ? Integer.compare(this.getColIndex(), o.getColIndex()) : parent;
	}
	
	@Override
	public Type getType() {
		return ICell.TYPE;
	}
	
	@Override
	public Type[] getKinds() {
		return ICell.KINDS;
	}
	
}

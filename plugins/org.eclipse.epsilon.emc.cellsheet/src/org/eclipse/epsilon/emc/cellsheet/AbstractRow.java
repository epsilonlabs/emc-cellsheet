package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractRow implements HasType, IRow {

	public static final Type[] KINDS = {IRow.TYPE};
	
	@Override
	public int compareTo(IRow o) {
		if (null == o) return 1;
		if (this == o) return 0;
		
		int parent = this.getSheet().compareTo(o.getSheet());
		return parent == 0 ? Integer.compare(this.getIndex(), o.getIndex()) : parent;
	}
	
	@Override
	public Type getType() {
		return IRow.TYPE;
	}
	
	@Override
	public Type[] getKinds() {
		return AbstractRow.KINDS;
	}
	
}

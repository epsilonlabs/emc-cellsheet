package org.eclipse.epsilon.emc.cellsheet;

public abstract class AbstractSheet implements HasType, ISheet {
	
	@Override
	public int compareTo(ISheet o) {
		if (o == null)
			return 1;
		if (this == o)
			return 0;
		return Integer.compare(this.getIndex(), o.getIndex());
	}

	@Override
	public Type getType() {
		return ISheet.TYPE;
	}

	@Override
	public Type[] getKinds() {
		return ISheet.KIND;
	}

}

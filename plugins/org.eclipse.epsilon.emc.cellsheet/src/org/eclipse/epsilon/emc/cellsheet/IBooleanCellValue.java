package org.eclipse.epsilon.emc.cellsheet;

public interface IBooleanCellValue extends ICellValue<Boolean> {

	public static final Type TYPE = Type.BOOLEAN_CELL_VALUE;
	public static final Type[] KINDS = {IBooleanCellValue.TYPE, Type.CELL_VALUE};
	
	@Override
	default Type getType() {
		return IBooleanCellValue.TYPE;
	}
	
	@Override
	default Type[] getKinds() {
		return IBooleanCellValue.KINDS;
	}
	
}

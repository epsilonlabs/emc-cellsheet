package org.eclipse.epsilon.emc.cellsheet;

public interface IErrorCellValue extends ICellValue<String> {
	public static final Type TYPE = Type.ERROR_CELL_VALUE;
	public static final Type[] KINDS = { IErrorCellValue.TYPE, Type.CELL_VALUE };

	@Override
	default Type getType() {
		return IErrorCellValue.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IErrorCellValue.KINDS;
	}
}

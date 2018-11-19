package org.eclipse.epsilon.emc.cellsheet;

import java.util.Date;

public interface IDateCellValue extends ICellValue<Date> {

	public static final Type TYPE = Type.DATE_CELL_VALUE;
	public static final Type[] KINDS = { IDateCellValue.TYPE, Type.CELL_VALUE };

	@Override
	default Type getType() {
		return IDateCellValue.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IDateCellValue.KINDS;
	}

}

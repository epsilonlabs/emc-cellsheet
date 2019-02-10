package org.eclipse.epsilon.emc.cellsheet;

import java.util.Date;

/**
 * <p>
 * Model element denoting a Date formatted cell value
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface IDateCellValue extends ICellValue<Date> {

	/**
	 * <p>
	 * Model element type: {@link Type#DATE_CELL_VALUE}
	 * </p>
	 */
	public static final Type TYPE = Type.DATE_CELL_VALUE;
	
	/**
	 * <p>
	 * Model element kinds: [{@link Type#DATE_CELL_VALUE}, {@link Type#CELL_VALUE}]
	 * </p>
	 */
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

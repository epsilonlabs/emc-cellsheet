package org.eclipse.epsilon.emc.cellsheet;

/**
 * <p>
 * Model element denoting a blank cell value
 * </p>
 * 
 * <p>
 * In theory a cell could also return null as it's value
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface IBlankCellValue extends ICellValue<Void> {

	/**
	 * <p>
	 * Model element type: {@link Type#CELL}
	 * </p>
	 */
	public static final Type TYPE = Type.BLANK_CELL_VALUE;

	/**
	 * <p>
	 * Model element kinds: [{@link Type#BLANK_CELL_VALUE}, {@link Type#CELL_VALUE}]
	 * </p>
	 */
	public static final Type[] KINDS = { IBlankCellValue.TYPE, Type.CELL_VALUE };

	@Override
	default Type getType() {
		return IBlankCellValue.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IBlankCellValue.KINDS;
	}

}

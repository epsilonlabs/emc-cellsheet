package org.eclipse.epsilon.emc.cellsheet;

/**
 * <p>
 * Model element denoting a Boolean Cell Value
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface IBooleanCellValue extends ICellValue<Boolean> {

	/**
	 * <p>
	 * Model element type: {@link Type#BOOLEAN_CELL_VALUE}
	 * </p>
	 */
	public static final Type TYPE = Type.BOOLEAN_CELL_VALUE;

	/**
	 * <p>
	 * Model element kinds: [{@link Type#BOOLEAN_CELL_VALUE},
	 * {@link Type#CELL_VALUE}]
	 * </p>
	 */
	public static final Type[] KINDS = { IBooleanCellValue.TYPE, Type.CELL_VALUE };

	@Override
	default Type getType() {
		return IBooleanCellValue.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IBooleanCellValue.KINDS;
	}

}

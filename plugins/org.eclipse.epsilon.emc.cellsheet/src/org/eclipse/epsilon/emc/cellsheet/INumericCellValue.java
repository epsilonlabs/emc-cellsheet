package org.eclipse.epsilon.emc.cellsheet;

/**
 * <p>
 * Model element denoting a Numeric Cell Value as a Double.
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface INumericCellValue extends ICellValue<Double> {

	/**
	 * <p>
	 * Model element type: {@link Type#NUMERIC_CELL_VALUE}
	 * </p>
	 */
	public static final Type TYPE = Type.NUMERIC_CELL_VALUE;

	/**
	 * <p>
	 * Model element kinds: [{@link Type#NUMERIC_CELL_VALUE},
	 * {@link Type#CELL_VALUE}]
	 * </p>
	 */
	public static final Type[] KINDS = { INumericCellValue.TYPE, Type.CELL_VALUE };

	@Override
	default Type getType() {
		return INumericCellValue.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return INumericCellValue.KINDS;
	}

}

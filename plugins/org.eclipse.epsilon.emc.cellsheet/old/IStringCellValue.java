package org.eclipse.epsilon.emc.cellsheet;

/**
 * <p>
 * Model element denoting a String Cell Value
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface IStringCellValue extends ICellValue<String> {

	/**
	 * <p>
	 * Model element type: {@link Type#STRING_CELL_VALUE}
	 * </p>
	 */
	public static final Type TYPE = Type.STRING_CELL_VALUE;

	/**
	 * <p>
	 * Model element kinds: [{@link Type#STRING_CELL_VALUE},
	 * {@link Type#CELL_VALUE}]
	 * </p>
	 */
	public static final Type[] KINDS = { IStringCellValue.TYPE, Type.CELL_VALUE };

	@Override
	default Type getType() {
		return IStringCellValue.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IStringCellValue.KINDS;
	}

}

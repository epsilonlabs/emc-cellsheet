package org.eclipse.epsilon.emc.cellsheet;

/**
 * <p>
 * Model element denoting an Error in evaluating the value of the cell, usually
 * caused by a bad formula.
 * </p>
 * 
 * <p>
 * This cell value wraps a String describing the error. In most cases this will
 * be a generic {@code N/A} or {@code ERROR} string though a stack trace may
 * also be reported if the error occurred within this API Library.
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface IErrorCellValue extends ICellValue<String> {

	/**
	 * <p>
	 * Model element type: {@link Type#ERROR_CELL_VALUE}
	 * </p>
	 */
	public static final Type TYPE = Type.ERROR_CELL_VALUE;

	/**
	 * <p>
	 * Model element kinds: [{@link Type#ERROR_CELL_VALUE}, {@link Type#CELL_VALUE}]
	 * </p>
	 */
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

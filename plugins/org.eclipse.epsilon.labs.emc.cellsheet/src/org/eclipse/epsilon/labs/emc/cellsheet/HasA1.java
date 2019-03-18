package org.eclipse.epsilon.labs.emc.cellsheet;

/**
 * <p>
 * Model element that can be address in a standard A1 style, i.e. A1, A2,
 * [Book]Sheet!A4
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface HasA1 {

	/**
	 * </p>
	 * The A1 style reference of this model element
	 * </p>
	 * 
	 * @return the A1 style reference of this model element
	 */
	public String getA1();
	
}

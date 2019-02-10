package org.eclipse.epsilon.emc.cellsheet;

/**
 * Tag interface for Model Elements
 * 
 * Defines methods for retrieving types and kinds
 * 
 * @author Jonathan Co
 *
 */
public interface HasType {

	/**
	 * @return the concrete type associated with the model element.
	 */
	public ElementType getType();

	/**
	 * @return the kinds associated with the model element. Hierarchy is not defined
	 *         in this system so types may be sub/super-types. Will include the
	 *         concrete type as retrieved from {@link #getType()}
	 */
	default ElementType[] getKinds() {
		return new ElementType[] { getType() };
	}

}

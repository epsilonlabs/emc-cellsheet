package org.eclipse.epsilon.emc.cellsheet;

/**
 * Interface for model elements with a type.
 * 
 * @author Jonathan Co
 *
 */
public interface HasType {

	/**
	 * @return the concrete type associated with the model element.
	 */
	public Type getType();

	/**
	 * @return the kinds associated with the model element. Hierarchy is not defined
	 *         in this system so types may be sub/super-types. Will include the
	 *         concrete type as retrieved from {@link #getType()}
	 */
	default Type[] getKinds() {
		return new Type[] { getType() };
	}

}

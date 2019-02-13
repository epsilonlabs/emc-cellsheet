package org.eclipse.epsilon.emc.cellsheet;

import java.util.Set;

/**
 * Tag interface for Model Elements
 * 
 * Defines methods for retrieving types and kinds
 * 
 * @author Jonathan Co
 *
 */
public interface HasType extends HasBook {

	/**
	 * @return the concrete type associated with the model element.
	 */
	public ElementType getType();

	/**
	 * @return the kinds associated with the model element. Hierarchy is not defined
	 *         in this system so types may be sub/super-types. Will include the
	 *         concrete type as retrieved from {@link #getType()}
	 */
	public Set<ElementType> getKinds();

}

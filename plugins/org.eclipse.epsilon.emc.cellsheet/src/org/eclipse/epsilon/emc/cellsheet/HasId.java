package org.eclipse.epsilon.emc.cellsheet;

/**
 * <p>
 * Interface for model elements that can be assigned a unique ID
 * </p>
 * 
 * <p>
 * In most cases implementations should be able to reuse the default
 * implementations defined in the model element interface definitions.
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface HasId extends HasType {

	/**
	 * </p>
	 * Retrieve the String ID of this model element
	 * </p>
	 * 
	 * @return the Unique ID
	 */
	public String getId();

}

package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Set;
import java.util.stream.Collectors;

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
public interface HasId {

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

	/**
	 * </p>
	 * Retrieve the String ID of this model element
	 * </p>
	 * 
	 * @return the Unique ID
	 */
	public String getId();

	public IBook getBook();

	default public String buildToString() {
		return String.format("%s <%s> <%s>", // Format
				getId(), // ID
				getType().getTypename(), // Typename
				getKinds().stream().map(ElementType::getTypename).collect(Collectors.joining(","))// Kinds
		);
	}
}
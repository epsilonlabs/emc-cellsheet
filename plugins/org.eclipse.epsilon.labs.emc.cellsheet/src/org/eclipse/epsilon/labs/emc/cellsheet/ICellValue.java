package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * Wrapper for the actual contents of a {@link ICell}.
 * </p>
 * 
 * @author Jonathan Co
 *
 */
public interface ICellValue extends HasId, HasCell, Comparable<ICellValue>, Iterable<IAst<?>> {

	public static final ElementType SUBTYPE = CoreType.CELL_VALUE;

	@Override
	default public Set<ElementType> getKinds() {
		return Stream.of(getType(), SUBTYPE).collect(Collectors.toSet());
	}

	/**
	 * <p>
	 * Retrieve the concrete type of this cell value. Can be any of those listed in
	 * {@link CellValueType}.
	 * </p>
	 * 
	 * @return the concrete type of this cell value
	 */
	public CellValueType getType();

	/**
	 * <p>
	 * Set the concrete type of this cell value.
	 * </p>
	 * @param type
	 */
	public void setType(CellValueType type);

	public boolean getBooleanValue();

	public double getNumericValue();

	public Date getDateValue();

	public String getStringValue();

	public String getFormula();

	public String getErrorValue();

	public IAst<?> getAst();

	/**
	 * Get the parent containing cell of this cell value
	 * 
	 * @return the parent cell
	 */
	public ICell getCell();

	public boolean isBlank();

}

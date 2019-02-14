package org.eclipse.epsilon.emc.cellsheet;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * CellValue is an abstract model element that defines the contents of a Cell.
 * <p>
 * 
 * @author Jonathan Co
 *
 * @param <T> Primitive type that the Cell Value wraps
 */
public interface ICellValue extends HasId, HasCell, Comparable<ICellValue> {

	public static final ElementType SUBTYPE = CoreType.CELL_VALUE;

	@Override
	default public Set<ElementType> getKinds() {
		return Stream.of(getType(), SUBTYPE).collect(Collectors.toSet());
	}
	
	public CellValueType getType();

	public boolean getBoolean();

	public double getNumber();

	public Date getDate();

	public String getString();

	public String getFormula();

	public String getError();

	public IAst getAst();

	/**
	 * Get the parent containing cell of this cell value
	 * 
	 * @return the parent cell
	 */
	public ICell getCell();

}

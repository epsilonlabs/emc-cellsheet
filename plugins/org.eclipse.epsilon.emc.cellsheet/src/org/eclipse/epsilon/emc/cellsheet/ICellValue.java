package org.eclipse.epsilon.emc.cellsheet;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

	public static final ElementType TYPE = CoreType.CELL_VALUE;
	public static final Set<ElementType> KINDS = new HashSet<>(Arrays.asList(TYPE));

	@Override
	default public ElementType getType() {
		return TYPE;
	}

	@Override
	default public Set<ElementType> getKinds() {
		return KINDS;
	}

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

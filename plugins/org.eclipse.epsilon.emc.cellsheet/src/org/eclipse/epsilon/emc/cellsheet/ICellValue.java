package org.eclipse.epsilon.emc.cellsheet;

import java.util.Date;

/**
 * <p>
 * CellValue is an abstract model element that defines the contents of a Cell.
 * <p>
 * 
 * @author Jonathan Co
 *
 * @param <T> Primitive type that the Cell Value wraps
 */
public interface ICellValue extends HasId, Comparable<ICellValue> {

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

	/**
	 * @return the Row this CellValue belongs to
	 */
	default public IRow getRow() {
		return getCell().getRow();
	}

	/**
	 * @return the Sheet this CellValue belongs to
	 */
	default public ISheet getSheet() {
		return getCell().getSheet();
	}

	/**
	 * @return the Book this CellValue belongs to
	 */
	default public IBook getBook() {
		return getCell().getBook();
	}

	@Override
	default String getId() {
		return getCell().getId() + "value/";
	}

	@Override
	default int compareTo(ICellValue o) {
		return getCell().compareTo(o.getCell());
	}

	@Override
	default ElementType[] getKinds() {
		return new ElementType[] { getType(), CellValueType.SUPER };
	}

}

package org.eclipse.epsilon.emc.cellsheet;

/**
 * <p>
 * CellValue is an abstract model element that defines the contents of a Cell.
 * <p>
 * 
 * <p>
 * Concrete model element types will primarily be wrappers for Java
 * primitives/boxed values. These include: {@link IBlankCellValue},
 * {@link IErrorCellValue}, {@link IBooleanCellValue},
 * {@link INumericCellValue}, {@link IDateCellValue}, {@link IStringCellValue}.
 * <p>
 * 
 * <p>
 * {@link IFormulaCellValue} is a special case that creates an AST of a cell
 * formula in addition to holding the raw formula and the evaluation result of
 * the formula.
 * </p>
 * 
 * @author Jonathan Co
 *
 * @param <T> Primitive type that the Cell Value wraps
 */
public interface ICellValue<T> extends HasId, Comparable<ICellValue<?>> {

	/**
	 * Get the parent containing cell of this cell value
	 * 
	 * @return the parent cell
	 */
	public ICell getCell();

	/**
	 * Retrieve the wrapped value of this cell value
	 * 
	 * @return the value of this cell value
	 */
	public T getValue();

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
	default int compareTo(ICellValue<?> o) {
		return getCell().compareTo(o.getCell());
	}

}

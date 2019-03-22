package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 * Cell are core structural model elements representing discrete cells within a
 * workbook.
 * </p>
 * 
 * <p>
 * Cells can be identified by using a tuple consisting of:
 * <ul>
 * <li>Book</li>
 * <li>Sheet Index/Name</li>
 * <li>Row Index</li>
 * <li>Column Index/Alpha</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Each Cell contains a {@link ICellValue} that represents the contents of the
 * cell. This can be one of {@link IBlankCellValue}, {@link IErrorCellValue},
 * {@link IBooleanCellValue}, {@link INumericCellValue}, {@link IDateCellValue},
 * {@link IStringCellValue} or {@link IFormulaCellValue}.
 * <p>
 * 
 * @author Jonathan Co
 *
 */
public interface ICell extends HasId, HasA1, HasRow, Comparable<ICell>, Iterable<ICellValue> {

	public static final ElementType TYPE = CoreType.CELL;
	public static final Set<ElementType> KINDS = new HashSet<>(Arrays.asList(TYPE));

	@Override
	default public ElementType getType() {
		return TYPE;
	}

	@Override
	default public Set<ElementType> getKinds() {
		return KINDS;
	}
	
	/**
	 * @return 0-based row index of this Cell
	 */
	public int getRowIndex();

	/**
	 * @return 0-based column index of this Cell
	 */
	public int getColIndex();

	/**
	 * @return Alpha-based column reference of this Cell
	 */
	public String getA1Col();

	/**
	 * @return A1 style row index of this Cell (1-based index)
	 */
	public int getA1Row();

	public ICellValue getCellValue();

	/**
	 * @return {@code true} if the cell contains no value, {@code false} otherwise
	 */
	public boolean isBlank();

	public int compareTo(ICell o);

	public String getId();

	public String getA1();

}

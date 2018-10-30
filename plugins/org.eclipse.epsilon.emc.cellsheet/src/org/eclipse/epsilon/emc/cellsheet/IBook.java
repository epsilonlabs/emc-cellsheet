package org.eclipse.epsilon.emc.cellsheet;

import java.util.List;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.models.IModel;

/**
 * Epsilon Model of a Spreadsheet Workbook.
 * 
 * Book is analogous to a model in the Epsilon architecture. This was chosen to
 * maintain the overall Spreadsheet methodology.
 * 
 * Element ID's should be implemented as cell references where possible TODO:
 * Determine correct way to ID individual columns and rows
 * 
 * @author Jonathan Co
 *
 */
public interface IBook extends HasId, IModel, Iterable<ISheet> {

	public static final Type TYPE = Type.BOOK;
	public static final Type[] KINDS = { TYPE };

	public ICell getCell(IRow row, int col);

	public ICell getCell(ISheet sheet, int row, int col);

	public ICell getCell(ISheet sheet, int row, String col);

	public ICell getCell(String sheetName, int row, int col);

	public ICell getCell(String sheetName, int row, String col);

	public ICell getCell(int sheetIndex, int row, int col);

	public ICell getCell(int sheetIndex, int row, String col);

	public IRow getRow(int sheetIndex, int index);

	public IRow getRow(ISheet sheet, int index);

	public IRow getRow(String sheetName, int index);

	public ISheet getSheet(int index);

	public ISheet getSheet(String name);

	public List<? extends ISheet> sheets();

	@Override
	default Object getTypeOf(Object obj) {
		Type type = obj instanceof HasType ? ((HasType) obj).getType() : null;
		if (type == null)
			throw new IllegalArgumentException("Object not a model element");
		return type;
	}
	
	default HasId getElementById(ElementId id) {
		// ID same as this book
		if (getId().equals(id.toString())) {
			return this;
		}

		// Check which book the element belongs to
		IBook book;
		if (id.getBook() != null) {
			if (getName().equals(id.getBook())) {
				book = this;
			} else {
				return null;
			}
		} else {
			book = this;
		}

		ISheet sheet = book.getSheet(id.getSheet());

		IRow row;
		if (id.getRow() > -1) {
			row = sheet.getRow(id.getRow());
		} else {
			return sheet;
		}

		ICell cell;
		if (id.getCol() > -1) {
			cell = row.getCell(id.getCol());
		} else {
			return row;
		}

		return cell;
	}

	@Override
	default Object getElementById(String id) {
		return getElementById(ElementId.fromString(id));
	}

	@Override
	default String getTypeNameOf(Object obj) {
		return ((Type) getTypeOf(obj)).getTypeName();
	}

	@Override
	default boolean isOfType(Object obj, String typename) throws EolModelElementTypeNotFoundException {
		Type type = Type.fromTypeName(typename);
		if (type == null)
			throw new EolModelElementTypeNotFoundException(this.getName(), typename);
		return type == (Type) getTypeOf(obj);
	}

	@Override
	default boolean hasType(String type) {
		return Type.fromTypeName(type) != null;
	}

	@Override
	default Type getType() {
		return IBook.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IBook.KINDS;
	}

	@Override
	default String getId() {
		return ElementId.toString(getName(), null, -1, -1);
	}

}

package org.eclipse.epsilon.emc.cellsheet;

import java.util.Arrays;
import java.util.Iterator;
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

	@Override
	default Object getElementById(String id) {
		// Sanitise if this is a relative id
		if (id.startsWith("/")) {
			id = getName() + id;
		}
		
		Iterator<String> parts = Arrays.stream(id.split("/")).iterator();
				
		IBook book;
		if (parts.hasNext() && getName().equals(parts.next())) {
			book = this;
		} else {
			return null;
		}
		
		ISheet sheet;		
		if (parts.hasNext()) {
			sheet = book.getSheet(parts.next());
		} else {
			return book;
		}
		
		IRow row;
		if (parts.hasNext()) {
			row = sheet.getRow(Integer.parseInt(parts.next()));
		} else {
			return sheet;
		}

		ICell cell;
		if (parts.hasNext()) {
			cell = row.getCell(Integer.parseInt(parts.next()));
		} else {
			return row;
		}
		
		if (!parts.hasNext()) {
			return cell;
		}
		
		Object toReturn = null;
		switch(parts.next()) {
		case "value":
			if (cell.getValue().getType() != Type.FORMULA_CELL_VALUE) {
				toReturn = cell.getValue();
				break;
			}
			
			// No more parts, only interested in the formula cell value element
			if (!parts.hasNext()) {
				toReturn = cell.getFormulaCellValue();
				break;
			}
			
			// Get the first tree element
			IFormulaTree tree = cell.getFormulaCellValue().getFormulaTree();
			toReturn = tree;
			if (Integer.parseInt(parts.next()) != 0) {
				throw new IllegalArgumentException("Given root node that is not 0");
			}
			
			// Continue walking the tree
			while (parts.hasNext()) {
				tree = tree.getChildAt(Integer.parseInt(parts.next()));
				toReturn = tree;
			}
			
			break;
		default:
			throw new IllegalArgumentException("Unknown type given");
		}
		
		if (parts.hasNext()) {
			throw new IllegalArgumentException("Unconsumed parts in ID");
		}
		
		return toReturn;
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
		return getName() + "/";
	}

}

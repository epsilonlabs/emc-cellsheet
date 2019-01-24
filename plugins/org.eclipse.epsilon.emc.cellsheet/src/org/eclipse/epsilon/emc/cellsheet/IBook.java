package org.eclipse.epsilon.emc.cellsheet;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.models.IModel;

/**
 * Epsilon Model of a Spreadsheet Workbook.
 * 
 * Book is analogous to a model in the EMC architecture. This was chosen to
 * maintain the overall Spreadsheet methodology.
 * 
 * @author Jonathan Co
 *
 */
public interface IBook extends HasId, IModel, Iterable<ISheet>, HasA1 {

	public static final Type TYPE = Type.BOOK;
	public static final Type[] KINDS = { TYPE };

	/*
	 * SHEETS
	 */
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
		if (id == null) {
			return null;
		}

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
		switch (parts.next()) {
		case "value":
			if (cell.getCellValue().getType() != Type.FORMULA_CELL_VALUE) {
				toReturn = cell.getCellValue();
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
	
	default Collection<?> getAllOfType(Type type) throws EolModelElementTypeNotFoundException {
		return getAllOfType(type.getTypename());
	}
	
	default Collection<?> getAllOfKind(Type type) throws EolModelElementTypeNotFoundException {
		return getAllOfKind(type.getTypename());
	}

	@Override
	default String getTypeNameOf(Object obj) {
		return ((Type) getTypeOf(obj)).getTypename();
	}

	@Override
	default boolean isOfType(Object instance, String typename) throws EolModelElementTypeNotFoundException {
		return isOfTypeOrKind(instance, typename, false);
	}

	@Override
	default boolean isOfKind(Object instance, String typename) throws EolModelElementTypeNotFoundException {
		return isOfTypeOrKind(instance, typename, true);
	}

	@Override
	default boolean hasType(String type) {
		return Type.fromTypename(type) != null;
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

	@Override
	default String getA1Ref() {
		return "[" + getName() + "]";
	}

	default boolean isOfTypeOrKind(Object instance, String typename, boolean isKind)
			throws EolModelElementTypeNotFoundException {
		Type type = Type.fromTypename(typename);
		if (type == null) {
			throw new EolModelElementTypeNotFoundException(this.getName(), typename);
		}
		if (!(instance instanceof HasType)) {
			throw new IllegalArgumentException("Element is not typed: " + instance);
		}
		if (isKind) {
			return Arrays.stream(((HasType) instance).getKinds()).anyMatch(type::equals);
		} else {
			return ((HasType) instance).getType() == type;
		}
	}
}

package org.eclipse.epsilon.emc.cellsheet;

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
public interface IBook extends HasType, IModel, Iterable<ISheet> {
	
	public static final Type TYPE = Type.BOOK;
	public static final Type[] KINDS = {TYPE};
	
	public ICell getCell(int sheetIndex, int row, int col);

	public ICell getCell(ISheet sheet, int row, int col);

	public ICell getCell(IRow row, int col);
	
	public ICell getCell(String sheetName, int row, int col);
	
	public ICell getCell(String sheetName, int row, String col);
	
	public ICell getCell(int sheetIndex, int row, String col);
	
	public IDResolver getIDResolver();
	
	public IRow getRow(int sheetIndex, int index);
	
	public IRow getRow(ISheet sheet, int index);
	
	public IRow getRow(String sheetName, int index);
	
	public ISheet getSheet(int index);
	
	public ISheet getSheet(String name);

	public void setIDResolver(IDResolver idResolver);

	public List<? extends ISheet> sheets();
	
	public Iterator<? extends ISheet> sheetIterator();

	@Override
	default Object getTypeOf(Object obj) {		
		Type type = typeOf(obj);
		if (type == null) throw new IllegalArgumentException("Object not a model element");
		return type;
	}
	
	@Override
	default String getTypeNameOf(Object obj) {
		return typeOf(obj).getTypeName();
	}
	
	@Override
	default boolean isOfType(Object obj, String typename) throws EolModelElementTypeNotFoundException {
		final Type type = Type.fromTypeName(typename);
		if (type == null) throw new EolModelElementTypeNotFoundException(this.getName(), typename);
		
		final Type instanceType = typeOf(obj);
		return type == instanceType;
	}

	@Override
	default boolean hasType(String type) {
		for (Type ct : Type.values()) {
			if (ct.getTypeName().equals(type)) return true;
		}
		return false;
	}

	@Override
	default Type getType() {
		return IBook.TYPE;
	}

	@Override
	default Type[] getKinds() {
		return IBook.KINDS;
	}
	
	default Type typeOf(Object obj) {
		return obj instanceof HasType ? ((HasType) obj).getType() : null;
	}
}

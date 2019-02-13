package org.eclipse.epsilon.emc.cellsheet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
public interface IBook<ModelElementType> extends HasId, IModel, Iterable<ISheet>, HasA1 {

	public static final ElementType TYPE = CoreType.BOOK;
	public static final Set<ElementType> KINDS = new HashSet<>(Arrays.asList(CoreType.BOOK));

	@Override
	default public ElementType getType() {
		return TYPE;
	}

	@Override
	default public Set<ElementType> getKinds() {
		return KINDS;
	}

	/*
	 * SHEETS
	 */
	public ISheet getSheet(int index);

	public ISheet getSheet(String name);

	public List<? extends ISheet> sheets();

	public boolean isOfType(Object instance, ElementType type) throws EolModelElementTypeNotFoundException;

	public boolean isOfKind(Object instance, ElementType kind) throws EolModelElementTypeNotFoundException;

	public Collection<ModelElementType> getAllOfType(ElementType type) throws EolModelElementTypeNotFoundException;

	public Collection<ModelElementType> getAllOfKind(ElementType type) throws EolModelElementTypeNotFoundException;

}

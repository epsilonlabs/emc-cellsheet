package org.eclipse.epsilon.emc.cellsheet;

import org.eclipse.epsilon.eol.models.Model;

public abstract class AbstractBook extends Model implements IBook {

	@Override
	public String getElementId(Object instance) {
		if (instance instanceof ISheet)
			return this.getIDResolver().getID((ISheet) instance);
		if (instance instanceof IRow)
			return this.getIDResolver().getID((IRow) instance);
		if (instance instanceof ICell)
			return this.getIDResolver().getID((ICell) instance);

		return null;
	}

	@Override
	public boolean hasType(String type) {
		return type.equals(ISheet.TYPENAME) || type.equals(IRow.TYPENAME) || type.equals(ICell.TYPENAME);
	}

}

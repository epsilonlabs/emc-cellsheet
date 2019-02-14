package org.eclipse.epsilon.emc.cellsheet.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.AbstractBook;
import org.eclipse.epsilon.emc.cellsheet.ElementType;
import org.eclipse.epsilon.emc.cellsheet.HasId;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

public class ExcelBook extends AbstractBook implements IBook, HasDelegate<Workbook> {

	public static final String PROPERTY_NAME_DEFAULT = "Excel";
	public static final String PROPERTY_FILE = "file";

	// Lower level access fields
	protected Workbook delegate = null;
	protected File excelFile = null;

	public ExcelBook() {
		;
	}

	public ExcelBook(Workbook delegate) {
		this.delegate = delegate;
	}

	@Override
	public ExcelSheet getSheet(int index) {
		if (index < 0 || index >= delegate.getNumberOfSheets()) {
			throw new IndexOutOfBoundsException(
					"index must be p∂ositive and within range of number of existing sheets, was given: " + index);
		}
		return new ExcelSheet(this, index);
	}

	@Override
	public ExcelSheet getSheet(String name) {
		int index = delegate.getSheetIndex(name);
		return index < 0 ? null : getSheet(index);
	}

	@Override
	public Iterator<ISheet> iterator() {
		return new TransformIterator<Sheet, ISheet>(delegate.iterator(), new Transformer<Sheet, ISheet>() {
			@Override
			public ISheet transform(Sheet input) {
				return getSheet(delegate.getSheetIndex(input));
			}
		});
	}

	@Override
	public List<ISheet> sheets() {
		return IteratorUtils.toList(iterator());
	}

	@Override
	public Workbook getDelegate() {
		return delegate;
	}

	@Override
	protected Collection<HasId> allContentsFromModel() {
		Collection<HasId> allContents = new ArrayList<>();
//		allContents.add(this);
//		forEach(sheet ->
//			{
//				allContents.add(sheet);
//				sheet.forEach(row ->
//					{
//						allContents.add(row);
//						row.forEach(cell ->
//							{
//								allContents.add(cell);
//								allContents.add(cell.getCellValue());
//								if (cell.getCellValue().getType() == Type.FORMULA_CELL_VALUE) {
//									allContents.addAll(cell.getFormulaCellValue().getFormulaTree().getAllTrees());
//								}
//							});
//					});
//			});

		return allContents;
	}

	@Override
	public Collection<HasId> getAllOfKindFromModel(String typename) throws EolModelElementTypeNotFoundException {
		// TODO: Lazy collections - Guava or backed by an Iterator 
		final ElementType type = getElementTypeOrThrow(typename);
		return allContents().stream().filter(e -> e.getKinds().contains(type)).collect(Collectors.toList());
	}

	@Override
	public Collection<HasId> getAllOfTypeFromModel(String typename) throws EolModelElementTypeNotFoundException {
		final ElementType type = getElementTypeOrThrow(typename);
		return allContents().stream().filter(e -> e.getType() == type).collect(Collectors.toList());
	}

	@Override
	public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
		super.load(properties, resolver);
		setExcelFile(properties.getProperty(ExcelBook.PROPERTY_FILE));
		setName(properties.getProperty(ExcelBook.PROPERTY_NAME, "Excel"));
		load();
	}

	@Override
	public void loadModel() throws EolModelLoadingException {
		try {
			delegate = WorkbookFactory.create(excelFile, null, true);
			delegate.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
		} catch (Exception e) {
			throw new EolModelLoadingException(e, this);
		}
	}

	@Override
	public boolean store() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean store(String location) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected void disposeModel() {
		try {
			delegate.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setExcelFile(String filepath) {
		setExcelFile(new File(filepath));
	}

	public void setExcelFile(File file) {
		excelFile = file;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ExcelBook)) {
			return false;
		}
		ExcelBook other = (ExcelBook) obj;
		if (excelFile == null) {
			if (other.excelFile != null)
				return false;
		} else if (!excelFile.equals(other.excelFile)) {
			return false;
		}
		if (delegate == null && other.delegate != null) {
			return false;
		} else if (!delegate.equals(other.delegate)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((excelFile == null) ? 0 : excelFile.hashCode());
		result = prime * result + ((delegate == null) ? 0 : delegate.hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getClass().getSimpleName()).append("@").append(hashCode()).append("]");
		sb.append("(id: ").append(getId());
		sb.append(", excelRef: ").append(getA1());
		sb.append(")");
		return sb.toString();
	}

}

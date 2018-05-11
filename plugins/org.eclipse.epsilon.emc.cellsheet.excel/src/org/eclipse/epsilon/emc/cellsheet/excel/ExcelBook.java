package org.eclipse.epsilon.emc.cellsheet.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.AbstractBook;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IColumn;
import org.eclipse.epsilon.emc.cellsheet.IDResolver;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelBook extends AbstractBook implements IBook, HasRaw<Workbook> {

	public static final String EXCEL_FILE = "EXCEL_FILE";

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBook.class);

	// Lower level access fields
	protected Workbook raw = null;
	protected File excelFile = null;

	@Override
	public void addSheet(int index, ISheet sheet) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addSheet(ISheet sheet) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<?> allContents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object createInstance(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void deleteElement(Object instance) throws EolRuntimeException {
		throw new UnsupportedOperationException();

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ExcelBook))
			return false;
		ExcelBook other = (ExcelBook) obj;
		if (excelFile == null) {
			if (other.excelFile != null)
				return false;
		} else if (!excelFile.equals(other.excelFile))
			return false;
		if (raw == null) {
			if (other.raw != null)
				return false;
		} else if (!raw.equals(other.raw))
			return false;
		return true;
	}

	@Override
	public Collection<?> getAllOfKind(String type) throws EolModelElementTypeNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<?> getAllOfType(String type) throws EolModelElementTypeNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getElementById(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public IDResolver getIDResolver() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Workbook getRaw() {
		return this.raw;
	}

	@Override
	public ISheet getSheet(int index) {
		if (index < 0 || index >= this.raw.getNumberOfSheets())
			throw new IndexOutOfBoundsException();
		return new ExcelSheet(this, this.raw.getSheetAt(index));
	}

	@Override
	public ISheet getSheet(String name) {
		return this.getSheet(this.raw.getSheetIndex(name));
	}

	@Override
	public String getTypeNameOf(Object instance) {
		LOGGER.trace("Called " + this.name + ".getTypeNameOf: " + instance);
		if (instance instanceof ISheet)
			return ISheet.TYPENAME;
		if (instance instanceof IRow)
			return IRow.TYPENAME;
		if (instance instanceof IColumn)
			return IColumn.TYPENAME;
		if (instance instanceof ICell)
			return ICell.TYPENAME;
		if (instance instanceof IBook)
			return IBook.TYPENAME;

		// TODO: Should this return null instead?
		final IllegalArgumentException e = new IllegalArgumentException(
				"Not a valid Cellsheet type: " + instance + " (" + instance.getClass().getCanonicalName() + ") ");
		LOGGER.error(e.getMessage(), e);
		throw e;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((excelFile == null) ? 0 : excelFile.hashCode());
		result = prime * result + ((raw == null) ? 0 : raw.hashCode());
		return result;
	}

	@Override
	public boolean isInstantiable(String type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<ISheet> iterator() {
		return new TransformIterator<Sheet, ISheet>(this.raw.sheetIterator(), new Transformer<Sheet, ISheet>() {
			@Override
			public ISheet transform(Sheet sheet) {
				return new ExcelSheet(ExcelBook.this, sheet);
			}
		});
	}

	@Override
	public void load() throws EolModelLoadingException {
		try {
			this.raw = WorkbookFactory.create(excelFile);
		} catch (Exception e) {
			throw new EolModelLoadingException(e, this);
		}
	}

	@Override
	public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
		super.load(properties, resolver);

		final String excelFilePath = properties.getProperty(ExcelBook.EXCEL_FILE);
		final String resolvedPath = resolver.resolve(excelFilePath);

		LOGGER.info("Excel Filepath: " + excelFilePath);
		LOGGER.info("Resolved Excel Filepath " + resolvedPath);

		try {
			this.setExcelFile(resolvedPath);
		} catch (Exception e) {
			throw new EolModelLoadingException(e, this);
		}

		this.load();
	}

	@Override
	public boolean owns(Object instance) {
		if (this.equals(instance))
			return true;

		if (instance instanceof ISheet)
			return ((ISheet) instance).getBook().equals(this);

		if (instance instanceof IRow)
			return this.owns(((IRow) instance).getSheet());

		if (instance instanceof IColumn)
			return this.owns(((IColumn) instance).getSheet());

		if (instance instanceof ICell)
			return this.owns(((ICell) instance).getRow());

		return false;
	}

	@Override
	public void setElementId(Object instance, String newId) {
		throw new UnsupportedOperationException();

	}

	public void setExcelFile(final String filepath) {
		final File file = new File(filepath);
		if (!file.exists()) {
			final IllegalArgumentException e = new IllegalArgumentException("Bad filepath given: " + filepath);
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		this.excelFile = file;
	}

	@Override
	public void setIDResolver(IDResolver idResolver) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void setRaw(Workbook raw) {
		this.raw = raw;
	}

	@Override
	public Iterator<ISheet> sheetIterator() {
		return this.iterator();
	}

	@Override
	public List<ISheet> sheets() {
		final List<ISheet> sheets = new ArrayList<ISheet>();
		final Iterator<ISheet> it = sheetIterator();

		while (it.hasNext()) {
			final ExcelSheet next = (ExcelSheet) it.next();
			sheets.add(next);
		}

		return sheets;
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
	public String toString() {
		return "[" + this.excelFile.getName().toString() + "]";
	}
}

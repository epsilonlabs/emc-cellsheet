package org.eclipse.epsilon.emc.cellsheet.excel;

import java.io.File;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.EBook;
import org.eclipse.epsilon.emc.cellsheet.ECell;
import org.eclipse.epsilon.emc.cellsheet.EColumn;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IDResolver;
import org.eclipse.epsilon.emc.cellsheet.ERow;
import org.eclipse.epsilon.emc.cellsheet.ESheet;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelBook extends Model implements EBook, HasRaw<Workbook> {

	public static final String EXCEL_FILE = "EXCEL_FILE";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBook.class);

	// Lower level access fields
	protected Workbook raw = null;
	protected File excelFile = null;
	
	// Abstracted elements
	protected Map<Object, ESheet> sheets;
	
	public ExcelBook() {
		this.sheets = new Hashtable<Object, ESheet>();
	}

	@Override
	public void load() throws EolModelLoadingException {
		try {
			this.raw = WorkbookFactory.create(excelFile) ;
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
	
	public void setExcelFile(final String filepath) {
		final File file = new File(filepath);
		if (!file.exists()) {
			final IllegalArgumentException e =  new IllegalArgumentException("Bad filepath given: " + filepath);
			LOGGER.error(e.getMessage(), e);
			throw e;
		}
		this.excelFile = file;
	}
	
	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> allContents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> getAllOfType(String type) throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<?> getAllOfKind(String type) throws EolModelElementTypeNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTypeNameOf(Object instance) {
		LOGGER.trace("Called " + this.name + ".getTypeNameOf: " + instance);
		if (instance instanceof ESheet)
			return ESheet.TYPENAME;
		if (instance instanceof ERow)
			return ERow.TYPENAME;
		if (instance instanceof EColumn)
			return EColumn.TYPENAME;
		if (instance instanceof ECell)
			return ECell.TYPENAME;
		if (instance instanceof EBook)
			return EBook.TYPENAME;

		// TODO: Should this return null instead?
		final IllegalArgumentException e = new IllegalArgumentException(
				"Not a valid Cellsheet type: " + instance + " (" + instance.getClass().getCanonicalName() + ") ");
		LOGGER.error(e.getMessage(), e);
		throw e;
	}

	@Override
	public Object createInstance(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getElementById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getElementId(Object instance) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setElementId(Object instance, String newId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteElement(Object instance) throws EolRuntimeException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean owns(Object instance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInstantiable(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasType(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store(String location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ESheet next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ESheet getSheet(int index) {
		ESheet sheet = this.sheets.get(index);
		
		if (sheet == null) {
			final Sheet rawSheet = this.raw.getSheetAt(index);
			sheet = new ExcelSheet(rawSheet);
			this.sheets.put(index, sheet);
		}
		
		return sheet;
	}

	@Override
	public ESheet getSheet(String name) {
		ESheet sheet = this.sheets.get(name);
		
		if (sheet == null) {
			final Sheet rawSheet = this.raw.getSheet(name);
			sheet = new ExcelSheet(rawSheet);
			this.sheets.put(name, sheet);
		}
		
		return sheet;
	}

	@Override
	public Iterator<ESheet> sheetIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSheet(ESheet sheet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSheet(int index, ESheet sheet) {
		// TODO Auto-generated method stub

	}

	@Override
	public IDResolver getIDResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setIDResolver(IDResolver idResolver) {
		// TODO Auto-generated method stub

	}

	@Override
	public Workbook getRaw() {
		return this.raw;
	}

	@Override
	public void setRaw(Workbook raw) {
		this.raw = raw;
	}

}

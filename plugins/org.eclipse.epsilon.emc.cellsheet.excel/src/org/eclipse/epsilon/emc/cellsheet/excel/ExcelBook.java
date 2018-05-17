package org.eclipse.epsilon.emc.cellsheet.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IDResolver;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExcelBook extends Model implements IBook, HasRaw<Workbook> {

	public static final String EXCEL_FILE = "EXCEL_FILE";

	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelBook.class);

	final Map<Sheet, ExcelSheet> _sheets = new HashMap<Sheet, ExcelSheet>();
	final Map<Row, ExcelRow> _rows = new HashMap<Row, ExcelRow>();
	final Map<Cell, ExcelCell> _cells = new HashMap<Cell, ExcelCell>();

	// Lower level access fields
	protected Workbook raw = null;
	protected File excelFile = null;
	
	private final ExcelIDResolver idResolver = new ExcelIDResolver();

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
	public ICell getCell(int sheet, int row, int col) {
		return getCell(getSheet(sheet), getRow(sheet, row), col);
	}

	@Override
	public ICell getCell(ISheet sheet, int row, int col) {
		return getCell(sheet, getRow(sheet, row), col);
	}

	@Override
	public ICell getCell(ISheet sheet, IRow row, int col) {
		if (col < 0) throw new IndexOutOfBoundsException();
		if (!owns(sheet)) throw new IllegalArgumentException();
		if (!owns(row)) throw new IllegalArgumentException();
		
		final Cell rawCell = ((ExcelRow) row).getRaw().getCell(col);
		ExcelCell excelCell = _cells.get(rawCell);
		if (excelCell == null) {
			excelCell = new ExcelCell(this, rawCell);
			_cells.put(rawCell, excelCell);
		}
		return excelCell;
	}

	@Override
	public ICell getCell(String sheet, int row, int col) {
		return getCell(getSheet(sheet), getRow(sheet, row), col);
	}

	@Override
	public Object getElementById(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getElementId(Object instance) {
		return idResolver.getID(instance);
	}

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public IDResolver getIDResolver() {
		return this.idResolver;
	}

	@Override
	public Workbook getRaw() {
		return this.raw;
	}

	@Override
	public IRow getRow(int sheet, int index) {
		return getRow(getSheet(sheet), index);
	}

	@Override
	public IRow getRow(ISheet sheet, int index) {
		if (index < 0) throw new IndexOutOfBoundsException();
		if (!this.owns(sheet)) throw new IllegalArgumentException();
		
		final Row rawRow = ((ExcelSheet) sheet).getRaw().getRow(index);
		ExcelRow excelRow = _rows.get(rawRow);
		if (excelRow == null) {
			excelRow = new ExcelRow(this, rawRow);
			_rows.put(rawRow, excelRow);
		}
		return excelRow;
	}

	@Override
	public IRow getRow(String sheet, int index) {
		return getRow(getSheet(sheet), index);
	}

	@Override
	public ExcelSheet getSheet(int index) {
		if (index < 0 || index >= raw.getNumberOfSheets())
			throw new IndexOutOfBoundsException();
		return getSheet(index);
	}

	@Override
	public ExcelSheet getSheet(String name) {
		final Sheet rawSheet = raw.getSheet(name);
		if (rawSheet == null)
			return null;

		ExcelSheet excelSheet = _sheets.get(rawSheet);
		if (excelSheet == null) {
			excelSheet = new ExcelSheet(this, rawSheet);
			_sheets.put(rawSheet, excelSheet);
		}
		
		return excelSheet;
	}

	@Override
	public String getTypeNameOf(Object instance) {
		LOGGER.trace("Called " + this.name + ".getTypeNameOf: " + instance);
		if (instance instanceof ISheet)
			return ISheet.TYPENAME;
		if (instance instanceof IRow)
			return IRow.TYPENAME;
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
	public boolean hasType(String type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInstantiable(String type) {
		throw new UnsupportedOperationException();
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

		if (instance instanceof ExcelSheet)
			return this.equals(((ExcelSheet) instance).getBook().getRaw());

		if (instance instanceof IRow)
			return this.owns(((ExcelRow) instance).getSheet());

		if (instance instanceof ICell)
			return this.owns(((ExcelCell) instance).getSheet());

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

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<ExcelSheet> sheetIterator() {
		return this._sheets.values().iterator();
	}

	@Override
	public List<ExcelSheet> sheets() {
		List<ExcelSheet> list = new ArrayList<ExcelSheet>(_sheets.values());
		Collections.sort(list);
		return list;
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

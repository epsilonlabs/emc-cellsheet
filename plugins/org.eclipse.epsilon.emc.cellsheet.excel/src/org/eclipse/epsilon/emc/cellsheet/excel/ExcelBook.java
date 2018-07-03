package org.eclipse.epsilon.emc.cellsheet.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.AbstractBook;
import org.eclipse.epsilon.emc.cellsheet.HasDelegate;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IDResolver;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.emc.cellsheet.Type;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

public class ExcelBook extends AbstractBook implements IBook, HasDelegate<Workbook> {

	public static final String EXCEL_PROPERTY_NAME = "EXCEL_NAME";
	public static final String EXCEL_PROPERTY_NAME_DEFAULT = "Excel";
	
	public static final String EXCEL_PROPERTY_FILE = "EXCEL_FILE";

	final Map<Sheet, ExcelSheet> _sheets = new HashMap<Sheet, ExcelSheet>();
	final Map<Row, ExcelRow> _rows = new HashMap<Row, ExcelRow>();
	final Map<Cell, ExcelCell> _cells = new HashMap<Cell, ExcelCell>();

	// Lower level access fields
	protected Workbook delegate = null;
	protected File excelFile = null;
	protected FormulaParsingWorkbook fpw = null;
	
	private final ExcelIDResolver idResolver = new ExcelIDResolver();
	
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
	public Object getElementById(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getElementId(Object instance) {
		return idResolver.getID(instance);
	}
	
	@Override
	public boolean isInstantiable(String type) {
		throw new UnsupportedOperationException();
	}	
	
	@Override
	public boolean owns(Object instance) {
		if (instance == null) return false;
		
		if (instance instanceof IBook)
			return this.equals(instance);

		if (instance instanceof ISheet)
			return this.owns(((ExcelSheet) instance).getBook());

		if (instance instanceof IRow)
			return this.owns(((ExcelRow) instance).getSheet());

		if (instance instanceof ICell)
			return this.owns(((ExcelCell) instance).getSheet());

		return false;
	}

	@Override
	public Collection<?> allContents() {
		throw new UnsupportedOperationException();
	}

	public ExcelCell getCell(Cell delegate) {		
		if (!delegate.getSheet().getWorkbook().equals(this.delegate)) 
			throw new IllegalArgumentException();
		
		ExcelCell excelCell = _cells.get(delegate);
		if (excelCell == null) {
			excelCell = new ExcelCell(this, delegate);
			_cells.put(delegate, excelCell);
		}
		return excelCell;
	}

	@Override
	public ExcelCell getCell(int sheetIndex, int row, int col) {
		return this.getCell(this.getRow(sheetIndex, row), col);
	}

	@Override
	public ExcelCell getCell(IRow row, int col) {
		return this.getCell(((ExcelRow) row).getDelegate().getCell(col, MissingCellPolicy.CREATE_NULL_AS_BLANK));
	}

	@Override
	public ExcelCell getCell(ISheet sheet, int row, int col) {
		return this.getCell(this.getRow(sheet, row), col);
	}

	@Override
	public ExcelCell getCell(String sheetName, int row, int col) {
		return this.getCell(this.getRow(sheetName, row), col);
	}
	
	@Override
	public ExcelCell getCell(String sheetName, int row, String col) {
		return this.getCell(sheetName, row, CellReference.convertColStringToIndex(col));
	}
	
	@Override
	public ExcelCell getCell(int sheetIndex, int row, String col) {
		return this.getCell(sheetIndex, row, CellReference.convertColStringToIndex(col));
	}

	@Override
	public IDResolver getIDResolver() {
		return this.idResolver;
	}
	
	public ExcelRow getRow(Row delegate) {
		ExcelRow excelRow = _rows.get(delegate);
		if (excelRow == null) {
			excelRow = new ExcelRow(this, delegate);
			_rows.put(delegate, excelRow);
		}
		return excelRow;
	}
	
	@Override
	public ExcelRow getRow(ISheet sheet, int index) {
		if (index < 0) throw new IndexOutOfBoundsException();
		if (!this.owns(sheet)) throw new IllegalArgumentException();
		
		// Get a POI row to work with
		Row poiRow = ((ExcelSheet) sheet).getDelegate().getRow(index);
		if (poiRow == null) {
			poiRow =  ((ExcelSheet) sheet).getDelegate().createRow(index);
		}
		return this.getRow(poiRow);
	}
	
	@Override
	public ExcelRow getRow(int sheet, int index) {
		return this.getRow(getSheet(sheet), index);
	}
	@Override
	public ExcelRow getRow(String sheet, int index) {
		return getRow(getSheet(sheet), index);
	}
	
	public ExcelSheet getSheet(Sheet delegate) {
		if (delegate == null) return null;
		
		ExcelSheet excelSheet = _sheets.get(delegate);
		if (excelSheet == null) {
			excelSheet = new ExcelSheet(this, delegate);
			_sheets.put(delegate, excelSheet);
		}
		return excelSheet;
	}

	@Override
	public ExcelSheet getSheet(int index) {
		if (index < 0 || index >= delegate.getNumberOfSheets())
			throw new IndexOutOfBoundsException();
		return this.getSheet(this.delegate.getSheetAt(index));
	}

	@Override
	public ExcelSheet getSheet(String name) {
		return this.getSheet(this.delegate.getSheet(name));
	}

	@Override
	public void setElementId(Object instance, String newId) {
		throw new UnsupportedOperationException();

	}

	@Override
	public Iterator<ISheet> iterator() {
		return new TransformIterator<ExcelSheet, ISheet>(this.sheetIterator(),
				new Transformer<ExcelSheet, ISheet>() {
					@Override
					public ISheet transform(ExcelSheet input) {
						return input;
					}
				});
	}
	
	@Override
	public Iterator<ExcelSheet> sheetIterator() {
		return this.sheets().iterator();
	}

	@Override
	public List<ExcelSheet> sheets() {
		this.delegate.sheetIterator().forEachRemaining(s -> this.getSheet(s));
		List<ExcelSheet> list = new ArrayList<ExcelSheet>(_sheets.values());
		Collections.sort(list);
		return list;
	}

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOfKind(Object instance, String metaClass) throws EolModelElementTypeNotFoundException {
		// FIXME: Add in subtypes for Excel only implementations
		return isOfType(instance, metaClass);
	}



	@Override
	public Collection<?> getAllOfKind(String type) throws EolModelElementTypeNotFoundException {
		// FIXME: Add in subtypes for Excel only implementations
		return this.getAllOfType(type);
	}

	@Override
	public Collection<?> getAllOfType(String typename) throws EolModelElementTypeNotFoundException {		
		if (!this.hasType(typename)) throw new EolModelElementTypeNotFoundException(this.name, typename);
		
		final Type type = Type.fromTypeName(typename);		
		
		if (type == Type.BOOK) {
			List<IBook> list = new ArrayList<IBook>(1);
			list.add(this);
			return list;
		}
		
		if (type == Type.SHEET) {
			return this.sheets();
		}
		
		if (type == Type.ROW) {
			final List<ExcelRow> rows = new ArrayList<ExcelRow>();
			for (ExcelSheet sheet : this.sheets()) {
				rows.addAll(sheet.rows());
			}
			return rows;
		}
		
		if (type == Type.CELL) {
			final List<ExcelCell> cells = new ArrayList<ExcelCell>();
			for (ExcelSheet sheet : this.sheets()) {
				for (ExcelRow row : sheet.rows()) {
					cells.addAll(row.cells());
				}
			}
			return cells;
		}
	
		throw new AssertionError();
	}

	@Override
	public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
		super.load(properties, resolver);

		final String excelFilePath = properties.getProperty(ExcelBook.EXCEL_PROPERTY_FILE);
		this.setExcelFile(excelFilePath);

		final String modelName = properties.getProperty(ExcelBook.EXCEL_PROPERTY_NAME, "Excel");
		this.setName(modelName);

		this.load();
	}
	
	@Override
	public void load() throws EolModelLoadingException {
		try {
			this.delegate = WorkbookFactory.create(excelFile);
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
	
	public void setExcelFile(final String filepath) {
		final File file = (new File(filepath)).getAbsoluteFile();
		if (!file.exists()) {
			final IllegalArgumentException e = new IllegalArgumentException("Bad filepath given: " + filepath);
			throw e;
		}
		this.excelFile = file;
	}
	
	@Override
	public Workbook getDelegate() {
		return this.delegate;
	}
	
	@Override
	public void setDelegate(Workbook delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public void setIDResolver(IDResolver idResolver) {
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
		if (delegate == null) {
			if (other.delegate != null)
				return false;
		} else if (!delegate.equals(other.delegate))
			return false;
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
		return "[" + this.excelFile.getName().toString() + "]";
	}

}

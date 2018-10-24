package org.eclipse.epsilon.emc.cellsheet.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.HasId;
import org.eclipse.epsilon.emc.cellsheet.HasType;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.emc.cellsheet.IdUtil;
import org.eclipse.epsilon.emc.cellsheet.Type;
import org.eclipse.epsilon.emc.cellsheet.excel.functions.AiFunctions;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.eol.models.Model;

public class ExcelBook extends Model implements IBook, HasDelegate<Workbook> {

	public static final String EXCEL_PROPERTY_NAME = "EXCEL_NAME";
	public static final String EXCEL_PROPERTY_NAME_DEFAULT = "Excel";
	public static final String EXCEL_PROPERTY_FILE = "EXCEL_FILE";

	// Lower level access fields
	protected Workbook delegate = null;
	protected File excelFile = null;

	final Map<Sheet, ExcelSheet> _sheets = new HashMap<Sheet, ExcelSheet>();
	final Map<Row, ExcelRow> _rows = new HashMap<Row, ExcelRow>();
	final Map<Cell, ExcelCell> _cells = new HashMap<Cell, ExcelCell>();

	WorkbookEvaluator evaluator = null;
	FormulaParsingWorkbook fpw = null;
	AiFunctions aiFunctions = null;

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
		return IdUtil.getElementById(this, id);
	}

	@Override
	public String getElementId(Object instance) {
		if (instance instanceof HasId) {
			return ((HasId) instance).getId();
		}

		if (instance instanceof HasType) {
			throw new UnsupportedOperationException(
					"ID not implemented for model type: " + ((HasType) instance).getType());
		}
		throw new IllegalArgumentException("No such model element type exists for " + instance.toString());
	}

	@Override
	public String getId() {
		return IdUtil.getId(this);
	}

	@Override
	public boolean isInstantiable(String type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean owns(Object instance) {
		if (instance == null) {
			return false;
		}
		if (instance instanceof IBook) {
			return this.equals(instance);
		}
		if (instance instanceof ISheet) {
			return this.owns(((ISheet) instance).getBook());
		}
		if (instance instanceof IRow) {
			return this.owns(((IRow) instance).getBook());
		}
		if (instance instanceof ICell) {
			return this.owns(((ICell) instance).getBook());
		}
		return false;
	}

	@Override
	public Collection<?> allContents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ExcelCell getCell(IRow row, int col) {
		if (col < 0) {
			throw new IndexOutOfBoundsException("col index must be positive, was given: " + col);
		}
		if (row == null) {
			throw new IllegalArgumentException("row arg must not be null");
		}
		if (!(row instanceof ExcelRow)) {
			throw new IllegalArgumentException("row arg must be an ExcelRow instance, was given: " + row);
		}
		if (!owns(row)) {
			throw new IllegalArgumentException("row arg must belong to this book, was given: " + row);
		}
		
		return getCell(((ExcelRow) row).getDelegate().getCell(col));
	}

	@Override
	public ExcelCell getCell(ISheet sheet, int row, int col) {
		return getCell(sheet.getRow(row), col);
	}

	@Override
	public ICell getCell(ISheet sheet, int row, String col) {
		return getCell(sheet.getRow(row), CellReference.convertColStringToIndex(col));
	}

	@Override
	public ExcelCell getCell(String sheetName, int row, int col) {
		return getCell(getRow(sheetName, row), col);
	}

	@Override
	public ExcelCell getCell(int sheetIndex, int row, int col) {
		return getCell(getRow(sheetIndex, row), col);
	}

	@Override
	public ExcelCell getCell(String sheetName, int row, String col) {
		return getCell(getRow(sheetName, row), CellReference.convertColStringToIndex(col));
	}

	@Override
	public ExcelCell getCell(int sheetIndex, int row, String col) {
		return getCell(getRow(sheetIndex, row), CellReference.convertColStringToIndex(col));
	}
	
	ExcelCell getCell(Cell cell) {
		if (cell == null) {
			return null;
		}
		
		ExcelCell excelCell = _cells.get(cell);
		if (excelCell == null) {
			excelCell = new ExcelCell(getRow(cell.getRow()), cell);
			_cells.put(cell, excelCell);
		}
		return excelCell;
	}

	@Override
	public ExcelRow getRow(ISheet sheet, int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException("index must be positive, was given: " + index);
		}
		if (sheet == null) {
			throw new IllegalArgumentException("sheet arg must not be null");
		}
		if (!(sheet instanceof ExcelSheet)) {
			throw new IllegalArgumentException("sheet arg must be an ExcelSheet instance, was given: " + sheet);
		}
		if (!this.owns(sheet)) {
			throw new IllegalArgumentException("sheet arg must belong to this book, was given: " + sheet);
		}

		Row row = ((ExcelSheet) sheet).getDelegate().getRow(index);
		if (row == null) {
			row = ((ExcelSheet) sheet).getDelegate().createRow(index);
		}
		return getRow(row);
	}

	@Override
	public ExcelRow getRow(int sheet, int index) {
		return getRow(getSheet(sheet), index);
	}

	@Override
	public ExcelRow getRow(String sheet, int index) {
		return getRow(getSheet(sheet), index);
	}
	
	ExcelRow getRow(Row row) {
		if (row == null) {
			return null;
		}
		
		// Check if cached row already exists.
		ExcelRow excelRow = _rows.get(row);
		if (excelRow == null) {
			excelRow = new ExcelRow(getSheet(row.getSheet()), row);
			_rows.put(row, excelRow);
		}
		return excelRow;
	}

	@Override
	public ExcelSheet getSheet(int index) {
		if (index < 0 || index >= delegate.getNumberOfSheets()) {
			throw new IndexOutOfBoundsException(
					"index must be positive and within range of number of existing sheets, was given: " + index);
		}
		return getSheet(delegate.getSheetAt(index));

	}

	@Override
	public ExcelSheet getSheet(String name) {
		return getSheet(delegate.getSheet(name));
	}

	ExcelSheet getSheet(Sheet delegate) {
		if (delegate == null) {
			return null;
		}

		ExcelSheet excelSheet = _sheets.get(delegate);
		if (excelSheet == null) {
			excelSheet = new ExcelSheet(this, delegate);
			_sheets.put(delegate, excelSheet);
		}
		return excelSheet;
	}

	@Override
	public void setElementId(Object instance, String newId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<ISheet> iterator() {
		return new TransformIterator<Sheet, ISheet>(delegate.iterator(), new Transformer<Sheet, ISheet>() {
			@Override
			public ISheet transform(Sheet input) {
				return getSheet(input);
			}
		});
	}

	@Override
	public List<ISheet> sheets() {
		return IteratorUtils.toList(iterator());
	}

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOfKind(Object instance, String metaClass) throws EolModelElementTypeNotFoundException {
		// FIXME: Add in sub-types for Excel only implementations
		return isOfType(instance, metaClass);
	}

	@Override
	public Collection<?> getAllOfKind(String type) throws EolModelElementTypeNotFoundException {
		// FIXME: Add in sub-types for Excel only implementations
		return getAllOfType(type);
	}

	@Override
	public Collection<?> getAllOfType(String typename) throws EolModelElementTypeNotFoundException {
		if (!hasType(typename)) {
			throw new EolModelElementTypeNotFoundException(name, typename);
		}

		final List<Object> list = new ArrayList<Object>();
		
		switch(Type.fromTypeName(typename)) {
		case BOOK:
			list.add(this);
			break;
		case SHEET:
			list.addAll(sheets());
			break;
		case ROW:
			iterator().forEachRemaining(s -> list.addAll(s.rows()));
			break;
		case CELL:
			iterator().forEachRemaining(s -> s.iterator().forEachRemaining(r -> list.addAll(r.cells())));
			break;
		
		default:
			throw new AssertionError();
		}
		
		return list;
	}

	@Override
	public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
		super.load(properties, resolver);
		setExcelFile(properties.getProperty(ExcelBook.EXCEL_PROPERTY_FILE));
		setName(properties.getProperty(ExcelBook.EXCEL_PROPERTY_NAME, "Excel"));
		load();
	}

	@Override
	public void load() throws EolModelLoadingException {
		try {
			fpw = null;
			delegate = WorkbookFactory.create(excelFile);
			delegate.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
			
			if (delegate instanceof HSSFWorkbook) {
				fpw = HSSFEvaluationWorkbook.create((HSSFWorkbook) delegate);
			}
			if (delegate instanceof XSSFWorkbook) {
				fpw = XSSFEvaluationWorkbook.create((XSSFWorkbook) delegate);
			}
			if (delegate instanceof SXSSFWorkbook) {
				fpw = SXSSFEvaluationWorkbook.create((SXSSFWorkbook) delegate);
			}
			if (fpw == null) {
				throw new AssertionError("Workbook technology not supported");
			}

			evaluator = ((WorkbookEvaluatorProvider) delegate.getCreationHelper().createFormulaEvaluator())
					._getWorkbookEvaluator();
			aiFunctions = AiFunctions.create(this);
			delegate.addToolPack(aiFunctions);

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
		excelFile = (new File(filepath)).getAbsoluteFile();
		if (!excelFile.exists()) {
			final IllegalArgumentException e = new IllegalArgumentException("Bad filepath given: " + filepath);
			throw e;
		}
	}

	@Override
	public Workbook getDelegate() {
		return delegate;
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
	public Object getTypeOf(Object instance) {
		return IBook.super.getTypeOf(instance);
	}

	@Override
	public String getTypeNameOf(Object instance) {
		return IBook.super.getTypeNameOf(instance);
	}

	@Override
	public boolean isOfType(Object instance, String metaClass) throws EolModelElementTypeNotFoundException { // stub
		return IBook.super.isOfType(instance, metaClass);
	}

	public WorkbookEvaluator getEvaluator() {
		return evaluator;
	}

	public AiFunctions getAiFunctions() {
		return aiFunctions;
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
		return "[" + excelFile.getName().toString() + "]";
	}

}

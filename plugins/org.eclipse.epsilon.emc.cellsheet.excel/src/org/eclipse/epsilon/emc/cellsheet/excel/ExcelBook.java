package org.eclipse.epsilon.emc.cellsheet.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.emc.cellsheet.Type;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.exceptions.models.EolNotInstantiableModelElementTypeException;
import org.eclipse.epsilon.eol.models.CachedModel;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;

public class ExcelBook extends CachedModel<HasId> implements IBook, HasDelegate<Workbook> {

	public static final String EXCEL_PROPERTY_NAME = "EXCEL_NAME";
	public static final String EXCEL_PROPERTY_NAME_DEFAULT = "Excel";
	public static final String EXCEL_PROPERTY_FILE = "EXCEL_FILE";

	// Lower level access fields
	protected Workbook delegate = null;
	protected File excelFile = null;

	protected Map<String, HasId> idMap = new HashMap<String, HasId>();

	WorkbookEvaluator evaluator = null;
	FormulaParsingWorkbook fpw = null;

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

		// Check cache for existing ExcelCell
		if (cachingEnabled) {
			ExcelCell cell = (ExcelCell) idMap.get(row.getId() + "/" + col);
			if (cell != null) {
				return cell;
			}
		}

		// No cached value available, create new ExcelCell
		Cell poi = ((ExcelRow) row).getDelegate().getCell(col, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		ExcelCell cell = new ExcelCell((ExcelRow) row, poi);
		if (cachingEnabled) {
			idMap.put(cell.getId(), cell);
			idMap.put(cell.getCellValue().getId(), cell.getCellValue());
			if (cell.getCellValue().getType() == Type.FORMULA_CELL_VALUE) {
				for (IFormulaTree tree : cell.getFormulaCellValue().getFormulaTree().getAllTrees()) {
					idMap.put(tree.getId(), tree);
				}
			}
		}

		return cell;
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

		// Check cache for existing ExcelRow
		if (cachingEnabled) {
			ExcelRow row = (ExcelRow) idMap.get(sheet.getId() + "/" + index);
			if (row != null) {
				return row;
			}
		}

		// No cached value available, create new ExcelRow
		Row poi = ((ExcelSheet) sheet).getDelegate().getRow(index);
		if (poi == null) {
			poi = ((ExcelSheet) sheet).getDelegate().createRow(index);
		}
		ExcelRow row = new ExcelRow((ExcelSheet) sheet, poi);
		if (cachingEnabled) {
			idMap.put(row.getId(), row);
		}

		return row;
	}

	@Override
	public ExcelRow getRow(int sheet, int index) {
		return getRow(getSheet(sheet), index);
	}

	@Override
	public ExcelRow getRow(String sheet, int index) {
		return getRow(getSheet(sheet), index);
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

	ExcelSheet getSheet(Sheet poi) {
		if (poi == null) {
			return null;
		}

		// Check cache for existing ExcelSheet
		if (cachingEnabled) {
			ExcelSheet sheet = (ExcelSheet) idMap.get(getId() + "/" + poi.getSheetName());
			if (sheet != null) {
				return sheet;
			}
		}

		// No cached value available, create new ExcelSheet
		ExcelSheet sheet = new ExcelSheet(this, poi);
		if (cachingEnabled) {
			idMap.put(sheet.getId(), sheet);
		}

		return sheet;
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
	public Workbook getDelegate() {
		return delegate;
	}

	public WorkbookEvaluator getEvaluator() {
		return evaluator;
	}

	@Override
	protected Collection<HasId> allContentsFromModel() {
		// TODO: Add in concurrent loading?
		if (cachingEnabled) {
			idMap = new HashMap<>();
			idMap.put(getId(), this);
			forEach(sheet -> sheet.forEach(row -> row.forEach(cell -> cell.getCellValue())));
			return idMap.values();
		}

		// No caching, iterate and store
		Collection<HasId> allContents = new ArrayList<>();
		allContents.add(this);
		forEach(sheet -> {
			allContents.add(sheet);
			sheet.forEach(row -> {
				allContents.add(row);
				row.forEach(cell -> {
					allContents.add(cell);
					allContents.add(cell.getCellValue());
					if (cell.getCellValue().getType() == Type.FORMULA_CELL_VALUE) {
						allContents.addAll(cell.getFormulaCellValue().getFormulaTree().getAllTrees());
					}
				});
			});
		});

		return allContents;
	}

	@Override
	protected void addToCache(String type, HasId instance) throws EolModelElementTypeNotFoundException {
		super.addToCache(type, instance);
		if (cachingEnabled) {
			idMap.put(instance.getId(), instance);
		}
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

	@Override
	protected Collection<String> getAllTypeNamesOf(Object instance) {
		if (instance instanceof HasType) {
			return Arrays.stream(((HasType) instance).getKinds()).map(k -> k.getName()).collect(Collectors.toSet());
		}
		throw new IllegalArgumentException();
	}

	@Override
	protected Object getCacheKeyForType(String typename) throws EolModelElementTypeNotFoundException {
		Type type = Type.fromName(typename);
		if (type == null) {
			throw new EolModelElementTypeNotFoundException(name, typename);
		}
		return type;
	}

	@Override
	protected HasId createInstanceInModel(String type)
			throws EolModelElementTypeNotFoundException, EolNotInstantiableModelElementTypeException {
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean deleteElementInModel(Object instance) throws EolRuntimeException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object getElementById(String id) {
		if (cachingEnabled) {
			HasId element = idMap.get(id);
			if (element != null) {
				return element;
			}
		}
		return IBook.super.getElementById(id);
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
	public void setElementId(Object instance, String newId) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isInstantiable(String type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean owns(Object instance) {
		try {
			String id = getElementId(instance);
			return getElementById(id) != null;
		} catch (IllegalArgumentException e) {
			return false;
		}
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
	public Collection<HasId> getAllOfKindFromModel(String type) throws EolModelElementTypeNotFoundException {
		if (!hasType(type)) {
			throw new EolModelElementTypeNotFoundException(name, type);
		}
		
		return allContents()
				.stream()
				.filter(e -> Arrays.stream(e.getKinds()).anyMatch(Type.fromName(type)::equals))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<HasId> getAllOfTypeFromModel(String typename) throws EolModelElementTypeNotFoundException {
		if (!hasType(typename)) {
			throw new EolModelElementTypeNotFoundException(name, typename);
		}

		return allContents()
				.stream()
				.filter(e -> e.getType() == Type.fromName(typename))
				.collect(Collectors.toList());
	}

	@Override
	public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
		super.load(properties, resolver);
		setExcelFile(properties.getProperty(ExcelBook.EXCEL_PROPERTY_FILE));
		setName(properties.getProperty(ExcelBook.EXCEL_PROPERTY_NAME, "Excel"));
		load();
	}

	@Override
	public void loadModel() throws EolModelLoadingException {
		try {
			fpw = null;
			delegate = WorkbookFactory.create(excelFile, null, true);
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
		idMap.clear();
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
		sb.append(", excelRef: ").append("[").append(getName()).append("]");
		sb.append(")");
		return sb.toString();
	}

}

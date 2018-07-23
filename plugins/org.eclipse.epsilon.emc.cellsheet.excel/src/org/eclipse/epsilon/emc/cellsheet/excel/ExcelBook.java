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
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.AbstractBook;
import org.eclipse.epsilon.emc.cellsheet.HasDelegate;
import org.eclipse.epsilon.emc.cellsheet.HasId;
import org.eclipse.epsilon.emc.cellsheet.HasType;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IIdResolver;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.emc.cellsheet.Type;
import org.eclipse.epsilon.emc.cellsheet.excel.functions.AiFunctions;
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

  // Lower level access fields
  protected Workbook delegate = null;
  protected File excelFile = null;

  final Map<Sheet, ExcelSheet> _sheets = new HashMap<Sheet, ExcelSheet>();
  final Map<Row, ExcelRow> _rows = new HashMap<Row, ExcelRow>();
  final Map<Cell, ExcelCell> _cells = new HashMap<Cell, ExcelCell>();
  final ExcelIdResolver _idResolver = new ExcelIdResolver();

  WorkbookEvaluator _evaluator = null;
  FormulaParsingWorkbook fpw = null;

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
    return _idResolver.getElementById(this, id);
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
    throw new IllegalArgumentException(
        "No such model element type exists for " + instance.toString());
  }

  @Override
  public IIdResolver getIdResolver() {
    return _idResolver;
  }

  @Override
  public String getId() {
    return _idResolver.getId(this);
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

    final Cell poiCell =
        ((ExcelRow) row).getDelegate().getCell(col, MissingCellPolicy.CREATE_NULL_AS_BLANK);
    ExcelCell excelCell = _cells.get(poiCell);
    if (excelCell == null) {
      excelCell = new ExcelCell((ExcelRow) row, poiCell);
      _cells.put(poiCell, excelCell);
    }
    return excelCell;
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
      throw new IllegalArgumentException(
          "sheet arg must be an ExcelSheet instance, was given: " + sheet);
    }
    if (!this.owns(sheet)) {
      throw new IllegalArgumentException("sheet arg must belong to this book, was given: " + sheet);
    }

    // Get a POI row to work with
    final ExcelSheet excelSheet = (ExcelSheet) sheet;
    Row poiRow = excelSheet.getDelegate().getRow(index);
    if (poiRow == null) {
      poiRow = excelSheet.getDelegate().createRow(index);
    }

    // Check if cached row already exists.
    ExcelRow excelRow = _rows.get(poiRow);
    if (excelRow == null) {
      excelRow = new ExcelRow((ExcelSheet) sheet, poiRow);
      _rows.put(poiRow, excelRow);
    }
    return excelRow;
  }

  @Override
  public ExcelRow getRow(int sheet, int index) {
    return this.getRow(getSheet(sheet), index);
  }

  @Override
  public ExcelRow getRow(String sheet, int index) {
    return getRow(getSheet(sheet), index);
  }

  @Override
  public ExcelSheet getSheet(int index) {
    if (index < 0 || index >= delegate.getNumberOfSheets()) {
      throw new IndexOutOfBoundsException(
          "index must be positive and within range of number of existing sheets, was given: "
              + index);
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
  public Object getEnumerationValue(String enumeration, String label)
      throws EolEnumerationValueNotFoundException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isOfKind(Object instance, String metaClass)
      throws EolModelElementTypeNotFoundException {
    // FIXME: Add in sub-types for Excel only implementations
    return isOfType(instance, metaClass);
  }

  @Override
  public Collection<?> getAllOfKind(String type) throws EolModelElementTypeNotFoundException {
    // FIXME: Add in sub-types for Excel only implementations
    return this.getAllOfType(type);
  }

  @Override
  public Collection<?> getAllOfType(String typename) throws EolModelElementTypeNotFoundException {
    if (!this.hasType(typename)) {
      throw new EolModelElementTypeNotFoundException(this.name, typename);
    }

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
      final List<IRow> rows = new ArrayList<>();
      for (ExcelSheet sheet : this.sheets()) {
        rows.addAll(sheet.rows());
      }
      return rows;
    }

    if (type == Type.CELL) {
      final List<ICell> cells = new ArrayList<>();
      for (ExcelSheet sheet : this.sheets()) {
        for (IRow row : sheet.rows()) {
          cells.addAll(row.cells());
        }
      }
      return cells;
    }

    throw new AssertionError();
  }

  @Override
  public void load(StringProperties properties, IRelativePathResolver resolver)
      throws EolModelLoadingException {
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
      fpw = null;
      delegate = WorkbookFactory.create(excelFile);

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
      _evaluator =
          ((WorkbookEvaluatorProvider) delegate.getCreationHelper().createFormulaEvaluator())
              ._getWorkbookEvaluator();
      delegate.addToolPack(AiFunctions.instance());
      
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
      final IllegalArgumentException e =
          new IllegalArgumentException("Bad filepath given: " + filepath);
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
    return "[" + this.excelFile.getName().toString() + "]";
  }

}

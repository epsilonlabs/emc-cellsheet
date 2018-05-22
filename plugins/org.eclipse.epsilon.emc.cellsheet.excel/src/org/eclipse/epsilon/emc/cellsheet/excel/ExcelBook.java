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
import org.apache.poi.ss.formula.FormulaParser;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IDResolver;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.emc.cellsheet.excel.cell.ExcelFormulaValue;
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
	protected FormulaParsingWorkbook fpw = null;
	
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

	public ExcelCell getCell(Cell rawCell) {
		if (!rawCell.getSheet().getWorkbook().equals(this.raw)) 
			throw new IllegalArgumentException();
		
		ExcelCell excelCell = _cells.get(rawCell);
		if (excelCell == null) {
			excelCell = new ExcelCell(this, rawCell);
			_cells.put(rawCell, excelCell);
		}
		return excelCell;
	}

	@Override
	public ExcelCell getCell(int sheetIndex, int row, int col) {
		return this.getCell(this.getRow(sheetIndex, row), col);
	}

	@Override
	public ExcelCell getCell(IRow row, int col) {
		return this.getCell(((ExcelRow) row).getRaw().getCell(col));
	}

	@Override
	public ExcelCell getCell(ISheet sheet, int row, int col) {
		return this.getCell(this.getRow(sheet, row), col);
	}

	@Override
	public ExcelCell getCell(String sheet, int row, int col) {
		return this.getCell(this.getRow(sheet, row), col);
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
	public ExcelRow getRow(int sheet, int index) {
		return this.getRow(getSheet(sheet), index);
	}

	@Override
	public ExcelRow getRow(ISheet sheet, int index) {
		if (index < 0) throw new IndexOutOfBoundsException();
		if (!this.owns(sheet)) throw new IllegalArgumentException();
		
		return this.getRow(((ExcelSheet) sheet).getRaw().getRow(index));
	}
	
	public ExcelRow getRow(Row rawRow) {
		ExcelRow excelRow = _rows.get(rawRow);
		if (excelRow == null) {
			excelRow = new ExcelRow(this, rawRow);
			_rows.put(rawRow, excelRow);
		}
		return excelRow;
	}

	@Override
	public ExcelRow getRow(String sheet, int index) {
		return getRow(getSheet(sheet), index);
	}

	@Override
	public ExcelSheet getSheet(int index) {
		if (index < 0 || index >= raw.getNumberOfSheets())
			throw new IndexOutOfBoundsException();
		return this.getSheet(this.raw.getSheetAt(index));
	}

	public ExcelSheet getSheet(Sheet rawSheet) {
		if (rawSheet == null) return null;
		
		ExcelSheet excelSheet = _sheets.get(rawSheet);
		if (excelSheet == null) {
			excelSheet = new ExcelSheet(this, rawSheet);
			_sheets.put(rawSheet, excelSheet);
		}
		return excelSheet;
	}
	
	@Override
	public ExcelSheet getSheet(String name) {
		return this.getSheet(this.raw.getSheet(name));
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((excelFile == null) ? 0 : excelFile.hashCode());
		result = prime * result + ((raw == null) ? 0 : raw.hashCode());
		return result;
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
			return this.equals(((ExcelSheet) instance).getBook());

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
		this.raw.sheetIterator().forEachRemaining(s -> this.getSheet(s));
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
	
	public Ptg[] parseFormula(ExcelFormulaValue cellValue) {
		if (fpw == null) {
			System.out.println(raw.getClass());
			if (raw instanceof HSSFWorkbook) fpw = HSSFEvaluationWorkbook.create((HSSFWorkbook) raw);
			if (raw instanceof XSSFWorkbook) fpw = XSSFEvaluationWorkbook.create((XSSFWorkbook) raw);
			if (raw instanceof SXSSFWorkbook) fpw = SXSSFEvaluationWorkbook.create((SXSSFWorkbook) raw);
			if (fpw == null) throw new AssertionError();
		}
		
		return FormulaParser.parse(cellValue.getValue(), 
				fpw, 
				FormulaType.CELL, 
				cellValue.getCell().getSheet().getIndex());
	}
}

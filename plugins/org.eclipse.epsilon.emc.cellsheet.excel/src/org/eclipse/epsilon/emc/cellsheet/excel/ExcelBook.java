package org.eclipse.epsilon.emc.cellsheet.excel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.poi.hssf.usermodel.HSSFEvaluationWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFEvaluationWorkbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFEvaluationWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.emc.cellsheet.HasId;
import org.eclipse.epsilon.emc.cellsheet.HasType;
import org.eclipse.epsilon.emc.cellsheet.IBook;
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

	public static final String PROPERTY_NAME_DEFAULT = "Excel";
	public static final String PROPERTY_FILE = "file";

	// Lower level access fields
	protected Workbook delegate = null;
	protected File excelFile = null;

	WorkbookEvaluator evaluator = null;
	FormulaParsingWorkbook fpw = null;
	
	@Override
	public ISheet getSheet(int index) {
		if (index < 0 || index >= delegate.getNumberOfSheets()) {
			throw new IndexOutOfBoundsException(
					"index must be positive and within range of number of existing sheets, was given: " + index);
		}
		return new ExcelSheet(this, index);
	}
	
	@Override
	public ISheet getSheet(String name) {
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

	public WorkbookEvaluator getEvaluator() {
		return evaluator;
	}

	@Override
	protected Collection<HasId> allContentsFromModel() {
		Collection<HasId> allContents = new ArrayList<>();
		allContents.add(this);
		forEach(sheet ->
			{
				allContents.add(sheet);
				sheet.forEach(row ->
					{
						allContents.add(row);
						row.forEach(cell ->
							{
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
			return Arrays.stream(((HasType) instance).getKinds()).map(k -> k.getTypename()).collect(Collectors.toSet());
		}
		throw new IllegalArgumentException();
	}

	@Override
	protected Object getCacheKeyForType(String typename) throws EolModelElementTypeNotFoundException {
		Type type = Type.fromTypename(typename);
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
	public String getElementId(Object instance) {
		if (instance instanceof HasId) {
			return ((HasId) instance).getId();
		}

		if (instance instanceof HasType) {
			throw new UnsupportedOperationException(
					"ID not implemented for model type: " + ((HasType) instance).getType());
		}
		return null;
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
		return getElementById(getElementId(instance)) != null;
	}

	@Override
	public Object getEnumerationValue(String enumeration, String label) throws EolEnumerationValueNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOfKind(Object instance, String metaClass) throws EolModelElementTypeNotFoundException {
		return IBook.super.isOfKind(instance, metaClass);
	}

	@Override
	public Collection<HasId> getAllOfKindFromModel(String type) throws EolModelElementTypeNotFoundException {
		if (!hasType(type)) {
			throw new EolModelElementTypeNotFoundException(name, type);
		}

		return allContents().stream().filter(e -> Arrays.stream(e.getKinds()).anyMatch(Type.fromTypename(type)::equals))
				.collect(Collectors.toList());
	}

	@Override
	public Collection<HasId> getAllOfTypeFromModel(String typename) throws EolModelElementTypeNotFoundException {
		if (!hasType(typename)) {
			throw new EolModelElementTypeNotFoundException(name, typename);
		}

		return allContents().stream().filter(e -> e.getType() == Type.fromTypename(typename)).collect(Collectors.toList());
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
		try {
			delegate.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		sb.append(", excelRef: ").append(getA1Ref());
		sb.append(")");
		return sb.toString();
	}

	public boolean isLoaded() {
		return allContentsAreCached;
	}
}

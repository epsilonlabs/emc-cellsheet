package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.eclipse.epsilon.common.util.StringProperties;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IRelativePathResolver;
import org.eclipse.epsilon.labs.emc.cellsheet.AbstractBook;
import org.eclipse.epsilon.labs.emc.cellsheet.CoreType;
import org.eclipse.epsilon.labs.emc.cellsheet.IBook;
import org.eclipse.epsilon.labs.emc.cellsheet.ISheet;
import org.eclipse.epsilon.labs.emc.cellsheet.Profiler;

import com.google.common.collect.Iterators;

public class ExcelBook extends AbstractBook implements IBook, HasDelegate<Workbook> {

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
					"index must be positive and within range of number of existing sheets, was given: " + index);
		}
		if (isCached()) {
			return typeCache.get(CoreType.SHEET)
							.stream()
							.map(s -> (ExcelSheet) s)
							.filter(s -> s.getIndex() == index)
							.findFirst()
							.orElse(null);
		}
		return new ExcelSheet.Builder().withBook(this).withIndex(index).withName(delegate.getSheetName(index)).build();
	}

	@Override
	public ExcelSheet getSheet(String name) {
		if (isCached()) {
			return typeCache.get(CoreType.SHEET)
							.stream()
							.map(s -> (ExcelSheet) s)
							.filter(s -> s.getName().equals(name))
							.findFirst()
							.orElse(null);
		}
		Sheet sheet = delegate.getSheet(name);
		return sheet == null ? null
				: new ExcelSheet.Builder()	.withBook(this)
											.withIndex(delegate.getSheetIndex(sheet))
											.withName(name)
											.build();
	}

	@Override
	public Iterator<ISheet> iterator() {
		if (isCached()) {
			return Iterators.transform(typeCache.get(CoreType.SHEET).iterator(), s -> (ExcelSheet) s);
		}

		// Not cached
		final ExcelSheet.Builder b = new ExcelSheet.Builder().withBook(this);
		return Iterators.transform(delegate.iterator(),
				s -> b.withIndex(delegate.getSheetIndex(s)).withName(s.getSheetName()).build());
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
	public void load(StringProperties properties, IRelativePathResolver resolver) throws EolModelLoadingException {
		super.load(properties, resolver);
		setExcelFile(properties.getProperty(ExcelBook.PROPERTY_FILE));
		setName(properties.getProperty(ExcelBook.PROPERTY_NAME, PROPERTY_NAME_DEFAULT));
		load();
	}

	@Override
	public void loadModel() throws EolModelLoadingException {
		Profiler.profileStart(this, "loadModel");
		try {
			delegate = WorkbookFactory.create(excelFile, null, true);
			delegate.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);
		} catch (Exception e) {
			throw new EolModelLoadingException(e, this);
		}
		this.bookname = excelFile.getName();
		Profiler.profileStop(this, "loadModel");
	}

	@Override
	protected void doPreCache() throws EolModelLoadingException {
		Profiler.profileStart(this, "doPreCache()");
		allContents();
		Profiler.profileStop(this, "doPreCache()");
		System.out.println();
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

	public void setExcelFile(String path) {
		setExcelFile(Paths.get(path).toFile());
	}

	public void setExcelFile(File file) {
		excelFile = file;
	}

	@Override
	public int hashCode() {
		return Objects.hash(delegate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExcelBook other = (ExcelBook) obj;
		return Objects.equals(delegate, other.delegate); // POI Delegate
	}

}

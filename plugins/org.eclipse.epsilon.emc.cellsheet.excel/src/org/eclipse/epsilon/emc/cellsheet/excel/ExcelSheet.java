package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.epsilon.emc.cellsheet.IBook;
import org.eclipse.epsilon.emc.cellsheet.IRow;
import org.eclipse.epsilon.emc.cellsheet.ISheet;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;

public class ExcelSheet implements ISheet, HasRaw<Sheet> {
	
	protected Sheet raw;

	public ExcelSheet(Sheet sheet) {
		this.raw = sheet;
	}
	
	@Override
	public Iterator<IRow> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		return this.raw.getSheetName();
	}

	@Override
	public void setName(String name) {
		
	}

	@Override
	public IBook getBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBook(IBook book) {
		// TODO Auto-generated method stub

	}

	@Override
	public IRow getRow(int rowIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<IRow> rowIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRow(IRow row) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createRow(int rowIdx, IRow row) {
		// TODO Auto-generated method stub

	}

	@Override
	public Sheet getRaw() {
		return this.raw;
	}

	@Override
	public void setRaw(Sheet raw) {
		this.raw = raw;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId() {
		// TODO Auto-generated method stub
		
	}

}

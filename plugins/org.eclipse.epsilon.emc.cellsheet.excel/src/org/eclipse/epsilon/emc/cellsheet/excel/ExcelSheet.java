package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.Iterator;

import org.apache.poi.ss.usermodel.Sheet;
import org.eclipse.epsilon.emc.cellsheet.EBook;
import org.eclipse.epsilon.emc.cellsheet.ERow;
import org.eclipse.epsilon.emc.cellsheet.ESheet;
import org.eclipse.epsilon.emc.cellsheet.HasRaw;

public class ExcelSheet implements ESheet, HasRaw<Sheet> {
	
	protected Sheet raw;

	public ExcelSheet(Sheet sheet) {
		this.raw = sheet;
	}
	
	@Override
	public Iterator<ERow> iterator() {
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
	public EBook getBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBook(EBook book) {
		// TODO Auto-generated method stub

	}

	@Override
	public ERow getRow(int rowIdx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<ERow> rowIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createRow(ERow row) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createRow(int rowIdx, ERow row) {
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

package org.eclipse.epsilon.emc.cellsheet.excel;

import org.eclipse.epsilon.emc.cellsheet.ICellValue;

public abstract class AbstractExcelCellValue<T> implements ICellValue<T> {

  protected ExcelBook book;
  protected ExcelSheet sheet;
  protected ExcelCell cell;

  AbstractExcelCellValue(ExcelCell cell) {
    this.cell = cell;
    this.sheet = cell.getSheet();
    this.book = cell.getBook();
  }

  @Override
  public ExcelCell getCell() {
    return this.cell;
  }

}

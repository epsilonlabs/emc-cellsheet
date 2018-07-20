package org.eclipse.epsilon.emc.cellsheet.excel;

import org.apache.poi.ss.usermodel.CellType;
import org.eclipse.epsilon.emc.cellsheet.IStringCellValue;

public class ExcelStringCellValue extends AbstractExcelCellValue<String>
    implements IStringCellValue {

  ExcelStringCellValue(ExcelCell cell) {
    super(cell);
    if (cell.delegate.getCellTypeEnum() != CellType.STRING)
      throw new IllegalArgumentException("Delegate cell must have a String value");
  }

  @Override
  public String getValue() {
    return cell.getDelegate().getStringCellValue();
  }

  @Override
  public String toString() {
    return getValue();
  }

}

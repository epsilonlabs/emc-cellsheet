package org.eclipse.epsilon.emc.cellsheet;

public interface INumericCellValue extends ICellValue<Double> {

  public static final Type TYPE = Type.NUMERIC_CELL_VALUE;
  public static final Type[] KINDS = {INumericCellValue.TYPE, Type.CELL_VALUE};

  @Override
  default Type getType() {
    return INumericCellValue.TYPE;
  }

  @Override
  default Type[] getKinds() {
    return INumericCellValue.KINDS;
  }

}

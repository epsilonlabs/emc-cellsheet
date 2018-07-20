package org.eclipse.epsilon.emc.cellsheet;

public interface IBlankCellValue extends ICellValue<Void> {

  public static final Type TYPE = Type.BLANK_CELL_VALUE;
  public static final Type[] KINDS = {IBlankCellValue.TYPE, Type.CELL_VALUE};

  @Override
  default Type getType() {
    return IBlankCellValue.TYPE;
  }

  @Override
  default Type[] getKinds() {
    return IBlankCellValue.KINDS;
  }

}

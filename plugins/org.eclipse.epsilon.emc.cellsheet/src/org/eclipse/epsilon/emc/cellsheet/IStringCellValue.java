package org.eclipse.epsilon.emc.cellsheet;

public interface IStringCellValue extends ICellValue<String> {

  public static final Type TYPE = Type.STRING_CELL_VALUE;
  public static final Type[] KINDS = {IStringCellValue.TYPE, Type.CELL_VALUE};

  @Override
  default Type getType() {
    return IStringCellValue.TYPE;
  }

  @Override
  default Type[] getKinds() {
    return IStringCellValue.KINDS;
  }

}

package org.eclipse.epsilon.labs.emc.cellsheet;

public class CellValue {

    private String strValue = "";
    private double numValue = 0.0;
    private boolean hasError = false;

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public double getNumValue() {
        return numValue;
    }

    public void setNumValue(double numValue) {
        this.numValue = numValue;
    }

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

}

package org.eclipse.epsilon.labs.emc.cellsheet;

public class AstEval {

    private String stringValue = "";
    private double numberValue = 0.0;
    private boolean isString = false;
    private boolean isNumber = false;
    private boolean isError = false;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public double getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(double numberValue) {
        this.numberValue = numberValue;
    }

    public boolean setIsString() {
        return isString;
    }

    public void setString(boolean string) {
        isString = string;
    }

    public boolean setIsNumber() {
        return isNumber;
    }

    public void setNumber(boolean number) {
        isNumber = number;
    }

    public boolean hasError() {
        return isError;
    }

    public void setIsError(boolean error) {
        this.isError = error;
    }

    @Override
    public String toString() {
        if (isNumber) return Double.toString(numberValue);
        if (isError) return stringValue;
        return stringValue;
    }
}

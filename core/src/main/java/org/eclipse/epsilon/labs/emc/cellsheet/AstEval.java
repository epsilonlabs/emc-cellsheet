package org.eclipse.epsilon.labs.emc.cellsheet;

import java.util.Optional;

public class AstEval {

    public static final AstEval EMPTY = new AstEval();

    private Optional<String> text = Optional.empty();
    private Optional<Double> number = Optional.empty();
    private boolean isError = false;

    private AstEval() {
        this.text = Optional.of("");
    }

    public AstEval(double value) {
        this.number = Optional.of(value);
    }

    public AstEval(String value, boolean isError) {
        this.text = Optional.of(value == null ? "" : value);
        this.isError = isError;
    }

    public AstEval(String value) {
        this(value, false);
    }

    public String getText() {
        return toString();
    }

    public double getNumber() {
        return number.orElse(0.0);
    }

    public boolean isText() {
        return !isError && text.isPresent();
    }

    public boolean isNumber() {
        return number.isPresent();
    }

    public boolean isError() {
        return isError;
    }

    @Override
    public String toString() {
        return number.isPresent() ? Double.toString(number.get()) : text.orElse("");
    }

}

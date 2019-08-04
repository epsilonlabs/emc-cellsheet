package org.eclipse.epsilon.labs.emc.cellsheet;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Arrays;

public enum CellsheetType {

    HAS_ID("HasId"),
    HAS_A1("HasA1"),

    WORKSPACE("Workspace"),
    BOOK("Book"),
    SHEET("Sheet"),
    ROW("Row"),
    CELL("Cell"),

    BLANK_CELL("BlankCell"),
    BOOLEAN_CELL("BooleanCell"),
    TEXT_CELL("TextCell"),
    NUMERIC_CELL("NumericCell"),
    DATE_CELL("DateCell"),
    FORMULA_CELL("FormulaCell"),
    ERROR_CELL("ErrorCell"),

    TOKEN("Token"),
    CELL_FORMAT("CellFormat"),

    AST("AST"),
    NOOP("NoOp"),
    OPERAND("Operand"),
    OPERATION("Operation"),
    PREFIX_OPERATOR("PrefixOperator"),
    INFIX_OPERATOR("InfixOperator"),
    POSTFIX_OPERATOR("PostfixOperator"),
    UNKNOWN("Unknown"),

    TEXT("Text"),
    NUMBER("Number"),
    LOGICAL("Logical"),
    ERROR("Error"),
    RANGE("Range"),
    REF("Ref"),

    RELATIVE_REF("RelativeRef"),
    RELATIVE_RANGE("RelativeRange"),

    FUNCTION("Function"),

    PLUS("Plus"),
    NEGATION("Negation"),
    PERCENT("Percent"),

    EXPONENTIATION("Exponentiation"),
    MULTIPLICATION("Multiplication"),
    DIVISION("Division"),
    ADDITION("Addition"),
    SUBTRACTION("Subtraction"),
    CONCATENATION("Concatenation"),
    EQ("EQ"),
    LT("LT"),
    GT("GT"),
    LTE("LTE"),
    GTE("GTE"),
    NEQ("NEQ"),
    INTERSECTION("INTERSECTION"),
    UNION("UNION");

    private static ImmutableMap<String, CellsheetType> reverseLookup =
            Maps.uniqueIndex(Arrays.asList(CellsheetType.values()), CellsheetType::getTypeName);

    private final String typeName;

    CellsheetType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static CellsheetType fromTypeName(String typeName) {
        return reverseLookup.get(typeName);
    }

    public static void main(String[] args) {
        System.out.println(GT.getTypeName());
        System.out.println(CELL_FORMAT.getTypeName());
    }
}

package org.eclipse.epsilon.labs.emc.cellsheet.poi;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.Workbook;
import org.eclipse.epsilon.labs.emc.cellsheet.Ast;
import org.eclipse.epsilon.labs.emc.cellsheet.excel.PoiBook;
import org.eclipse.epsilon.labs.emc.cellsheet.excel.PoiCell;

import java.util.*;

/**
 * Extension of Apache POI {@link FormulaParser} that maintains additional metadata
 *
 * @author Jonathan Co
 */
public class PoiCellsheetFormulaParser extends FormulaParser {

    private Map<Ptg, String> ptgToNamedRange = new HashMap<>();
    private LinkedHashMap<Ptg, String> ptgs = new LinkedHashMap<>();

    /**
     * Create the formula parser, with the string that is to be
     * parsed against the supplied workbook.
     * A later call the parse() method to return ptg list in
     * rpn order, then call the getRPNPtg() to retrieve the
     * parse results.
     * This class is recommended only for single threaded use.
     * <p>
     * If you have a {@link HSSFWorkbook}, and not a
     * {@link Workbook}, then use the convenience method on
     * {@link HSSFFormulaEvaluator}
     *
     * @param formula
     * @param book
     * @param sheetIndex
     * @param rowIndex
     */
    public PoiCellsheetFormulaParser(String formula, FormulaParsingWorkbook book, int sheetIndex, int rowIndex) {
        super(formula, book, sheetIndex, rowIndex);
    }

    public PoiCellsheetFormulaParser(Ast ast) {
        this(ast.getFormula(),
                ((PoiBook) ast.getCell().getBook()).getFpw(),
                ast.getCell().getSheet().getSheetIndex(),
                ast.getCell().getRowIndex());
    }

    public PoiCellsheetFormulaParser(PoiCell cell) {
        this(cell.getRoot());
    }

    @Override
    protected ParseNode parseRangeExpression() {
        int start = _pointer;
        ParseNode result = super.parseRangeExpression();
        int end = _pointer;
        if (result.getToken() instanceof OperandPtg) {
            ptgToNamedRange.put(result.getToken(), _formulaString.substring(start - 1, end - 1));
        }
        return result;
    }

    public LinkedHashMap<Ptg, String> getPtgs() {
        if (_rootNode == null) parse();
        if (ptgs.isEmpty()) {
            final Iterator<Ptg> it = Arrays.asList(getRPNPtg(FormulaType.CELL)).iterator();
            Ptg ptg;
            while (it.hasNext()) {
                ptg = it.next();
                if (PtgHelper.isSumPtg(ptg)) ptg = FuncVarPtg.SUM;
                ptgs.put(ptg, ptgToNamedRange.getOrDefault(ptg, PtgHelper.valueOf(ptg)));
            }
        }
        return ptgs;
    }
}

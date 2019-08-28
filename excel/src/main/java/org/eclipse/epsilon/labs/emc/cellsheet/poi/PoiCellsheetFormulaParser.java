/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/
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

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Extension of Apache POI {@link FormulaParser} that maintains additional metadata
 *
 * @author Jonathan Co
 */
public class PoiCellsheetFormulaParser extends FormulaParser implements Iterable<Ptg> {

    private Ptg[] ptgs;
    private Map<Ptg, String> tokenMap = new IdentityHashMap<>();

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
            tokenMap.put(result.getToken(), _formulaString.substring(start - 1, end - 1));
        }
        return result;
    }

    @Override
    protected void parse() {
        if (_rootNode == null) {
            super.parse();
            ptgs = getRPNPtg(FormulaType.CELL);
            for (int i = 0; i < ptgs.length; i++) {
                if (PtgHelper.isSumPtg(ptgs[i])) ptgs[i] = FuncVarPtg.SUM;
            }
        }
    }

    public String getTokenValue(Ptg ptg) {
        parse();
        return tokenMap.getOrDefault(ptg, PtgHelper.valueOf(ptg));
    }

    public Ptg[] getPtgs() {
        parse();
        return ptgs;
    }

    @Nonnull
    @Override
    public Iterator<Ptg> iterator() {
        parse();
        return Arrays.stream(ptgs).iterator();
    }

}

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

import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.eclipse.epsilon.labs.emc.cellsheet.excel.PoiBook;

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

    public PoiCellsheetFormulaParser(String formula, PoiBook book, int sheetIndex, int rowIndex) {
        super(formula, book.getFpw(), sheetIndex, rowIndex);
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

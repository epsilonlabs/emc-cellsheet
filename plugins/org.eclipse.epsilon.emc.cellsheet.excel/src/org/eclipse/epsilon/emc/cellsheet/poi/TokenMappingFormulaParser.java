package org.eclipse.epsilon.emc.cellsheet.poi;

import java.util.IdentityHashMap;
import java.util.Map;

import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.ptg.Ptg;

/**
 * A {@link FormulaParser} that maps parsed {@link Ptg}'s to their original
 * tokens.
 * 
 * @author Jonathan Co
 *
 */
public class TokenMappingFormulaParser extends FormulaParser {

	protected Map<Ptg, String> ptgTokens = new IdentityHashMap<Ptg, String>();

	/**
	 * 
	 * @param formula
	 * @param book
	 * @param sheetIndex
	 * @param rowIndex
	 */
	protected TokenMappingFormulaParser(String formula, FormulaParsingWorkbook book, int sheetIndex, int rowIndex) {
		super(formula, book, sheetIndex, rowIndex);
	}

	@Override
	protected ParseNode parseRangeExpression() {
		int start = _pointer;
		ParseNode result = super.parseRangeExpression();
		int end = _pointer;
		dumpToken(result, _formulaString.substring(start - 1, end - 1));
		return result;
	}

	protected void dumpToken(ParseNode node, String value) {
		ptgTokens.put(node.getToken(), value);
	}

}

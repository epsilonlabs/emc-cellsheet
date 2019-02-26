package org.eclipse.epsilon.emc.cellsheet.poi;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.formula.FormulaParsingWorkbook;
import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.AreaI;
import org.apache.poi.ss.formula.ptg.AttrPtg;
import org.apache.poi.ss.formula.ptg.BoolPtg;
import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.ss.formula.ptg.FuncVarPtg;
import org.apache.poi.ss.formula.ptg.IntPtg;
import org.apache.poi.ss.formula.ptg.IntersectionPtg;
import org.apache.poi.ss.formula.ptg.MemAreaPtg;
import org.apache.poi.ss.formula.ptg.MemErrPtg;
import org.apache.poi.ss.formula.ptg.MemFuncPtg;
import org.apache.poi.ss.formula.ptg.NumberPtg;
import org.apache.poi.ss.formula.ptg.OperandPtg;
import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.PercentPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.RefPtgBase;
import org.apache.poi.ss.formula.ptg.ScalarConstantPtg;
import org.apache.poi.ss.formula.ptg.UnaryMinusPtg;
import org.apache.poi.ss.formula.ptg.UnaryPlusPtg;
import org.apache.poi.ss.formula.ptg.UnionPtg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;
import org.eclipse.epsilon.emc.cellsheet.AstSupertype;
import org.eclipse.epsilon.emc.cellsheet.AstType;

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
	public TokenMappingFormulaParser(String formula, FormulaParsingWorkbook book, int sheetIndex, int rowIndex) {
		super(formula, book, sheetIndex, rowIndex);
	}

	@Override
	protected ParseNode parseRangeExpression() {
		int start = _pointer;
		ParseNode result = super.parseRangeExpression();
		int end = _pointer;
		if (result.getToken() instanceof OperandPtg) {
			ptgTokens.put(result.getToken(), _formulaString.substring(start - 1, end - 1));
		}
		return result;
	}

	/**
	 * Retrieves a map of the parsed {@link Ptg}'s mapped to their original token
	 * values. The map will maintain the ordering as defined by
	 * {@link #getRPNPtg(FormulaType)} i.e. in postfix order for evaluation.
	 * 
	 * @return {@link Ptg}'s mapped to their original token values as strings with
	 *         no optimisation
	 */
	public TokenMappings getTokenMappings() {
		if (_rootNode == null) {
			parse();
		}

		final Ptg[] ptgs = getRPNPtg(FormulaType.CELL);
		for (int i = 0; i < ptgs.length; i++) {
			if (PtgHelper.isSumPtg(ptgs[i])) {
				ptgs[i] = FuncVarPtg.SUM;
			}
		}

		final TokenMappings mappings = new TokenMappings(ptgs);
		for (Entry<Ptg, String> e : ptgTokens.entrySet()) {
			mappings.setToken(e.getKey(), e.getValue());
		}
		return mappings;
	}

	@SuppressWarnings("serial")
	public class TokenMappings extends LinkedList<Ptg> {
		private Map<Ptg, AstType> types;
		private Map<Ptg, AstSupertype> supertypes;
		private Map<Ptg, String> tokens;

		public TokenMappings(Ptg... ptgs) {
			super(Arrays.asList(ptgs));
			this.types = new IdentityHashMap<>(ptgs.length);
			this.supertypes = new IdentityHashMap<>(ptgs.length);
			this.tokens = new IdentityHashMap<>(ptgs.length);
		}

		public String getToken(Ptg ptg) {
			return tokens.getOrDefault(ptg, PtgHelper.toString(ptg));
		}

		public void setToken(Ptg ptg, String token) {
			tokens.put(ptg, token);
		}

		public AstSupertype getSupertype(Ptg ptg) {
			return supertypes.computeIfAbsent(ptg, k ->
				{
					// OPERANDS
					if (k instanceof OperandPtg || k instanceof ScalarConstantPtg)
						return AstSupertype.OPERAND;

					// FUNCTIONS/OPERATORS
					if (k instanceof OperationPtg) {
						if (k instanceof AbstractFunctionPtg)
							return AstSupertype.OPERATION;
						if (k instanceof PercentPtg)
							return AstSupertype.OPERATOR_POSTFIX;
						if (k instanceof UnaryMinusPtg || k instanceof UnaryPlusPtg)
							return AstSupertype.OPERATOR_PREFIX;
						return AstSupertype.OPERATOR_INFIX;
					}

					// SUM - Special Case
					if (PtgHelper.isSumPtg(k))
						return AstSupertype.OPERATION;

					// NO-OPS
					if (k instanceof AttrPtg || k instanceof ControlPtg || k instanceof MemAreaPtg
							|| k instanceof MemErrPtg || k instanceof MemFuncPtg)
						return AstSupertype.NOOP;

					// TODO: Handle Arrays

					// UNKNOWN
					return AstSupertype.UNKNOWN;
				});
		}

		public AstType getType(Ptg ptg) {
			return types.computeIfAbsent(ptg, k ->
				{
					switch (getSupertype(k)) {

					// OPERANDS
					case OPERAND:
						if (k instanceof IntPtg || k instanceof NumberPtg)
							return AstType.NUMBER;
						if (ptg instanceof BoolPtg)
							return AstType.LOGICAL;
						if (ptg instanceof AreaI)
							return AstType.RANGE;
						if (ptg instanceof RefPtgBase)
							return AstType.RANGE; // TODO: Change to ref?

					case OPERATION:
						return AstType.FUNCTION;
						
					// OPERATORS
					case OPERATOR_PREFIX:
					case OPERATOR_POSTFIX:
					case OPERATOR_INFIX:
						if (k instanceof ValueOperatorPtg) {
							try {
								final Method method = ValueOperatorPtg.class.getDeclaredMethod("getSid");
								method.setAccessible(true);
								switch ((Byte) method.invoke(k)) {
								case 0x03: // Add
									return AstType.ADDITION;
								case 0x08: // Concat
									return AstType.CONCATENATION;
								case 0x06: // Divide
									return AstType.DIVISION;
								case 0x0b: // Equal
									return AstType.EQ;
								case 0x0c: // GreaterEqual
									return AstType.GTE;
								case 0x0D: // GreaterThan
									return AstType.GT;
								case 0x0a: // LessEqual
									return AstType.LTE;
								case 0x09: // LessThan
									return AstType.LT;
								case 0x05: // Multiply
									return AstType.MULTIPLICATION;
								case 0x0e: // NotEqual
									return AstType.NEQ;
								case 0x14: // Percent
									return AstType.PERCENT;
								case 0x07: // Power
									return AstType.EXPONENTION;
								case 0x04: // Subtract
									return AstType.SUBTRACTION;
								case 0x13: // UnaryMinus
									return AstType.NEGATION;
								case 0x12: // UnaryPlus
									return AstType.PLUS;
								}
							} catch (Exception e) {
								throw new IllegalArgumentException(e);
							}
						}
						if (k instanceof UnionPtg)
							return AstType.UNION;
						if (k instanceof IntersectionPtg)
							return AstType.INTERSECTION;

						// Default to nothing
					default:
						return AstType.NOTHING;
					}
				});
		}
	}
}

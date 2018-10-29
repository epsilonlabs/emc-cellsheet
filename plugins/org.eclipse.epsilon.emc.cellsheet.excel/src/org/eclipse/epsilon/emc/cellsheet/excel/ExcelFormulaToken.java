package org.eclipse.epsilon.emc.cellsheet.excel;

import java.lang.reflect.Method;

import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;
import org.eclipse.epsilon.emc.cellsheet.IFormulaToken;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.Type;

/**
 * Excel Based implementation of {@link IFormulaToken}
 * 
 * @author Jonathan Co
 *
 */
public class ExcelFormulaToken implements IFormulaToken, HasDelegate<Ptg> {

	protected ExcelFormulaTree parent;
	protected Ptg delegate;

	ExcelFormulaToken(ExcelFormulaTree excelFormulaTree, Ptg delegate) {
		this.parent = excelFormulaTree;
		this.delegate = delegate;
	}
	
	@Override
	public IFormulaTree getFormulaTree() {
		return this.parent;
	}

	@Override
	public String toString() {
		try {
			// Simple Operators
			if (this.delegate instanceof ValueOperatorPtg) {
				Method method = ValueOperatorPtg.class.getDeclaredMethod("getSid");
				method.setAccessible(true);
				switch ((Byte) method.invoke(this.delegate)) {
				case 0x03: // Add
					return "+";
				case 0x08: // Concat
					return "&";
				case 0x06: // Divide
					return "/";
				case 0x0b: // Equal
					return "=";
				case 0x0c: // GreaterEqual
					return ">=";
				case 0x0D: // GreaterThan
					return ">";
				case 0x0a: // LessEqual
					return "<=";
				case 0x09: // LessThan
					return "<";
				case 0x05: // Multiply
					return "*";
				case 0x0e: // NotEqual
					return "<>";
				case 0x14: // Percent
					return "%";
				case 0x07: // Power
					return "^";
				case 0x04: // Subtract
					return "-";
				case 0x13: // UnaryMinus
					return "-";
				case 0x12: // UnaryPlus
					return "+";
				default:
					break;
				}
			}
		} catch (Exception e) {
			throw new UnsupportedOperationException("Some ValueOperatorPtg is not supported", e);
		}
		return this.delegate.toFormulaString();
	}
	
	@Override
	public Ptg getDelegate() {
		return this.delegate;
	}
	
	@Override
	public Type getType() {
		// TODO
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public Type[] getKinds() {
		return new Type[] { this.getType(), Type.FORMULA_TOKEN };
	}

}

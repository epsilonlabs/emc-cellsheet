package org.eclipse.epsilon.emc.cellsheet.excel;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.formula.ptg.ValueOperatorPtg;
import org.eclipse.epsilon.emc.cellsheet.HasDelegate;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.Type;

/**
 * 
 * @author Jonathan Co
 *
 */
public class ExcelFormulaTree implements IFormulaTree {
	
	protected ExcelFormulaValue cellValue;
	protected Token token;
	protected List<IFormulaTree> children;
	
	public ExcelFormulaTree(ExcelFormulaValue cellValue, Ptg ptg) {
		super();
		this.cellValue = cellValue;
		this.token = new ExcelToken(ptg);
		this.children = new LinkedList<>();
	}

	@Override
	public IFormulaCellValue getCellValue() {
		return this.cellValue;
	}

	@Override
	public Token getToken() {
		return this.token;
	}

	@Override
	public List<IFormulaTree> getChildren() {
		return children;
	}

	@Override
	public String evaluate() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Excel Based implementation of {@link IFormulaTree.Token}
	 * 
	 * @author Jonathan Co
	 *
	 */
	public class ExcelToken implements IFormulaTree.Token, HasDelegate<Ptg> {
		
		protected Ptg delegate;

		private ExcelToken(Ptg delegate) {
			this.delegate = delegate;
		}
		
		@Override
		public Type getType() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Type[] getKinds() {
			// TODO Auto-generated method stub
			return new Type[] {Type.FORMULA_TOKEN};
		}

		@Override
		public Ptg getDelegate() {
			return this.delegate;
		}

		@Override
		public void setDelegate(Ptg delegate) {
			this.delegate = delegate;
		}

		@Override
		public IFormulaTree getFormulaTree() {
			return ExcelFormulaTree.this;
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
	}

}

package org.eclipse.epsilon.emc.cellsheet.excel;

import java.lang.reflect.Method;
import java.util.Collections;
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
	protected ExcelFormulaTree parent;
	protected Token token;
	protected List<IFormulaTree> children;
	
	public ExcelFormulaTree(ExcelFormulaValue cellValue, ExcelFormulaTree parent, Ptg ptg) {
		super();
		this.cellValue = cellValue;
		this.token = new ExcelToken(ptg);
		this.parent = parent;
		this.children = new LinkedList<>();
	}
	
	public ExcelFormulaTree(ExcelFormulaValue cellValue, Ptg ptg) {
		this(cellValue, null, ptg);
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
	public IFormulaTree getParent() {
		return this.parent;
	}
	
	@Override
	public void setParent(IFormulaTree parent) {
		if (!(parent instanceof ExcelFormulaTree)) throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		this.parent = (ExcelFormulaTree) parent;
	}

	@Override
	public List<IFormulaTree> getChildren() {
		return Collections.unmodifiableList(this.children);
	}
	
	@Override
	public IFormulaTree getChildAt(int index) {
		return this.children.get(index);
	}
	
	@Override
	public void addChild(IFormulaTree child) {
		if (!(child instanceof ExcelFormulaTree)) throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		child.setParent(this);
		this.children.add(child);
	}

	@Override
	public String evaluate() {
		return PoiFormulaHelper.evaluate(this);
	}
	
	@Override
	public String toString() {
		return this.token.toString();
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
			return new Type[] {this.getType(), Type.FORMULA_TOKEN};
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

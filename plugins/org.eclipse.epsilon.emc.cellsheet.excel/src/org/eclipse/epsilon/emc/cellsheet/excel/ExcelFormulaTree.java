package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.formula.ptg.Ptg;
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
		return this.getCellValue();
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
			return this.delegate.toString();
		}
	}

}

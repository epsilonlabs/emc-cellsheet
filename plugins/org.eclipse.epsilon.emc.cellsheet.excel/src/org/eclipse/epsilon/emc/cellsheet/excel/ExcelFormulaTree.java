package org.eclipse.epsilon.emc.cellsheet.excel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.formula.WorkbookEvaluator;
import org.apache.poi.ss.formula.WorkbookEvaluatorProvider;
import org.apache.poi.ss.formula.eval.FunctionEval;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.functions.Function;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaToken;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.excel.functions.AIFunction;
import org.eclipse.epsilon.emc.cellsheet.excel.functions.AIVlookup;

/**
 * 
 * @author Jonathan Co
 *
 */
public class ExcelFormulaTree implements IFormulaTree {
	
	protected ExcelBook book;
	protected ExcelCell cell;
	protected ExcelFormulaCellValue cellValue;
	protected ExcelFormulaTree parent;
	protected IFormulaToken token;
	protected List<IFormulaTree> children;
	
	private static final Function[] EXCEL_FUNCTIONS = getExcelFunctions();
	private static final Function[] AI_FUNCTIONS = getAIFunctions();
	private static Field functionField = null;
	
	public ExcelFormulaTree(ExcelFormulaCellValue cellValue, ExcelFormulaTree parent, Ptg ptg) {
		super();
		this.book = cellValue.getCell().getBook();
		this.cell = cellValue.getCell();
		this.cellValue = cellValue;
		this.token = new ExcelFormulaToken(this, ptg);
		this.parent = parent;
		this.children = new LinkedList<>();
	}
	
	public ExcelFormulaTree(ExcelFormulaCellValue cellValue, Ptg ptg) {
		this(cellValue, null, ptg);
	}

	@Override
	public IFormulaCellValue getCellValue() {
		return this.cellValue;
	}

	@Override
	public IFormulaToken getToken() {
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
		return this.evaluate(false);
	}
	
	@Override
	public String evaluate(boolean doAi) {
		final WorkbookEvaluator evaluator = ((WorkbookEvaluatorProvider) book.getDelegate()
				.getCreationHelper()
				.createFormulaEvaluator())
				._getWorkbookEvaluator();		
		
		String formula = this.toFormula();
		CellReference ref = new CellReference(this.cell.getDelegate());	

		
		if (doAi) setFunctions(AI_FUNCTIONS);
		ValueEval result = evaluator.evaluate(formula, ref);
		if (doAi) setFunctions(EXCEL_FUNCTIONS);
		
		return OperandResolver.coerceValueToString(result);
	}
	
	@Override
	public String toFormula() {
		return PoiFormulaHelper.buildFormulaString(this);
	}
	
	@Override
	public String toString() {
		return this.token + " -> " + this.toFormula();
	}
	
	/**
	 * Accessor method for retrieving the {@link FunctionEval#functions} in a
	 * safe and reliable manner.
	 * 
	 * This method should be called for every access to this field to ensure
	 * access permissions will be set up.
	 * 
	 * @return
	 */
	static Field getFunctionField() {
		if (functionField == null) {
			try {
				functionField = FunctionEval.class.getDeclaredField("functions");
				functionField.setAccessible(true);
				Field modifiers = Field.class.getDeclaredField("modifiers");
				modifiers.setAccessible(true);
				modifiers.setInt(functionField, functionField.getModifiers() & ~Modifier.FINAL);
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		}
		return functionField;
	}

	static void setFunctions(Function[] functions) {
		try {
			getFunctionField().set(null, functions);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	static Function[] getExcelFunctions() {
		try {
			return (Function[]) getFunctionField().get(null);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	static Function[] getAIFunctions() {
		// Create functions
		final List<AIFunction> aiFunctions = new LinkedList<>();
		aiFunctions.add(new AIVlookup());

		// Create a copy so as not to overwrite originals for normal evaluation
		final Function[] oldFunctions = getExcelFunctions();
		final Function[] newFunctions = Arrays.copyOf(oldFunctions, oldFunctions.length);

		for (AIFunction f : aiFunctions) {
			newFunctions[f.getFunctionId()] = f;
		}

		return newFunctions;
	}

}

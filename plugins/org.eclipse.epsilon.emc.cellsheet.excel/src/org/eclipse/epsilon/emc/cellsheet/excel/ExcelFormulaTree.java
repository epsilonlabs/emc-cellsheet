package org.eclipse.epsilon.emc.cellsheet.excel;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.formula.FormulaRenderer;
import org.apache.poi.ss.formula.FormulaRenderingWorkbook;
import org.apache.poi.ss.formula.eval.OperandResolver;
import org.apache.poi.ss.formula.eval.ValueEval;
import org.apache.poi.ss.formula.ptg.AbstractFunctionPtg;
import org.apache.poi.ss.formula.ptg.Area3DPxg;
import org.apache.poi.ss.formula.ptg.AreaPtgBase;
import org.apache.poi.ss.formula.ptg.ControlPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.util.CellReference;
import org.eclipse.epsilon.emc.cellsheet.ICell;
import org.eclipse.epsilon.emc.cellsheet.IFormulaCellValue;
import org.eclipse.epsilon.emc.cellsheet.IFormulaTree;
import org.eclipse.epsilon.emc.cellsheet.Type;

/**
 * 
 * @author Jonathan Co
 *
 */
public class ExcelFormulaTree implements IFormulaTree, HasDelegate<Ptg> {

	protected ExcelFormulaTree parent;
	protected ExcelFormulaCellValue cellValue;
	protected int ptgIndex;
	
	protected List<ExcelFormulaTree> children = new LinkedList<>();

	protected Type type = Type.UNKNOWN_NODE;
	protected Type[] kinds = new Type[] {Type.FORMULA_TREE, type};

	public ExcelFormulaTree(ExcelFormulaCellValue cellValue, ExcelFormulaTree parent, int ptgIndex) {
		this.cellValue = cellValue;
		this.parent = parent;
		this.ptgIndex = ptgIndex;
	}

	@Override
	public ExcelFormulaCellValue getCellValue() {
		return cellValue;
	}

	@Override
	public void setCellValue(IFormulaCellValue cellValue) {
		if (!(cellValue instanceof ExcelFormulaCellValue))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaCellValue");
		this.cellValue = (ExcelFormulaCellValue) cellValue;
	}

	@Override
	public Ptg getDelegate() {
		return FormulaUtil.getPtgs(cellValue)[ptgIndex];
	}

	@Override
	public ExcelFormulaTree getParent() {
		return this.parent;
	}

	@Override
	public void setParent(IFormulaTree parent) {
		if (!(parent instanceof ExcelFormulaTree))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		this.parent = (ExcelFormulaTree) parent;
	}

	@Override
	public List<IFormulaTree> getChildren() {
		return Collections.unmodifiableList(this.children);
	}

	@Override
	public void addChild(IFormulaTree child) {
		if (!(child instanceof ExcelFormulaTree))
			throw new IllegalArgumentException("Parent must be of type ExcelFormulaTree");
		child.setParent(this);
		this.children.add((ExcelFormulaTree) child);
	}

	@Override
	public String evaluate() {
		return doEvaluation(getFormula());
	}

	@Override
	public ICell evaluateCell() {
		if (!(getDelegate() instanceof AbstractFunctionPtg)) {
			return getCell();
		}

		AbstractFunctionPtg function = (AbstractFunctionPtg) getDelegate();

		// TODO: Translate this to a transformation
		// TODO: Move to register based system
		switch (function.getName()) {
		case "VLOOKUP":
			// Determine new lookup table
			AreaPtgBase table_array = (AreaPtgBase) ((ExcelFormulaTree) getChildAt(1)).getDelegate();
			String lookupCol = CellReference.convertNumToColString(table_array.getFirstColumn());
			int firstRow = table_array.getFirstRow();
			int lastRow = table_array.getLastRow();

			String sheetName = getSheet().getName(); // Determine the sheet to use for refs
			if (table_array instanceof Area3DPxg) {
				sheetName = ((Area3DPxg) table_array).getSheetName();
			}

			String newTableArray = String.format("%s!%s%d:%s%d", sheetName, lookupCol, firstRow + 1, lookupCol,
					lastRow);

			// Determine whether to do range lookup
			String isRangeLookup = "1";
			if (function.getNumberOfOperands() > 3) {
				isRangeLookup = getChildAt(3).getFormula();
			}

			// ADDRESS( MATCH ($lookup_value, $lookup_array, $is_exact_match), $not_needed,
			// $not_needed, $sheet_to_look_in)
			String newFormula = String.format("ADDRESS(MATCH(%s,%s,%s),%s,,,\"%s\")", getChildAt(0).getFormula(),
					newTableArray, isRangeLookup, getChildAt(2).getFormula(), sheetName);

			// Evaluate and get cell reference
			CellReference cr = new CellReference(doEvaluation(newFormula));
			return getBook().getSheet(cr.getSheetName()).getRow(cr.getRow()).getCell(cr.getCol());
		default:
			throw new UnsupportedOperationException();
		}
	}

	String doEvaluation(String formula) {
		CellReference ref = new CellReference(getSheet().getName(), getRow().getIndex(), getCell().getColIndex(), false,
				false);

		ValueEval result = ((ExcelBook) getBook()).evaluator.evaluate(formula, ref);
		return OperandResolver.coerceValueToString(result);
	}

	@Override
	public String getFormula() {
		// AST does not take into account control characters such as brackets, therefore
		// they are not children.
		// Rebuild the PTG stack with these characters
		Ptg[] ptgs = FormulaUtil.getPtgs(cellValue);
		Deque<Ptg> stack = new ArrayDeque<>();
		int current = ptgIndex;
		int count = countAllChildren() + 1;

		while (count > 0) {
			stack.push(ptgs[current]);
			if (!(ptgs[current] instanceof ControlPtg) || FormulaUtil.isSumPtg(ptgs[current])) {
				count--;
			}
			current--;
		}
		return FormulaRenderer.toFormulaString((FormulaRenderingWorkbook) ((ExcelBook) getBook()).fpw,
				stack.toArray(new Ptg[0]));
	}

	@Override
	public String getToken() {
		return FormulaUtil.ptgToStr(getDelegate());
	}

	@Override
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
		kinds[1] = type;
	}

	@Override
	public Type[] getKinds() {
		return kinds;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[").append(getClass().getSimpleName()).append("@").append(hashCode()).append("]");
		sb.append("(id: ").append(getId());
		sb.append(", formula: ").append(getFormula());
		sb.append(", token: ").append(getToken());
		sb.append(", type: ").append(getType());
		if (isRoot()) {
			sb.append(", isRoot: true");
		}
		sb.append(")");
		return sb.toString();
	}
}

package org.eclipse.epsilon.labs.emc.cellsheet.excel;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

import org.apache.poi.ss.formula.ptg.OperationPtg;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.apache.poi.ss.usermodel.FormulaError;
import org.eclipse.epsilon.labs.emc.cellsheet.AbstractCellValue;
import org.eclipse.epsilon.labs.emc.cellsheet.AstSupertype;
import org.eclipse.epsilon.labs.emc.cellsheet.AstType;
import org.eclipse.epsilon.labs.emc.cellsheet.CellValueType;
import org.eclipse.epsilon.labs.emc.cellsheet.poi.TokenMappingFormulaParser.TokenMappings;

import com.google.common.primitives.Doubles;

public class ExcelCellValue extends AbstractCellValue {

	protected ExcelCell cell;

	public ExcelCellValue() {
		super();
		this.cell = null;
	}

	public ExcelCellValue(CellValueType type) {
		super(type);
	}

	public ExcelCellValue(ExcelCell cell, CellValueType type) {
		super(type);
		this.cell = cell;
	}

	@Override
	public ExcelCell getCell() {
		return this.cell;
	}

	public void setCell(ExcelCell cell) {
		this.cell = cell;
	}

	@Override
	public boolean getBooleanValue() {
		switch (type) {
		case BOOLEAN:
			return cell.getDelegate().getBooleanCellValue();
		case FORMULA:
			return Boolean.valueOf(getStringValue());
			default:
				 return false;
		}
	}

	@Override
	public double getNumericValue() {
		switch (type) {
		case NUMERIC:
			return cell.getDelegate().getNumericCellValue();
		case FORMULA:
			Double result = Doubles.tryParse(getStringValue());
			if (result != null) {
				return result;
			}
		default:
			return 0;
		}
	}

	@Override
	public Date getDateValue() {
		// FIXME: IF formula return evaluated date
		if (type == CellValueType.DATE) {
			return cell.getDelegate().getDateCellValue();
		}
		return null;
	}

	@Override
	public String getStringValue() {
		if (cell == null) {
			return "";
		}

		switch (type) {
		case BLANK:
		case NONE:
			return "";
		case BOOLEAN:
			return Boolean.toString(getBooleanValue());
		case NUMERIC:
			return Double.toString(getNumericValue());
		case DATE:
			return getDateValue().toString();
		case FORMULA:
			return getAst().evaluate();
		case ERROR:
			return getErrorValue();
		default:
			return cell.getDelegate().getStringCellValue();
		}
	}

	@Override
	public String getFormula() {
		switch (type) {
		case FORMULA:
			return cell.getDelegate().getCellFormula();
		case DATE:
		case STRING:
			return String.format("\"%s\"", getStringValue());
		default:
			return getStringValue();
		}
	}

	@Override
	public String getErrorValue() {
		if (type == CellValueType.ERROR) {
			return FormulaError.forInt(cell.getDelegate().getErrorCellValue()).name();
		}
		return null;
	}

	@Override
	public ExcelAst getAst() {
		if (type == CellValueType.FORMULA) {
			final TokenMappings tokenMap = EvaluationHelper.getPtgs(getFormula(), cell);
			final Deque<ExcelAst> stack = new ArrayDeque<>();
			for (Ptg ptg : tokenMap) {
				// Skip No-Ops
				if (tokenMap.getSupertype(ptg) == AstSupertype.NOOP)
					continue;

				final ExcelAst current = new ExcelAst.Builder().withToken(tokenMap.getToken(ptg))
						.withSupertype(tokenMap.getSupertype(ptg)).withType(tokenMap.getType(ptg))
						.withCellValue(cell.getCellValue()).build();

				if (ptg instanceof OperationPtg) {
					final OperationPtg cast = (OperationPtg) ptg;
					for (int j = cast.getNumberOfOperands(); j > 0; j--) {
						current.addChild(0, stack.pop());
					}
				}

				stack.push(current);
			}

			if (stack.size() != 1) {
				throw new AssertionError();
			}

			final ExcelAst root = stack.pop();
			root.setPosition(0);
			return root;
		}

		final ExcelAst.Builder b = new ExcelAst.Builder().withPosition(0).withToken(getStringValue())
				.withSupertype(AstSupertype.OPERAND).withCellValue(this);
		switch (type) {
		case BOOLEAN:
			b.withType(AstType.LOGICAL);
			break;
		case NUMERIC:
			b.withType(AstType.NUMBER);
			break;
		case ERROR:
			b.withType(AstType.ERROR);
			break;
		case DATE:
		case STRING:
			b.withType(AstType.TEXT);
			break;
		case BLANK:
		case NONE:
			b.withSupertype(AstSupertype.NOOP).withType(AstType.NOTHING);
			break;
		default:
			throw new IllegalArgumentException();
		}
		return b.build();
	}

}

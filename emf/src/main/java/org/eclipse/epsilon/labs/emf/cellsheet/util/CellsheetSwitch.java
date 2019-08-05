/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.epsilon.labs.emf.cellsheet.Addition;
import org.eclipse.epsilon.labs.emf.cellsheet.Ast;
import org.eclipse.epsilon.labs.emf.cellsheet.AstEval;
import org.eclipse.epsilon.labs.emf.cellsheet.BlankCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Book;
import org.eclipse.epsilon.labs.emf.cellsheet.BooleanCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Cell;
import org.eclipse.epsilon.labs.emf.cellsheet.CellFormat;
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage;
import org.eclipse.epsilon.labs.emf.cellsheet.Concatenation;
import org.eclipse.epsilon.labs.emf.cellsheet.DateCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Division;
import org.eclipse.epsilon.labs.emf.cellsheet.EQ;
import org.eclipse.epsilon.labs.emf.cellsheet.Exponentiation;
import org.eclipse.epsilon.labs.emf.cellsheet.FormulaCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Function;
import org.eclipse.epsilon.labs.emf.cellsheet.GT;
import org.eclipse.epsilon.labs.emf.cellsheet.GTE;
import org.eclipse.epsilon.labs.emf.cellsheet.HasA1;
import org.eclipse.epsilon.labs.emf.cellsheet.HasId;
import org.eclipse.epsilon.labs.emf.cellsheet.InfixOperator;
import org.eclipse.epsilon.labs.emf.cellsheet.Intersection;
import org.eclipse.epsilon.labs.emf.cellsheet.LT;
import org.eclipse.epsilon.labs.emf.cellsheet.LTE;
import org.eclipse.epsilon.labs.emf.cellsheet.Logical;
import org.eclipse.epsilon.labs.emf.cellsheet.Multiplication;
import org.eclipse.epsilon.labs.emf.cellsheet.NEQ;
import org.eclipse.epsilon.labs.emf.cellsheet.Negation;
import org.eclipse.epsilon.labs.emf.cellsheet.Noop;
import org.eclipse.epsilon.labs.emf.cellsheet.NumericCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Operand;
import org.eclipse.epsilon.labs.emf.cellsheet.Operation;
import org.eclipse.epsilon.labs.emf.cellsheet.Percent;
import org.eclipse.epsilon.labs.emf.cellsheet.Plus;
import org.eclipse.epsilon.labs.emf.cellsheet.PostfixOperator;
import org.eclipse.epsilon.labs.emf.cellsheet.PrefixOperator;
import org.eclipse.epsilon.labs.emf.cellsheet.Range;
import org.eclipse.epsilon.labs.emf.cellsheet.Ref;
import org.eclipse.epsilon.labs.emf.cellsheet.RelativeRange;
import org.eclipse.epsilon.labs.emf.cellsheet.RelativeRef;
import org.eclipse.epsilon.labs.emf.cellsheet.Row;
import org.eclipse.epsilon.labs.emf.cellsheet.Sheet;
import org.eclipse.epsilon.labs.emf.cellsheet.Subtraction;
import org.eclipse.epsilon.labs.emf.cellsheet.Text;
import org.eclipse.epsilon.labs.emf.cellsheet.TextCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Token;
import org.eclipse.epsilon.labs.emf.cellsheet.Union;
import org.eclipse.epsilon.labs.emf.cellsheet.Unknown;
import org.eclipse.epsilon.labs.emf.cellsheet.Workspace;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage
 * @generated
 */
public class CellsheetSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CellsheetPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CellsheetSwitch() {
		if (modelPackage == null) {
			modelPackage = CellsheetPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case CellsheetPackage.ESTRING_TO_TOKEN_ENTRY: {
				@SuppressWarnings("unchecked") Map.Entry<String, Token> eStringToTokenEntry = (Map.Entry<String, Token>)theEObject;
				T result = caseEStringToTokenEntry(eStringToTokenEntry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.HAS_A1: {
				HasA1 hasA1 = (HasA1)theEObject;
				T result = caseHasA1(hasA1);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.HAS_ID: {
				HasId hasId = (HasId)theEObject;
				T result = caseHasId(hasId);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.WORKSPACE: {
				Workspace workspace = (Workspace)theEObject;
				T result = caseWorkspace(workspace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.BOOK: {
				Book book = (Book)theEObject;
				T result = caseBook(book);
				if (result == null) result = caseHasId(book);
				if (result == null) result = caseHasA1(book);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.SHEET: {
				Sheet sheet = (Sheet)theEObject;
				T result = caseSheet(sheet);
				if (result == null) result = caseHasId(sheet);
				if (result == null) result = caseHasA1(sheet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.ROW: {
				Row row = (Row)theEObject;
				T result = caseRow(row);
				if (result == null) result = caseHasId(row);
				if (result == null) result = caseHasA1(row);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.CELL: {
				Cell cell = (Cell)theEObject;
				T result = caseCell(cell);
				if (result == null) result = caseHasId(cell);
				if (result == null) result = caseHasA1(cell);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.BLANK_CELL: {
				BlankCell blankCell = (BlankCell)theEObject;
				T result = caseBlankCell(blankCell);
				if (result == null) result = caseCell(blankCell);
				if (result == null) result = caseHasId(blankCell);
				if (result == null) result = caseHasA1(blankCell);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.TEXT_CELL: {
				TextCell textCell = (TextCell)theEObject;
				T result = caseTextCell(textCell);
				if (result == null) result = caseCell(textCell);
				if (result == null) result = caseHasId(textCell);
				if (result == null) result = caseHasA1(textCell);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.NUMERIC_CELL: {
				NumericCell numericCell = (NumericCell)theEObject;
				T result = caseNumericCell(numericCell);
				if (result == null) result = caseCell(numericCell);
				if (result == null) result = caseHasId(numericCell);
				if (result == null) result = caseHasA1(numericCell);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.BOOLEAN_CELL: {
				BooleanCell booleanCell = (BooleanCell)theEObject;
				T result = caseBooleanCell(booleanCell);
				if (result == null) result = caseCell(booleanCell);
				if (result == null) result = caseHasId(booleanCell);
				if (result == null) result = caseHasA1(booleanCell);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.DATE_CELL: {
				DateCell dateCell = (DateCell)theEObject;
				T result = caseDateCell(dateCell);
				if (result == null) result = caseCell(dateCell);
				if (result == null) result = caseHasId(dateCell);
				if (result == null) result = caseHasA1(dateCell);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.FORMULA_CELL: {
				FormulaCell formulaCell = (FormulaCell)theEObject;
				T result = caseFormulaCell(formulaCell);
				if (result == null) result = caseCell(formulaCell);
				if (result == null) result = caseHasId(formulaCell);
				if (result == null) result = caseHasA1(formulaCell);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.TOKEN: {
				Token token = (Token)theEObject;
				T result = caseToken(token);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.AST_EVAL: {
				AstEval astEval = (AstEval)theEObject;
				T result = caseAstEval(astEval);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.AST: {
				Ast ast = (Ast)theEObject;
				T result = caseAst(ast);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.OPERAND: {
				Operand operand = (Operand)theEObject;
				T result = caseOperand(operand);
				if (result == null) result = caseAst(operand);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.OPERATION: {
				Operation operation = (Operation)theEObject;
				T result = caseOperation(operation);
				if (result == null) result = caseAst(operation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.PREFIX_OPERATOR: {
				PrefixOperator prefixOperator = (PrefixOperator)theEObject;
				T result = casePrefixOperator(prefixOperator);
				if (result == null) result = caseAst(prefixOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.INFIX_OPERATOR: {
				InfixOperator infixOperator = (InfixOperator)theEObject;
				T result = caseInfixOperator(infixOperator);
				if (result == null) result = caseAst(infixOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.POSTFIX_OPERATOR: {
				PostfixOperator postfixOperator = (PostfixOperator)theEObject;
				T result = casePostfixOperator(postfixOperator);
				if (result == null) result = caseAst(postfixOperator);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.UNKNOWN: {
				Unknown unknown = (Unknown)theEObject;
				T result = caseUnknown(unknown);
				if (result == null) result = caseAst(unknown);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.NOOP: {
				Noop noop = (Noop)theEObject;
				T result = caseNoop(noop);
				if (result == null) result = caseAst(noop);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.TEXT: {
				Text text = (Text)theEObject;
				T result = caseText(text);
				if (result == null) result = caseOperand(text);
				if (result == null) result = caseAst(text);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.NUMBER: {
				org.eclipse.epsilon.labs.emf.cellsheet.Number number = (org.eclipse.epsilon.labs.emf.cellsheet.Number)theEObject;
				T result = caseNumber(number);
				if (result == null) result = caseOperand(number);
				if (result == null) result = caseAst(number);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.LOGICAL: {
				Logical logical = (Logical)theEObject;
				T result = caseLogical(logical);
				if (result == null) result = caseOperand(logical);
				if (result == null) result = caseAst(logical);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.ERROR: {
				org.eclipse.epsilon.labs.emf.cellsheet.Error error = (org.eclipse.epsilon.labs.emf.cellsheet.Error)theEObject;
				T result = caseError(error);
				if (result == null) result = caseOperand(error);
				if (result == null) result = caseAst(error);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.REF: {
				Ref ref = (Ref)theEObject;
				T result = caseRef(ref);
				if (result == null) result = caseOperand(ref);
				if (result == null) result = caseAst(ref);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.RANGE: {
				Range range = (Range)theEObject;
				T result = caseRange(range);
				if (result == null) result = caseOperand(range);
				if (result == null) result = caseAst(range);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.RELATIVE_REF: {
				RelativeRef relativeRef = (RelativeRef)theEObject;
				T result = caseRelativeRef(relativeRef);
				if (result == null) result = caseRef(relativeRef);
				if (result == null) result = caseOperand(relativeRef);
				if (result == null) result = caseAst(relativeRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.RELATIVE_RANGE: {
				RelativeRange relativeRange = (RelativeRange)theEObject;
				T result = caseRelativeRange(relativeRange);
				if (result == null) result = caseRef(relativeRange);
				if (result == null) result = caseOperand(relativeRange);
				if (result == null) result = caseAst(relativeRange);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.FUNCTION: {
				Function function = (Function)theEObject;
				T result = caseFunction(function);
				if (result == null) result = caseOperation(function);
				if (result == null) result = caseAst(function);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.PLUS: {
				Plus plus = (Plus)theEObject;
				T result = casePlus(plus);
				if (result == null) result = casePrefixOperator(plus);
				if (result == null) result = caseAst(plus);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.NEGATION: {
				Negation negation = (Negation)theEObject;
				T result = caseNegation(negation);
				if (result == null) result = casePrefixOperator(negation);
				if (result == null) result = caseAst(negation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.PERCENT: {
				Percent percent = (Percent)theEObject;
				T result = casePercent(percent);
				if (result == null) result = casePostfixOperator(percent);
				if (result == null) result = caseAst(percent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.EXPONENTIATION: {
				Exponentiation exponentiation = (Exponentiation)theEObject;
				T result = caseExponentiation(exponentiation);
				if (result == null) result = caseInfixOperator(exponentiation);
				if (result == null) result = caseAst(exponentiation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.MULTIPLICATION: {
				Multiplication multiplication = (Multiplication)theEObject;
				T result = caseMultiplication(multiplication);
				if (result == null) result = caseInfixOperator(multiplication);
				if (result == null) result = caseAst(multiplication);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.DIVISION: {
				Division division = (Division)theEObject;
				T result = caseDivision(division);
				if (result == null) result = caseInfixOperator(division);
				if (result == null) result = caseAst(division);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.ADDITION: {
				Addition addition = (Addition)theEObject;
				T result = caseAddition(addition);
				if (result == null) result = caseInfixOperator(addition);
				if (result == null) result = caseAst(addition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.SUBTRACTION: {
				Subtraction subtraction = (Subtraction)theEObject;
				T result = caseSubtraction(subtraction);
				if (result == null) result = caseInfixOperator(subtraction);
				if (result == null) result = caseAst(subtraction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.CONCATENATION: {
				Concatenation concatenation = (Concatenation)theEObject;
				T result = caseConcatenation(concatenation);
				if (result == null) result = caseInfixOperator(concatenation);
				if (result == null) result = caseAst(concatenation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.EQ: {
				EQ eq = (EQ)theEObject;
				T result = caseEQ(eq);
				if (result == null) result = caseInfixOperator(eq);
				if (result == null) result = caseAst(eq);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.LT: {
				LT lt = (LT)theEObject;
				T result = caseLT(lt);
				if (result == null) result = caseInfixOperator(lt);
				if (result == null) result = caseAst(lt);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.GT: {
				GT gt = (GT)theEObject;
				T result = caseGT(gt);
				if (result == null) result = caseInfixOperator(gt);
				if (result == null) result = caseAst(gt);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.LTE: {
				LTE lte = (LTE)theEObject;
				T result = caseLTE(lte);
				if (result == null) result = caseInfixOperator(lte);
				if (result == null) result = caseAst(lte);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.GTE: {
				GTE gte = (GTE)theEObject;
				T result = caseGTE(gte);
				if (result == null) result = caseInfixOperator(gte);
				if (result == null) result = caseAst(gte);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.NEQ: {
				NEQ neq = (NEQ)theEObject;
				T result = caseNEQ(neq);
				if (result == null) result = caseInfixOperator(neq);
				if (result == null) result = caseAst(neq);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.INTERSECTION: {
				Intersection intersection = (Intersection)theEObject;
				T result = caseIntersection(intersection);
				if (result == null) result = caseInfixOperator(intersection);
				if (result == null) result = caseAst(intersection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.UNION: {
				Union union = (Union)theEObject;
				T result = caseUnion(union);
				if (result == null) result = caseInfixOperator(union);
				if (result == null) result = caseAst(union);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case CellsheetPackage.CELL_FORMAT: {
				CellFormat cellFormat = (CellFormat)theEObject;
				T result = caseCellFormat(cellFormat);
				if (result == null) result = caseHasId(cellFormat);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EString To Token Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EString To Token Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEStringToTokenEntry(Map.Entry<String, Token> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Has A1</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Has A1</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHasA1(HasA1 object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Has Id</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Has Id</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHasId(HasId object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Workspace</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Workspace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWorkspace(Workspace object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Book</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Book</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBook(Book object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sheet</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sheet</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSheet(Sheet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Row</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Row</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRow(Row object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cell</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cell</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCell(Cell object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Blank Cell</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Blank Cell</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBlankCell(BlankCell object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text Cell</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text Cell</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTextCell(TextCell object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Numeric Cell</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Numeric Cell</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumericCell(NumericCell object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Boolean Cell</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Boolean Cell</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBooleanCell(BooleanCell object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Date Cell</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Date Cell</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDateCell(DateCell object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Formula Cell</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Formula Cell</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormulaCell(FormulaCell object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Token</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Token</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseToken(Token object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ast Eval</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ast Eval</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAstEval(AstEval object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ast</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ast</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAst(Ast object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operand</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operand</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperand(Operand object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperation(Operation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Prefix Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Prefix Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrefixOperator(PrefixOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Infix Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Infix Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInfixOperator(InfixOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Postfix Operator</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Postfix Operator</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePostfixOperator(PostfixOperator object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Unknown</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Unknown</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnknown(Unknown object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Noop</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Noop</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNoop(Noop object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseText(Text object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Number</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Number</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNumber(org.eclipse.epsilon.labs.emf.cellsheet.Number object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Logical</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Logical</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLogical(Logical object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Error</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Error</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseError(org.eclipse.epsilon.labs.emf.cellsheet.Error object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRef(Ref object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Range</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Range</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRange(Range object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relative Ref</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relative Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelativeRef(RelativeRef object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relative Range</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relative Range</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelativeRange(RelativeRange object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Function</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Function</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFunction(Function object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Plus</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plus</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePlus(Plus object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Negation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Negation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNegation(Negation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Percent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Percent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePercent(Percent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exponentiation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exponentiation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExponentiation(Exponentiation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multiplication</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multiplication</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiplication(Multiplication object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Division</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Division</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDivision(Division object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Addition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Addition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAddition(Addition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Subtraction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Subtraction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSubtraction(Subtraction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Concatenation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Concatenation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConcatenation(Concatenation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EQ</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EQ</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEQ(EQ object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>LT</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>LT</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLT(LT object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>GT</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>GT</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGT(GT object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>LTE</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>LTE</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLTE(LTE object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>GTE</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>GTE</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGTE(GTE object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>NEQ</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>NEQ</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNEQ(NEQ object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Intersection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Intersection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIntersection(Intersection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnion(Union object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cell Format</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cell Format</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCellFormat(CellFormat object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //CellsheetSwitch

/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.epsilon.labs.emf.cellsheet.Addition;
import org.eclipse.epsilon.labs.emf.cellsheet.BlankCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Book;
import org.eclipse.epsilon.labs.emf.cellsheet.BooleanCell;
import org.eclipse.epsilon.labs.emf.cellsheet.CellFormat;
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetFactory;
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage;
import org.eclipse.epsilon.labs.emf.cellsheet.Concatenation;
import org.eclipse.epsilon.labs.emf.cellsheet.DateCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Division;
import org.eclipse.epsilon.labs.emf.cellsheet.EQ;
import org.eclipse.epsilon.labs.emf.cellsheet.ErrorCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Exponentiation;
import org.eclipse.epsilon.labs.emf.cellsheet.Exponention;
import org.eclipse.epsilon.labs.emf.cellsheet.FormulaCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Function;
import org.eclipse.epsilon.labs.emf.cellsheet.GT;
import org.eclipse.epsilon.labs.emf.cellsheet.GTE;
import org.eclipse.epsilon.labs.emf.cellsheet.Intersection;
import org.eclipse.epsilon.labs.emf.cellsheet.LT;
import org.eclipse.epsilon.labs.emf.cellsheet.LTE;
import org.eclipse.epsilon.labs.emf.cellsheet.Logical;
import org.eclipse.epsilon.labs.emf.cellsheet.Multiplication;
import org.eclipse.epsilon.labs.emf.cellsheet.NEQ;
import org.eclipse.epsilon.labs.emf.cellsheet.Negation;
import org.eclipse.epsilon.labs.emf.cellsheet.Noop;
import org.eclipse.epsilon.labs.emf.cellsheet.NumericCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Percent;
import org.eclipse.epsilon.labs.emf.cellsheet.Plus;
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
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CellsheetFactoryImpl extends EFactoryImpl implements CellsheetFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CellsheetFactory init() {
		try {
			CellsheetFactory theCellsheetFactory = (CellsheetFactory)EPackage.Registry.INSTANCE.getEFactory(CellsheetPackage.eNS_URI);
			if (theCellsheetFactory != null) {
				return theCellsheetFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CellsheetFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CellsheetFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CellsheetPackage.ESTRING_TO_TOKEN_ENTRY: return (EObject)createEStringToTokenEntry();
			case CellsheetPackage.WORKSPACE: return createWorkspace();
			case CellsheetPackage.BOOK: return createBook();
			case CellsheetPackage.SHEET: return createSheet();
			case CellsheetPackage.ROW: return createRow();
			case CellsheetPackage.BLANK_CELL: return createBlankCell();
			case CellsheetPackage.TEXT_CELL: return createTextCell();
			case CellsheetPackage.NUMERIC_CELL: return createNumericCell();
			case CellsheetPackage.BOOLEAN_CELL: return createBooleanCell();
			case CellsheetPackage.DATE_CELL: return createDateCell();
			case CellsheetPackage.FORMULA_CELL: return createFormulaCell();
			case CellsheetPackage.TOKEN: return createToken();
			case CellsheetPackage.UNKNOWN: return createUnknown();
			case CellsheetPackage.NOOP: return createNoop();
			case CellsheetPackage.TEXT: return createText();
			case CellsheetPackage.NUMBER: return createNumber();
			case CellsheetPackage.LOGICAL: return createLogical();
			case CellsheetPackage.ERROR: return createError();
			case CellsheetPackage.REF: return createRef();
			case CellsheetPackage.RANGE: return createRange();
			case CellsheetPackage.RELATIVE_REF: return createRelativeRef();
			case CellsheetPackage.RELATIVE_RANGE: return createRelativeRange();
			case CellsheetPackage.FUNCTION: return createFunction();
			case CellsheetPackage.PLUS: return createPlus();
			case CellsheetPackage.NEGATION: return createNegation();
			case CellsheetPackage.PERCENT: return createPercent();
			case CellsheetPackage.EXPONENTIATION: return createExponentiation();
			case CellsheetPackage.MULTIPLICATION: return createMultiplication();
			case CellsheetPackage.DIVISION: return createDivision();
			case CellsheetPackage.ADDITION: return createAddition();
			case CellsheetPackage.SUBTRACTION: return createSubtraction();
			case CellsheetPackage.CONCATENATION: return createConcatenation();
			case CellsheetPackage.EQ: return createEQ();
			case CellsheetPackage.LT: return createLT();
			case CellsheetPackage.GT: return createGT();
			case CellsheetPackage.LTE: return createLTE();
			case CellsheetPackage.GTE: return createGTE();
			case CellsheetPackage.NEQ: return createNEQ();
			case CellsheetPackage.INTERSECTION: return createIntersection();
			case CellsheetPackage.UNION: return createUnion();
			case CellsheetPackage.CELL_FORMAT: return createCellFormat();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, Token> createEStringToTokenEntry() {
		EStringToTokenEntryImpl eStringToTokenEntry = new EStringToTokenEntryImpl();
		return eStringToTokenEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Workspace createWorkspace() {
		WorkspaceImpl workspace = new WorkspaceImpl();
		return workspace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Book createBook() {
		BookImpl book = new BookImpl();
		return book;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Sheet createSheet() {
		SheetImpl sheet = new SheetImpl();
		return sheet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Row createRow() {
		RowImpl row = new RowImpl();
		return row;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BlankCell createBlankCell() {
		BlankCellImpl blankCell = new BlankCellImpl();
		return blankCell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TextCell createTextCell() {
		TextCellImpl textCell = new TextCellImpl();
		return textCell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NumericCell createNumericCell() {
		NumericCellImpl numericCell = new NumericCellImpl();
		return numericCell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BooleanCell createBooleanCell() {
		BooleanCellImpl booleanCell = new BooleanCellImpl();
		return booleanCell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DateCell createDateCell() {
		DateCellImpl dateCell = new DateCellImpl();
		return dateCell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FormulaCell createFormulaCell() {
		FormulaCellImpl formulaCell = new FormulaCellImpl();
		return formulaCell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Token createToken() {
		TokenImpl token = new TokenImpl();
		return token;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Unknown createUnknown() {
		UnknownImpl unknown = new UnknownImpl();
		return unknown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Noop createNoop() {
		NoopImpl noop = new NoopImpl();
		return noop;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Text createText() {
		TextImpl text = new TextImpl();
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.epsilon.labs.emf.cellsheet.Number createNumber() {
		NumberImpl number = new NumberImpl();
		return number;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Logical createLogical() {
		LogicalImpl logical = new LogicalImpl();
		return logical;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.eclipse.epsilon.labs.emf.cellsheet.Error createError() {
		ErrorImpl error = new ErrorImpl();
		return error;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Ref createRef() {
		RefImpl ref = new RefImpl();
		return ref;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Range createRange() {
		RangeImpl range = new RangeImpl();
		return range;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelativeRef createRelativeRef() {
		RelativeRefImpl relativeRef = new RelativeRefImpl();
		return relativeRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RelativeRange createRelativeRange() {
		RelativeRangeImpl relativeRange = new RelativeRangeImpl();
		return relativeRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Function createFunction() {
		FunctionImpl function = new FunctionImpl();
		return function;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Plus createPlus() {
		PlusImpl plus = new PlusImpl();
		return plus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Negation createNegation() {
		NegationImpl negation = new NegationImpl();
		return negation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Percent createPercent() {
		PercentImpl percent = new PercentImpl();
		return percent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Exponentiation createExponentiation() {
		ExponentiationImpl exponentiation = new ExponentiationImpl();
		return exponentiation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Multiplication createMultiplication() {
		MultiplicationImpl multiplication = new MultiplicationImpl();
		return multiplication;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Division createDivision() {
		DivisionImpl division = new DivisionImpl();
		return division;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Addition createAddition() {
		AdditionImpl addition = new AdditionImpl();
		return addition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Subtraction createSubtraction() {
		SubtractionImpl subtraction = new SubtractionImpl();
		return subtraction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Concatenation createConcatenation() {
		ConcatenationImpl concatenation = new ConcatenationImpl();
		return concatenation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EQ createEQ() {
		EQImpl eq = new EQImpl();
		return eq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LT createLT() {
		LTImpl lt = new LTImpl();
		return lt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GT createGT() {
		GTImpl gt = new GTImpl();
		return gt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LTE createLTE() {
		LTEImpl lte = new LTEImpl();
		return lte;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GTE createGTE() {
		GTEImpl gte = new GTEImpl();
		return gte;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NEQ createNEQ() {
		NEQImpl neq = new NEQImpl();
		return neq;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Intersection createIntersection() {
		IntersectionImpl intersection = new IntersectionImpl();
		return intersection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Union createUnion() {
		UnionImpl union = new UnionImpl();
		return union;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CellFormat createCellFormat() {
		CellFormatImpl cellFormat = new CellFormatImpl();
		return cellFormat;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CellsheetPackage getCellsheetPackage() {
		return (CellsheetPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CellsheetPackage getPackage() {
		return CellsheetPackage.eINSTANCE;
	}

} //CellsheetFactoryImpl

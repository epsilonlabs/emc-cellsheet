/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.epsilon.labs.emf.cellsheet.Addition;
import org.eclipse.epsilon.labs.emf.cellsheet.Ast;
import org.eclipse.epsilon.labs.emf.cellsheet.BlankCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Book;
import org.eclipse.epsilon.labs.emf.cellsheet.BooleanCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Cell;
import org.eclipse.epsilon.labs.emf.cellsheet.CellFormat;
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetFactory;
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage;
import org.eclipse.epsilon.labs.emf.cellsheet.Concatenation;
import org.eclipse.epsilon.labs.emf.cellsheet.DateCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Division;
import org.eclipse.epsilon.labs.emf.cellsheet.ErrorCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Exponentiation;
import org.eclipse.epsilon.labs.emf.cellsheet.Exponention;
import org.eclipse.epsilon.labs.emf.cellsheet.FormulaCell;
import org.eclipse.epsilon.labs.emf.cellsheet.Function;
import org.eclipse.epsilon.labs.emf.cellsheet.HasA1;
import org.eclipse.epsilon.labs.emf.cellsheet.HasId;
import org.eclipse.epsilon.labs.emf.cellsheet.InfixOperator;
import org.eclipse.epsilon.labs.emf.cellsheet.Intersection;
import org.eclipse.epsilon.labs.emf.cellsheet.Logical;
import org.eclipse.epsilon.labs.emf.cellsheet.Multiplication;
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
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CellsheetPackageImpl extends EPackageImpl implements CellsheetPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eStringToTokenEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hasA1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass hasIdEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass workspaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bookEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sheetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass blankCellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textCellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numericCellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanCellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dateCellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formulaCellEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tokenEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass astEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operandEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass prefixOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass infixOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass postfixOperatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unknownEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass noopEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numberEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass logicalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass errorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass refEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relativeRefEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass relativeRangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass plusEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass negationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass percentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass exponentiationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiplicationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass divisionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass additionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subtractionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass concatenationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eqEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ltEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gtEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass neqEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intersectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cellFormatEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CellsheetPackageImpl() {
		super(eNS_URI, CellsheetFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link CellsheetPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CellsheetPackage init() {
		if (isInited) return (CellsheetPackage)EPackage.Registry.INSTANCE.getEPackage(CellsheetPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredCellsheetPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		CellsheetPackageImpl theCellsheetPackage = registeredCellsheetPackage instanceof CellsheetPackageImpl ? (CellsheetPackageImpl)registeredCellsheetPackage : new CellsheetPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theCellsheetPackage.createPackageContents();

		// Initialize created meta-data
		theCellsheetPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCellsheetPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CellsheetPackage.eNS_URI, theCellsheetPackage);
		return theCellsheetPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEStringToTokenEntry() {
		return eStringToTokenEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEStringToTokenEntry_Key() {
		return (EAttribute)eStringToTokenEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEStringToTokenEntry_Value() {
		return (EReference)eStringToTokenEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHasA1() {
		return hasA1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHasA1_A1() {
		return (EAttribute)hasA1EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHasId() {
		return hasIdEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHasId_Id() {
		return (EAttribute)hasIdEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWorkspace() {
		return workspaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkspace_Books() {
		return (EReference)workspaceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWorkspace_Tokens() {
		return (EReference)workspaceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBook() {
		return bookEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBook_Workspace() {
		return (EReference)bookEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBook_CellFormats() {
		return (EReference)bookEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBook_Sheets() {
		return (EReference)bookEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBook_Bookname() {
		return (EAttribute)bookEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSheet() {
		return sheetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSheet_Book() {
		return (EReference)sheetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSheet_Rows() {
		return (EReference)sheetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSheet_SheetName() {
		return (EAttribute)sheetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSheet_SheetIndex() {
		return (EAttribute)sheetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRow() {
		return rowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRow_Sheet() {
		return (EReference)rowEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRow_Cells() {
		return (EReference)rowEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRow_RowIndex() {
		return (EAttribute)rowEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCell() {
		return cellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCell_Row() {
		return (EReference)cellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCell_ColIndex() {
		return (EAttribute)cellEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCell_Asts() {
		return (EReference)cellEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCell_Root() {
		return (EReference)cellEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBlankCell() {
		return blankCellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBlankCell_Value() {
		return (EAttribute)blankCellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTextCell() {
		return textCellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTextCell_Value() {
		return (EAttribute)textCellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNumericCell() {
		return numericCellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNumericCell_Value() {
		return (EAttribute)numericCellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBooleanCell() {
		return booleanCellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBooleanCell_Value() {
		return (EAttribute)booleanCellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDateCell() {
		return dateCellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDateCell_Value() {
		return (EAttribute)dateCellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFormulaCell() {
		return formulaCellEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFormulaCell_Value() {
		return (EAttribute)formulaCellEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getToken() {
		return tokenEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getToken_Value() {
		return (EAttribute)tokenEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getToken_UsedBy() {
		return (EReference)tokenEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAst() {
		return astEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAst_Children() {
		return (EReference)astEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAst_Cell() {
		return (EReference)astEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAst_Token() {
		return (EReference)astEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperand() {
		return operandEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPrefixOperator() {
		return prefixOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInfixOperator() {
		return infixOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPostfixOperator() {
		return postfixOperatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUnknown() {
		return unknownEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNoop() {
		return noopEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getText() {
		return textEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNumber() {
		return numberEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLogical() {
		return logicalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getError() {
		return errorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRef() {
		return refEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRange() {
		return rangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRelativeRef() {
		return relativeRefEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRelativeRange() {
		return relativeRangeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFunction() {
		return functionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPlus() {
		return plusEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNegation() {
		return negationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPercent() {
		return percentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getExponentiation() {
		return exponentiationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMultiplication() {
		return multiplicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDivision() {
		return divisionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAddition() {
		return additionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSubtraction() {
		return subtractionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConcatenation() {
		return concatenationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEQ() {
		return eqEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLT() {
		return ltEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGT() {
		return gtEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLTE() {
		return lteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGTE() {
		return gteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNEQ() {
		return neqEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIntersection() {
		return intersectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getUnion() {
		return unionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCellFormat() {
		return cellFormatEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCellFormat_Book() {
		return (EReference)cellFormatEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCellFormat_Value() {
		return (EAttribute)cellFormatEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CellsheetFactory getCellsheetFactory() {
		return (CellsheetFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		eStringToTokenEntryEClass = createEClass(ESTRING_TO_TOKEN_ENTRY);
		createEAttribute(eStringToTokenEntryEClass, ESTRING_TO_TOKEN_ENTRY__KEY);
		createEReference(eStringToTokenEntryEClass, ESTRING_TO_TOKEN_ENTRY__VALUE);

		hasA1EClass = createEClass(HAS_A1);
		createEAttribute(hasA1EClass, HAS_A1__A1);

		hasIdEClass = createEClass(HAS_ID);
		createEAttribute(hasIdEClass, HAS_ID__ID);

		workspaceEClass = createEClass(WORKSPACE);
		createEReference(workspaceEClass, WORKSPACE__BOOKS);
		createEReference(workspaceEClass, WORKSPACE__TOKENS);

		bookEClass = createEClass(BOOK);
		createEReference(bookEClass, BOOK__WORKSPACE);
		createEReference(bookEClass, BOOK__CELL_FORMATS);
		createEReference(bookEClass, BOOK__SHEETS);
		createEAttribute(bookEClass, BOOK__BOOKNAME);

		sheetEClass = createEClass(SHEET);
		createEReference(sheetEClass, SHEET__BOOK);
		createEReference(sheetEClass, SHEET__ROWS);
		createEAttribute(sheetEClass, SHEET__SHEET_NAME);
		createEAttribute(sheetEClass, SHEET__SHEET_INDEX);

		rowEClass = createEClass(ROW);
		createEReference(rowEClass, ROW__SHEET);
		createEReference(rowEClass, ROW__CELLS);
		createEAttribute(rowEClass, ROW__ROW_INDEX);

		cellEClass = createEClass(CELL);
		createEReference(cellEClass, CELL__ROW);
		createEAttribute(cellEClass, CELL__COL_INDEX);
		createEReference(cellEClass, CELL__ASTS);
		createEReference(cellEClass, CELL__ROOT);

		blankCellEClass = createEClass(BLANK_CELL);
		createEAttribute(blankCellEClass, BLANK_CELL__VALUE);

		textCellEClass = createEClass(TEXT_CELL);
		createEAttribute(textCellEClass, TEXT_CELL__VALUE);

		numericCellEClass = createEClass(NUMERIC_CELL);
		createEAttribute(numericCellEClass, NUMERIC_CELL__VALUE);

		booleanCellEClass = createEClass(BOOLEAN_CELL);
		createEAttribute(booleanCellEClass, BOOLEAN_CELL__VALUE);

		dateCellEClass = createEClass(DATE_CELL);
		createEAttribute(dateCellEClass, DATE_CELL__VALUE);

		formulaCellEClass = createEClass(FORMULA_CELL);
		createEAttribute(formulaCellEClass, FORMULA_CELL__VALUE);

		tokenEClass = createEClass(TOKEN);
		createEAttribute(tokenEClass, TOKEN__VALUE);
		createEReference(tokenEClass, TOKEN__USED_BY);

		astEClass = createEClass(AST);
		createEReference(astEClass, AST__CHILDREN);
		createEReference(astEClass, AST__CELL);
		createEReference(astEClass, AST__TOKEN);

		operandEClass = createEClass(OPERAND);

		operationEClass = createEClass(OPERATION);

		prefixOperatorEClass = createEClass(PREFIX_OPERATOR);

		infixOperatorEClass = createEClass(INFIX_OPERATOR);

		postfixOperatorEClass = createEClass(POSTFIX_OPERATOR);

		unknownEClass = createEClass(UNKNOWN);

		noopEClass = createEClass(NOOP);

		textEClass = createEClass(TEXT);

		numberEClass = createEClass(NUMBER);

		logicalEClass = createEClass(LOGICAL);

		errorEClass = createEClass(ERROR);

		refEClass = createEClass(REF);

		rangeEClass = createEClass(RANGE);

		relativeRefEClass = createEClass(RELATIVE_REF);

		relativeRangeEClass = createEClass(RELATIVE_RANGE);

		functionEClass = createEClass(FUNCTION);

		plusEClass = createEClass(PLUS);

		negationEClass = createEClass(NEGATION);

		percentEClass = createEClass(PERCENT);

		exponentiationEClass = createEClass(EXPONENTIATION);

		multiplicationEClass = createEClass(MULTIPLICATION);

		divisionEClass = createEClass(DIVISION);

		additionEClass = createEClass(ADDITION);

		subtractionEClass = createEClass(SUBTRACTION);

		concatenationEClass = createEClass(CONCATENATION);

		eqEClass = createEClass(EQ);

		ltEClass = createEClass(LT);

		gtEClass = createEClass(GT);

		lteEClass = createEClass(LTE);

		gteEClass = createEClass(GTE);

		neqEClass = createEClass(NEQ);

		intersectionEClass = createEClass(INTERSECTION);

		unionEClass = createEClass(UNION);

		cellFormatEClass = createEClass(CELL_FORMAT);
		createEReference(cellFormatEClass, CELL_FORMAT__BOOK);
		createEAttribute(cellFormatEClass, CELL_FORMAT__VALUE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		bookEClass.getESuperTypes().add(this.getHasId());
		bookEClass.getESuperTypes().add(this.getHasA1());
		sheetEClass.getESuperTypes().add(this.getHasId());
		sheetEClass.getESuperTypes().add(this.getHasA1());
		rowEClass.getESuperTypes().add(this.getHasId());
		rowEClass.getESuperTypes().add(this.getHasA1());
		cellEClass.getESuperTypes().add(this.getHasId());
		cellEClass.getESuperTypes().add(this.getHasA1());
		blankCellEClass.getESuperTypes().add(this.getCell());
		textCellEClass.getESuperTypes().add(this.getCell());
		numericCellEClass.getESuperTypes().add(this.getCell());
		booleanCellEClass.getESuperTypes().add(this.getCell());
		dateCellEClass.getESuperTypes().add(this.getCell());
		formulaCellEClass.getESuperTypes().add(this.getCell());
		operandEClass.getESuperTypes().add(this.getAst());
		operationEClass.getESuperTypes().add(this.getAst());
		prefixOperatorEClass.getESuperTypes().add(this.getAst());
		infixOperatorEClass.getESuperTypes().add(this.getAst());
		postfixOperatorEClass.getESuperTypes().add(this.getAst());
		unknownEClass.getESuperTypes().add(this.getAst());
		noopEClass.getESuperTypes().add(this.getAst());
		textEClass.getESuperTypes().add(this.getOperand());
		numberEClass.getESuperTypes().add(this.getOperand());
		logicalEClass.getESuperTypes().add(this.getOperand());
		errorEClass.getESuperTypes().add(this.getOperand());
		refEClass.getESuperTypes().add(this.getOperand());
		rangeEClass.getESuperTypes().add(this.getOperand());
		relativeRefEClass.getESuperTypes().add(this.getRef());
		relativeRangeEClass.getESuperTypes().add(this.getRef());
		functionEClass.getESuperTypes().add(this.getOperation());
		plusEClass.getESuperTypes().add(this.getPrefixOperator());
		negationEClass.getESuperTypes().add(this.getPrefixOperator());
		percentEClass.getESuperTypes().add(this.getPostfixOperator());
		exponentiationEClass.getESuperTypes().add(this.getInfixOperator());
		multiplicationEClass.getESuperTypes().add(this.getInfixOperator());
		divisionEClass.getESuperTypes().add(this.getInfixOperator());
		additionEClass.getESuperTypes().add(this.getInfixOperator());
		subtractionEClass.getESuperTypes().add(this.getInfixOperator());
		concatenationEClass.getESuperTypes().add(this.getInfixOperator());
		eqEClass.getESuperTypes().add(this.getInfixOperator());
		ltEClass.getESuperTypes().add(this.getInfixOperator());
		gtEClass.getESuperTypes().add(this.getInfixOperator());
		lteEClass.getESuperTypes().add(this.getInfixOperator());
		gteEClass.getESuperTypes().add(this.getInfixOperator());
		neqEClass.getESuperTypes().add(this.getInfixOperator());
		intersectionEClass.getESuperTypes().add(this.getInfixOperator());
		unionEClass.getESuperTypes().add(this.getInfixOperator());
		cellFormatEClass.getESuperTypes().add(this.getHasId());

		// Initialize classes and features; add operations and parameters
		initEClass(eStringToTokenEntryEClass, Map.Entry.class, "EStringToTokenEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEStringToTokenEntry_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEStringToTokenEntry_Value(), this.getToken(), null, "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(hasA1EClass, HasA1.class, "HasA1", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHasA1_A1(), ecorePackage.getEString(), "a1", null, 0, 1, HasA1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(hasIdEClass, HasId.class, "HasId", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHasId_Id(), ecorePackage.getEString(), "id", null, 0, 1, HasId.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workspaceEClass, Workspace.class, "Workspace", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWorkspace_Books(), this.getBook(), this.getBook_Workspace(), "books", null, 0, -1, Workspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkspace_Tokens(), this.getEStringToTokenEntry(), null, "tokens", null, 0, -1, Workspace.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bookEClass, Book.class, "Book", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBook_Workspace(), this.getWorkspace(), this.getWorkspace_Books(), "workspace", null, 0, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBook_CellFormats(), this.getCellFormat(), this.getCellFormat_Book(), "cellFormats", null, 0, -1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBook_Sheets(), this.getSheet(), this.getSheet_Book(), "sheets", null, 0, -1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBook_Bookname(), ecorePackage.getEString(), "bookname", null, 0, 1, Book.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sheetEClass, Sheet.class, "Sheet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSheet_Book(), this.getBook(), this.getBook_Sheets(), "book", null, 0, 1, Sheet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSheet_Rows(), this.getRow(), this.getRow_Sheet(), "rows", null, 0, -1, Sheet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSheet_SheetName(), ecorePackage.getEString(), "sheetName", null, 0, 1, Sheet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSheet_SheetIndex(), ecorePackage.getEInt(), "sheetIndex", null, 0, 1, Sheet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rowEClass, Row.class, "Row", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRow_Sheet(), this.getSheet(), this.getSheet_Rows(), "sheet", null, 0, 1, Row.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRow_Cells(), this.getCell(), this.getCell_Row(), "cells", null, 0, -1, Row.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRow_RowIndex(), ecorePackage.getEInt(), "rowIndex", null, 0, 1, Row.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(rowEClass, this.getBook(), "getBook", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(rowEClass, ecorePackage.getEInt(), "getA1RowIndex", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(cellEClass, Cell.class, "Cell", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCell_Row(), this.getRow(), this.getRow_Cells(), "row", null, 0, 1, Cell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCell_ColIndex(), ecorePackage.getEInt(), "colIndex", null, 0, 1, Cell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCell_Asts(), this.getAst(), this.getAst_Cell(), "asts", null, 0, -1, Cell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCell_Root(), this.getAst(), null, "root", null, 0, 1, Cell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(cellEClass, this.getBook(), "getBook", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(cellEClass, this.getSheet(), "getSheet", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(cellEClass, ecorePackage.getEInt(), "getRowIndex", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(cellEClass, ecorePackage.getEInt(), "getA1RowIndex", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(cellEClass, ecorePackage.getEString(), "getA1ColIndex", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(blankCellEClass, BlankCell.class, "BlankCell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBlankCell_Value(), ecorePackage.getEJavaObject(), "value", null, 0, 1, BlankCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textCellEClass, TextCell.class, "TextCell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTextCell_Value(), ecorePackage.getEString(), "value", null, 0, 1, TextCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(numericCellEClass, NumericCell.class, "NumericCell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNumericCell_Value(), ecorePackage.getEDoubleObject(), "value", null, 0, 1, NumericCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(booleanCellEClass, BooleanCell.class, "BooleanCell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBooleanCell_Value(), ecorePackage.getEBooleanObject(), "value", null, 0, 1, BooleanCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dateCellEClass, DateCell.class, "DateCell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDateCell_Value(), ecorePackage.getEDate(), "value", null, 0, 1, DateCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(formulaCellEClass, FormulaCell.class, "FormulaCell", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFormulaCell_Value(), ecorePackage.getEString(), "value", null, 0, 1, FormulaCell.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tokenEClass, Token.class, "Token", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getToken_Value(), ecorePackage.getEString(), "value", null, 0, 1, Token.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getToken_UsedBy(), this.getAst(), this.getAst_Token(), "usedBy", null, 0, -1, Token.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(astEClass, Ast.class, "Ast", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAst_Children(), this.getAst(), null, "children", null, 0, -1, Ast.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAst_Cell(), this.getCell(), this.getCell_Asts(), "cell", null, 0, 1, Ast.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAst_Token(), this.getToken(), this.getToken_UsedBy(), "token", null, 0, 1, Ast.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(astEClass, ecorePackage.getEString(), "evaluate", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(operandEClass, Operand.class, "Operand", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(operationEClass, Operation.class, "Operation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(prefixOperatorEClass, PrefixOperator.class, "PrefixOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(infixOperatorEClass, InfixOperator.class, "InfixOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(postfixOperatorEClass, PostfixOperator.class, "PostfixOperator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unknownEClass, Unknown.class, "Unknown", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(noopEClass, Noop.class, "Noop", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(textEClass, Text.class, "Text", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(numberEClass, org.eclipse.epsilon.labs.emf.cellsheet.Number.class, "Number", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(logicalEClass, Logical.class, "Logical", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(errorEClass, org.eclipse.epsilon.labs.emf.cellsheet.Error.class, "Error", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(refEClass, Ref.class, "Ref", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rangeEClass, Range.class, "Range", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(relativeRefEClass, RelativeRef.class, "RelativeRef", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(relativeRangeEClass, RelativeRange.class, "RelativeRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(functionEClass, Function.class, "Function", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(plusEClass, Plus.class, "Plus", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(negationEClass, Negation.class, "Negation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(percentEClass, Percent.class, "Percent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(exponentiationEClass, Exponentiation.class, "Exponentiation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(multiplicationEClass, Multiplication.class, "Multiplication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(divisionEClass, Division.class, "Division", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(additionEClass, Addition.class, "Addition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(subtractionEClass, Subtraction.class, "Subtraction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(concatenationEClass, Concatenation.class, "Concatenation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(eqEClass, org.eclipse.epsilon.labs.emf.cellsheet.EQ.class, "EQ", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(ltEClass, org.eclipse.epsilon.labs.emf.cellsheet.LT.class, "LT", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(gtEClass, org.eclipse.epsilon.labs.emf.cellsheet.GT.class, "GT", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(lteEClass, org.eclipse.epsilon.labs.emf.cellsheet.LTE.class, "LTE", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(gteEClass, org.eclipse.epsilon.labs.emf.cellsheet.GTE.class, "GTE", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(neqEClass, org.eclipse.epsilon.labs.emf.cellsheet.NEQ.class, "NEQ", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(intersectionEClass, Intersection.class, "Intersection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(unionEClass, Union.class, "Union", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(cellFormatEClass, CellFormat.class, "CellFormat", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCellFormat_Book(), this.getBook(), this.getBook_CellFormats(), "book", null, 0, 1, CellFormat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCellFormat_Value(), ecorePackage.getEString(), "value", null, 0, 1, CellFormat.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //CellsheetPackageImpl

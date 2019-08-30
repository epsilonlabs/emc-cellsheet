/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel basePackage='org.eclipse.epsilon.labs.emf'"
 * @generated
 */
public interface CellsheetPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cellsheet";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "org.eclipse.epsilon.labs.cellsheet";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cellsheet";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CellsheetPackage eINSTANCE = org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.EStringToTokenEntryImpl <em>EString To Token Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.EStringToTokenEntryImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getEStringToTokenEntry()
	 * @generated
	 */
	int ESTRING_TO_TOKEN_ENTRY = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_TOKEN_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_TOKEN_ENTRY__VALUE = 1;

	/**
	 * The of of structural features of the '<em>EString To Token Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ESTRING_TO_TOKEN_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.HasA1Impl <em>Has A1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.HasA1Impl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getHasA1()
	 * @generated
	 */
	int HAS_A1 = 1;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_A1__A1 = 0;

	/**
	 * The of of structural features of the '<em>Has A1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_A1_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.HasIdImpl <em>Has Id</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.HasIdImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getHasId()
	 * @generated
	 */
	int HAS_ID = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_ID__ID = 0;

	/**
	 * The of of structural features of the '<em>Has Id</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HAS_ID_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.WorkspaceImpl <em>Workspace</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.WorkspaceImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getWorkspace()
	 * @generated
	 */
	int WORKSPACE = 3;

	/**
	 * The feature id for the '<em><b>Books</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__BOOKS = 0;

	/**
	 * The feature id for the '<em><b>Tokens</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE__TOKENS = 1;

	/**
	 * The of of structural features of the '<em>Workspace</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORKSPACE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BookImpl <em>Book</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.BookImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getBook()
	 * @generated
	 */
	int BOOK = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__ID = HAS_ID__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__A1 = HAS_ID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Workspace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__WORKSPACE = HAS_ID_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cell Formats</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__CELL_FORMATS = HAS_ID_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sheets</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__SHEETS = HAS_ID_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Bookname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__BOOKNAME = HAS_ID_FEATURE_COUNT + 4;

	/**
	 * The of of structural features of the '<em>Book</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK_FEATURE_COUNT = HAS_ID_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.SheetImpl <em>Sheet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.SheetImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getSheet()
	 * @generated
	 */
	int SHEET = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEET__ID = HAS_ID__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEET__A1 = HAS_ID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Book</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEET__BOOK = HAS_ID_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Rows</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEET__ROWS = HAS_ID_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Sheet Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEET__SHEET_NAME = HAS_ID_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Sheet Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEET__SHEET_INDEX = HAS_ID_FEATURE_COUNT + 4;

	/**
	 * The of of structural features of the '<em>Sheet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SHEET_FEATURE_COUNT = HAS_ID_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RowImpl <em>Row</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RowImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRow()
	 * @generated
	 */
	int ROW = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW__ID = HAS_ID__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW__A1 = HAS_ID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sheet</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW__SHEET = HAS_ID_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cells</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW__CELLS = HAS_ID_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Row Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW__ROW_INDEX = HAS_ID_FEATURE_COUNT + 3;

	/**
	 * The of of structural features of the '<em>Row</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROW_FEATURE_COUNT = HAS_ID_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.CellImpl <em>Cell</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getCell()
	 * @generated
	 */
	int CELL = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL__ID = HAS_ID__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL__A1 = HAS_ID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Row</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL__ROW = HAS_ID_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Col Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL__COL_INDEX = HAS_ID_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Asts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL__ASTS = HAS_ID_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL__ROOT = HAS_ID_FEATURE_COUNT + 4;

	/**
	 * The of of structural features of the '<em>Cell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL_FEATURE_COUNT = HAS_ID_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BlankCellImpl <em>Blank Cell</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.BlankCellImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getBlankCell()
	 * @generated
	 */
	int BLANK_CELL = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLANK_CELL__ID = CELL__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLANK_CELL__A1 = CELL__A1;

	/**
	 * The feature id for the '<em><b>Row</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLANK_CELL__ROW = CELL__ROW;

	/**
	 * The feature id for the '<em><b>Col Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLANK_CELL__COL_INDEX = CELL__COL_INDEX;

	/**
	 * The feature id for the '<em><b>Asts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLANK_CELL__ASTS = CELL__ASTS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLANK_CELL__ROOT = CELL__ROOT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLANK_CELL__VALUE = CELL_FEATURE_COUNT + 0;

	/**
	 * The of of structural features of the '<em>Blank Cell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BLANK_CELL_FEATURE_COUNT = CELL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.TextCellImpl <em>Text Cell</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.TextCellImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getTextCell()
	 * @generated
	 */
	int TEXT_CELL = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CELL__ID = CELL__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CELL__A1 = CELL__A1;

	/**
	 * The feature id for the '<em><b>Row</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CELL__ROW = CELL__ROW;

	/**
	 * The feature id for the '<em><b>Col Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CELL__COL_INDEX = CELL__COL_INDEX;

	/**
	 * The feature id for the '<em><b>Asts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CELL__ASTS = CELL__ASTS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CELL__ROOT = CELL__ROOT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CELL__VALUE = CELL_FEATURE_COUNT + 0;

	/**
	 * The of of structural features of the '<em>Text Cell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_CELL_FEATURE_COUNT = CELL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NumericCellImpl <em>Numeric Cell</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NumericCellImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNumericCell()
	 * @generated
	 */
	int NUMERIC_CELL = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CELL__ID = CELL__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CELL__A1 = CELL__A1;

	/**
	 * The feature id for the '<em><b>Row</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CELL__ROW = CELL__ROW;

	/**
	 * The feature id for the '<em><b>Col Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CELL__COL_INDEX = CELL__COL_INDEX;

	/**
	 * The feature id for the '<em><b>Asts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CELL__ASTS = CELL__ASTS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CELL__ROOT = CELL__ROOT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CELL__VALUE = CELL_FEATURE_COUNT + 0;

	/**
	 * The of of structural features of the '<em>Numeric Cell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMERIC_CELL_FEATURE_COUNT = CELL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BooleanCellImpl <em>Boolean Cell</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.BooleanCellImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getBooleanCell()
	 * @generated
	 */
	int BOOLEAN_CELL = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CELL__ID = CELL__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CELL__A1 = CELL__A1;

	/**
	 * The feature id for the '<em><b>Row</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CELL__ROW = CELL__ROW;

	/**
	 * The feature id for the '<em><b>Col Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CELL__COL_INDEX = CELL__COL_INDEX;

	/**
	 * The feature id for the '<em><b>Asts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CELL__ASTS = CELL__ASTS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CELL__ROOT = CELL__ROOT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CELL__VALUE = CELL_FEATURE_COUNT + 0;

	/**
	 * The of of structural features of the '<em>Boolean Cell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_CELL_FEATURE_COUNT = CELL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.DateCellImpl <em>Date Cell</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.DateCellImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getDateCell()
	 * @generated
	 */
	int DATE_CELL = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CELL__ID = CELL__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CELL__A1 = CELL__A1;

	/**
	 * The feature id for the '<em><b>Row</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CELL__ROW = CELL__ROW;

	/**
	 * The feature id for the '<em><b>Col Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CELL__COL_INDEX = CELL__COL_INDEX;

	/**
	 * The feature id for the '<em><b>Asts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CELL__ASTS = CELL__ASTS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CELL__ROOT = CELL__ROOT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CELL__VALUE = CELL_FEATURE_COUNT + 0;

	/**
	 * The of of structural features of the '<em>Date Cell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_CELL_FEATURE_COUNT = CELL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.FormulaCellImpl <em>Formula Cell</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.FormulaCellImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getFormulaCell()
	 * @generated
	 */
	int FORMULA_CELL = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CELL__ID = CELL__ID;

	/**
	 * The feature id for the '<em><b>A1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CELL__A1 = CELL__A1;

	/**
	 * The feature id for the '<em><b>Row</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CELL__ROW = CELL__ROW;

	/**
	 * The feature id for the '<em><b>Col Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CELL__COL_INDEX = CELL__COL_INDEX;

	/**
	 * The feature id for the '<em><b>Asts</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CELL__ASTS = CELL__ASTS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CELL__ROOT = CELL__ROOT;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CELL__VALUE = CELL_FEATURE_COUNT + 0;

	/**
	 * The of of structural features of the '<em>Formula Cell</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORMULA_CELL_FEATURE_COUNT = CELL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.TokenImpl <em>Token</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.TokenImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getToken()
	 * @generated
	 */
	int TOKEN = 14;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__VALUE = 0;

	/**
	 * The feature id for the '<em><b>Used By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN__USED_BY = 1;

	/**
	 * The of of structural features of the '<em>Token</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TOKEN_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl <em>Ast Eval</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getAstEval()
	 * @generated
	 */
	int AST_EVAL = 15;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST_EVAL__TEXT = 0;

	/**
	 * The feature id for the '<em><b>Number Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST_EVAL__NUMBER_VALUE = 1;

	/**
	 * The feature id for the '<em><b>Is Error</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST_EVAL__IS_ERROR = 2;

	/**
	 * The of of structural features of the '<em>Ast Eval</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST_EVAL_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstImpl <em>Ast</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.AstImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getAst()
	 * @generated
	 */
	int AST = 16;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST__CHILDREN = 0;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST__CELL = 1;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST__TOKEN = 2;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST__RESULT = 3;

	/**
	 * The of of structural features of the '<em>Ast</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AST_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.OperandImpl <em>Operand</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.OperandImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getOperand()
	 * @generated
	 */
	int OPERAND = 17;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERAND__CHILDREN = AST__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERAND__CELL = AST__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERAND__TOKEN = AST__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERAND__RESULT = AST__RESULT;

	/**
	 * The of of structural features of the '<em>Operand</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERAND_FEATURE_COUNT = AST_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.OperationImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 18;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__CHILDREN = AST__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__CELL = AST__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__TOKEN = AST__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__RESULT = AST__RESULT;

	/**
	 * The of of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = AST_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.PrefixOperatorImpl <em>Prefix Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.PrefixOperatorImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getPrefixOperator()
	 * @generated
	 */
	int PREFIX_OPERATOR = 19;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_OPERATOR__CHILDREN = AST__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_OPERATOR__CELL = AST__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_OPERATOR__TOKEN = AST__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_OPERATOR__RESULT = AST__RESULT;

	/**
	 * The of of structural features of the '<em>Prefix Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFIX_OPERATOR_FEATURE_COUNT = AST_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.InfixOperatorImpl <em>Infix Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.InfixOperatorImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getInfixOperator()
	 * @generated
	 */
	int INFIX_OPERATOR = 20;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_OPERATOR__CHILDREN = AST__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_OPERATOR__CELL = AST__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_OPERATOR__TOKEN = AST__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_OPERATOR__RESULT = AST__RESULT;

	/**
	 * The of of structural features of the '<em>Infix Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INFIX_OPERATOR_FEATURE_COUNT = AST_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.PostfixOperatorImpl <em>Postfix Operator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.PostfixOperatorImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getPostfixOperator()
	 * @generated
	 */
	int POSTFIX_OPERATOR = 21;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_OPERATOR__CHILDREN = AST__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_OPERATOR__CELL = AST__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_OPERATOR__TOKEN = AST__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_OPERATOR__RESULT = AST__RESULT;

	/**
	 * The of of structural features of the '<em>Postfix Operator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTFIX_OPERATOR_FEATURE_COUNT = AST_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.UnknownImpl <em>Unknown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.UnknownImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getUnknown()
	 * @generated
	 */
	int UNKNOWN = 22;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__CHILDREN = AST__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__CELL = AST__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__TOKEN = AST__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN__RESULT = AST__RESULT;

	/**
	 * The of of structural features of the '<em>Unknown</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNKNOWN_FEATURE_COUNT = AST_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NoopImpl <em>Noop</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NoopImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNoop()
	 * @generated
	 */
	int NOOP = 23;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOOP__CHILDREN = AST__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOOP__CELL = AST__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOOP__TOKEN = AST__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOOP__RESULT = AST__RESULT;

	/**
	 * The of of structural features of the '<em>Noop</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NOOP_FEATURE_COUNT = AST_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.TextImpl <em>Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.TextImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getText()
	 * @generated
	 */
	int TEXT = 24;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__CHILDREN = OPERAND__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__CELL = OPERAND__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__TOKEN = OPERAND__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__RESULT = OPERAND__RESULT;

	/**
	 * The of of structural features of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NumberImpl <em>Number</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NumberImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNumber()
	 * @generated
	 */
	int NUMBER = 25;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER__CHILDREN = OPERAND__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER__CELL = OPERAND__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER__TOKEN = OPERAND__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER__RESULT = OPERAND__RESULT;

	/**
	 * The of of structural features of the '<em>Number</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NUMBER_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.LogicalImpl <em>Logical</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.LogicalImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getLogical()
	 * @generated
	 */
	int LOGICAL = 26;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL__CHILDREN = OPERAND__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL__CELL = OPERAND__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL__TOKEN = OPERAND__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL__RESULT = OPERAND__RESULT;

	/**
	 * The of of structural features of the '<em>Logical</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOGICAL_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.ErrorImpl <em>Error</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.ErrorImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getError()
	 * @generated
	 */
	int ERROR = 27;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR__CHILDREN = OPERAND__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR__CELL = OPERAND__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR__TOKEN = OPERAND__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR__RESULT = OPERAND__RESULT;

	/**
	 * The of of structural features of the '<em>Error</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ERROR_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RefImpl <em>Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RefImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRef()
	 * @generated
	 */
	int REF = 28;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF__CHILDREN = OPERAND__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF__CELL = OPERAND__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF__TOKEN = OPERAND__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF__RESULT = OPERAND__RESULT;

	/**
	 * The of of structural features of the '<em>Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REF_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RangeImpl <em>Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RangeImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRange()
	 * @generated
	 */
	int RANGE = 29;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANGE__CHILDREN = OPERAND__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANGE__CELL = OPERAND__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANGE__TOKEN = OPERAND__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANGE__RESULT = OPERAND__RESULT;

	/**
	 * The of of structural features of the '<em>Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RANGE_FEATURE_COUNT = OPERAND_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RelativeRefImpl <em>Relative Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RelativeRefImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRelativeRef()
	 * @generated
	 */
	int RELATIVE_REF = 30;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_REF__CHILDREN = REF__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_REF__CELL = REF__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_REF__TOKEN = REF__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_REF__RESULT = REF__RESULT;

	/**
	 * The of of structural features of the '<em>Relative Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_REF_FEATURE_COUNT = REF_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RelativeRangeImpl <em>Relative Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RelativeRangeImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRelativeRange()
	 * @generated
	 */
	int RELATIVE_RANGE = 31;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_RANGE__CHILDREN = REF__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_RANGE__CELL = REF__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_RANGE__TOKEN = REF__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_RANGE__RESULT = REF__RESULT;

	/**
	 * The of of structural features of the '<em>Relative Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIVE_RANGE_FEATURE_COUNT = REF_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.FunctionImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 32;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__CHILDREN = OPERATION__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__CELL = OPERATION__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__TOKEN = OPERATION__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__RESULT = OPERATION__RESULT;

	/**
	 * The of of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.PlusImpl <em>Plus</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.PlusImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getPlus()
	 * @generated
	 */
	int PLUS = 33;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUS__CHILDREN = PREFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUS__CELL = PREFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUS__TOKEN = PREFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUS__RESULT = PREFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Plus</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PLUS_FEATURE_COUNT = PREFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NegationImpl <em>Negation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NegationImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNegation()
	 * @generated
	 */
	int NEGATION = 34;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION__CHILDREN = PREFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION__CELL = PREFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION__TOKEN = PREFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION__RESULT = PREFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Negation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEGATION_FEATURE_COUNT = PREFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.PercentImpl <em>Percent</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.PercentImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getPercent()
	 * @generated
	 */
	int PERCENT = 35;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENT__CHILDREN = POSTFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENT__CELL = POSTFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENT__TOKEN = POSTFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENT__RESULT = POSTFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Percent</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERCENT_FEATURE_COUNT = POSTFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.ExponentiationImpl <em>Exponentiation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.ExponentiationImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getExponentiation()
	 * @generated
	 */
	int EXPONENTIATION = 36;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIATION__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIATION__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIATION__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIATION__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Exponentiation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPONENTIATION_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.MultiplicationImpl <em>Multiplication</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.MultiplicationImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getMultiplication()
	 * @generated
	 */
	int MULTIPLICATION = 37;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Multiplication</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MULTIPLICATION_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.DivisionImpl <em>Division</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.DivisionImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getDivision()
	 * @generated
	 */
	int DIVISION = 38;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Division</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIVISION_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AdditionImpl <em>Addition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.AdditionImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getAddition()
	 * @generated
	 */
	int ADDITION = 39;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Addition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADDITION_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.SubtractionImpl <em>Subtraction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.SubtractionImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getSubtraction()
	 * @generated
	 */
	int SUBTRACTION = 40;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTRACTION__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTRACTION__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTRACTION__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTRACTION__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Subtraction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBTRACTION_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.ConcatenationImpl <em>Concatenation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.ConcatenationImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getConcatenation()
	 * @generated
	 */
	int CONCATENATION = 41;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCATENATION__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCATENATION__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCATENATION__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCATENATION__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Concatenation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCATENATION_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.EQImpl <em>EQ</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.EQImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getEQ()
	 * @generated
	 */
	int EQ = 42;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQ__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQ__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQ__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQ__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>EQ</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EQ_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.LTImpl <em>LT</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.LTImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getLT()
	 * @generated
	 */
	int LT = 43;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LT__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LT__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LT__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LT__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>LT</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LT_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.GTImpl <em>GT</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.GTImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getGT()
	 * @generated
	 */
	int GT = 44;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GT__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GT__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GT__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GT__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>GT</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GT_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.LTEImpl <em>LTE</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.LTEImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getLTE()
	 * @generated
	 */
	int LTE = 45;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LTE__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LTE__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LTE__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LTE__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>LTE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LTE_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.GTEImpl <em>GTE</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.GTEImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getGTE()
	 * @generated
	 */
	int GTE = 46;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GTE__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GTE__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GTE__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GTE__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>GTE</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GTE_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NEQImpl <em>NEQ</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NEQImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNEQ()
	 * @generated
	 */
	int NEQ = 47;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEQ__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEQ__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEQ__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEQ__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>NEQ</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NEQ_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.IntersectionImpl <em>Intersection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.IntersectionImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getIntersection()
	 * @generated
	 */
	int INTERSECTION = 48;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERSECTION__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERSECTION__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERSECTION__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERSECTION__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Intersection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERSECTION_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.UnionImpl <em>Union</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.UnionImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getUnion()
	 * @generated
	 */
	int UNION = 49;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION__CHILDREN = INFIX_OPERATOR__CHILDREN;

	/**
	 * The feature id for the '<em><b>Cell</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION__CELL = INFIX_OPERATOR__CELL;

	/**
	 * The feature id for the '<em><b>Token</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION__TOKEN = INFIX_OPERATOR__TOKEN;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION__RESULT = INFIX_OPERATOR__RESULT;

	/**
	 * The of of structural features of the '<em>Union</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_FEATURE_COUNT = INFIX_OPERATOR_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.CellFormatImpl <em>Cell Format</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellFormatImpl
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getCellFormat()
	 * @generated
	 */
	int CELL_FORMAT = 50;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL_FORMAT__ID = HAS_ID__ID;

	/**
	 * The feature id for the '<em><b>Book</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL_FORMAT__BOOK = HAS_ID_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL_FORMAT__VALUE = HAS_ID_FEATURE_COUNT + 1;

	/**
	 * The of of structural features of the '<em>Cell Format</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CELL_FORMAT_FEATURE_COUNT = HAS_ID_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>EString To Token Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EString To Token Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueType="org.eclipse.epsilon.labs.emf.cellsheet.Token"
	 * @generated
	 */
	EClass getEStringToTokenEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToTokenEntry()
	 * @generated
	 */
	EAttribute getEStringToTokenEntry_Key();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getEStringToTokenEntry()
	 * @generated
	 */
	EReference getEStringToTokenEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.HasA1 <em>Has A1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Has A1</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.HasA1
	 * @generated
	 */
	EClass getHasA1();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.HasA1#getA1 <em>A1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>A1</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.HasA1#getA1()
	 * @see #getHasA1()
	 * @generated
	 */
	EAttribute getHasA1_A1();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.HasId <em>Has Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Has Id</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.HasId
	 * @generated
	 */
	EClass getHasId();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.HasId#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.HasId#getId()
	 * @see #getHasId()
	 * @generated
	 */
	EAttribute getHasId_Id();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Workspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Workspace</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Workspace
	 * @generated
	 */
	EClass getWorkspace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epsilon.labs.emf.cellsheet.Workspace#getBooks <em>Books</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Books</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Workspace#getBooks()
	 * @see #getWorkspace()
	 * @generated
	 */
	EReference getWorkspace_Books();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.epsilon.labs.emf.cellsheet.Workspace#getTokens <em>Tokens</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Tokens</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Workspace#getTokens()
	 * @see #getWorkspace()
	 * @generated
	 */
	EReference getWorkspace_Tokens();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book <em>Book</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Book</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Book
	 * @generated
	 */
	EClass getBook();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Workspace</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Book#getWorkspace()
	 * @see #getBook()
	 * @generated
	 */
	EReference getBook_Workspace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getCellFormats <em>Cell Formats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cell Formats</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Book#getCellFormats()
	 * @see #getBook()
	 * @generated
	 */
	EReference getBook_CellFormats();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getSheets <em>Sheets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sheets</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Book#getSheets()
	 * @see #getBook()
	 * @generated
	 */
	EReference getBook_Sheets();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getBookname <em>Bookname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bookname</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Book#getBookname()
	 * @see #getBook()
	 * @generated
	 */
	EAttribute getBook_Bookname();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet <em>Sheet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sheet</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Sheet
	 * @generated
	 */
	EClass getSheet();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getBook <em>Book</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Book</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getBook()
	 * @see #getSheet()
	 * @generated
	 */
	EReference getSheet_Book();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rows</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getRows()
	 * @see #getSheet()
	 * @generated
	 */
	EReference getSheet_Rows();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getSheetName <em>Sheet Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sheet Name</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getSheetName()
	 * @see #getSheet()
	 * @generated
	 */
	EAttribute getSheet_SheetName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getSheetIndex <em>Sheet Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sheet Index</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getSheetIndex()
	 * @see #getSheet()
	 * @generated
	 */
	EAttribute getSheet_SheetIndex();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Row <em>Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Row</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Row
	 * @generated
	 */
	EClass getRow();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getSheet <em>Sheet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Sheet</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Row#getSheet()
	 * @see #getRow()
	 * @generated
	 */
	EReference getRow_Sheet();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getCells <em>Cells</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cells</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Row#getCells()
	 * @see #getRow()
	 * @generated
	 */
	EReference getRow_Cells();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getRowIndex <em>Row Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Row Index</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Row#getRowIndex()
	 * @see #getRow()
	 * @generated
	 */
	EAttribute getRow_RowIndex();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell <em>Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cell</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Cell
	 * @generated
	 */
	EClass getCell();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRow <em>Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Row</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRow()
	 * @see #getCell()
	 * @generated
	 */
	EReference getCell_Row();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getColIndex <em>Col Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Col Index</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Cell#getColIndex()
	 * @see #getCell()
	 * @generated
	 */
	EAttribute getCell_ColIndex();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getAsts <em>Asts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Asts</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Cell#getAsts()
	 * @see #getCell()
	 * @generated
	 */
	EReference getCell_Asts();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Root</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRoot()
	 * @see #getCell()
	 * @generated
	 */
	EReference getCell_Root();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.BlankCell <em>Blank Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Blank Cell</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.BlankCell
	 * @generated
	 */
	EClass getBlankCell();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.BlankCell#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.BlankCell#getValue()
	 * @see #getBlankCell()
	 * @generated
	 */
	EAttribute getBlankCell_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.TextCell <em>Text Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Cell</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.TextCell
	 * @generated
	 */
	EClass getTextCell();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.TextCell#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.TextCell#getValue()
	 * @see #getTextCell()
	 * @generated
	 */
	EAttribute getTextCell_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.NumericCell <em>Numeric Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Numeric Cell</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.NumericCell
	 * @generated
	 */
	EClass getNumericCell();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.NumericCell#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.NumericCell#getValue()
	 * @see #getNumericCell()
	 * @generated
	 */
	EAttribute getNumericCell_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.BooleanCell <em>Boolean Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Cell</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.BooleanCell
	 * @generated
	 */
	EClass getBooleanCell();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.BooleanCell#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.BooleanCell#getValue()
	 * @see #getBooleanCell()
	 * @generated
	 */
	EAttribute getBooleanCell_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.DateCell <em>Date Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Cell</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.DateCell
	 * @generated
	 */
	EClass getDateCell();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.DateCell#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.DateCell#getValue()
	 * @see #getDateCell()
	 * @generated
	 */
	EAttribute getDateCell_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.FormulaCell <em>Formula Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Formula Cell</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.FormulaCell
	 * @generated
	 */
	EClass getFormulaCell();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.FormulaCell#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.FormulaCell#getValue()
	 * @see #getFormulaCell()
	 * @generated
	 */
	EAttribute getFormulaCell_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Token <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Token</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Token
	 * @generated
	 */
	EClass getToken();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.Token#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Token#getValue()
	 * @see #getToken()
	 * @generated
	 */
	EAttribute getToken_Value();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.epsilon.labs.emf.cellsheet.Token#getUsedBy <em>Used By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Used By</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Token#getUsedBy()
	 * @see #getToken()
	 * @generated
	 */
	EReference getToken_UsedBy();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval <em>Ast Eval</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ast Eval</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.AstEval
	 * @generated
	 */
	EClass getAstEval();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getText()
	 * @see #getAstEval()
	 * @generated
	 */
	EAttribute getAstEval_Text();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getNumberValue <em>Number Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number Value</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getNumberValue()
	 * @see #getAstEval()
	 * @generated
	 */
	EAttribute getAstEval_NumberValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#isIsError <em>Is Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Error</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.AstEval#isIsError()
	 * @see #getAstEval()
	 * @generated
	 */
	EAttribute getAstEval_IsError();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast <em>Ast</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ast</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Ast
	 * @generated
	 */
	EClass getAst();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Ast#getChildren()
	 * @see #getAst()
	 * @generated
	 */
	EReference getAst_Children();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getCell <em>Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cell</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Ast#getCell()
	 * @see #getAst()
	 * @generated
	 */
	EReference getAst_Cell();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getToken <em>Token</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Token</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Ast#getToken()
	 * @see #getAst()
	 * @generated
	 */
	EReference getAst_Token();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Result</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Ast#getResult()
	 * @see #getAst()
	 * @generated
	 */
	EReference getAst_Result();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Operand <em>Operand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operand</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Operand
	 * @generated
	 */
	EClass getOperand();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.PrefixOperator <em>Prefix Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Prefix Operator</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.PrefixOperator
	 * @generated
	 */
	EClass getPrefixOperator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.InfixOperator <em>Infix Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Infix Operator</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.InfixOperator
	 * @generated
	 */
	EClass getInfixOperator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.PostfixOperator <em>Postfix Operator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Postfix Operator</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.PostfixOperator
	 * @generated
	 */
	EClass getPostfixOperator();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Unknown <em>Unknown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unknown</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Unknown
	 * @generated
	 */
	EClass getUnknown();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Noop <em>Noop</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Noop</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Noop
	 * @generated
	 */
	EClass getNoop();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Text <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Text
	 * @generated
	 */
	EClass getText();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Number <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Number</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Number
	 * @generated
	 */
	EClass getNumber();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Logical <em>Logical</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Logical</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Logical
	 * @generated
	 */
	EClass getLogical();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Error <em>Error</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Error</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Error
	 * @generated
	 */
	EClass getError();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ref <em>Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ref</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Ref
	 * @generated
	 */
	EClass getRef();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Range <em>Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Range</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Range
	 * @generated
	 */
	EClass getRange();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.RelativeRef <em>Relative Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relative Ref</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.RelativeRef
	 * @generated
	 */
	EClass getRelativeRef();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.RelativeRange <em>Relative Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relative Range</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.RelativeRange
	 * @generated
	 */
	EClass getRelativeRange();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Plus <em>Plus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Plus</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Plus
	 * @generated
	 */
	EClass getPlus();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Negation <em>Negation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Negation</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Negation
	 * @generated
	 */
	EClass getNegation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Percent <em>Percent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Percent</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Percent
	 * @generated
	 */
	EClass getPercent();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Exponentiation <em>Exponentiation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exponentiation</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Exponentiation
	 * @generated
	 */
	EClass getExponentiation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Multiplication <em>Multiplication</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Multiplication</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Multiplication
	 * @generated
	 */
	EClass getMultiplication();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Division <em>Division</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Division</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Division
	 * @generated
	 */
	EClass getDivision();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Addition <em>Addition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Addition</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Addition
	 * @generated
	 */
	EClass getAddition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Subtraction <em>Subtraction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subtraction</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Subtraction
	 * @generated
	 */
	EClass getSubtraction();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Concatenation <em>Concatenation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concatenation</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Concatenation
	 * @generated
	 */
	EClass getConcatenation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.EQ <em>EQ</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EQ</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.EQ
	 * @generated
	 */
	EClass getEQ();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.LT <em>LT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>LT</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.LT
	 * @generated
	 */
	EClass getLT();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.GT <em>GT</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GT</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.GT
	 * @generated
	 */
	EClass getGT();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.LTE <em>LTE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>LTE</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.LTE
	 * @generated
	 */
	EClass getLTE();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.GTE <em>GTE</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>GTE</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.GTE
	 * @generated
	 */
	EClass getGTE();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.NEQ <em>NEQ</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>NEQ</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.NEQ
	 * @generated
	 */
	EClass getNEQ();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Intersection <em>Intersection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Intersection</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Intersection
	 * @generated
	 */
	EClass getIntersection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.Union <em>Union</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Union
	 * @generated
	 */
	EClass getUnion();

	/**
	 * Returns the meta object for class '{@link org.eclipse.epsilon.labs.emf.cellsheet.CellFormat <em>Cell Format</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cell Format</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellFormat
	 * @generated
	 */
	EClass getCellFormat();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getBook <em>Book</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Book</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getBook()
	 * @see #getCellFormat()
	 * @generated
	 */
	EReference getCellFormat_Book();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getValue()
	 * @see #getCellFormat()
	 * @generated
	 */
	EAttribute getCellFormat_Value();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CellsheetFactory getCellsheetFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.EStringToTokenEntryImpl <em>EString To Token Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.EStringToTokenEntryImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getEStringToTokenEntry()
		 * @generated
		 */
		EClass ESTRING_TO_TOKEN_ENTRY = eINSTANCE.getEStringToTokenEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ESTRING_TO_TOKEN_ENTRY__KEY = eINSTANCE.getEStringToTokenEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ESTRING_TO_TOKEN_ENTRY__VALUE = eINSTANCE.getEStringToTokenEntry_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.HasA1Impl <em>Has A1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.HasA1Impl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getHasA1()
		 * @generated
		 */
		EClass HAS_A1 = eINSTANCE.getHasA1();

		/**
		 * The meta object literal for the '<em><b>A1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HAS_A1__A1 = eINSTANCE.getHasA1_A1();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.HasIdImpl <em>Has Id</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.HasIdImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getHasId()
		 * @generated
		 */
		EClass HAS_ID = eINSTANCE.getHasId();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HAS_ID__ID = eINSTANCE.getHasId_Id();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.WorkspaceImpl <em>Workspace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.WorkspaceImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getWorkspace()
		 * @generated
		 */
		EClass WORKSPACE = eINSTANCE.getWorkspace();

		/**
		 * The meta object literal for the '<em><b>Books</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE__BOOKS = eINSTANCE.getWorkspace_Books();

		/**
		 * The meta object literal for the '<em><b>Tokens</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORKSPACE__TOKENS = eINSTANCE.getWorkspace_Tokens();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BookImpl <em>Book</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.BookImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getBook()
		 * @generated
		 */
		EClass BOOK = eINSTANCE.getBook();

		/**
		 * The meta object literal for the '<em><b>Workspace</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOK__WORKSPACE = eINSTANCE.getBook_Workspace();

		/**
		 * The meta object literal for the '<em><b>Cell Formats</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOK__CELL_FORMATS = eINSTANCE.getBook_CellFormats();

		/**
		 * The meta object literal for the '<em><b>Sheets</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOK__SHEETS = eINSTANCE.getBook_Sheets();

		/**
		 * The meta object literal for the '<em><b>Bookname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOK__BOOKNAME = eINSTANCE.getBook_Bookname();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.SheetImpl <em>Sheet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.SheetImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getSheet()
		 * @generated
		 */
		EClass SHEET = eINSTANCE.getSheet();

		/**
		 * The meta object literal for the '<em><b>Book</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHEET__BOOK = eINSTANCE.getSheet_Book();

		/**
		 * The meta object literal for the '<em><b>Rows</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHEET__ROWS = eINSTANCE.getSheet_Rows();

		/**
		 * The meta object literal for the '<em><b>Sheet Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHEET__SHEET_NAME = eINSTANCE.getSheet_SheetName();

		/**
		 * The meta object literal for the '<em><b>Sheet Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHEET__SHEET_INDEX = eINSTANCE.getSheet_SheetIndex();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RowImpl <em>Row</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RowImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRow()
		 * @generated
		 */
		EClass ROW = eINSTANCE.getRow();

		/**
		 * The meta object literal for the '<em><b>Sheet</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROW__SHEET = eINSTANCE.getRow_Sheet();

		/**
		 * The meta object literal for the '<em><b>Cells</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROW__CELLS = eINSTANCE.getRow_Cells();

		/**
		 * The meta object literal for the '<em><b>Row Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROW__ROW_INDEX = eINSTANCE.getRow_RowIndex();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.CellImpl <em>Cell</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getCell()
		 * @generated
		 */
		EClass CELL = eINSTANCE.getCell();

		/**
		 * The meta object literal for the '<em><b>Row</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CELL__ROW = eINSTANCE.getCell_Row();

		/**
		 * The meta object literal for the '<em><b>Col Index</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CELL__COL_INDEX = eINSTANCE.getCell_ColIndex();

		/**
		 * The meta object literal for the '<em><b>Asts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CELL__ASTS = eINSTANCE.getCell_Asts();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CELL__ROOT = eINSTANCE.getCell_Root();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BlankCellImpl <em>Blank Cell</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.BlankCellImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getBlankCell()
		 * @generated
		 */
		EClass BLANK_CELL = eINSTANCE.getBlankCell();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BLANK_CELL__VALUE = eINSTANCE.getBlankCell_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.TextCellImpl <em>Text Cell</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.TextCellImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getTextCell()
		 * @generated
		 */
		EClass TEXT_CELL = eINSTANCE.getTextCell();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT_CELL__VALUE = eINSTANCE.getTextCell_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NumericCellImpl <em>Numeric Cell</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NumericCellImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNumericCell()
		 * @generated
		 */
		EClass NUMERIC_CELL = eINSTANCE.getNumericCell();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NUMERIC_CELL__VALUE = eINSTANCE.getNumericCell_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BooleanCellImpl <em>Boolean Cell</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.BooleanCellImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getBooleanCell()
		 * @generated
		 */
		EClass BOOLEAN_CELL = eINSTANCE.getBooleanCell();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_CELL__VALUE = eINSTANCE.getBooleanCell_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.DateCellImpl <em>Date Cell</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.DateCellImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getDateCell()
		 * @generated
		 */
		EClass DATE_CELL = eINSTANCE.getDateCell();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATE_CELL__VALUE = eINSTANCE.getDateCell_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.FormulaCellImpl <em>Formula Cell</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.FormulaCellImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getFormulaCell()
		 * @generated
		 */
		EClass FORMULA_CELL = eINSTANCE.getFormulaCell();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FORMULA_CELL__VALUE = eINSTANCE.getFormulaCell_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.TokenImpl <em>Token</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.TokenImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getToken()
		 * @generated
		 */
		EClass TOKEN = eINSTANCE.getToken();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TOKEN__VALUE = eINSTANCE.getToken_Value();

		/**
		 * The meta object literal for the '<em><b>Used By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TOKEN__USED_BY = eINSTANCE.getToken_UsedBy();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl <em>Ast Eval</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getAstEval()
		 * @generated
		 */
		EClass AST_EVAL = eINSTANCE.getAstEval();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AST_EVAL__TEXT = eINSTANCE.getAstEval_Text();

		/**
		 * The meta object literal for the '<em><b>Number Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AST_EVAL__NUMBER_VALUE = eINSTANCE.getAstEval_NumberValue();

		/**
		 * The meta object literal for the '<em><b>Is Error</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AST_EVAL__IS_ERROR = eINSTANCE.getAstEval_IsError();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstImpl <em>Ast</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.AstImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getAst()
		 * @generated
		 */
		EClass AST = eINSTANCE.getAst();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AST__CHILDREN = eINSTANCE.getAst_Children();

		/**
		 * The meta object literal for the '<em><b>Cell</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AST__CELL = eINSTANCE.getAst_Cell();

		/**
		 * The meta object literal for the '<em><b>Token</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AST__TOKEN = eINSTANCE.getAst_Token();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference AST__RESULT = eINSTANCE.getAst_Result();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.OperandImpl <em>Operand</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.OperandImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getOperand()
		 * @generated
		 */
		EClass OPERAND = eINSTANCE.getOperand();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.OperationImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.PrefixOperatorImpl <em>Prefix Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.PrefixOperatorImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getPrefixOperator()
		 * @generated
		 */
		EClass PREFIX_OPERATOR = eINSTANCE.getPrefixOperator();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.InfixOperatorImpl <em>Infix Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.InfixOperatorImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getInfixOperator()
		 * @generated
		 */
		EClass INFIX_OPERATOR = eINSTANCE.getInfixOperator();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.PostfixOperatorImpl <em>Postfix Operator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.PostfixOperatorImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getPostfixOperator()
		 * @generated
		 */
		EClass POSTFIX_OPERATOR = eINSTANCE.getPostfixOperator();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.UnknownImpl <em>Unknown</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.UnknownImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getUnknown()
		 * @generated
		 */
		EClass UNKNOWN = eINSTANCE.getUnknown();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NoopImpl <em>Noop</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NoopImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNoop()
		 * @generated
		 */
		EClass NOOP = eINSTANCE.getNoop();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.TextImpl <em>Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.TextImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getText()
		 * @generated
		 */
		EClass TEXT = eINSTANCE.getText();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NumberImpl <em>Number</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NumberImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNumber()
		 * @generated
		 */
		EClass NUMBER = eINSTANCE.getNumber();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.LogicalImpl <em>Logical</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.LogicalImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getLogical()
		 * @generated
		 */
		EClass LOGICAL = eINSTANCE.getLogical();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.ErrorImpl <em>Error</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.ErrorImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getError()
		 * @generated
		 */
		EClass ERROR = eINSTANCE.getError();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RefImpl <em>Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RefImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRef()
		 * @generated
		 */
		EClass REF = eINSTANCE.getRef();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RangeImpl <em>Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RangeImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRange()
		 * @generated
		 */
		EClass RANGE = eINSTANCE.getRange();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RelativeRefImpl <em>Relative Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RelativeRefImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRelativeRef()
		 * @generated
		 */
		EClass RELATIVE_REF = eINSTANCE.getRelativeRef();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.RelativeRangeImpl <em>Relative Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.RelativeRangeImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getRelativeRange()
		 * @generated
		 */
		EClass RELATIVE_RANGE = eINSTANCE.getRelativeRange();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.FunctionImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.PlusImpl <em>Plus</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.PlusImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getPlus()
		 * @generated
		 */
		EClass PLUS = eINSTANCE.getPlus();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NegationImpl <em>Negation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NegationImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNegation()
		 * @generated
		 */
		EClass NEGATION = eINSTANCE.getNegation();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.PercentImpl <em>Percent</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.PercentImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getPercent()
		 * @generated
		 */
		EClass PERCENT = eINSTANCE.getPercent();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.ExponentiationImpl <em>Exponentiation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.ExponentiationImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getExponentiation()
		 * @generated
		 */
		EClass EXPONENTIATION = eINSTANCE.getExponentiation();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.MultiplicationImpl <em>Multiplication</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.MultiplicationImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getMultiplication()
		 * @generated
		 */
		EClass MULTIPLICATION = eINSTANCE.getMultiplication();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.DivisionImpl <em>Division</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.DivisionImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getDivision()
		 * @generated
		 */
		EClass DIVISION = eINSTANCE.getDivision();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AdditionImpl <em>Addition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.AdditionImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getAddition()
		 * @generated
		 */
		EClass ADDITION = eINSTANCE.getAddition();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.SubtractionImpl <em>Subtraction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.SubtractionImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getSubtraction()
		 * @generated
		 */
		EClass SUBTRACTION = eINSTANCE.getSubtraction();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.ConcatenationImpl <em>Concatenation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.ConcatenationImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getConcatenation()
		 * @generated
		 */
		EClass CONCATENATION = eINSTANCE.getConcatenation();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.EQImpl <em>EQ</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.EQImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getEQ()
		 * @generated
		 */
		EClass EQ = eINSTANCE.getEQ();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.LTImpl <em>LT</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.LTImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getLT()
		 * @generated
		 */
		EClass LT = eINSTANCE.getLT();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.GTImpl <em>GT</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.GTImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getGT()
		 * @generated
		 */
		EClass GT = eINSTANCE.getGT();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.LTEImpl <em>LTE</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.LTEImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getLTE()
		 * @generated
		 */
		EClass LTE = eINSTANCE.getLTE();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.GTEImpl <em>GTE</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.GTEImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getGTE()
		 * @generated
		 */
		EClass GTE = eINSTANCE.getGTE();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.NEQImpl <em>NEQ</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.NEQImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getNEQ()
		 * @generated
		 */
		EClass NEQ = eINSTANCE.getNEQ();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.IntersectionImpl <em>Intersection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.IntersectionImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getIntersection()
		 * @generated
		 */
		EClass INTERSECTION = eINSTANCE.getIntersection();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.UnionImpl <em>Union</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.UnionImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getUnion()
		 * @generated
		 */
		EClass UNION = eINSTANCE.getUnion();

		/**
		 * The meta object literal for the '{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.CellFormatImpl <em>Cell Format</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellFormatImpl
		 * @see org.eclipse.epsilon.labs.emf.cellsheet.impl.CellsheetPackageImpl#getCellFormat()
		 * @generated
		 */
		EClass CELL_FORMAT = eINSTANCE.getCellFormat();

		/**
		 * The meta object literal for the '<em><b>Book</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CELL_FORMAT__BOOK = eINSTANCE.getCellFormat_Book();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CELL_FORMAT__VALUE = eINSTANCE.getCellFormat_Value();

	}

} //CellsheetPackage

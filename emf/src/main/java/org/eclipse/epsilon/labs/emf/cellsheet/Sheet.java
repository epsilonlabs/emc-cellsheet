/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sheet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getBook <em>Book</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getRows <em>Rows</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getSheetName <em>Sheet Name</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getSheetIndex <em>Sheet Index</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getSheet()
 * @model
 * @generated
 */
public interface Sheet extends HasId, HasA1 {
	/**
	 * Returns the value of the '<em><b>Book</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getSheets <em>Sheets</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Book</em>' container reference.
	 * @see #setBook(Book)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getSheet_Book()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Book#getSheets
	 * @model opposite="sheets" transient="false"
	 * @generated
	 */
	Book getBook();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getBook <em>Book</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Book</em>' container reference.
	 * @see #getBook()
	 * @generated
	 */
	void setBook(Book value);

	/**
	 * Returns the value of the '<em><b>Rows</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.labs.emf.cellsheet.Row}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getSheet <em>Sheet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rows</em>' containment reference list.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getSheet_Rows()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Row#getSheet
	 * @model opposite="sheet" containment="true"
	 * @generated
	 */
	EList<Row> getRows();

	/**
	 * Returns the value of the '<em><b>Sheet Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sheet Name</em>' attribute.
	 * @see #setSheetName(String)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getSheet_SheetName()
	 * @model
	 * @generated
	 */
	String getSheetName();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getSheetName <em>Sheet Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sheet Name</em>' attribute.
	 * @see #getSheetName()
	 * @generated
	 */
	void setSheetName(String value);

	/**
	 * Returns the value of the '<em><b>Sheet Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sheet Index</em>' attribute.
	 * @see #setSheetIndex(int)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getSheet_SheetIndex()
	 * @model
	 * @generated
	 */
	int getSheetIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getSheetIndex <em>Sheet Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sheet Index</em>' attribute.
	 * @see #getSheetIndex()
	 * @generated
	 */
	void setSheetIndex(int value);

} // Sheet

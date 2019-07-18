/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cell Format</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getBook <em>Book</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getCellFormat()
 * @model
 * @generated
 */
public interface CellFormat extends HasId {
	/**
	 * Returns the value of the '<em><b>Book</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getCellFormats <em>Cell Formats</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Book</em>' container reference.
	 * @see #setBook(Book)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getCellFormat_Book()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Book#getCellFormats
	 * @model opposite="cellFormats" transient="false"
	 * @generated
	 */
	Book getBook();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getBook <em>Book</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Book</em>' container reference.
	 * @see #getBook()
	 * @generated
	 */
	void setBook(Book value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getCellFormat_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // CellFormat

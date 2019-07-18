/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getWorkspace <em>Workspace</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getCellFormats <em>Cell Formats</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getTokens <em>Tokens</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getSheets <em>Sheets</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getBookname <em>Bookname</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getBook()
 * @model
 * @generated
 */
public interface Book extends HasId, HasA1 {
	/**
	 * Returns the value of the '<em><b>Workspace</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Workspace#getBooks <em>Books</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workspace</em>' container reference.
	 * @see #setWorkspace(Workspace)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getBook_Workspace()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Workspace#getBooks
	 * @model opposite="books" transient="false"
	 * @generated
	 */
	Workspace getWorkspace();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getWorkspace <em>Workspace</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workspace</em>' container reference.
	 * @see #getWorkspace()
	 * @generated
	 */
	void setWorkspace(Workspace value);

	/**
	 * Returns the value of the '<em><b>Cell Formats</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.labs.emf.cellsheet.CellFormat}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getBook <em>Book</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cell Formats</em>' containment reference list.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getBook_CellFormats()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellFormat#getBook
	 * @model opposite="book" containment="true"
	 * @generated
	 */
	EList<CellFormat> getCellFormats();

	/**
	 * Returns the value of the '<em><b>Tokens</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.epsilon.labs.emf.cellsheet.Token},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tokens</em>' map.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getBook_Tokens()
	 * @model mapType="org.eclipse.epsilon.labs.emf.cellsheet.EStringToTokenEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.epsilon.labs.emf.cellsheet.Token&gt;"
	 * @generated
	 */
	EMap<String, Token> getTokens();

	/**
	 * Returns the value of the '<em><b>Sheets</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getBook <em>Book</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sheets</em>' containment reference list.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getBook_Sheets()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getBook
	 * @model opposite="book" containment="true"
	 * @generated
	 */
	EList<Sheet> getSheets();

	/**
	 * Returns the value of the '<em><b>Bookname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bookname</em>' attribute.
	 * @see #setBookname(String)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getBook_Bookname()
	 * @model
	 * @generated
	 */
	String getBookname();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getBookname <em>Bookname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bookname</em>' attribute.
	 * @see #getBookname()
	 * @generated
	 */
	void setBookname(String value);

} // Book

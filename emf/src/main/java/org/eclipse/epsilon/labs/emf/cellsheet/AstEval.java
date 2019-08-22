/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast Eval</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getText <em>Text</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getNumberValue <em>Number Value</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#isIsError <em>Is Error</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAstEval()
 * @model
 * @generated
 */
public interface AstEval extends EObject {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAstEval_Text()
	 * @model
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

	/**
	 * Returns the value of the '<em><b>Number Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Value</em>' attribute.
	 * @see #setNumberValue(Double)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAstEval_NumberValue()
	 * @model
	 * @generated
	 */
	Double getNumberValue();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getNumberValue <em>Number Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Value</em>' attribute.
	 * @see #getNumberValue()
	 * @generated
	 */
	void setNumberValue(Double value);

	/**
	 * Returns the value of the '<em><b>Is Error</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Error</em>' attribute.
	 * @see #setIsError(boolean)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAstEval_IsError()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsError();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#isIsError <em>Is Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Error</em>' attribute.
	 * @see #isIsError()
	 * @generated
	 */
	void setIsError(boolean value);

} // AstEval

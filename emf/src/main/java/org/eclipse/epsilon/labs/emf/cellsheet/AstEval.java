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
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getStringValue <em>String Value</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getNumberValue <em>Number Value</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#isIsString <em>Is String</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#isIsNumber <em>Is Number</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#isIsError <em>Is Error</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAstEval()
 * @model
 * @generated
 */
public interface AstEval extends EObject {
	/**
	 * Returns the value of the '<em><b>String Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String Value</em>' attribute.
	 * @see #setStringValue(String)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAstEval_StringValue()
	 * @model
	 * @generated
	 */
	String getStringValue();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getStringValue <em>String Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String Value</em>' attribute.
	 * @see #getStringValue()
	 * @generated
	 */
	void setStringValue(String value);

	/**
	 * Returns the value of the '<em><b>Number Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Number Value</em>' attribute.
	 * @see #setNumberValue(double)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAstEval_NumberValue()
	 * @model
	 * @generated
	 */
	double getNumberValue();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#getNumberValue <em>Number Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Number Value</em>' attribute.
	 * @see #getNumberValue()
	 * @generated
	 */
	void setNumberValue(double value);

	/**
	 * Returns the value of the '<em><b>Is String</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is String</em>' attribute.
	 * @see #setIsString(boolean)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAstEval_IsString()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsString();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#isIsString <em>Is String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is String</em>' attribute.
	 * @see #isIsString()
	 * @generated
	 */
	void setIsString(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Number</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Number</em>' attribute.
	 * @see #setIsNumber(boolean)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAstEval_IsNumber()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsNumber();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.AstEval#isIsNumber <em>Is Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Number</em>' attribute.
	 * @see #isIsNumber()
	 * @generated
	 */
	void setIsNumber(boolean value);

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

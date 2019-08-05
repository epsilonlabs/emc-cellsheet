/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ast</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getCell <em>Cell</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getToken <em>Token</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getResult <em>Result</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAst()
 * @model abstract="true"
 * @generated
 */
public interface Ast extends EObject {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.labs.emf.cellsheet.Ast}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAst_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Ast> getChildren();

	/**
	 * Returns the value of the '<em><b>Cell</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getAsts <em>Asts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cell</em>' container reference.
	 * @see #setCell(Cell)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAst_Cell()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Cell#getAsts
	 * @model opposite="asts" transient="false"
	 * @generated
	 */
	Cell getCell();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getCell <em>Cell</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cell</em>' container reference.
	 * @see #getCell()
	 * @generated
	 */
	void setCell(Cell value);

	/**
	 * Returns the value of the '<em><b>Token</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Token#getUsedBy <em>Used By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Token</em>' reference.
	 * @see #setToken(Token)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAst_Token()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Token#getUsedBy
	 * @model opposite="usedBy"
	 * @generated
	 */
	Token getToken();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getToken <em>Token</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Token</em>' reference.
	 * @see #getToken()
	 * @generated
	 */
	void setToken(Token value);

	/**
	 * Returns the value of the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result</em>' containment reference.
	 * @see #setResult(AstEval)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getAst_Result()
	 * @model containment="true"
	 * @generated
	 */
	AstEval getResult();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getResult <em>Result</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result</em>' containment reference.
	 * @see #getResult()
	 * @generated
	 */
	void setResult(AstEval value);

} // Ast

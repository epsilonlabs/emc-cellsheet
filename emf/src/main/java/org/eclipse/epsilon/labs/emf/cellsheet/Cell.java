/*******************************************************************************
 * Copyright (c) 2019 The University of York.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 ******************************************************************************/

/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cell</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRow <em>Row</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getColIndex <em>Col Index</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getAsts <em>Asts</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getCell()
 * @model abstract="true"
 * @generated
 */
public interface Cell extends HasId, HasA1 {
	/**
	 * Returns the value of the '<em><b>Row</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getCells <em>Cells</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row</em>' container reference.
	 * @see #setRow(Row)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getCell_Row()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Row#getCells
	 * @model opposite="cells" transient="false"
	 * @generated
	 */
	Row getRow();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRow <em>Row</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row</em>' container reference.
	 * @see #getRow()
	 * @generated
	 */
	void setRow(Row value);

	/**
	 * Returns the value of the '<em><b>Col Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Col Index</em>' attribute.
	 * @see #setColIndex(int)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getCell_ColIndex()
	 * @model
	 * @generated
	 */
	int getColIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getColIndex <em>Col Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Col Index</em>' attribute.
	 * @see #getColIndex()
	 * @generated
	 */
	void setColIndex(int value);

	/**
	 * Returns the value of the '<em><b>Asts</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.labs.emf.cellsheet.Ast}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Ast#getCell <em>Cell</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Asts</em>' containment reference list.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getCell_Asts()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Ast#getCell
	 * @model opposite="cell" containment="true"
	 * @generated
	 */
	EList<Ast> getAsts();

	/**
	 * Returns the value of the '<em><b>Root</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' reference.
	 * @see #setRoot(Ast)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getCell_Root()
	 * @model
	 * @generated
	 */
	Ast getRoot();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRoot <em>Root</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(Ast value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Book getBook();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Sheet getSheet();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	int getRowIndex();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	int getA1RowIndex();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getA1ColIndex();

} // Cell

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
 * A representation of the model object '<em><b>Row</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getSheet <em>Sheet</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getCells <em>Cells</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getRowIndex <em>Row Index</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getRow()
 * @model
 * @generated
 */
public interface Row extends HasId, HasA1 {
	/**
	 * Returns the value of the '<em><b>Sheet</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sheet</em>' container reference.
	 * @see #setSheet(Sheet)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getRow_Sheet()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Sheet#getRows
	 * @model opposite="rows" transient="false"
	 * @generated
	 */
	Sheet getSheet();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getSheet <em>Sheet</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sheet</em>' container reference.
	 * @see #getSheet()
	 * @generated
	 */
	void setSheet(Sheet value);

	/**
	 * Returns the value of the '<em><b>Cells</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.labs.emf.cellsheet.Cell}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRow <em>Row</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cells</em>' containment reference list.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getRow_Cells()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Cell#getRow
	 * @model opposite="row" containment="true"
	 * @generated
	 */
	EList<Cell> getCells();

	/**
	 * Returns the value of the '<em><b>Row Index</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Row Index</em>' attribute.
	 * @see #setRowIndex(int)
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getRow_RowIndex()
	 * @model
	 * @generated
	 */
	int getRowIndex();

	/**
	 * Sets the value of the '{@link org.eclipse.epsilon.labs.emf.cellsheet.Row#getRowIndex <em>Row Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Row Index</em>' attribute.
	 * @see #getRowIndex()
	 * @generated
	 */
	void setRowIndex(int value);

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
	int getA1RowIndex();

} // Row

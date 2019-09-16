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
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Workspace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Workspace#getBooks <em>Books</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.Workspace#getTokens <em>Tokens</em>}</li>
 * </ul>
 *
 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getWorkspace()
 * @model
 * @generated
 */
public interface Workspace extends EObject {
	/**
	 * Returns the value of the '<em><b>Books</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.epsilon.labs.emf.cellsheet.Book}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.epsilon.labs.emf.cellsheet.Book#getWorkspace <em>Workspace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Books</em>' containment reference list.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getWorkspace_Books()
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.Book#getWorkspace
	 * @model opposite="workspace" containment="true"
	 * @generated
	 */
	EList<Book> getBooks();

	/**
	 * Returns the value of the '<em><b>Tokens</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.epsilon.labs.emf.cellsheet.Token},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tokens</em>' map.
	 * @see org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage#getWorkspace_Tokens()
	 * @model mapType="org.eclipse.epsilon.labs.emf.cellsheet.EStringToTokenEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.epsilon.labs.emf.cellsheet.Token&gt;"
	 * @generated
	 */
	EMap<String, Token> getTokens();

} // Workspace

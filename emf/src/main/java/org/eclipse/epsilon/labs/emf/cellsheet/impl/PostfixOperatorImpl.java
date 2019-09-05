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
package org.eclipse.epsilon.labs.emf.cellsheet.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage;
import org.eclipse.epsilon.labs.emf.cellsheet.PostfixOperator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Postfix Operator</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class PostfixOperatorImpl extends AstImpl implements PostfixOperator {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PostfixOperatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CellsheetPackage.Literals.POSTFIX_OPERATOR;
	}

} //PostfixOperatorImpl

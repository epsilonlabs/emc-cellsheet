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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epsilon.labs.emf.cellsheet.Book;
import org.eclipse.epsilon.labs.emf.cellsheet.CellFormat;
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage;
import org.eclipse.epsilon.labs.emf.cellsheet.HasA1;
import org.eclipse.epsilon.labs.emf.cellsheet.Sheet;
import org.eclipse.epsilon.labs.emf.cellsheet.Workspace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BookImpl#getA1 <em>A1</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BookImpl#getWorkspace <em>Workspace</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BookImpl#getCellFormats <em>Cell Formats</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BookImpl#getSheets <em>Sheets</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.BookImpl#getBookname <em>Bookname</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BookImpl extends HasIdImpl implements Book {
	/**
	 * The default value of the '{@link #getA1() <em>A1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA1()
	 * @generated
	 * @ordered
	 */
	protected static final String A1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getA1() <em>A1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getA1()
	 * @generated
	 * @ordered
	 */
	protected String a1 = A1_EDEFAULT;

	/**
	 * The cached value of the '{@link #getCellFormats() <em>Cell Formats</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCellFormats()
	 * @generated
	 * @ordered
	 */
	protected EList<CellFormat> cellFormats;

	/**
	 * The cached value of the '{@link #getSheets() <em>Sheets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSheets()
	 * @generated
	 * @ordered
	 */
	protected EList<Sheet> sheets;

	/**
	 * The default value of the '{@link #getBookname() <em>Bookname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBookname()
	 * @generated
	 * @ordered
	 */
	protected static final String BOOKNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBookname() <em>Bookname</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBookname()
	 * @generated
	 * @ordered
	 */
	protected String bookname = BOOKNAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BookImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CellsheetPackage.Literals.BOOK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getA1() {
		return a1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setA1(String newA1) {
		String oldA1 = a1;
		a1 = newA1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.BOOK__A1, oldA1, a1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Workspace getWorkspace() {
		if (eContainerFeatureID() != CellsheetPackage.BOOK__WORKSPACE) return null;
		return (Workspace)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkspace(Workspace newWorkspace, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWorkspace, CellsheetPackage.BOOK__WORKSPACE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWorkspace(Workspace newWorkspace) {
		if (newWorkspace != eInternalContainer() || (eContainerFeatureID() != CellsheetPackage.BOOK__WORKSPACE && newWorkspace != null)) {
			if (EcoreUtil.isAncestor(this, newWorkspace))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWorkspace != null)
				msgs = ((InternalEObject)newWorkspace).eInverseAdd(this, CellsheetPackage.WORKSPACE__BOOKS, Workspace.class, msgs);
			msgs = basicSetWorkspace(newWorkspace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.BOOK__WORKSPACE, newWorkspace, newWorkspace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<CellFormat> getCellFormats() {
		if (cellFormats == null) {
			cellFormats = new EObjectContainmentWithInverseEList<CellFormat>(CellFormat.class, this, CellsheetPackage.BOOK__CELL_FORMATS, CellsheetPackage.CELL_FORMAT__BOOK);
		}
		return cellFormats;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Sheet> getSheets() {
		if (sheets == null) {
			sheets = new EObjectContainmentWithInverseEList<Sheet>(Sheet.class, this, CellsheetPackage.BOOK__SHEETS, CellsheetPackage.SHEET__BOOK);
		}
		return sheets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBookname() {
		return bookname;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBookname(String newBookname) {
		String oldBookname = bookname;
		bookname = newBookname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.BOOK__BOOKNAME, oldBookname, bookname));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CellsheetPackage.BOOK__WORKSPACE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWorkspace((Workspace)otherEnd, msgs);
			case CellsheetPackage.BOOK__CELL_FORMATS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCellFormats()).basicAdd(otherEnd, msgs);
			case CellsheetPackage.BOOK__SHEETS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSheets()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CellsheetPackage.BOOK__WORKSPACE:
				return basicSetWorkspace(null, msgs);
			case CellsheetPackage.BOOK__CELL_FORMATS:
				return ((InternalEList<?>)getCellFormats()).basicRemove(otherEnd, msgs);
			case CellsheetPackage.BOOK__SHEETS:
				return ((InternalEList<?>)getSheets()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CellsheetPackage.BOOK__WORKSPACE:
				return eInternalContainer().eInverseRemove(this, CellsheetPackage.WORKSPACE__BOOKS, Workspace.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CellsheetPackage.BOOK__A1:
				return getA1();
			case CellsheetPackage.BOOK__WORKSPACE:
				return getWorkspace();
			case CellsheetPackage.BOOK__CELL_FORMATS:
				return getCellFormats();
			case CellsheetPackage.BOOK__SHEETS:
				return getSheets();
			case CellsheetPackage.BOOK__BOOKNAME:
				return getBookname();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CellsheetPackage.BOOK__A1:
				setA1((String)newValue);
				return;
			case CellsheetPackage.BOOK__WORKSPACE:
				setWorkspace((Workspace)newValue);
				return;
			case CellsheetPackage.BOOK__CELL_FORMATS:
				getCellFormats().clear();
				getCellFormats().addAll((Collection<? extends CellFormat>)newValue);
				return;
			case CellsheetPackage.BOOK__SHEETS:
				getSheets().clear();
				getSheets().addAll((Collection<? extends Sheet>)newValue);
				return;
			case CellsheetPackage.BOOK__BOOKNAME:
				setBookname((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CellsheetPackage.BOOK__A1:
				setA1(A1_EDEFAULT);
				return;
			case CellsheetPackage.BOOK__WORKSPACE:
				setWorkspace((Workspace)null);
				return;
			case CellsheetPackage.BOOK__CELL_FORMATS:
				getCellFormats().clear();
				return;
			case CellsheetPackage.BOOK__SHEETS:
				getSheets().clear();
				return;
			case CellsheetPackage.BOOK__BOOKNAME:
				setBookname(BOOKNAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CellsheetPackage.BOOK__A1:
				return A1_EDEFAULT == null ? a1 != null : !A1_EDEFAULT.equals(a1);
			case CellsheetPackage.BOOK__WORKSPACE:
				return getWorkspace() != null;
			case CellsheetPackage.BOOK__CELL_FORMATS:
				return cellFormats != null && !cellFormats.isEmpty();
			case CellsheetPackage.BOOK__SHEETS:
				return sheets != null && !sheets.isEmpty();
			case CellsheetPackage.BOOK__BOOKNAME:
				return BOOKNAME_EDEFAULT == null ? bookname != null : !BOOKNAME_EDEFAULT.equals(bookname);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == HasA1.class) {
			switch (derivedFeatureID) {
				case CellsheetPackage.BOOK__A1: return CellsheetPackage.HAS_A1__A1;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == HasA1.class) {
			switch (baseFeatureID) {
				case CellsheetPackage.HAS_A1__A1: return CellsheetPackage.BOOK__A1;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (a1: ");
		result.append(a1);
		result.append(", bookname: ");
		result.append(bookname);
		result.append(')');
		return result.toString();
	}

} //BookImpl

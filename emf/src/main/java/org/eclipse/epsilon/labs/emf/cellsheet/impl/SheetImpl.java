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
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage;
import org.eclipse.epsilon.labs.emf.cellsheet.HasA1;
import org.eclipse.epsilon.labs.emf.cellsheet.Row;
import org.eclipse.epsilon.labs.emf.cellsheet.Sheet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sheet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.SheetImpl#getA1 <em>A1</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.SheetImpl#getBook <em>Book</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.SheetImpl#getRows <em>Rows</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.SheetImpl#getSheetName <em>Sheet Name</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.SheetImpl#getSheetIndex <em>Sheet Index</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SheetImpl extends HasIdImpl implements Sheet {
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
	 * The cached value of the '{@link #getRows() <em>Rows</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRows()
	 * @generated
	 * @ordered
	 */
	protected EList<Row> rows;

	/**
	 * The default value of the '{@link #getSheetName() <em>Sheet Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSheetName()
	 * @generated
	 * @ordered
	 */
	protected static final String SHEET_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSheetName() <em>Sheet Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSheetName()
	 * @generated
	 * @ordered
	 */
	protected String sheetName = SHEET_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getSheetIndex() <em>Sheet Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSheetIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int SHEET_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getSheetIndex() <em>Sheet Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSheetIndex()
	 * @generated
	 * @ordered
	 */
	protected int sheetIndex = SHEET_INDEX_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SheetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CellsheetPackage.Literals.SHEET;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.SHEET__A1, oldA1, a1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Book getBook() {
		if (eContainerFeatureID() != CellsheetPackage.SHEET__BOOK) return null;
		return (Book)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBook(Book newBook, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newBook, CellsheetPackage.SHEET__BOOK, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBook(Book newBook) {
		if (newBook != eInternalContainer() || (eContainerFeatureID() != CellsheetPackage.SHEET__BOOK && newBook != null)) {
			if (EcoreUtil.isAncestor(this, newBook))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newBook != null)
				msgs = ((InternalEObject)newBook).eInverseAdd(this, CellsheetPackage.BOOK__SHEETS, Book.class, msgs);
			msgs = basicSetBook(newBook, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.SHEET__BOOK, newBook, newBook));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Row> getRows() {
		if (rows == null) {
			rows = new EObjectContainmentWithInverseEList<Row>(Row.class, this, CellsheetPackage.SHEET__ROWS, CellsheetPackage.ROW__SHEET);
		}
		return rows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSheetName(String newSheetName) {
		String oldSheetName = sheetName;
		sheetName = newSheetName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.SHEET__SHEET_NAME, oldSheetName, sheetName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getSheetIndex() {
		return sheetIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSheetIndex(int newSheetIndex) {
		int oldSheetIndex = sheetIndex;
		sheetIndex = newSheetIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.SHEET__SHEET_INDEX, oldSheetIndex, sheetIndex));
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
			case CellsheetPackage.SHEET__BOOK:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetBook((Book)otherEnd, msgs);
			case CellsheetPackage.SHEET__ROWS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRows()).basicAdd(otherEnd, msgs);
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
			case CellsheetPackage.SHEET__BOOK:
				return basicSetBook(null, msgs);
			case CellsheetPackage.SHEET__ROWS:
				return ((InternalEList<?>)getRows()).basicRemove(otherEnd, msgs);
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
			case CellsheetPackage.SHEET__BOOK:
				return eInternalContainer().eInverseRemove(this, CellsheetPackage.BOOK__SHEETS, Book.class, msgs);
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
			case CellsheetPackage.SHEET__A1:
				return getA1();
			case CellsheetPackage.SHEET__BOOK:
				return getBook();
			case CellsheetPackage.SHEET__ROWS:
				return getRows();
			case CellsheetPackage.SHEET__SHEET_NAME:
				return getSheetName();
			case CellsheetPackage.SHEET__SHEET_INDEX:
				return getSheetIndex();
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
			case CellsheetPackage.SHEET__A1:
				setA1((String)newValue);
				return;
			case CellsheetPackage.SHEET__BOOK:
				setBook((Book)newValue);
				return;
			case CellsheetPackage.SHEET__ROWS:
				getRows().clear();
				getRows().addAll((Collection<? extends Row>)newValue);
				return;
			case CellsheetPackage.SHEET__SHEET_NAME:
				setSheetName((String)newValue);
				return;
			case CellsheetPackage.SHEET__SHEET_INDEX:
				setSheetIndex((Integer)newValue);
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
			case CellsheetPackage.SHEET__A1:
				setA1(A1_EDEFAULT);
				return;
			case CellsheetPackage.SHEET__BOOK:
				setBook((Book)null);
				return;
			case CellsheetPackage.SHEET__ROWS:
				getRows().clear();
				return;
			case CellsheetPackage.SHEET__SHEET_NAME:
				setSheetName(SHEET_NAME_EDEFAULT);
				return;
			case CellsheetPackage.SHEET__SHEET_INDEX:
				setSheetIndex(SHEET_INDEX_EDEFAULT);
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
			case CellsheetPackage.SHEET__A1:
				return A1_EDEFAULT == null ? a1 != null : !A1_EDEFAULT.equals(a1);
			case CellsheetPackage.SHEET__BOOK:
				return getBook() != null;
			case CellsheetPackage.SHEET__ROWS:
				return rows != null && !rows.isEmpty();
			case CellsheetPackage.SHEET__SHEET_NAME:
				return SHEET_NAME_EDEFAULT == null ? sheetName != null : !SHEET_NAME_EDEFAULT.equals(sheetName);
			case CellsheetPackage.SHEET__SHEET_INDEX:
				return sheetIndex != SHEET_INDEX_EDEFAULT;
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
				case CellsheetPackage.SHEET__A1: return CellsheetPackage.HAS_A1__A1;
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
				case CellsheetPackage.HAS_A1__A1: return CellsheetPackage.SHEET__A1;
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
		result.append(", sheetName: ");
		result.append(sheetName);
		result.append(", sheetIndex: ");
		result.append(sheetIndex);
		result.append(')');
		return result.toString();
	}

} //SheetImpl

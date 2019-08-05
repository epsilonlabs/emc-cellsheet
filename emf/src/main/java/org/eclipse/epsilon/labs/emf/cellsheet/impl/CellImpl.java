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

import org.eclipse.epsilon.labs.emf.cellsheet.Ast;
import org.eclipse.epsilon.labs.emf.cellsheet.Book;
import org.eclipse.epsilon.labs.emf.cellsheet.Cell;
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage;
import org.eclipse.epsilon.labs.emf.cellsheet.HasA1;
import org.eclipse.epsilon.labs.emf.cellsheet.Row;
import org.eclipse.epsilon.labs.emf.cellsheet.Sheet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cell</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.CellImpl#getA1 <em>A1</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.CellImpl#getRow <em>Row</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.CellImpl#getColIndex <em>Col Index</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.CellImpl#getAsts <em>Asts</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.CellImpl#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CellImpl extends HasIdImpl implements Cell {
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
	 * The default value of the '{@link #getColIndex() <em>Col Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColIndex()
	 * @generated
	 * @ordered
	 */
	protected static final int COL_INDEX_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getColIndex() <em>Col Index</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColIndex()
	 * @generated
	 * @ordered
	 */
	protected int colIndex = COL_INDEX_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAsts() <em>Asts</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsts()
	 * @generated
	 * @ordered
	 */
	protected EList<Ast> asts;

	/**
	 * The cached value of the '{@link #getRoot() <em>Root</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoot()
	 * @generated
	 * @ordered
	 */
	protected Ast root;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CellImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CellsheetPackage.Literals.CELL;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.CELL__A1, oldA1, a1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Row getRow() {
		if (eContainerFeatureID() != CellsheetPackage.CELL__ROW) return null;
		return (Row)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRow(Row newRow, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRow, CellsheetPackage.CELL__ROW, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRow(Row newRow) {
		if (newRow != eInternalContainer() || (eContainerFeatureID() != CellsheetPackage.CELL__ROW && newRow != null)) {
			if (EcoreUtil.isAncestor(this, newRow))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRow != null)
				msgs = ((InternalEObject)newRow).eInverseAdd(this, CellsheetPackage.ROW__CELLS, Row.class, msgs);
			msgs = basicSetRow(newRow, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.CELL__ROW, newRow, newRow));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getColIndex() {
		return colIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColIndex(int newColIndex) {
		int oldColIndex = colIndex;
		colIndex = newColIndex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.CELL__COL_INDEX, oldColIndex, colIndex));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Ast> getAsts() {
		if (asts == null) {
			asts = new EObjectContainmentWithInverseEList<Ast>(Ast.class, this, CellsheetPackage.CELL__ASTS, CellsheetPackage.AST__CELL);
		}
		return asts;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Ast getRoot() {
		if (root != null && root.eIsProxy()) {
			InternalEObject oldRoot = (InternalEObject)root;
			root = (Ast)eResolveProxy(oldRoot);
			if (root != oldRoot) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CellsheetPackage.CELL__ROOT, oldRoot, root));
			}
		}
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ast basicGetRoot() {
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRoot(Ast newRoot) {
		Ast oldRoot = root;
		root = newRoot;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.CELL__ROOT, oldRoot, root));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Book getBook() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Sheet getSheet() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getRowIndex() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getA1RowIndex() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getA1ColIndex() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
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
			case CellsheetPackage.CELL__ROW:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRow((Row)otherEnd, msgs);
			case CellsheetPackage.CELL__ASTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAsts()).basicAdd(otherEnd, msgs);
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
			case CellsheetPackage.CELL__ROW:
				return basicSetRow(null, msgs);
			case CellsheetPackage.CELL__ASTS:
				return ((InternalEList<?>)getAsts()).basicRemove(otherEnd, msgs);
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
			case CellsheetPackage.CELL__ROW:
				return eInternalContainer().eInverseRemove(this, CellsheetPackage.ROW__CELLS, Row.class, msgs);
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
			case CellsheetPackage.CELL__A1:
				return getA1();
			case CellsheetPackage.CELL__ROW:
				return getRow();
			case CellsheetPackage.CELL__COL_INDEX:
				return getColIndex();
			case CellsheetPackage.CELL__ASTS:
				return getAsts();
			case CellsheetPackage.CELL__ROOT:
				if (resolve) return getRoot();
				return basicGetRoot();
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
			case CellsheetPackage.CELL__A1:
				setA1((String)newValue);
				return;
			case CellsheetPackage.CELL__ROW:
				setRow((Row)newValue);
				return;
			case CellsheetPackage.CELL__COL_INDEX:
				setColIndex((Integer)newValue);
				return;
			case CellsheetPackage.CELL__ASTS:
				getAsts().clear();
				getAsts().addAll((Collection<? extends Ast>)newValue);
				return;
			case CellsheetPackage.CELL__ROOT:
				setRoot((Ast)newValue);
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
			case CellsheetPackage.CELL__A1:
				setA1(A1_EDEFAULT);
				return;
			case CellsheetPackage.CELL__ROW:
				setRow((Row)null);
				return;
			case CellsheetPackage.CELL__COL_INDEX:
				setColIndex(COL_INDEX_EDEFAULT);
				return;
			case CellsheetPackage.CELL__ASTS:
				getAsts().clear();
				return;
			case CellsheetPackage.CELL__ROOT:
				setRoot((Ast)null);
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
			case CellsheetPackage.CELL__A1:
				return A1_EDEFAULT == null ? a1 != null : !A1_EDEFAULT.equals(a1);
			case CellsheetPackage.CELL__ROW:
				return getRow() != null;
			case CellsheetPackage.CELL__COL_INDEX:
				return colIndex != COL_INDEX_EDEFAULT;
			case CellsheetPackage.CELL__ASTS:
				return asts != null && !asts.isEmpty();
			case CellsheetPackage.CELL__ROOT:
				return root != null;
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
				case CellsheetPackage.CELL__A1: return CellsheetPackage.HAS_A1__A1;
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
				case CellsheetPackage.HAS_A1__A1: return CellsheetPackage.CELL__A1;
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
		result.append(", colIndex: ");
		result.append(colIndex);
		result.append(')');
		return result.toString();
	}

} //CellImpl

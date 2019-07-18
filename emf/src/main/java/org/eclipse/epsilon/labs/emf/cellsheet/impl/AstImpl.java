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
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.epsilon.labs.emf.cellsheet.Ast;
import org.eclipse.epsilon.labs.emf.cellsheet.Cell;
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage;
import org.eclipse.epsilon.labs.emf.cellsheet.Token;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstImpl#getCell <em>Cell</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstImpl#getToken <em>Token</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstImpl#getCachedResult <em>Cached Result</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AstImpl extends EObjectImpl implements Ast {
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<Ast> children;

	/**
	 * The cached value of the '{@link #getToken() <em>Token</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getToken()
	 * @generated
	 * @ordered
	 */
	protected Token token;

	/**
	 * The default value of the '{@link #getCachedResult() <em>Cached Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCachedResult()
	 * @generated
	 * @ordered
	 */
	protected static final String CACHED_RESULT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCachedResult() <em>Cached Result</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCachedResult()
	 * @generated
	 * @ordered
	 */
	protected String cachedResult = CACHED_RESULT_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AstImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CellsheetPackage.Literals.AST;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Token getToken() {
		if (token != null && token.eIsProxy()) {
			InternalEObject oldToken = (InternalEObject)token;
			token = (Token)eResolveProxy(oldToken);
			if (token != oldToken) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CellsheetPackage.AST__TOKEN, oldToken, token));
			}
		}
		return token;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Token basicGetToken() {
		return token;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetToken(Token newToken, NotificationChain msgs) {
		Token oldToken = token;
		token = newToken;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST__TOKEN, oldToken, newToken);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setToken(Token newToken) {
		if (newToken != token) {
			NotificationChain msgs = null;
			if (token != null)
				msgs = ((InternalEObject)token).eInverseRemove(this, CellsheetPackage.TOKEN__USED_BY, Token.class, msgs);
			if (newToken != null)
				msgs = ((InternalEObject)newToken).eInverseAdd(this, CellsheetPackage.TOKEN__USED_BY, Token.class, msgs);
			msgs = basicSetToken(newToken, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST__TOKEN, newToken, newToken));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Ast> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList<Ast>(Ast.class, this, CellsheetPackage.AST__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Cell getCell() {
		if (eContainerFeatureID() != CellsheetPackage.AST__CELL) return null;
		return (Cell)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCell(Cell newCell, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCell, CellsheetPackage.AST__CELL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCell(Cell newCell) {
		if (newCell != eInternalContainer() || (eContainerFeatureID() != CellsheetPackage.AST__CELL && newCell != null)) {
			if (EcoreUtil.isAncestor(this, newCell))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCell != null)
				msgs = ((InternalEObject)newCell).eInverseAdd(this, CellsheetPackage.CELL__ASTS, Cell.class, msgs);
			msgs = basicSetCell(newCell, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST__CELL, newCell, newCell));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCachedResult() {
		return cachedResult;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCachedResult(String newCachedResult) {
		String oldCachedResult = cachedResult;
		cachedResult = newCachedResult;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST__CACHED_RESULT, oldCachedResult, cachedResult));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public String evaluate() {
		return getCachedResult();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CellsheetPackage.AST__CELL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCell((Cell)otherEnd, msgs);
			case CellsheetPackage.AST__TOKEN:
				if (token != null)
					msgs = ((InternalEObject)token).eInverseRemove(this, CellsheetPackage.TOKEN__USED_BY, Token.class, msgs);
				return basicSetToken((Token)otherEnd, msgs);
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
			case CellsheetPackage.AST__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
			case CellsheetPackage.AST__CELL:
				return basicSetCell(null, msgs);
			case CellsheetPackage.AST__TOKEN:
				return basicSetToken(null, msgs);
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
			case CellsheetPackage.AST__CELL:
				return eInternalContainer().eInverseRemove(this, CellsheetPackage.CELL__ASTS, Cell.class, msgs);
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
			case CellsheetPackage.AST__CHILDREN:
				return getChildren();
			case CellsheetPackage.AST__CELL:
				return getCell();
			case CellsheetPackage.AST__TOKEN:
				if (resolve) return getToken();
				return basicGetToken();
			case CellsheetPackage.AST__CACHED_RESULT:
				return getCachedResult();
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
			case CellsheetPackage.AST__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends Ast>)newValue);
				return;
			case CellsheetPackage.AST__CELL:
				setCell((Cell)newValue);
				return;
			case CellsheetPackage.AST__TOKEN:
				setToken((Token)newValue);
				return;
			case CellsheetPackage.AST__CACHED_RESULT:
				setCachedResult((String)newValue);
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
			case CellsheetPackage.AST__CHILDREN:
				getChildren().clear();
				return;
			case CellsheetPackage.AST__CELL:
				setCell((Cell)null);
				return;
			case CellsheetPackage.AST__TOKEN:
				setToken((Token)null);
				return;
			case CellsheetPackage.AST__CACHED_RESULT:
				setCachedResult(CACHED_RESULT_EDEFAULT);
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
			case CellsheetPackage.AST__CHILDREN:
				return children != null && !children.isEmpty();
			case CellsheetPackage.AST__CELL:
				return getCell() != null;
			case CellsheetPackage.AST__TOKEN:
				return token != null;
			case CellsheetPackage.AST__CACHED_RESULT:
				return CACHED_RESULT_EDEFAULT == null ? cachedResult != null : !CACHED_RESULT_EDEFAULT.equals(cachedResult);
		}
		return super.eIsSet(featureID);
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
		result.append(" (cachedResult: ");
		result.append(cachedResult);
		result.append(')');
		return result.toString();
	}

} //AstImpl

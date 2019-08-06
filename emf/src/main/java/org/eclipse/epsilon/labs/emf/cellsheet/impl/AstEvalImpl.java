/**
 */
package org.eclipse.epsilon.labs.emf.cellsheet.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.epsilon.labs.emf.cellsheet.AstEval;
import org.eclipse.epsilon.labs.emf.cellsheet.CellsheetPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ast Eval</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl#getNumberValue <em>Number Value</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl#isIsError <em>Is Error</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AstEvalImpl extends EObjectImpl implements AstEval {
	/**
	 * The default value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getText() <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText()
	 * @generated
	 * @ordered
	 */
	protected String text = TEXT_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumberValue() <em>Number Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberValue()
	 * @generated
	 * @ordered
	 */
	protected static final Double NUMBER_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNumberValue() <em>Number Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberValue()
	 * @generated
	 * @ordered
	 */
	protected Double numberValue = NUMBER_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsError() <em>Is Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsError()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_ERROR_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsError() <em>Is Error</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsError()
	 * @generated
	 * @ordered
	 */
	protected boolean isError = IS_ERROR_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AstEvalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CellsheetPackage.Literals.AST_EVAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText() {
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setText(String newText) {
		String oldText = text;
		text = newText;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST_EVAL__TEXT, oldText, text));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getNumberValue() {
		return numberValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNumberValue(Double newNumberValue) {
		Double oldNumberValue = numberValue;
		numberValue = newNumberValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST_EVAL__NUMBER_VALUE, oldNumberValue, numberValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsError() {
		return isError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsError(boolean newIsError) {
		boolean oldIsError = isError;
		isError = newIsError;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST_EVAL__IS_ERROR, oldIsError, isError));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CellsheetPackage.AST_EVAL__TEXT:
				return getText();
			case CellsheetPackage.AST_EVAL__NUMBER_VALUE:
				return getNumberValue();
			case CellsheetPackage.AST_EVAL__IS_ERROR:
				return isIsError();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CellsheetPackage.AST_EVAL__TEXT:
				setText((String)newValue);
				return;
			case CellsheetPackage.AST_EVAL__NUMBER_VALUE:
				setNumberValue((Double)newValue);
				return;
			case CellsheetPackage.AST_EVAL__IS_ERROR:
				setIsError((Boolean)newValue);
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
			case CellsheetPackage.AST_EVAL__TEXT:
				setText(TEXT_EDEFAULT);
				return;
			case CellsheetPackage.AST_EVAL__NUMBER_VALUE:
				setNumberValue(NUMBER_VALUE_EDEFAULT);
				return;
			case CellsheetPackage.AST_EVAL__IS_ERROR:
				setIsError(IS_ERROR_EDEFAULT);
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
			case CellsheetPackage.AST_EVAL__TEXT:
				return TEXT_EDEFAULT == null ? text != null : !TEXT_EDEFAULT.equals(text);
			case CellsheetPackage.AST_EVAL__NUMBER_VALUE:
				return NUMBER_VALUE_EDEFAULT == null ? numberValue != null : !NUMBER_VALUE_EDEFAULT.equals(numberValue);
			case CellsheetPackage.AST_EVAL__IS_ERROR:
				return isError != IS_ERROR_EDEFAULT;
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
		result.append(" (text: ");
		result.append(text);
		result.append(", numberValue: ");
		result.append(numberValue);
		result.append(", isError: ");
		result.append(isError);
		result.append(')');
		return result.toString();
	}

} //AstEvalImpl

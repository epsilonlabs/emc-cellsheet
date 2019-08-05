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
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl#getStringValue <em>String Value</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl#getNumberValue <em>Number Value</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl#isIsString <em>Is String</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl#isIsNumber <em>Is Number</em>}</li>
 *   <li>{@link org.eclipse.epsilon.labs.emf.cellsheet.impl.AstEvalImpl#isIsError <em>Is Error</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AstEvalImpl extends EObjectImpl implements AstEval {
	/**
	 * The default value of the '{@link #getStringValue() <em>String Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringValue()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStringValue() <em>String Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStringValue()
	 * @generated
	 * @ordered
	 */
	protected String stringValue = STRING_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNumberValue() <em>Number Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberValue()
	 * @generated
	 * @ordered
	 */
	protected static final double NUMBER_VALUE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getNumberValue() <em>Number Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberValue()
	 * @generated
	 * @ordered
	 */
	protected double numberValue = NUMBER_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsString() <em>Is String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsString()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_STRING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsString() <em>Is String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsString()
	 * @generated
	 * @ordered
	 */
	protected boolean isString = IS_STRING_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsNumber() <em>Is Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNumber()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NUMBER_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNumber() <em>Is Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNumber()
	 * @generated
	 * @ordered
	 */
	protected boolean isNumber = IS_NUMBER_EDEFAULT;

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
	public String getStringValue() {
		return stringValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStringValue(String newStringValue) {
		String oldStringValue = stringValue;
		stringValue = newStringValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST_EVAL__STRING_VALUE, oldStringValue, stringValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getNumberValue() {
		return numberValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNumberValue(double newNumberValue) {
		double oldNumberValue = numberValue;
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
	public boolean isIsString() {
		return isString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsString(boolean newIsString) {
		boolean oldIsString = isString;
		isString = newIsString;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST_EVAL__IS_STRING, oldIsString, isString));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsNumber() {
		return isNumber;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsNumber(boolean newIsNumber) {
		boolean oldIsNumber = isNumber;
		isNumber = newIsNumber;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CellsheetPackage.AST_EVAL__IS_NUMBER, oldIsNumber, isNumber));
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
			case CellsheetPackage.AST_EVAL__STRING_VALUE:
				return getStringValue();
			case CellsheetPackage.AST_EVAL__NUMBER_VALUE:
				return getNumberValue();
			case CellsheetPackage.AST_EVAL__IS_STRING:
				return isIsString();
			case CellsheetPackage.AST_EVAL__IS_NUMBER:
				return isIsNumber();
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
			case CellsheetPackage.AST_EVAL__STRING_VALUE:
				setStringValue((String)newValue);
				return;
			case CellsheetPackage.AST_EVAL__NUMBER_VALUE:
				setNumberValue((Double)newValue);
				return;
			case CellsheetPackage.AST_EVAL__IS_STRING:
				setIsString((Boolean)newValue);
				return;
			case CellsheetPackage.AST_EVAL__IS_NUMBER:
				setIsNumber((Boolean)newValue);
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
			case CellsheetPackage.AST_EVAL__STRING_VALUE:
				setStringValue(STRING_VALUE_EDEFAULT);
				return;
			case CellsheetPackage.AST_EVAL__NUMBER_VALUE:
				setNumberValue(NUMBER_VALUE_EDEFAULT);
				return;
			case CellsheetPackage.AST_EVAL__IS_STRING:
				setIsString(IS_STRING_EDEFAULT);
				return;
			case CellsheetPackage.AST_EVAL__IS_NUMBER:
				setIsNumber(IS_NUMBER_EDEFAULT);
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
			case CellsheetPackage.AST_EVAL__STRING_VALUE:
				return STRING_VALUE_EDEFAULT == null ? stringValue != null : !STRING_VALUE_EDEFAULT.equals(stringValue);
			case CellsheetPackage.AST_EVAL__NUMBER_VALUE:
				return numberValue != NUMBER_VALUE_EDEFAULT;
			case CellsheetPackage.AST_EVAL__IS_STRING:
				return isString != IS_STRING_EDEFAULT;
			case CellsheetPackage.AST_EVAL__IS_NUMBER:
				return isNumber != IS_NUMBER_EDEFAULT;
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
		result.append(" (stringValue: ");
		result.append(stringValue);
		result.append(", numberValue: ");
		result.append(numberValue);
		result.append(", isString: ");
		result.append(isString);
		result.append(", isNumber: ");
		result.append(isNumber);
		result.append(", isError: ");
		result.append(isError);
		result.append(')');
		return result.toString();
	}

} //AstEvalImpl

/**
 * 
 */
package org.informationManager.dto;

/**
 * @author sony
 * 
 */
public class ErrorDTO {
	public static final String SEVERE = "severe";
	public static final String WARN = "warning";
	public static final String INFO = "info";

	private String errorSeverity = null;
	private String errorMessage = null;
	private String errorCode = null;
	private String bindKey = null;

	/**
	 * @return the errorSeverity
	 */
	public String getErrorSeverity() {
		return errorSeverity;
	}

	/**
	 * @param errorSeverity
	 *            the errorSeverity to set
	 */
	public void setErrorSeverity(String errorSeverity) {
		this.errorSeverity = errorSeverity;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the bindKey
	 */
	public String getBindKey() {
		return bindKey;
	}

	/**
	 * @param bindKey
	 *            the bindKey to set
	 */
	public void setBindKey(String bindKey) {
		this.bindKey = bindKey;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}

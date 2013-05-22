/**
 * 
 */
package org.informationManager.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * @author sony
 * 
 */
public abstract class BaseResponseDTO implements Serializable {

	private static final long serialVersionUID = -3851646664925854693L;

	private List<ErrorDTO> errorList = new ArrayList<ErrorDTO>();

	/**
	 * @return the errorList
	 */
	public List<ErrorDTO> getErrorList() {
		return errorList;
	}

	/**
	 * @param errorList
	 *            the errorList to set
	 */
	public void setErrorList(List<ErrorDTO> errorList) {
	}

	/**
	 * @param error
	 */
	public void addError(ErrorDTO error) {
		this.errorList.add(error);
	}

	public void clearErrors() {
		this.errorList.clear();
	}
	
}

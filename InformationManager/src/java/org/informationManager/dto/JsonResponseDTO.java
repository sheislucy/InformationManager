/**
 * 
 */
package org.informationManager.dto;

import org.informationManager.utils.JsonStatus;

/**
 * @author sony
 * 
 */
public class JsonResponseDTO extends BaseResponseDTO {
	private static final long serialVersionUID = 3544837376061482957L;
	private JsonStatus status = null;
	private int page = 0; // current page number
	private int total = 0;// total page count
	private int records = 0;// total record count

	/**
	 * @return the status
	 */
	public JsonStatus getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(JsonStatus status) {
		this.status = status;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}

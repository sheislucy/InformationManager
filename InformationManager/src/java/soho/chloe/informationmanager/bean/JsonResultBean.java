package soho.chloe.informationmanager.bean;

import java.io.Serializable;

import soho.chloe.informationmanager.utils.JsonStatus;

public class JsonResultBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private JsonStatus status;
	private String message;

	public JsonStatus getStatus() {
		return status;
	}

	public void setStatus(JsonStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

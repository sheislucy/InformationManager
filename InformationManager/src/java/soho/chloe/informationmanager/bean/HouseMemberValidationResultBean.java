package soho.chloe.informationmanager.bean;

import java.util.ArrayList;
import java.util.List;

public class HouseMemberValidationResultBean extends JsonResultBean {
	private static final long serialVersionUID = 1L;
	private List<String> errorList = new ArrayList<String>();

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

}

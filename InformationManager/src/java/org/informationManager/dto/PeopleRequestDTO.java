package org.informationManager.dto;

public class PeopleRequestDTO extends JsonRequestDTO {
	private static final long serialVersionUID = -5393258427572099568L;
	private String searchName = null;
	private String searchGender = null;
	private String searchAgeRule = null;
	private String searchAge = null;

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchGender() {
		return searchGender;
	}

	public void setSearchGender(String searchGender) {
		this.searchGender = searchGender;
	}

	public String getSearchAgeRule() {
		return searchAgeRule;
	}

	public void setSearchAgeRule(String searchAgeRule) {
		this.searchAgeRule = searchAgeRule;
	}

	public String getSearchAge() {
		return searchAge;
	}

	public void setSearchAge(String searchAge) {
		this.searchAge = searchAge;
	}

}

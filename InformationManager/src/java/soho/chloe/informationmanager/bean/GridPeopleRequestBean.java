package soho.chloe.informationmanager.bean;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GridPeopleRequestBean extends GridJsonRequestBean {
	private static final long serialVersionUID = 1L;
	private String name = null;
	private String gender = null;
	private String ageLow = null;
	private String ageUp = null;
	private String incomeLow = null;
	private String incomeUp = null;
	private List<Integer> political = new ArrayList<Integer>();
	private List<Integer> education = new ArrayList<Integer>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAgeLow() {
		return ageLow;
	}

	public void setAgeLow(String ageLow) {
		this.ageLow = ageLow;
	}

	public String getAgeUp() {
		return ageUp;
	}

	public void setAgeUp(String ageUp) {
		this.ageUp = ageUp;
	}

	public String getIncomeLow() {
		return incomeLow;
	}

	public void setIncomeLow(String incomeLow) {
		this.incomeLow = incomeLow;
	}

	public String getIncomeUp() {
		return incomeUp;
	}

	public void setIncomeUp(String incomeUp) {
		this.incomeUp = incomeUp;
	}

	public List<Integer> getPolitical() {
		return political;
	}

	public void setPolitical(List<Integer> political) {
		this.political = political;
	}

	public List<Integer> getEducation() {
		return education;
	}

	public void setEducation(List<Integer> education) {
		this.education = education;
	}

}

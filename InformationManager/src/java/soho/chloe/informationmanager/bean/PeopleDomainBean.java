package soho.chloe.informationmanager.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import soho.chloe.informationmanager.web.CustomDateSerializer;

public class PeopleDomainBean extends PeopleMiniDomainBean {
	private static final long serialVersionUID = 1L;

	private String ptype;// 常住/外出/暂住
	private String sname;
	private String education;
	private String addr;
	private String job;
	private String tel;
	private String phone;
	private String wplace;
	private String spec;
	private String incomeSource;
	private String political;
	private String relation;// 和户主关系
	private String village;
	private String ethnic;
	private String army;
	private String marriage;
	private String resident;// 户口性质：农/非农
	private String health;
	private Integer yearIncome;
	private String memo;
	private String position;// 职务
	private String social;// 社会身份
	private Boolean isLowSafe;
	private Boolean isaddsafe;
	private Boolean isCorps;
	private Boolean isOut;
	private Boolean isOverSea;
	private Double height;
	private String diffCond;
	private String companyName;
	private String currentAddress;
	private Date lastUpdateTime;

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWplace() {
		return wplace;
	}

	public void setWplace(String wplace) {
		this.wplace = wplace;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getIncomeSource() {
		return incomeSource;
	}

	public void setIncomeSource(String incomeSource) {
		this.incomeSource = incomeSource;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getArmy() {
		return army;
	}

	public void setArmy(String army) {
		this.army = army;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}

	public String getResident() {
		return resident;
	}

	public void setResident(String resident) {
		this.resident = resident;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

	public Integer getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(Integer yearIncome) {
		this.yearIncome = yearIncome;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getSocial() {
		return social;
	}

	public void setSocial(String social) {
		this.social = social;
	}

	public Boolean getIsLowSafe() {
		return isLowSafe;
	}

	public void setIsLowSafe(Boolean isLowSafe) {
		this.isLowSafe = isLowSafe;
	}

	public Boolean getIsaddsafe() {
		return isaddsafe;
	}

	public void setIsaddsafe(Boolean isaddsafe) {
		this.isaddsafe = isaddsafe;
	}

	public Boolean getIsCorps() {
		return isCorps;
	}

	public void setIsCorps(Boolean isCorps) {
		this.isCorps = isCorps;
	}

	public Boolean getIsOut() {
		return isOut;
	}

	public void setIsOut(Boolean isOut) {
		this.isOut = isOut;
	}

	public Boolean getIsOverSea() {
		return isOverSea;
	}

	public void setIsOverSea(Boolean isOverSea) {
		this.isOverSea = isOverSea;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getDiffCond() {
		return diffCond;
	}

	public void setDiffCond(String diffCond) {
		this.diffCond = diffCond;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

}

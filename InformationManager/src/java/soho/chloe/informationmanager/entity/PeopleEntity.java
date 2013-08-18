/**
 * 
 */
package soho.chloe.informationmanager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author lucy
 * 
 */
@Entity
@Table(name = "person")
public class PeopleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	private Integer ptype;// 常住/外出/暂住

	@Column(name = "cardid")
	private String cardId;
	private String name;
	private String sname;
	private Date birthday;
	private Integer gender;
	private Integer education;
	private String addr;
	private String job;
	private String tel;
	private String phone;
	private String wplace;
	private String spec;

	@Column(name = "houseid")
	private Integer houseId;

	@Column(name = "incomesource")
	private String incomeSource;
	private Integer political;

	@Column(name = "relationid")
	private Integer relationId;

	@Column(name = "villageid")
	private Integer villageId;
	private String ethnic;
	private String army;

	@Column(name = "marriageid")
	private Integer marriageId;

	@Column(name = "residentid")
	private Integer residentId;// 户口性质：农/非农
	private String health;

	@Column(name = "yearincome")
	private Integer yearIncome;
	private String memo;
	private Integer position;// 职务
	private Integer social;// 社会身份

	@Column(name = "islowsafe")
	private Boolean isLowSafe;

	@Column(name = "isAddSafe")
	private Boolean isaddsafe;

	@Column(name = "iscorps")
	private Boolean isCorps;

	@Column(name = "isout")
	private Boolean isOut;

	@Column(name = "isoversea")
	private Boolean isOverSea;
	private Double height;

	@Column(name = "diffCond")
	private String diffCond;

	@Column(name = "companyname")
	private String companyName;

	@Column(name = "currentaddress")
	private String currentAddress;

	@Column(name = "lastupdatetime")
	private Date lastUpdateTime;

	@ManyToOne
	@JoinColumn(name = "education", insertable = false, updatable = false)
	private CommonType educationType;

	@ManyToOne
	@JoinColumn(name = "marriageid", insertable = false, updatable = false)
	private CommonType marriageType;

	@ManyToOne
	@JoinColumn(name = "ptype", insertable = false, updatable = false)
	private CommonType mobilityType;

	@ManyToOne
	@JoinColumn(name = "residentid", insertable = false, updatable = false)
	private CommonType residentType;

	@ManyToOne
	@JoinColumn(name = "political", insertable = false, updatable = false)
	private PoliticalType politicalType;

	@ManyToOne
	@JoinColumn(name = "position", insertable = false, updatable = false)
	private PositionType positionType;

	@ManyToOne
	@JoinColumn(name = "relationid", insertable = false, updatable = false)
	private RelationType relationType;

	@ManyToOne
	@JoinColumn(name = "social", insertable = false, updatable = false)
	private SocialType socialType;

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getPtype() {
		return ptype;
	}

	public void setPtype(Integer ptype) {
		this.ptype = ptype;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getName() {
		return name.trim();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
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

	public Integer getPolitical() {
		return political;
	}

	public void setPolitical(Integer political) {
		this.political = political;
	}

	public Integer getRelationId() {
		return relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Integer getVillageId() {
		return villageId;
	}

	public void setVillageId(Integer villageId) {
		this.villageId = villageId;
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

	public Integer getMarriageId() {
		return marriageId;
	}

	public void setMarriageId(Integer marriageId) {
		this.marriageId = marriageId;
	}

	public Integer getResidentId() {
		return residentId;
	}

	public void setResidentId(Integer residentId) {
		this.residentId = residentId;
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

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Integer getSocial() {
		return social;
	}

	public void setSocial(Integer social) {
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

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public CommonType getEducationType() {
		return educationType;
	}

	public void setEducationType(CommonType educationType) {
		this.educationType = educationType;
	}

	public CommonType getMarriageType() {
		return marriageType;
	}

	public void setMarriageType(CommonType marriageType) {
		this.marriageType = marriageType;
	}

	public CommonType getMobilityType() {
		return mobilityType;
	}

	public void setMobilityType(CommonType mobilityType) {
		this.mobilityType = mobilityType;
	}

	public CommonType getResidentType() {
		return residentType;
	}

	public void setResidentType(CommonType residentType) {
		this.residentType = residentType;
	}

	public PoliticalType getPoliticalType() {
		return politicalType;
	}

	public void setPoliticalType(PoliticalType politicalType) {
		this.politicalType = politicalType;
	}

	public PositionType getPositionType() {
		return positionType;
	}

	public void setPositionType(PositionType positionType) {
		this.positionType = positionType;
	}

	public RelationType getRelationType() {
		return relationType;
	}

	public void setRelationType(RelationType relationType) {
		this.relationType = relationType;
	}

	public SocialType getSocialType() {
		return socialType;
	}

	public void setSocialType(SocialType socialType) {
		this.socialType = socialType;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

}

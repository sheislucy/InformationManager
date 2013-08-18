package soho.chloe.informationmanager.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author lucy
 * 
 */
@Entity
@Table(name = "house")
public class HouseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "usetype")
	private String useType;
	private String address;

	@Column(name = "buildstruct")
	private String buildStruct;

	@Column(name = "floorcount")
	private Integer floorCount;

	@Column(name = "islegal")
	private Boolean isLegal;

	@Column(name = "landclass")
	private String landClass;

	@Column(name = "landarea")
	private String landArea;

	@Column(name = "buildingarea")
	private String buildingArea;

	@Column(name = "buildingage")
	private Integer buildingAge;

	private String property;

	@Column(name = "propertyno")
	private String propertyNo;

	@Column(name = "landno")
	private String landNo;

	@Column(name = "approvalsno")
	private String approvalsNo;

	@Column(name = "parcelno")
	private String parcelNo;

	@Column(name = "faceto")
	private String faceTo;

	@Column(name = "haswall")
	private Boolean hasWall;

	@Column(name = "isdangerous")
	private Boolean isDangerous;

	@Column(name = "lastupdatetime")
	private Date lastUpdateTime;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "houseId")
	private Set<PeopleEntity> houseMembers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUseType() {
		return useType;
	}

	public void setUseType(String useType) {
		this.useType = useType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBuildStruct() {
		return buildStruct;
	}

	public void setBuildStruct(String buildStruct) {
		this.buildStruct = buildStruct;
	}

	public Integer getFloorCount() {
		return floorCount;
	}

	public void setFloorCount(Integer floorCount) {
		this.floorCount = floorCount;
	}

	public Boolean getIsLegal() {
		return isLegal;
	}

	public void setIsLegal(Boolean isLegal) {
		this.isLegal = isLegal;
	}

	public String getLandClass() {
		return landClass;
	}

	public void setLandClass(String landClass) {
		this.landClass = landClass;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}

	public String getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(String buildingArea) {
		this.buildingArea = buildingArea;
	}

	public Integer getBuildingAge() {
		return buildingAge;
	}

	public void setBuildingAge(Integer buildingAge) {
		this.buildingAge = buildingAge;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getPropertyNo() {
		return propertyNo;
	}

	public void setPropertyNo(String propertyNo) {
		this.propertyNo = propertyNo;
	}

	public String getLandNo() {
		return landNo;
	}

	public void setLandNo(String landNo) {
		this.landNo = landNo;
	}

	public String getApprovalsNo() {
		return approvalsNo;
	}

	public void setApprovalsNo(String approvalsNo) {
		this.approvalsNo = approvalsNo;
	}

	public String getParcelNo() {
		return parcelNo;
	}

	public void setParcelNo(String parcelNo) {
		this.parcelNo = parcelNo;
	}

	public String getFaceTo() {
		return faceTo;
	}

	public void setFaceTo(String faceTo) {
		this.faceTo = faceTo;
	}

	public Boolean getHasWall() {
		return hasWall;
	}

	public void setHasWall(Boolean hasWall) {
		this.hasWall = hasWall;
	}

	public Boolean getIsDangerous() {
		return isDangerous;
	}

	public void setIsDangerous(Boolean isDangerous) {
		this.isDangerous = isDangerous;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Set<PeopleEntity> getHouseMembers() {
		return houseMembers;
	}

	public void setHouseMembers(Set<PeopleEntity> houseMembers) {
		this.houseMembers = houseMembers;
	}

}

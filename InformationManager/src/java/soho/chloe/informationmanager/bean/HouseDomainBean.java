package soho.chloe.informationmanager.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import soho.chloe.informationmanager.web.CustomDateSerializer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseDomainBean extends HouseMiniDomainBean {
	private static final long serialVersionUID = 1L;
	private int pid; // host pid
	private String useType;
	private String address;
	private String buildStruct;
	private Integer floorCount;
	private Boolean isLegal;
	private String landClass;
	private String landArea;
	private String buildingArea;
	private Integer buildingAge;
	private String property;
	private String propertyNo;
	private String landNo;
	private String approvalsNo;
	private String parcelNo;
	private String faceTo;
	private Boolean hasWall;
	private Boolean isDangerous;
	private Date lastUpdateTime;

	private List<HousePictureDomainBean> pictures = new ArrayList<HousePictureDomainBean>();
	private List<PeopleDomainBean> members = new ArrayList<PeopleDomainBean>();

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
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

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public List<HousePictureDomainBean> getPictures() {
		return pictures;
	}

	public void setPictures(List<HousePictureDomainBean> pictures) {
		this.pictures = pictures;
	}

	public List<PeopleDomainBean> getMembers() {
		return members;
	}

	public void setMembers(List<PeopleDomainBean> members) {
		this.members = members;
	}

}

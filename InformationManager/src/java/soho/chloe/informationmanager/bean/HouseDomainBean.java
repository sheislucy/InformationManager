package soho.chloe.informationmanager.bean;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import soho.chloe.informationmanager.web.CustomDateSerializer;

public class HouseDomainBean extends HouseMiniDomainBean {
	private static final long serialVersionUID = 1L;
	private int pid; // host pid
	private String useType;
	private String address;
	private String buildStruct;
	private String floorCount;
	private Boolean isLegal;
	private String landClass;
	private String landArea;
	private Double buildingArea;
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

	public String getFloorCount() {
		return floorCount;
	}

	public void setFloorCount(String floorCount) {
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

	public Double getBuildingArea() {
		return buildingArea;
	}

	public void setBuildingArea(Double buildingArea) {
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

}

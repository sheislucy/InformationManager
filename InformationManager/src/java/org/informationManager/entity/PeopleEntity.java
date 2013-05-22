/**
 * 
 */
package org.informationManager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.informationManager.web.CustomDateSerializer;

/**
 * @author sony
 * 
 */
@Entity
@Table(name = "person")
public class PeopleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int pid;
	private int psortId;
	private String idcard;
	private String lname;
	private String nname;
	private boolean gender;
	private String education;
	private String address;
	private Date comeTime;
	private String job;
	private String cellphone;
	private String phone;
	private String workPlace;
	private String physicalCharact;
	private String incomeSource;
	private String psocialId;
	private String relation;
	private String skill;
	private String photo;
	private String countryId;
	private String village;
	private Date createDate;
	private Date updateDate;

	private int hostId;
	private boolean isShow;

	@ManyToOne
	@JoinColumn(name = "hostId", insertable = false, updatable = false)
	private PeopleEntity host;

	@ManyToOne
	@JoinColumn(name = "psortId", insertable = false, updatable = false)
	private PersonSortEntity sort;

	@ManyToOne
	@JoinColumn(name = "psocialId", insertable = false, updatable = false)
	private SocialIdentityEntity socialIdentity;

	@Transient
	public String getSortText() {
		return this.getSort().getSortText();
	}

	@Transient
	public String getSocialText() {
		return this.getSocialIdentity().getSocialText();
	}

	@Transient
	public String getHostName() {
		return this.getHost().getLname();
	}

	public PersonSortEntity getSort() {
		return sort;
	}

	public void setSort(PersonSortEntity sort) {
		this.sort = sort;
	}

	public SocialIdentityEntity getSocialIdentity() {
		return socialIdentity;
	}

	public void setSocialIdentity(SocialIdentityEntity socialIdentity) {
		this.socialIdentity = socialIdentity;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPsortId() {
		return psortId;
	}

	public void setPsortId(int psortId) {
		this.psortId = psortId;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getNname() {
		return nname;
	}

	public void setNname(String nname) {
		this.nname = nname;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getComeTime() {
		return comeTime;
	}

	public void setComeTime(Date comeTime) {
		this.comeTime = comeTime;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getPhysicalCharact() {
		return physicalCharact;
	}

	public void setPhysicalCharact(String physicalCharact) {
		this.physicalCharact = physicalCharact;
	}

	public String getIncomeSource() {
		return incomeSource;
	}

	public void setIncomeSource(String incomeSource) {
		this.incomeSource = incomeSource;
	}

	public String getPsocialId() {
		return psocialId;
	}

	public void setPsocialId(String psocialId) {
		this.psocialId = psocialId;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getHostId() {
		return hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public PeopleEntity getHost() {
		return host;
	}

	public void setHost(PeopleEntity host) {
		this.host = host;
	}

}

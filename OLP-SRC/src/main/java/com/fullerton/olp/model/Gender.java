package com.fullerton.olp.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the gender database table.
 * 
 */
@Entity
@NamedQuery(name="Gender.findAll", query="SELECT g FROM Gender g")
public class Gender extends BaseModel<Long, Gender>  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private Boolean active;

	private String name;

	//bi-directional many-to-one association to PersonalDetail
	@OneToMany(mappedBy="gender")
	private List<PersonalDetail> personalDetails;

	public Gender() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return this.createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return this.modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 

//	public UserDetail addUserDetail(UserDetail userDetail) {
//		getUserDetails().add(userDetail);
//		userDetail.setGender(this);
//
//		return userDetail;
//	}
//
//	public UserDetail removeUserDetail(UserDetail userDetail) {
//		getUserDetails().remove(userDetail);
//		userDetail.setGender(null);
//
//		return userDetail;
//	}

	public List<PersonalDetail> getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(List<PersonalDetail> personalDetails) {
		this.personalDetails = personalDetails;
	}

	@Override
	public Gender copy() {
		Gender gender = new Gender();
		copyTo(gender);
		return gender;
	}

	@Override
	public void copyTo(Gender gender) {
		gender.setId(getId());
		gender.setName(getName());
		gender.setActive(getActive());
	}

}
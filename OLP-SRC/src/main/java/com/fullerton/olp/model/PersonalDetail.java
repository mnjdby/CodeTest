package com.fullerton.olp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fullerton.olp.model.base.BaseModel;

/**
 * The persistent class for the user_detail database table.
 * 
 */
@Entity
@Table(name="personal_detail")
public class PersonalDetail extends BaseModel<Long,PersonalDetail>  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name="dob")
	private Date dob;
	
	@Column(name="upi_id")
	private String upiId;

	//bi-directional many-to-one association to MarritalStatus
	@ManyToOne
	@JoinColumn(name="marrital_status_id")
	private MarritalStatus marritalStatus;

	 
	//bi-directional many-to-one association to ResidenceAddress
	@ManyToOne
	@JoinColumn(name="residence_address_id")
	private ResidenceAddress residenceAddress;
	
	@ManyToOne
	@JoinColumn(name="gender_id")
	private Gender gender;

	public PersonalDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	 
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getUpiId() {
		return upiId;
	}

	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
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

	 
	public MarritalStatus getMarritalStatus() {
		return this.marritalStatus;
	}

	public void setMarritalStatus(MarritalStatus marritalStatus) {
		this.marritalStatus = marritalStatus;
	}

	public ResidenceAddress getResidenceAddress() {
		return this.residenceAddress;
	}

	public void setResidenceAddress(ResidenceAddress residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	@Override
	public PersonalDetail copy() {
		PersonalDetail userDetail = new PersonalDetail();
		copyTo(userDetail);
		return userDetail;
	}

	@Override
	public void copyTo(PersonalDetail userDetail) {
		userDetail.setId(getId());
		userDetail.setDob(getDob());
		userDetail.setUpiId(getUpiId());
		userDetail.setResidenceAddress(getResidenceAddress() != null?getResidenceAddress().copy():null);
		userDetail.setMarritalStatus(getMarritalStatus() != null?getMarritalStatus().copy():null);
		userDetail.setGender(getGender() != null?getGender().copy():null);
	}
}